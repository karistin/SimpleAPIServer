// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trace/v1/trace.proto

package io.opentelemetry.proto.trace.v1;

public interface StatusOrBuilder extends
    // @@protoc_insertion_point(interface_extends:opentelemetry.proto.trace.v1.Status)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * A developer-facing human readable error message.
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The message.
   */
  String getMessage();
  /**
   * <pre>
   * A developer-facing human readable error message.
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   * The status code.
   * </pre>
   *
   * <code>.opentelemetry.proto.trace.v1.Status.StatusCode code = 3;</code>
   * @return The enum numeric value on the wire for code.
   */
  int getCodeValue();
  /**
   * <pre>
   * The status code.
   * </pre>
   *
   * <code>.opentelemetry.proto.trace.v1.Status.StatusCode code = 3;</code>
   * @return The code.
   */
  Status.StatusCode getCode();
}
