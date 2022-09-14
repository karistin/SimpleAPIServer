package lucas.exporter;

import io.opencensus.common.Scope;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;

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
public class MyClassWithTracing {
    private static final Tracer tracer = Tracing.getTracer();
    public static void doWork(){
        try (Scope ss =
                     (Scope) tracer.spanBuilder("MyChildWorkSpan")
                             .setRecordEvents(true)

        ) {

        }
    }
}
