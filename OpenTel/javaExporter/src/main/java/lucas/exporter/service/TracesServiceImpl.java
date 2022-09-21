package lucas.exporter.service;

import io.grpc.stub.StreamObserver;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceGrpc;
import lucas.exporter.OtplServer;

import java.util.logging.Logger;

/**
 * packageName    : lucas.exporter.service
 * fileName       : TracesServiceImpl
 * author         : lucas
 * date           : 2022-09-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-19        lucas       최초 생성
 */
public class TracesServiceImpl extends TraceServiceGrpc.TraceServiceImplBase {
    private static final Logger logger = Logger.getLogger(OtplServer.class.getName());

    @Override
    public void export(ExportTraceServiceRequest request, StreamObserver<ExportTraceServiceResponse> responseObserver) {
        logger.info(request.toString());
        responseObserver.onNext(ExportTraceServiceResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
