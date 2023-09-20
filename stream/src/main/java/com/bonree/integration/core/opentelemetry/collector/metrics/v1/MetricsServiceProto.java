// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: collector/metrics/v1/metrics_service.proto

package com.bonree.integration.core.opentelemetry.collector.metrics.v1;

public final class MetricsServiceProto {
  private MetricsServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsPartialSuccess_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsPartialSuccess_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n*collector/metrics/v1/metrics_service.p" +
      "roto\022(opentelemetry.proto.collector.metr" +
      "ics.v1\032\030metrics/v1/metrics.proto\"h\n\033Expo" +
      "rtMetricsServiceRequest\022I\n\020resource_metr" +
      "ics\030\001 \003(\0132/.opentelemetry.proto.metrics." +
      "v1.ResourceMetrics\"~\n\034ExportMetricsServi" +
      "ceResponse\022^\n\017partial_success\030\001 \001(\0132E.op" +
      "entelemetry.proto.collector.metrics.v1.E" +
      "xportMetricsPartialSuccess\"R\n\033ExportMetr" +
      "icsPartialSuccess\022\034\n\024rejected_data_point" +
      "s\030\001 \001(\003\022\025\n\rerror_message\030\002 \001(\t2\254\001\n\016Metri" +
      "csService\022\231\001\n\006Export\022E.opentelemetry.pro" +
      "to.collector.metrics.v1.ExportMetricsSer" +
      "viceRequest\032F.opentelemetry.proto.collec" +
      "tor.metrics.v1.ExportMetricsServiceRespo" +
      "nse\"\000B\267\001\n>com.bonree.integration.core.op" +
      "entelemetry.collector.metrics.v1B\023Metric" +
      "sServiceProtoP\001Z3go.opentelemetry.io/pro" +
      "to/otlp/collector/metrics/v1\252\002(OpenTelem" +
      "etry.Proto.Collector.Metrics.V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.bonree.integration.core.opentelemetry.metrics.v1.MetricsProto.getDescriptor(),
        });
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceRequest_descriptor,
        new String[] { "ResourceMetrics", });
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsServiceResponse_descriptor,
        new String[] { "PartialSuccess", });
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsPartialSuccess_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsPartialSuccess_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_metrics_v1_ExportMetricsPartialSuccess_descriptor,
        new String[] { "RejectedDataPoints", "ErrorMessage", });
    com.bonree.integration.core.opentelemetry.metrics.v1.MetricsProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}