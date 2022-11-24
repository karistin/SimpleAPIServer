import entity.CpuInfo;
import entity.DiskInfo;
import entity.MemInfo;
import entity.ServerInfo;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * packageName    : com.lucas.influxdb
 * fileName       : insertMain
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */

public class insertMain {
    static List<String> hostnameList = new ArrayList<>(
            Arrays.asList("serverA","serverB","serverC", "serverD", "serverE", "serverF", "serverG","serverH")
    );

    public static void main(String[] args) throws InterruptedException {
        final String serverURL = "http://127.0.0.1:8086", username = "root", password = "root";
        String databases = "OsData";
        InfluxDB influxDB = InfluxDBFactory.connect(serverURL);
        influxDB.setDatabase(databases);


        Runtime.getRuntime().addShutdownHook(new Thread(influxDB::close));
        influxDB.enableBatch(
                BatchOptions.DEFAULTS
                        .threadFactory(runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setDaemon(true);
                            return thread;
                        })
        );



        Random rand = new Random();
        long seed = System.currentTimeMillis();
        rand.setSeed(seed);


        while(true) {

            List<CpuInfo> cpuinfoList = new ArrayList<>();

            for (String hostname : hostnameList) {
                CpuInfo cpuinfo = new CpuInfo();
                cpuinfo.setUid(hostname);
                cpuinfo.setHostname(hostname);
                cpuinfo.setCpuUsage(Math.floor(rand.nextDouble()*10000)/100);

                MemInfo memInfo = new MemInfo();
                memInfo.setUid(hostname);
                memInfo.setHostname(hostname);
                memInfo.setMemUsage(Math.floor(rand.nextDouble()*10000)/100);
                memInfo.setMemUsageByteAll("1231232132");
                memInfo.setMemUsageByteFree("123123232");

                DiskInfo diskInfo = new DiskInfo();
                diskInfo.setUid(hostname);
                diskInfo.setHostname(hostname);
                diskInfo.setDiskUsage(Math.floor(rand.nextDouble()*10000)/100);
                diskInfo.setDiskinfo("/");

                ServerInfo serverInfo = new ServerInfo();
                serverInfo.setUid(hostname);
                serverInfo.setOsType("linux");
                serverInfo.setCore(4);
                serverInfo.setCpuUsage(cpuinfo.getCpuUsage());
                serverInfo.setMenUsage(memInfo.getMemUsage());
                serverInfo.setDiskUsage(diskInfo.getDiskUsage());
                serverInfo.setNetworkTraffic("null");
                serverInfo.setIp("192.62.123.23");



                influxDB.write(Point.measurementByPOJO(CpuInfo.class).addFieldsFromPOJO(cpuinfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(MemInfo.class).addFieldsFromPOJO(memInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(DiskInfo.class).addFieldsFromPOJO(diskInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(ServerInfo.class).addFieldsFromPOJO(serverInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());

            }
            sleep(50E00);
        }

    }
}
