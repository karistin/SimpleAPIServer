package main;

import static java.lang.Thread.sleep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import config.ApplicationConfig;
import entity.CpuInfo;
import entity.DiskInfo;
import entity.MemInfo;
import entity.NetworkInfo;
import entity.ProcessInfo;
import entity.ServerInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

/**
 * packageName    : com.lucas.influxdb
 * fileName       : main.insertMain
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */

public class insertMain {

    private static final String settingName = "./dummy.yml";

    private static final Path path = Paths.get(settingName);

    static ApplicationConfig config = new ApplicationConfig();

    static {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File Settings are not correct");
            }
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            config = objectMapper.readValue(path.toFile(), ApplicationConfig.class);
            System.out.println("Application config info " + config.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static List<String> hostnameList = new ArrayList<>(
            Arrays.asList("serverA","serverB","serverC", "serverD", "serverE",
                "serverF", "serverG","serverH")
    );

    public static void main(String[] args) throws InterruptedException {
        final String serverURL = config.getInfluxdb().getUrl() +":"+ config.getInfluxdb().getPort();
        final String username = config.getInfluxdb().getUsername();
        final String password = config.getInfluxdb().getPassword();
        final String databases = config.getInfluxdb().getDbname();
        InfluxDB influxDB = InfluxDBFactory.connect(serverURL);
        if (!influxDB.ping().isGood()) {
            System.out.println("Not find DB");
            return;
        }
        System.out.println("Ping OK!");
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
                diskInfo.setDiskIOPS(diskInfo.getDiskIOPSRead()+ diskInfo.getDiskIOPSWrite());
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
                serverInfo.setHostName(hostname);
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
                double cpuUsage = 20 * rand.nextDouble();
                process1.setCpuUsage(cpuUsage);
                process1.setCpuUsageCount(2);
                process1.setCpuUsageMax(cpuUsage*0.8);
                process1.setCpuUsageMin(cpuUsage*0.2);


                double memUsage = 15 * rand.nextDouble();
                process1.setMemUsage(memUsage);
                process1.setMemUsageCount(3);
                process1.setMemUsageMax(memUsage*0.7);
                process1.setMemUsageMin(memUsage*0.2);

                double diskUsage = 100 * rand.nextDouble();
                process1.setDiskUsage(diskUsage);




                ProcessInfo process2 = new ProcessInfo();
                process2.setUid(hostname);
                process2.setHostname(hostname);
                process2.setProcessUser("ksj");
                process2.setProcessName("chrome");

                cpuUsage = 20 * rand.nextDouble();

                process2.setCpuUsage(cpuUsage);
                process2.setCpuUsageCount(1);
                process2.setCpuUsageMax(cpuUsage);
                process2.setCpuUsageMin(cpuUsage);

                memUsage = 15 * rand.nextDouble();
                process2.setMemUsage(100 * rand.nextDouble());
                process2.setMemUsageCount(1);
                process2.setMemUsageMax(memUsage);
                process2.setMemUsageMin(memUsage);

                process2.setDiskUsage(100 * rand.nextDouble());



                ProcessInfo process3 = new ProcessInfo();
                process3.setUid(hostname);
                process3.setHostname(hostname);
                process3.setProcessUser("ksj");
                process3.setProcessName("agent");

                process3.setCpuUsage(100 * rand.nextDouble());
                cpuUsage = 20 * rand.nextDouble();
                process3.setCpuUsage(cpuUsage);
                process3.setCpuUsageCount(2);
                process3.setCpuUsageMax(cpuUsage*0.6);
                process3.setCpuUsageMin(cpuUsage*0.4);


                process3.setMemUsage(100 * rand.nextDouble());
                memUsage = 15 * rand.nextDouble();
                process3.setMemUsage(memUsage);
                process3.setMemUsageCount(1);
                process3.setMemUsageMax(memUsage);
                process3.setMemUsageMin(memUsage);

                process3.setDiskUsage(100 * rand.nextDouble());




                influxDB.write(Point.measurementByPOJO(CpuInfo.class).addFieldsFromPOJO(cpuinfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
                influxDB.write(Point.measurementByPOJO(MemInfo.class).addFieldsFromPOJO(memInfo).time(System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS).build());
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

            }
            System.out.println("Running");
            sleep(config.getDummy().getIntervel());
        }

    }

    private ProcessInfo mkProcess(String hostname, String user, String processName, Random rand) {
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setUid(hostname);
        processInfo.setHostname(hostname);
        processInfo.setProcessUser(user);
        processInfo.setProcessName(processName);
        processInfo.setCpuUsage(100 * rand.nextDouble());
        processInfo.setMemUsage(100 * rand.nextDouble());
        processInfo.setDiskUsage(100 * rand.nextDouble());

        return processInfo;
    }

    public static long cutDouble(double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(false);

        String val = nf.format(2);
        return Long.parseLong(val);
    }
}
