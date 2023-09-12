package com.bonree.integration.core.opentelemetry.collector.trace.v1;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Service that can be used to push spans between one Application instrumented with
 * OpenTelemetry and a collector, or between a collector and a central collector (in this
 * case spans are sent/received to/from multiple Applications).
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: collector/trace/v1/trace_service.proto")
public final class TraceServiceGrpc {

  private TraceServiceGrpc() {}

  public static final String SERVICE_NAME = "opentelemetry.proto.collector.trace.v1.TraceService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ExportTraceServiceRequest,
      ExportTraceServiceResponse> METHOD_EXPORT =
      io.grpc.MethodDescriptor.<ExportTraceServiceRequest, ExportTraceServiceResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "opentelemetry.proto.collector.trace.v1.TraceService", "Export"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ExportTraceServiceRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ExportTraceServiceResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TraceServiceStub newStub(io.grpc.Channel channel) {
    return new TraceServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TraceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TraceServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TraceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TraceServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Service that can be used to push spans between one Application instrumented with
   * OpenTelemetry and a collector, or between a collector and a central collector (in this
   * case spans are sent/received to/from multiple Applications).
   * </pre>
   */
  public static abstract class TraceServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public void export(ExportTraceServiceRequest request,
                       io.grpc.stub.StreamObserver<ExportTraceServiceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXPORT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_EXPORT,
            asyncUnaryCall(
              new MethodHandlers<
                ExportTraceServiceRequest,
                ExportTraceServiceResponse>(
                  this, METHODID_EXPORT)))
          .build();
    }
  }

  /**
   * <pre>
   * Service that can be used to push spans between one Application instrumented with
   * OpenTelemetry and a collector, or between a collector and a central collector (in this
   * case spans are sent/received to/from multiple Applications).
   * </pre>
   */
  public static final class TraceServiceStub extends io.grpc.stub.AbstractStub<TraceServiceStub> {
    private TraceServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TraceServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TraceServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TraceServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public void export(ExportTraceServiceRequest request,
                       io.grpc.stub.StreamObserver<ExportTraceServiceResponse> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(METHOD_EXPORT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service that can be used to push spans between one Application instrumented with
   * OpenTelemetry and a collector, or between a collector and a central collector (in this
   * case spans are sent/received to/from multiple Applications).
   * </pre>
   */
  public static final class TraceServiceBlockingStub extends io.grpc.stub.AbstractStub<TraceServiceBlockingStub> {
    private TraceServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TraceServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TraceServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TraceServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public ExportTraceServiceResponse export(ExportTraceServiceRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXPORT, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service that can be used to push spans between one Application instrumented with
   * OpenTelemetry and a collector, or between a collector and a central collector (in this
   * case spans are sent/received to/from multiple Applications).
   * </pre>
   */
  public static final class TraceServiceFutureStub extends io.grpc.stub.AbstractStub<TraceServiceFutureStub> {
    private TraceServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TraceServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TraceServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TraceServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * For performance reasons, it is recommended to keep this RPC
     * alive for the entire life of the application.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ExportTraceServiceResponse> export(
        ExportTraceServiceRequest request) {
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
    private final TraceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TraceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXPORT:
          serviceImpl.export((ExportTraceServiceRequest) request,
              (io.grpc.stub.StreamObserver<ExportTraceServiceResponse>) responseObserver);
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

  private static final class TraceServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return TraceServiceProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TraceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TraceServiceDescriptorSupplier())
              .addMethod(METHOD_EXPORT)
              .build();
        }
      }
    }
    return result;
  }
}
