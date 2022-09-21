package lucas.exporter;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lucas.exporter.service.TracesServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * packageName    : lucas.exporter
 * fileName       : OtplServer
 * author         : lucas
 * date           : 2022-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-19        lucas       최초 생성
 */
public class OtplServer {
    private static final Logger logger = Logger.getLogger(OtplServer.class.getName());

    private Server server;

    private void start() throws IOException {
        int port = 8080;
        server = ServerBuilder.forPort(port)
                .addService(new TracesServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try{
                OtplServer.this.stop();
            }catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        final OtplServer server = new OtplServer();
        server.start();
        server.blockUntilShutdown();
    }
}
