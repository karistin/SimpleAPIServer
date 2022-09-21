package lucas.exporter;

import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import io.opentelemetry.proto.collector.logs.v1.LogsServiceGrpc;
import io.opentelemetry.proto.collector.metrics.v1.MetricsServiceGrpc;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceGrpc;
import io.opentelemetry.proto.collector.trace.v1.TraceServiceProto;
import io.opentelemetry.proto.common.v1.AnyValue;
import io.opentelemetry.proto.common.v1.InstrumentationLibrary;
import io.opentelemetry.proto.common.v1.KeyValue;
import io.opentelemetry.proto.logs.v1.InstrumentationLibraryLogs;
import io.opentelemetry.proto.logs.v1.LogRecord;
import io.opentelemetry.proto.logs.v1.ResourceLogs;
import io.opentelemetry.proto.resource.v1.Resource;
import io.opentelemetry.proto.trace.v1.InstrumentationLibrarySpans;
import io.opentelemetry.proto.trace.v1.ResourceSpans;
import io.opentelemetry.proto.trace.v1.Span;
import io.opentelemetry.proto.trace.v1.Status;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OtplExporter {
    private static final Logger logger = Logger.getLogger(OtplExporter.class.getName());
    private final LogsServiceGrpc.LogsServiceBlockingStub logsServiceBlockingStub;
    private final MetricsServiceGrpc.MetricsServiceBlockingStub metricsServiceBlockingStub;
    private final TraceServiceGrpc.TraceServiceBlockingStub traceServiceBlockingStub;

    public OtplExporter(Channel channel) {
        this.logsServiceBlockingStub = LogsServiceGrpc.newBlockingStub(channel);
        this.metricsServiceBlockingStub = MetricsServiceGrpc.newBlockingStub(channel);
        this.traceServiceBlockingStub = TraceServiceGrpc.newBlockingStub(channel);
    }

    public void log(){
        Resource resource = Resource.newBuilder().addAttributes(
                        KeyValue.newBuilder()
                                .setKey("kkk")
                                .setValue(
                                        AnyValue.newBuilder().setStringValue("vvv").build()
                                ).build()
                )
                .setDroppedAttributesCount(0)
                .build();

        LogRecord logRecord = LogRecord.newBuilder()
                .setTimeUnixNano(Instant.now().getNano())
                .setObservedTimeUnixNano(Instant.now().getNano())
                .setSeverityText("INFO")
                .setName("test")
                .setBody(AnyValue.newBuilder().setStringValue("This is a test log.").build())
                .setTraceId(ByteString.copyFromUtf8("109481290347498213478"))
                .setSpanId(ByteString.copyFromUtf8("214124213512"))
                .build();

        InstrumentationLibraryLogs instrumentationLibraryLogs = InstrumentationLibraryLogs.newBuilder()
                .addLogRecords(logRecord)
                .build();

        ResourceLogs resourceLogs = ResourceLogs.newBuilder()
                .setResource(resource)
                .addInstrumentationLibraryLogs(instrumentationLibraryLogs)
                .build();
        ExportLogsServiceRequest request = ExportLogsServiceRequest.newBuilder().addResourceLogs(resourceLogs).build();
        try {
            ExportLogsServiceResponse response = logsServiceBlockingStub.export(request);
            System.out.println(response.toString());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
    }

    public void metrics(){

    }

    public void trace(){
        KeyValue SpanAttrMethod = KeyValue.newBuilder().setKey("http.method").setValue(AnyValue.newBuilder().setStringValue("GET")).build();
        KeyValue SpanStatusCode = KeyValue.newBuilder().setKey("http.status_code").setValue(AnyValue.newBuilder().setIntValue(200)).build();
        KeyValue SpanAttrFamily = KeyValue.newBuilder().setKey("net.sock.family").setValue(AnyValue.newBuilder().setStringValue("inet")).build();
        KeyValue SpanAttrflavor= KeyValue.newBuilder().setKey("http.flavor").setValue(AnyValue.newBuilder().setStringValue("1.0")).build();
        KeyValue SpanAttrscheme = KeyValue.newBuilder().setKey("http.scheme").setValue(AnyValue.newBuilder().setStringValue("http")).build();
        KeyValue SpanAttrtarget = KeyValue.newBuilder().setKey("http.target").setValue(AnyValue.newBuilder().setStringValue("/app")).build();
        KeyValue SpanAttrname = KeyValue.newBuilder().setKey("net.host.name").setValue(AnyValue.newBuilder().setStringValue("0.0.0.0")).build();


        KeyValue ResourceAttr = KeyValue.newBuilder().setKey("webengine.name").setValue(AnyValue.newBuilder().setStringValue("Spring")).build();
        KeyValue Resourceversion = KeyValue.newBuilder().setKey("webengine.version").setValue(AnyValue.newBuilder().setStringValue("1.0.0")).build();
        KeyValue Resourcename = KeyValue.newBuilder().setKey("service.name").setValue(AnyValue.newBuilder().setStringValue("Sample-Spring")).build();

        Status status = Status.newBuilder()
                .setMessage("OK")
                .setCode(Status.StatusCode.STATUS_CODE_OK)
                .build();


        Random r = new Random();

        Span span = Span.newBuilder()
                .setTraceId(ByteString.copyFromUtf8("0123456701234567"))
                .setSpanId(ByteString.copyFromUtf8("01234567"))
                .setName("GET /api")
                .setKind(Span.SpanKind.SPAN_KIND_SERVER)
                .setStartTimeUnixNano(System.nanoTime())
                .setEndTimeUnixNano(System.nanoTime()+30*1000*1000)
                .addAttributes(
                        SpanAttrMethod
                )
                .addAttributes(
                        SpanAttrFamily
                )
                .addAttributes(
                        SpanAttrflavor
                )
                .addAttributes(
                        SpanAttrscheme
                )
                .addAttributes(
                        SpanAttrtarget
                )
                .addAttributes(
                        SpanAttrname
                )
                .addAttributes(
                        SpanStatusCode
                )
                .setStatus(status)
                .build();


        Resource resource = Resource.newBuilder()
                .addAttributes(
                        ResourceAttr
                )
                .addAttributes(
                        Resourceversion
                )
                .addAttributes(
                        Resourcename
                )
                .build();


        InstrumentationLibrary InScope = InstrumentationLibrary.newBuilder().setName("jennifer-test").setVersion("0.0.1").build();


        InstrumentationLibrarySpans scopeSpans = InstrumentationLibrarySpans.newBuilder()
                .setInstrumentationLibrary(InScope)
                .addSpans(span)
                .build();

        ResourceSpans resourceSpans = ResourceSpans.newBuilder()
                .setResource(resource)
                .addInstrumentationLibrarySpans(scopeSpans)
                .build();

        ExportTraceServiceRequest requestTest = ExportTraceServiceRequest.newBuilder().getDefaultInstanceForType();
        ExportTraceServiceRequest req = ExportTraceServiceRequest.newBuilder()
                .addResourceSpans(resourceSpans)
                .build();

        try {
            ExportTraceServiceResponse response = traceServiceBlockingStub.export(req);
//            System.out.println(req.toString());
            System.out.println("success");
            System.out.println(response.toString());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed {0}", e.getStatus());
        }
    }
}
