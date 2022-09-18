// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trace/v1/trace_config.proto

package io.opentelemetry.proto.trace.v1;

/**
 * <pre>
 * Sampler that tries to uniformly sample traces with a given ratio.
 * The ratio of sampling a trace is equal to that of the specified ratio.
 * </pre>
 *
 * Protobuf type {@code opentelemetry.proto.trace.v1.TraceIdRatioBased}
 */
public final class TraceIdRatioBased extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:opentelemetry.proto.trace.v1.TraceIdRatioBased)
    TraceIdRatioBasedOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TraceIdRatioBased.newBuilder() to construct.
  private TraceIdRatioBased(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TraceIdRatioBased() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new TraceIdRatioBased();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TraceIdRatioBased(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 9: {

            samplingRatio_ = input.readDouble();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return TraceConfigProto.internal_static_opentelemetry_proto_trace_v1_TraceIdRatioBased_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return TraceConfigProto.internal_static_opentelemetry_proto_trace_v1_TraceIdRatioBased_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            TraceIdRatioBased.class, Builder.class);
  }

  public static final int SAMPLINGRATIO_FIELD_NUMBER = 1;
  private double samplingRatio_;
  /**
   * <pre>
   * The desired ratio of sampling. Must be within [0.0, 1.0].
   * </pre>
   *
   * <code>double samplingRatio = 1;</code>
   * @return The samplingRatio.
   */
  @Override
  public double getSamplingRatio() {
    return samplingRatio_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (Double.doubleToRawLongBits(samplingRatio_) != 0) {
      output.writeDouble(1, samplingRatio_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (Double.doubleToRawLongBits(samplingRatio_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, samplingRatio_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof TraceIdRatioBased)) {
      return super.equals(obj);
    }
    TraceIdRatioBased other = (TraceIdRatioBased) obj;

    if (Double.doubleToLongBits(getSamplingRatio())
        != Double.doubleToLongBits(
            other.getSamplingRatio())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SAMPLINGRATIO_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        Double.doubleToLongBits(getSamplingRatio()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static TraceIdRatioBased parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static TraceIdRatioBased parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static TraceIdRatioBased parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static TraceIdRatioBased parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static TraceIdRatioBased parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static TraceIdRatioBased parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static TraceIdRatioBased parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static TraceIdRatioBased parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static TraceIdRatioBased parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static TraceIdRatioBased parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static TraceIdRatioBased parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static TraceIdRatioBased parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(TraceIdRatioBased prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Sampler that tries to uniformly sample traces with a given ratio.
   * The ratio of sampling a trace is equal to that of the specified ratio.
   * </pre>
   *
   * Protobuf type {@code opentelemetry.proto.trace.v1.TraceIdRatioBased}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:opentelemetry.proto.trace.v1.TraceIdRatioBased)
      TraceIdRatioBasedOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return TraceConfigProto.internal_static_opentelemetry_proto_trace_v1_TraceIdRatioBased_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return TraceConfigProto.internal_static_opentelemetry_proto_trace_v1_TraceIdRatioBased_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              TraceIdRatioBased.class, Builder.class);
    }

    // Construct using io.opentelemetry.proto.trace.v1.TraceIdRatioBased.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      samplingRatio_ = 0D;

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return TraceConfigProto.internal_static_opentelemetry_proto_trace_v1_TraceIdRatioBased_descriptor;
    }

    @Override
    public TraceIdRatioBased getDefaultInstanceForType() {
      return TraceIdRatioBased.getDefaultInstance();
    }

    @Override
    public TraceIdRatioBased build() {
      TraceIdRatioBased result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public TraceIdRatioBased buildPartial() {
      TraceIdRatioBased result = new TraceIdRatioBased(this);
      result.samplingRatio_ = samplingRatio_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof TraceIdRatioBased) {
        return mergeFrom((TraceIdRatioBased)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(TraceIdRatioBased other) {
      if (other == TraceIdRatioBased.getDefaultInstance()) return this;
      if (other.getSamplingRatio() != 0D) {
        setSamplingRatio(other.getSamplingRatio());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      TraceIdRatioBased parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (TraceIdRatioBased) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double samplingRatio_ ;
    /**
     * <pre>
     * The desired ratio of sampling. Must be within [0.0, 1.0].
     * </pre>
     *
     * <code>double samplingRatio = 1;</code>
     * @return The samplingRatio.
     */
    @Override
    public double getSamplingRatio() {
      return samplingRatio_;
    }
    /**
     * <pre>
     * The desired ratio of sampling. Must be within [0.0, 1.0].
     * </pre>
     *
     * <code>double samplingRatio = 1;</code>
     * @param value The samplingRatio to set.
     * @return This builder for chaining.
     */
    public Builder setSamplingRatio(double value) {
      
      samplingRatio_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The desired ratio of sampling. Must be within [0.0, 1.0].
     * </pre>
     *
     * <code>double samplingRatio = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSamplingRatio() {
      
      samplingRatio_ = 0D;
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:opentelemetry.proto.trace.v1.TraceIdRatioBased)
  }

  // @@protoc_insertion_point(class_scope:opentelemetry.proto.trace.v1.TraceIdRatioBased)
  private static final TraceIdRatioBased DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new TraceIdRatioBased();
  }

  public static TraceIdRatioBased getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TraceIdRatioBased>
      PARSER = new com.google.protobuf.AbstractParser<TraceIdRatioBased>() {
    @Override
    public TraceIdRatioBased parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TraceIdRatioBased(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TraceIdRatioBased> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<TraceIdRatioBased> getParserForType() {
    return PARSER;
  }

  @Override
  public TraceIdRatioBased getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

