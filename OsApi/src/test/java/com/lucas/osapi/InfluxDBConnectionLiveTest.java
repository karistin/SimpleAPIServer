package com.lucas.osapi;

import com.lucas.osapi.Entity.Cpu;
import com.lucas.osapi.entity.Cpuinfo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.InfluxDBIOException;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * packageName    : com.lucas.osapi
 * fileName       : InfluxDBConnectionLiveTest
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */

@Slf4j
public class InfluxDBConnectionLiveTest {

    @Test
    public void whenCorrectInfoDatabaseConnects(){
        InfluxDB connection = connectDatabase();
        assertTrue(pingServer(connection));
    }

    private InfluxDB connectDatabase() {
        return InfluxDBFactory.connect("http://127.0.0.1:8086", "admin", "admin");
    }

    private boolean pingServer(InfluxDB connection) {
        try {
            Pong response = connection.ping();
            if (response.getVersion().equalsIgnoreCase("unknown")) {
                log.error("Error pinging server");
                return false;
            } else {
                log.info("Database version : {}", response.getVersion());
                return true;
            }
        } catch (InfluxDBIOException e) {
            log.error("Exception while pinging database : ", e);
            return false;
        }
    }

    @Test
    public void whenDatabaseCreatedDatabaseCheckOK(){
        InfluxDB connection = connectDatabase();
        String databaseName = "test";

//        create "test" and check it
        connection.query(new Query("create database " + databaseName));
        assertTrue(connection.databaseExists(databaseName));

//        verify databases
        assertFalse(connection.databaseExists("foobar"));

//        drop checking
        connection.deleteDatabase(databaseName);
        assertFalse(connection.databaseExists(databaseName));
    }

    @Test
    public void influxDBwriteChecking() throws InterruptedException {
        InfluxDB connection = connectDatabase();
        String databaseName = "CpuPoint";
        String retentionPolicyName = "defaultPolicy";

        connection.query(new Query("create database " + databaseName));
        connection.setDatabase(databaseName);
        /*
        * About influx db policy
        * https://docs.influxdata.com/influxdb/v1.7/query_language/database_management/#retention-policy-management
        * */
        connection.query(new Query("CREATE RETENTION POLICY " + retentionPolicyName
                    +" ON "+databaseName + " DURATION 1d REPLICATION 1 DEFAULT"));
        connection.setRetentionPolicy(retentionPolicyName);

        connection.enableBatch(
                BatchOptions.DEFAULTS
                        .threadFactory(runnable ->{
                            Thread thread = new Thread(runnable);
                            thread.setDaemon(true);
                            return thread;
                        })
        );

        Runtime.getRuntime().addShutdownHook(new Thread(connection::close));

        connection.write(Point.measurement("h2o_feet")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("location", "santa_monica")
                .addField("level description", "below 3 feet")
                .addField("water_level", 2.064d)
                .build());

        connection.write(Point.measurement("h2o_feet")
                .tag("location", "coyote_creek")
                .addField("level description", "between 6 and 9 feet")
                .addField("water_level", 8.12d)
                .build());

        Thread.sleep(5000L);

        QueryResult queryResult = connection.query(new Query("SELECT * FROM h2o_feet")); // (7)


        List<QueryResult.Result> result =queryResult.getResults();
        assertFalse(result.isEmpty());
//        assertEquals(result.size(), 2);

        connection.deleteDatabase(databaseName);

        assertFalse(connection.databaseExists(databaseName));
    }

    @Test
    public void influxDBReadPOJO(){
        InfluxDB connection = connectDatabase();

        String dbName = "OsData";
        QueryResult queryResult = connection.query(new Query("select * from CpuInfo where uid = 'serverA' order by time desc limit 5", dbName));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Cpuinfo> cpuList = resultMapper.toPOJO(queryResult, Cpuinfo.class);
        for (Cpuinfo cpuinfo : cpuList) {
            log.info(String.valueOf(cpuinfo.getCpuUsage()));
        }
    }

    @Test
    public void influxDBwritePOJO()  {
        InfluxDB connection = connectDatabase();

        String dbName = "myTimeseries";
        QueryResult queryResult = connection.query(new Query("SELECT * FROM cpu", dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper(); // thread-safe - can be reused
        List<Cpu> cpuList = resultMapper.toPOJO(queryResult, Cpu.class);
        Cpu cpu = new Cpu();
        cpu.setHappydevop(true);
        cpu.setIdle(1.99);
        cpu.setHostname("jennifer");
//        cpu.setTime(Instant.ofEpochSecond(System.currentTimeMillis()));
        cpu.setRegion("kor");
        cpu.setUptimeSecs(System.currentTimeMillis());

//        log.info(cpuList.toString());

        Point point = Point.measurementByPOJO(Cpu.class).addFieldsFromPOJO(cpu).build();

        InfluxDBMapper influxDBMapper = new InfluxDBMapper(connection);
        influxDBMapper.save(cpu);

        cpuList = resultMapper.toPOJO(queryResult, Cpu.class);
        for (Cpu cpu1 : cpuList) {
            log.info(cpu1.getHostname());
        }
    }
}
