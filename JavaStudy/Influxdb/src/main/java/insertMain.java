import entity.CpuInfo;
import entity.DiskInfo;
import entity.MemInfo;
import entity.NetworkInfo;
import entity.ProcessInfo;
import entity.ServerInfo;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

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
    private String GiGa = "GiB";


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




        while(true) {
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);
            List<CpuInfo> cpuinfoList = new ArrayList<>();

            for (String hostname : hostnameList) {
                CpuInfo cpuinfo = new CpuInfo();
                cpuinfo.setUid(hostname);
                cpuinfo.setHostname(hostname);
                double usage = (20+(20) * rand.nextDouble());
                cpuinfo.setCpuUsage(usage);
                cpuinfo.setSysUsage(usage/2);
                cpuinfo.setUserUsage(usage/2);
                cpuinfo.setIdleUsage(100 - usage);

                cpuinfo.setNiceUsage(0);
                cpuinfo.setIrqUsage(10*rand.nextDouble());
                cpuinfo.setSoftIrqUsage(10*rand.nextDouble());
                cpuinfo.setStealUage(10*rand.nextDouble());
                cpuinfo.setIrqUsage(10*rand.nextDouble());
                cpuinfo.setWaitIoUsage(10*rand.nextDouble());

//                only linux
                cpuinfo.setCpuLoad1((2) * rand.nextDouble());
                cpuinfo.setCpuLoad5((2) * rand.nextDouble());
                cpuinfo.setCpuLoad15((2) * rand.nextDouble());


                MemInfo memInfo = new MemInfo();
                memInfo.setUid(hostname);
                memInfo.setHostname(hostname);
                usage = (5+(20) * rand.nextDouble());
                memInfo.setMemUsage(usage);
//                Gib = KB * 1e6
//                gib = byte * 1e9
                double mem = 7.894967 * 1E9;
                memInfo.setMemUsageByteAll(mem);
                memInfo.setMemUsageByteFree(mem*0.5);
                memInfo.setMemUsageByteCached(mem*0.3);
                memInfo.setMemUsageByteBuffers(mem*0.1);
                memInfo.setMemUsageByteUsed(mem*0.2);
                memInfo.setMemAvilable(memInfo.getMemUsageByteFree());

                //MiB
                memInfo.setMemSReclaimable( ((36+(60) * rand.nextDouble())) *1e6);
                memInfo.setMemSUnreclaim((45+(75) * rand.nextDouble()) * 1e6);
                memInfo.setMemSlab( (80+(120) * rand.nextDouble()) * 1e6);

                memInfo.setMemSwapUsed(rand.nextDouble());
                memInfo.setMemSwapUsedByte(rand.nextDouble());
                memInfo.setMemPageFault(((42) * rand.nextDouble()) * 1e3);



                DiskInfo diskInfo = new DiskInfo();

                diskInfo.setUid(hostname);
                diskInfo.setHostname(hostname);

//                TODO : KIB
                diskInfo.setDiskDeviceId("/dev/sdd");
                diskInfo.setDiskFileSystem("ext4");
                diskInfo.setDiskMountPoint("/");
                diskInfo.setDiskMountOptions("rw");
                diskInfo.setDiskBlockSize("4KiB");

                diskInfo.setDiskUsage(10 + 50*rand.nextDouble());
                diskInfo.setDiskIOPSRead(0.2*rand.nextDouble());
                diskInfo.setDiskIOPSWrite(2*rand.nextDouble());
                diskInfo.setDiskBpsRead(20 * rand.nextDouble()+"KiB");
                diskInfo.setDiskBpsWrite(20 * rand.nextDouble() +"KiB");
                diskInfo.setDiskUsedSpace(30*rand.nextDouble());
                diskInfo.setDiskUsedSpaceByte(30*rand.nextDouble() + "GiB");
                diskInfo.setDiskQueuelength(0);
                diskInfo.setDiskInodeUsed(5*rand.nextDouble());
                diskInfo.setDiskFreeSpacePercentage(80+10*rand.nextDouble());
                diskInfo.setDiskFreeSpaceByte(300*rand.nextDouble() + "GiB");

                NetworkInfo networkInfo = new NetworkInfo();
                networkInfo.setUid(hostname);
                networkInfo.setHostname(hostname);
                networkInfo.setNetworkInterface("eth0");
                networkInfo.setTrafficIn(10*rand.nextDouble()+"Kib");
                networkInfo.setTrafficOut(10*rand.nextDouble()+"Kib");

                networkInfo.setPacketIn(3*rand.nextDouble());
                networkInfo.setPacketOut(6*rand.nextDouble());

                networkInfo.setErrorIn(0);
                networkInfo.setErrorOut(0);

                networkInfo.setDropIn(0);
                networkInfo.setDropOut(0);



                ServerInfo serverInfo = new ServerInfo();
                serverInfo.setUid(hostname);
                serverInfo.setOsType("linux");
                serverInfo.setOsVersion("Ubuntu 18.04.6 LTS");
                serverInfo.setIpadress("172.26.36.19");
                serverInfo.setAgentVersion("2.2.7");
                serverInfo.setTotalMem("7.7GiB");
                serverInfo.setCore(4);


                ProcessInfo process1 = new ProcessInfo();
                process1.setUid(hostname);
                process1.setHostname(hostname);
                process1.setProcessUser("root");
                process1.setProcessName("docker");
                process1.setCpuUsage(100 * rand.nextDouble());
                process1.setMemUsage(100 * rand.nextDouble());
                process1.setDiskUsage(100 * rand.nextDouble());



                ProcessInfo process2 = new ProcessInfo();
                process2.setUid(hostname);
                process2.setHostname(hostname);
                process2.setProcessUser("ksj");
                process2.setProcessName("chrome");
                process2.setCpuUsage(100 * rand.nextDouble());
                process2.setMemUsage(100 * rand.nextDouble());
                process2.setDiskUsage(100 * rand.nextDouble());



                ProcessInfo process3 = new ProcessInfo();
                process3.setUid(hostname);
                process3.setHostname(hostname);
                process3.setProcessUser("ksj");
                process3.setProcessName("agent");
                process3.setCpuUsage(100 * rand.nextDouble());
                process3.setMemUsage(100 * rand.nextDouble());
                process3.setDiskUsage(100 * rand.nextDouble());




                influxDB.write(Point.measurementByPOJO(CpuInfo.class).addFieldsFromPOJO(cpuinfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(MemInfo.class).addFieldsFromPOJO(memInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(DiskInfo.class).addFieldsFromPOJO(diskInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(ServerInfo.class).addFieldsFromPOJO(serverInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(NetworkInfo.class).addFieldsFromPOJO(networkInfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());

                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process1).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process1).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process1).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());

                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process2).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process2).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());

                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process3).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(ProcessInfo.class).addFieldsFromPOJO(process3).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());

//                QueryResult queryResult = influxDB.query(new Query("select * from CpuInfo where uid = 'serverA' and time > now() -3m"));
//                InfluxDBResultMapper influxDBResultMapper = new InfluxDBResultMapper();
////                System.out.println(queryResult.toString());
//                List<CpuInfo> cpuInfo = influxDBResultMapper.toPOJO(queryResult, CpuInfo.class);
//                System.out.println(cpuInfo.size());
            }
            System.out.println("Running");
            sleep(5000);
        }

    }
}
