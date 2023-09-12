// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: metrics/v1/metrics.proto

package com.bonree.integration.core.opentelemetry.metrics.v1;

public interface MetricOrBuilder extends
    // @@protoc_insertion_point(interface_extends:opentelemetry.proto.metrics.v1.Metric)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * name of the metric, including its DNS name prefix. It must be unique.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  String getName();
  /**
   * <pre>
   * name of the metric, including its DNS name prefix. It must be unique.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * description of the metric, which can be used in documentation.
   * </pre>
   *
   * <code>string description = 2;</code>
   * @return The description.
   */
  String getDescription();
  /**
   * <pre>
   * description of the metric, which can be used in documentation.
   * </pre>
   *
   * <code>string description = 2;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <pre>
   * unit in which the metric value is reported. Follows the format
   * described by http://unitsofmeasure.org/ucum.html.
   * </pre>
   *
   * <code>string unit = 3;</code>
   * @return The unit.
   */
  String getUnit();
  /**
   * <pre>
   * unit in which the metric value is reported. Follows the format
   * described by http://unitsofmeasure.org/ucum.html.
   * </pre>
   *
   * <code>string unit = 3;</code>
   * @return The bytes for unit.
   */
  com.google.protobuf.ByteString
      getUnitBytes();

  /**
   * <code>.opentelemetry.proto.metrics.v1.Gauge gauge = 5;</code>
   * @return Whether the gauge field is set.
   */
  boolean hasGauge();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Gauge gauge = 5;</code>
   * @return The gauge.
   */
  Gauge getGauge();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Gauge gauge = 5;</code>
   */
  GaugeOrBuilder getGaugeOrBuilder();

  /**
   * <code>.opentelemetry.proto.metrics.v1.Sum sum = 7;</code>
   * @return Whether the sum field is set.
   */
  boolean hasSum();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Sum sum = 7;</code>
   * @return The sum.
   */
  Sum getSum();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Sum sum = 7;</code>
   */
  SumOrBuilder getSumOrBuilder();

  /**
   * <code>.opentelemetry.proto.metrics.v1.Histogram histogram = 9;</code>
   * @return Whether the histogram field is set.
   */
  boolean hasHistogram();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Histogram histogram = 9;</code>
   * @return The histogram.
   */
  Histogram getHistogram();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Histogram histogram = 9;</code>
   */
  HistogramOrBuilder getHistogramOrBuilder();

  /**
   * <code>.opentelemetry.proto.metrics.v1.ExponentialHistogram exponential_histogram = 10;</code>
   * @return Whether the exponentialHistogram field is set.
   */
  boolean hasExponentialHistogram();
  /**
   * <code>.opentelemetry.proto.metrics.v1.ExponentialHistogram exponential_histogram = 10;</code>
   * @return The exponentialHistogram.
   */
  ExponentialHistogram getExponentialHistogram();
  /**
   * <code>.opentelemetry.proto.metrics.v1.ExponentialHistogram exponential_histogram = 10;</code>
   */
  ExponentialHistogramOrBuilder getExponentialHistogramOrBuilder();

  /**
   * <code>.opentelemetry.proto.metrics.v1.Summary summary = 11;</code>
   * @return Whether the summary field is set.
   */
  boolean hasSummary();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Summary summary = 11;</code>
   * @return The summary.
   */
  Summary getSummary();
  /**
   * <code>.opentelemetry.proto.metrics.v1.Summary summary = 11;</code>
   */
  SummaryOrBuilder getSummaryOrBuilder();

  public Metric.DataCase getDataCase();
}
