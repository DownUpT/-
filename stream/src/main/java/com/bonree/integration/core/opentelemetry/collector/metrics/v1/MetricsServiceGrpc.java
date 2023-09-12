package com.bonree.integration.core.opentelemetry.collector.metrics.v1;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Service that can be used to push metrics between one Application
 * instrumented with OpenTelemetry and a collector, or between a collector and a
 * central collector.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: collector/metrics/v1/metrics_service.proto")
public final class MetricsServiceGrpc {

  private MetricsServiceGrpc() {}

  public static final String SERVICE_NAME = "opentelemetry.proto.collector.metrics.v1.MetricsService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ExportMetricsServiceRequest,
      ExportMetricsServiceResponse> METHOD_EXPORT =
      io.grpc.MethodDescriptor.<ExportMetricsServiceRequest, ExportMetricsServiceResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "opentelemetry.proto.collector.metrics.v1.MetricsService", "Export"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ExportMetricsServiceRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ExportMetricsServiceResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MetricsServiceStub newStub(io.grpc.Channel channel) {
    return new MetricsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MetricsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MetricsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MetricsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MetricsServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Service that can be used to push metrics between one Application
   * instrumented with OpenTelemetry and a collector, or between a collector and a
   * central collector.
   * </pre>
   */
  public static abstract class MetricsServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public void export(ExportMetricsServiceRequest request,
                       io.grpc.stub.StreamObserver<ExportMetricsServiceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXPORT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_EXPORT,
            asyncUnaryCall(
              new MethodHandlers<
                ExportMetricsServiceRequest,
                ExportMetricsServiceResponse>(
                  this, METHODID_EXPORT)))
          .build();
    }
  }

  /**
   * <pre>
   * Service that can be used to push metrics between one Application
   * instrumented with OpenTelemetry and a collector, or between a collector and a
   * central collector.
   * </pre>
   */
  public static final class MetricsServiceStub extends io.grpc.stub.AbstractStub<MetricsServiceStub> {
    private MetricsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MetricsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MetricsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MetricsServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public void export(ExportMetricsServiceRequest request,
                       io.grpc.stub.StreamObserver<ExportMetricsServiceResponse> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(METHOD_EXPORT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service that can be used to push metrics between one Application
   * instrumented with OpenTelemetry and a collector, or between a collector and a
   * central collector.
   * </pre>
   */
  public static final class MetricsServiceBlockingStub extends io.grpc.stub.AbstractStub<MetricsServiceBlockingStub> {
    private MetricsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MetricsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MetricsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MetricsServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public ExportMetricsServiceResponse export(ExportMetricsServiceRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXPORT, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service that can be used to push metrics between one Application
   * instrumented with OpenTelemetry and a collector, or between a collector and a
   * central collector.
   * </pre>
   */
  public static final class MetricsServiceFutureStub extends io.grpc.stub.AbstractStub<MetricsServiceFutureStub> {
    private MetricsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MetricsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MetricsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MetricsServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ExportMetricsServiceResponse> export(
        ExportMetricsServiceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXPORT, getCallOptions()), request);
    }
  }

  private static final int METHODID_EXPORT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MetricsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MetricsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXPORT:
          serviceImpl.export((ExportMetricsServiceRequest) request,
              (io.grpc.stub.StreamObserver<ExportMetricsServiceResponse>) responseObserver);
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

  private static final class MetricsServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return MetricsServiceProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MetricsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MetricsServiceDescriptorSupplier())
              .addMethod(METHOD_EXPORT)
              .build();
        }
      }
    }
    return result;
  }
}
