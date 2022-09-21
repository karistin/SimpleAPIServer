package lucas.exporter;



import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;


import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * packageName    : lucas.exporter
 * fileName       : Main
 * author         : lucas
 * date           : 2022-09-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-14        lucas       최초 생성
 * http://localhost:16686/search
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {

        String target = "localhost:4317";
//        String target = "localhost:8080";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();


        try{
            OtplExporter client = new OtplExporter(channel);
            client.trace();
            client.log();
        }finally {
//            TCP channel off
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }



    }


}
