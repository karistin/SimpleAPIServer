package io.opentelemetry.proto.collector.logs.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Service that can be used to push logs between one Application instrumented with
 * OpenTelemetry and an collector, or between an collector and a central collector (in this
 * case logs are sent/received to/from multiple Applications).
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: collector/logs/v1/logs_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LogsServiceGrpc {

  private LogsServiceGrpc() {}

  public static final String SERVICE_NAME = "opentelemetry.proto.collector.logs.v1.LogsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ExportLogsServiceRequest,
      ExportLogsServiceResponse> getExportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Export",
      requestType = ExportLogsServiceRequest.class,
      responseType = ExportLogsServiceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ExportLogsServiceRequest,
      ExportLogsServiceResponse> getExportMethod() {
    io.grpc.MethodDescriptor<ExportLogsServiceRequest, ExportLogsServiceResponse> getExportMethod;
    if ((getExportMethod = LogsServiceGrpc.getExportMethod) == null) {
      synchronized (LogsServiceGrpc.class) {
        if ((getExportMethod = LogsServiceGrpc.getExportMethod) == null) {
          LogsServiceGrpc.getExportMethod = getExportMethod =
              io.grpc.MethodDescriptor.<ExportLogsServiceRequest, ExportLogsServiceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Export"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExportLogsServiceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExportLogsServiceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LogsServiceMethodDescriptorSupplier("Export"))
              .build();
        }
      }
    }
    return getExportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LogsServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LogsServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LogsServiceStub>() {
        @Override
        public LogsServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LogsServiceStub(channel, callOptions);
        }
      };
    return LogsServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LogsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LogsServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LogsServiceBlockingStub>() {
        @Override
        public LogsServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LogsServiceBlockingStub(channel, callOptions);
        }
      };
    return LogsServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LogsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LogsServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LogsServiceFutureStub>() {
        @Override
        public LogsServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LogsServiceFutureStub(channel, callOptions);
        }
      };
    return LogsServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Service that can be used to push logs between one Application instrumented with
   * OpenTelemetry and an collector, or between an collector and a central collector (in this
   * case logs are sent/received to/from multiple Applications).
   * </pre>
   */
  public static abstract class LogsServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public void export(ExportLogsServiceRequest request,
                       io.grpc.stub.StreamObserver<ExportLogsServiceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExportMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getExportMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ExportLogsServiceRequest,
                ExportLogsServiceResponse>(
                  this, METHODID_EXPORT)))
          .build();
    }
  }

  /**
   * <pre>
   * Service that can be used to push logs between one Application instrumented with
   * OpenTelemetry and an collector, or between an collector and a central collector (in this
   * case logs are sent/received to/from multiple Applications).
   * </pre>
   */
  public static final class LogsServiceStub extends io.grpc.stub.AbstractAsyncStub<LogsServiceStub> {
    private LogsServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected LogsServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LogsServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public void export(ExportLogsServiceRequest request,
                       io.grpc.stub.StreamObserver<ExportLogsServiceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExportMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service that can be used to push logs between one Application instrumented with
   * OpenTelemetry and an collector, or between an collector and a central collector (in this
   * case logs are sent/received to/from multiple Applications).
   * </pre>
   */
  public static final class LogsServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<LogsServiceBlockingStub> {
    private LogsServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected LogsServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LogsServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public ExportLogsServiceResponse export(ExportLogsServiceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExportMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service that can be used to push logs between one Application instrumented with
   * OpenTelemetry and an collector, or between an collector and a central collector (in this
   * case logs are sent/received to/from multiple Applications).
   * </pre>
   */
  public static final class LogsServiceFutureStub extends io.grpc.stub.AbstractFutureStub<LogsServiceFutureStub> {
    private LogsServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected LogsServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LogsServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ExportLogsServiceResponse> export(
        ExportLogsServiceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExportMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXPORT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LogsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXPORT:
          serviceImpl.export((ExportLogsServiceRequest) request,
              (io.grpc.stub.StreamObserver<ExportLogsServiceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LogsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LogsServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return LogsServiceProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LogsService");
    }
  }

  private static final class LogsServiceFileDescriptorSupplier
      extends LogsServiceBaseDescriptorSupplier {
    LogsServiceFileDescriptorSupplier() {}
  }

  private static final class LogsServiceMethodDescriptorSupplier
      extends LogsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LogsServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LogsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LogsServiceFileDescriptorSupplier())
              .addMethod(getExportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
