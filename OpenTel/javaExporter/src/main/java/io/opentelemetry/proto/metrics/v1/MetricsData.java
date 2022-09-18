// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: metrics/v1/metrics.proto

package io.opentelemetry.proto.metrics.v1;

/**
 * <pre>
 * MetricsData represents the metrics data that can be stored in a persistent
 * storage, OR can be embedded by other protocols that transfer OTLP metrics
 * data but do not implement the OTLP protocol.
 * The main difference between this message and collector protocol is that
 * in this message there will not be any "control" or "metadata" specific to
 * OTLP protocol.
 * When new fields are added into this message, the OTLP request MUST be updated
 * as well.
 * </pre>
 *
 * Protobuf type {@code opentelemetry.proto.metrics.v1.MetricsData}
 */
public final class MetricsData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:opentelemetry.proto.metrics.v1.MetricsData)
    MetricsDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MetricsData.newBuilder() to construct.
  private MetricsData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MetricsData() {
    resourceMetrics_ = java.util.Collections.emptyList();
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new MetricsData();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MetricsData(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              resourceMetrics_ = new java.util.ArrayList<ResourceMetrics>();
              mutable_bitField0_ |= 0x00000001;
            }
            resourceMetrics_.add(
                input.readMessage(ResourceMetrics.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        resourceMetrics_ = java.util.Collections.unmodifiableList(resourceMetrics_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return MetricsProto.internal_static_opentelemetry_proto_metrics_v1_MetricsData_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return MetricsProto.internal_static_opentelemetry_proto_metrics_v1_MetricsData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            MetricsData.class, Builder.class);
  }

  public static final int RESOURCE_METRICS_FIELD_NUMBER = 1;
  private java.util.List<ResourceMetrics> resourceMetrics_;
  /**
   * <pre>
   * An array of ResourceMetrics.
   * For data coming from a single resource this array will typically contain
   * one element. Intermediary nodes that receive data from multiple origins
   * typically batch the data before forwarding further and in that case this
   * array will contain multiple elements.
   * </pre>
   *
   * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
   */
  @Override
  public java.util.List<ResourceMetrics> getResourceMetricsList() {
    return resourceMetrics_;
  }
  /**
   * <pre>
   * An array of ResourceMetrics.
   * For data coming from a single resource this array will typically contain
   * one element. Intermediary nodes that receive data from multiple origins
   * typically batch the data before forwarding further and in that case this
   * array will contain multiple elements.
   * </pre>
   *
   * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
   */
  @Override
  public java.util.List<? extends ResourceMetricsOrBuilder>
      getResourceMetricsOrBuilderList() {
    return resourceMetrics_;
  }
  /**
   * <pre>
   * An array of ResourceMetrics.
   * For data coming from a single resource this array will typically contain
   * one element. Intermediary nodes that receive data from multiple origins
   * typically batch the data before forwarding further and in that case this
   * array will contain multiple elements.
   * </pre>
   *
   * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
   */
  @Override
  public int getResourceMetricsCount() {
    return resourceMetrics_.size();
  }
  /**
   * <pre>
   * An array of ResourceMetrics.
   * For data coming from a single resource this array will typically contain
   * one element. Intermediary nodes that receive data from multiple origins
   * typically batch the data before forwarding further and in that case this
   * array will contain multiple elements.
   * </pre>
   *
   * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
   */
  @Override
  public ResourceMetrics getResourceMetrics(int index) {
    return resourceMetrics_.get(index);
  }
  /**
   * <pre>
   * An array of ResourceMetrics.
   * For data coming from a single resource this array will typically contain
   * one element. Intermediary nodes that receive data from multiple origins
   * typically batch the data before forwarding further and in that case this
   * array will contain multiple elements.
   * </pre>
   *
   * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
   */
  @Override
  public ResourceMetricsOrBuilder getResourceMetricsOrBuilder(
      int index) {
    return resourceMetrics_.get(index);
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
    for (int i = 0; i < resourceMetrics_.size(); i++) {
      output.writeMessage(1, resourceMetrics_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < resourceMetrics_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, resourceMetrics_.get(i));
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
    if (!(obj instanceof MetricsData)) {
      return super.equals(obj);
    }
    MetricsData other = (MetricsData) obj;

    if (!getResourceMetricsList()
        .equals(other.getResourceMetricsList())) return false;
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
    if (getResourceMetricsCount() > 0) {
      hash = (37 * hash) + RESOURCE_METRICS_FIELD_NUMBER;
      hash = (53 * hash) + getResourceMetricsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static MetricsData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MetricsData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MetricsData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MetricsData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MetricsData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MetricsData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MetricsData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MetricsData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static MetricsData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static MetricsData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static MetricsData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MetricsData parseFrom(
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
  public static Builder newBuilder(MetricsData prototype) {
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
   * MetricsData represents the metrics data that can be stored in a persistent
   * storage, OR can be embedded by other protocols that transfer OTLP metrics
   * data but do not implement the OTLP protocol.
   * The main difference between this message and collector protocol is that
   * in this message there will not be any "control" or "metadata" specific to
   * OTLP protocol.
   * When new fields are added into this message, the OTLP request MUST be updated
   * as well.
   * </pre>
   *
   * Protobuf type {@code opentelemetry.proto.metrics.v1.MetricsData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:opentelemetry.proto.metrics.v1.MetricsData)
      MetricsDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return MetricsProto.internal_static_opentelemetry_proto_metrics_v1_MetricsData_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return MetricsProto.internal_static_opentelemetry_proto_metrics_v1_MetricsData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              MetricsData.class, Builder.class);
    }

    // Construct using io.opentelemetry.proto.metrics.v1.MetricsData.newBuilder()
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
        getResourceMetricsFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      if (resourceMetricsBuilder_ == null) {
        resourceMetrics_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        resourceMetricsBuilder_.clear();
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return MetricsProto.internal_static_opentelemetry_proto_metrics_v1_MetricsData_descriptor;
    }

    @Override
    public MetricsData getDefaultInstanceForType() {
      return MetricsData.getDefaultInstance();
    }

    @Override
    public MetricsData build() {
      MetricsData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public MetricsData buildPartial() {
      MetricsData result = new MetricsData(this);
      int from_bitField0_ = bitField0_;
      if (resourceMetricsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          resourceMetrics_ = java.util.Collections.unmodifiableList(resourceMetrics_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.resourceMetrics_ = resourceMetrics_;
      } else {
        result.resourceMetrics_ = resourceMetricsBuilder_.build();
      }
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
      if (other instanceof MetricsData) {
        return mergeFrom((MetricsData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(MetricsData other) {
      if (other == MetricsData.getDefaultInstance()) return this;
      if (resourceMetricsBuilder_ == null) {
        if (!other.resourceMetrics_.isEmpty()) {
          if (resourceMetrics_.isEmpty()) {
            resourceMetrics_ = other.resourceMetrics_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureResourceMetricsIsMutable();
            resourceMetrics_.addAll(other.resourceMetrics_);
          }
          onChanged();
        }
      } else {
        if (!other.resourceMetrics_.isEmpty()) {
          if (resourceMetricsBuilder_.isEmpty()) {
            resourceMetricsBuilder_.dispose();
            resourceMetricsBuilder_ = null;
            resourceMetrics_ = other.resourceMetrics_;
            bitField0_ = (bitField0_ & ~0x00000001);
            resourceMetricsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getResourceMetricsFieldBuilder() : null;
          } else {
            resourceMetricsBuilder_.addAllMessages(other.resourceMetrics_);
          }
        }
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
      MetricsData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (MetricsData) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<ResourceMetrics> resourceMetrics_ =
      java.util.Collections.emptyList();
    private void ensureResourceMetricsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        resourceMetrics_ = new java.util.ArrayList<ResourceMetrics>(resourceMetrics_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        ResourceMetrics, ResourceMetrics.Builder, ResourceMetricsOrBuilder> resourceMetricsBuilder_;

    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public java.util.List<ResourceMetrics> getResourceMetricsList() {
      if (resourceMetricsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(resourceMetrics_);
      } else {
        return resourceMetricsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public int getResourceMetricsCount() {
      if (resourceMetricsBuilder_ == null) {
        return resourceMetrics_.size();
      } else {
        return resourceMetricsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public ResourceMetrics getResourceMetrics(int index) {
      if (resourceMetricsBuilder_ == null) {
        return resourceMetrics_.get(index);
      } else {
        return resourceMetricsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder setResourceMetrics(
        int index, ResourceMetrics value) {
      if (resourceMetricsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResourceMetricsIsMutable();
        resourceMetrics_.set(index, value);
        onChanged();
      } else {
        resourceMetricsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder setResourceMetrics(
        int index, ResourceMetrics.Builder builderForValue) {
      if (resourceMetricsBuilder_ == null) {
        ensureResourceMetricsIsMutable();
        resourceMetrics_.set(index, builderForValue.build());
        onChanged();
      } else {
        resourceMetricsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder addResourceMetrics(ResourceMetrics value) {
      if (resourceMetricsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResourceMetricsIsMutable();
        resourceMetrics_.add(value);
        onChanged();
      } else {
        resourceMetricsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder addResourceMetrics(
        int index, ResourceMetrics value) {
      if (resourceMetricsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResourceMetricsIsMutable();
        resourceMetrics_.add(index, value);
        onChanged();
      } else {
        resourceMetricsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder addResourceMetrics(
        ResourceMetrics.Builder builderForValue) {
      if (resourceMetricsBuilder_ == null) {
        ensureResourceMetricsIsMutable();
        resourceMetrics_.add(builderForValue.build());
        onChanged();
      } else {
        resourceMetricsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder addResourceMetrics(
        int index, ResourceMetrics.Builder builderForValue) {
      if (resourceMetricsBuilder_ == null) {
        ensureResourceMetricsIsMutable();
        resourceMetrics_.add(index, builderForValue.build());
        onChanged();
      } else {
        resourceMetricsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder addAllResourceMetrics(
        Iterable<? extends ResourceMetrics> values) {
      if (resourceMetricsBuilder_ == null) {
        ensureResourceMetricsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, resourceMetrics_);
        onChanged();
      } else {
        resourceMetricsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder clearResourceMetrics() {
      if (resourceMetricsBuilder_ == null) {
        resourceMetrics_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        resourceMetricsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public Builder removeResourceMetrics(int index) {
      if (resourceMetricsBuilder_ == null) {
        ensureResourceMetricsIsMutable();
        resourceMetrics_.remove(index);
        onChanged();
      } else {
        resourceMetricsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public ResourceMetrics.Builder getResourceMetricsBuilder(
        int index) {
      return getResourceMetricsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public ResourceMetricsOrBuilder getResourceMetricsOrBuilder(
        int index) {
      if (resourceMetricsBuilder_ == null) {
        return resourceMetrics_.get(index);  } else {
        return resourceMetricsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public java.util.List<? extends ResourceMetricsOrBuilder>
         getResourceMetricsOrBuilderList() {
      if (resourceMetricsBuilder_ != null) {
        return resourceMetricsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(resourceMetrics_);
      }
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public ResourceMetrics.Builder addResourceMetricsBuilder() {
      return getResourceMetricsFieldBuilder().addBuilder(
          ResourceMetrics.getDefaultInstance());
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public ResourceMetrics.Builder addResourceMetricsBuilder(
        int index) {
      return getResourceMetricsFieldBuilder().addBuilder(
          index, ResourceMetrics.getDefaultInstance());
    }
    /**
     * <pre>
     * An array of ResourceMetrics.
     * For data coming from a single resource this array will typically contain
     * one element. Intermediary nodes that receive data from multiple origins
     * typically batch the data before forwarding further and in that case this
     * array will contain multiple elements.
     * </pre>
     *
     * <code>repeated .opentelemetry.proto.metrics.v1.ResourceMetrics resource_metrics = 1;</code>
     */
    public java.util.List<ResourceMetrics.Builder>
         getResourceMetricsBuilderList() {
      return getResourceMetricsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        ResourceMetrics, ResourceMetrics.Builder, ResourceMetricsOrBuilder>
        getResourceMetricsFieldBuilder() {
      if (resourceMetricsBuilder_ == null) {
        resourceMetricsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            ResourceMetrics, ResourceMetrics.Builder, ResourceMetricsOrBuilder>(
                resourceMetrics_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        resourceMetrics_ = null;
      }
      return resourceMetricsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:opentelemetry.proto.metrics.v1.MetricsData)
  }

  // @@protoc_insertion_point(class_scope:opentelemetry.proto.metrics.v1.MetricsData)
  private static final MetricsData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new MetricsData();
  }

  public static MetricsData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MetricsData>
      PARSER = new com.google.protobuf.AbstractParser<MetricsData>() {
    @Override
    public MetricsData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MetricsData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MetricsData> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<MetricsData> getParserForType() {
    return PARSER;
  }

  @Override
  public MetricsData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

