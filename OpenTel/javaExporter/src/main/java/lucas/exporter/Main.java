package lucas.exporter;


import com.google.protobuf.AbstractMessageLite;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceGrpc;

import io.opentelemetry.proto.trace.v1.InstrumentationLibrarySpans;
import io.opentelemetry.proto.trace.v1.ResourceSpans;
import io.opentelemetry.sdk.OpenTelemetrySdk;


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
 */
public class Main {
    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",14250)
//                .usePlaintext()
//                .build();
////
//        TraceServiceGrpc.TraceServiceStub serviceGrpc = TraceServiceGrpc.newStub(channel);
////
////        Long time = System.currentTimeMillis();
////
//
//        ExportTraceServiceRequest request = ExportTraceServiceRequest.newBuilder().build();

//        ResourceSpans spans = ResourceSpans.newBuilder().build();
//
//        InstrumentationLibrarySpans instrumentationLibrarySpans = InstrumentationLibrarySpans.newBuilder().build();

//        request.toBuilder().addResourceSpans(ResourceSpans.newBuilder().getDefaultInstanceForType());



//
//        System.out.println(request.getSerializedSize());
////        serviceGrpc.export(request, StreamObserver< ExportTraceServiceResponse > responseObserver)
//
//    JaegerGrpcSpanExporter jaegerExporter =
//                JaegerGrpcSpanExporter.newBuilder().setChannel(channel).setServiceName("Testing").build();


    JaegerTraceExporter.createAndRegister();
    }
}
