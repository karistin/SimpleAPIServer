package lucas.exporter;


import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.TraceStateBuilder;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.TracerProvider;
import io.opentelemetry.context.Scope;

import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponseOrBuilder;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceGrpc;
import io.opentelemetry.proto.common.v1.AnyValue;
import io.opentelemetry.proto.common.v1.KeyValue;
//import io.opentelemetry.proto.resource.v1.Resource;
import io.opentelemetry.proto.resource.v1.Resource;
import io.opentelemetry.proto.trace.v1.*;


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


    public static void main(String[] args) throws ClassNotFoundException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 4317)
                .usePlaintext()
                .build();



        Channel rpcChannel = ManagedChannelBuilder.forAddress("localhost", 4317)
                .usePlaintext()
                .build();


        TraceServiceGrpc.TraceServiceStub stub = TraceServiceGrpc.newStub(channel);


//        ExportTraceServiceRequest req = ExportTraceServiceRequest.newBuilder().setResourceSpans(
//                ResourceSpans.newBuilder()
//                        .setResource(
//                        Resource.newBuilder().addAttributes(
//                                KeyValue.newBuilder().setKey("dfd").setValue(AnyValue.newBuilder().setStringValue("dsfsd"))
//                        )
//                        )).build();


        KeyValue SpanAttr = KeyValue.newBuilder().setKey("http.method").setValue(AnyValue.newBuilder().setStringValue("GET"))
                .setKey("net.sock.family").setValue(AnyValue.newBuilder().setStringValue("inet"))
                .setKey("http.flavor").setValue(AnyValue.newBuilder().setStringValue("1.1"))
                .setKey("http.scheme").setValue(AnyValue.newBuilder().setStringValue("http"))
                .setKey("http.target").setValue(AnyValue.newBuilder().setStringValue("/app"))
                .setKey("net.host.name").setValue(AnyValue.newBuilder().setStringValue("0.0.0.0"))
                .build();

        KeyValue ResourceAttr = KeyValue.newBuilder().setKey("webengine.name").setValue(AnyValue.newBuilder().setStringValue("Spring"))
                .setKey("webengine.version").setValue(AnyValue.newBuilder().setStringValue("1.0.0"))
                .setKey("service.name").setValue(AnyValue.newBuilder().setStringValue("Sample-Spring"))
                .build();


        Span span = Span.newBuilder()
                .setSpanId(ByteString.copyFromUtf8("sdfdsafasd"))
                .setTraceId(ByteString.copyFromUtf8("fsafasfasdfds"))
                .setName("GET /servlet")
                .setKind(Span.SpanKind.SPAN_KIND_SERVER)
                .setStartTimeUnixNano(System.nanoTime())
                .setEndTimeUnixNano(System.nanoTime()+30*1000*1000)
                .addAttributes(
                        SpanAttr
                )
                .build();

        Status status = Status.newBuilder()
                .setMessage("OK")
                .setCode(Status.StatusCode.STATUS_CODE_OK)
                .build();

        Resource resource = Resource.newBuilder()
                .addAttributes(
                        ResourceAttr
                )
                .build();

        ResourceSpans resourceSpans = ResourceSpans.newBuilder()
                .setResource(
                    resource
                )
                .build();


        ExportTraceServiceRequest request = ExportTraceServiceRequest.newBuilder()
                .addResourceSpans(
                        resourceSpans
                )

                .build();



//        stub.export(request, (StreamObserver<ExportTraceServiceResponse>) null);

//
//        TracerProvider provider = TracerProvider.noop();
//        AttributeKey.stringKey()

//        TracingDate date = new TracingDate();
//
//        SpanData span = date.selectWithTracing("Query");
////        List<SpanData> data = Collections.synchronizedList(new ArrayList<>());
//        spans.add(span);
//        OtlpExporter.export(spans);


    }


}
