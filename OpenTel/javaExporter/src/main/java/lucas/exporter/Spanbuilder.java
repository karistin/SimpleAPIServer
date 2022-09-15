package lucas.exporter;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequestOrBuilder;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponseOrBuilder;
import io.opentelemetry.proto.trace.v1.ResourceSpans;
import io.opentelemetry.proto.trace.v1.ResourceSpansOrBuilder;
import io.opentelemetry.proto.trace.v1.Span;

import java.util.List;
import java.util.Map;

/**
 * packageName    : lucas.exporter
 * fileName       : Spanbuilder
 * author         : lucas
 * date           : 2022-09-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-15        lucas       최초 생성
 */
public class Spanbuilder implements ExportTraceServiceRequestOrBuilder {

    @Override
    public List<ResourceSpans> getResourceSpansList() {
        return null;
    }

    @Override
    public ResourceSpans getResourceSpans(int i) {
        return null;
    }

    @Override
    public int getResourceSpansCount() {
        return 0;
    }

    @Override
    public List<? extends ResourceSpansOrBuilder> getResourceSpansOrBuilderList() {
        return null;
    }

    @Override
    public ResourceSpansOrBuilder getResourceSpansOrBuilder(int i) {
        return null;
    }

    @Override
    public Message getDefaultInstanceForType() {
        return null;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public List<String> findInitializationErrors() {
        return null;
    }

    @Override
    public String getInitializationErrorString() {
        return null;
    }

    @Override
    public Descriptors.Descriptor getDescriptorForType() {
        return null;
    }

    @Override
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return null;
    }

    @Override
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        return false;
    }

    @Override
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        return null;
    }

    @Override
    public boolean hasField(Descriptors.FieldDescriptor field) {
        return false;
    }

    @Override
    public Object getField(Descriptors.FieldDescriptor field) {
        return null;
    }

    @Override
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
        return 0;
    }

    @Override
    public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
        return null;
    }

    @Override
    public UnknownFieldSet getUnknownFields() {
        return null;
    }
}
