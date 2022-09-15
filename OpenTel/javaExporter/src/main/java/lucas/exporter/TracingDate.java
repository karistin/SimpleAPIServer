package lucas.exporter;



import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.trace.data.SpanData;


/**
 * packageName    : lucas.exporter
 * fileName       : Exporter
 * author         : lucas
 * date           : 2022-09-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-14        lucas       최초 생성
 */
public class TracingDate {
    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("demo-db-client", "0.1.0-beta1");

    public SpanData selectWithTracing(String query) {
//        대충 쿼리를 파싱한다.
        String dbName = "MYSQL";
        String collectionName = "Date";
        SpanData spanData = (SpanData) tracer.spanBuilder(String.format("SELECT %s.%s", dbName, collectionName))
                .setSpanKind(SpanKind.CLIENT)
                .setAttribute("db.name", dbName)
                .startSpan().setStatus(StatusCode.OK);
//        span의 범위를 측정하는 구역
//        상태를 기록하여 측정
        
//        SpanData.setStatus(StatusCode.OK);

        return spanData;
    }
}
