// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: collector/trace/v1/trace_service.proto

package com.bonree.integration.core.opentelemetry.collector.trace.v1;

public final class TraceServiceProto {
  private TraceServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTracePartialSuccess_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_trace_v1_ExportTracePartialSuccess_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n&collector/trace/v1/trace_service.proto" +
      "\022&opentelemetry.proto.collector.trace.v1" +
      "\032\024trace/v1/trace.proto\"`\n\031ExportTraceSer" +
      "viceRequest\022C\n\016resource_spans\030\001 \003(\0132+.op" +
      "entelemetry.proto.trace.v1.ResourceSpans" +
      "\"x\n\032ExportTraceServiceResponse\022Z\n\017partia" +
      "l_success\030\001 \001(\0132A.opentelemetry.proto.co" +
      "llector.trace.v1.ExportTracePartialSucce" +
      "ss\"J\n\031ExportTracePartialSuccess\022\026\n\016rejec" +
      "ted_spans\030\001 \001(\003\022\025\n\rerror_message\030\002 \001(\t2\242" +
      "\001\n\014TraceService\022\221\001\n\006Export\022A.opentelemet" +
      "ry.proto.collector.trace.v1.ExportTraceS" +
      "erviceRequest\032B.opentelemetry.proto.coll" +
      "ector.trace.v1.ExportTraceServiceRespons" +
      "e\"\000B\257\001\n<com.bonree.integration.core.open" +
      "telemetry.collector.trace.v1B\021TraceServi" +
      "ceProtoP\001Z1go.opentelemetry.io/proto/otl" +
      "p/collector/trace/v1\252\002&OpenTelemetry.Pro" +
      "to.Collector.Trace.V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.bonree.integration.core.opentelemetry.trace.v1.TraceProto.getDescriptor(),
        });
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceRequest_descriptor,
        new String[] { "ResourceSpans", });
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_trace_v1_ExportTraceServiceResponse_descriptor,
        new String[] { "PartialSuccess", });
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTracePartialSuccess_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_opentelemetry_proto_collector_trace_v1_ExportTracePartialSuccess_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_trace_v1_ExportTracePartialSuccess_descriptor,
        new String[] { "RejectedSpans", "ErrorMessage", });
    com.bonree.integration.core.opentelemetry.trace.v1.TraceProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}