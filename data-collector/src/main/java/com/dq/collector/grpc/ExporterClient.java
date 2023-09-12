package com.dq.collector.grpc;

public class ExporterClient {
//
//    private final ManagedChannel channel;
//    private final MetricExportServiceGrpc.MetricExportServiceStub blockingStub;
//
//
//    public ExporterClient(String host, int port) {
//        this.channel = ManagedChannelBuilder.forAddress(host, port)
//                .usePlaintext()
//                .maxInboundMetadataSize(Integer.MAX_VALUE)
//                .build();
//        blockingStub = MetricExportServiceGrpc.newStub(channel);
//    }
//
//    public static void main(String[] args) {
//        ExporterClient client = new ExporterClient("localhost", 50051);
//        System.out.println("====client: " + client);
//        client.subscription();
//        client.export();
//        client.close();
//    }
//
//    public void export() {
//        StreamObserver<ExportMetricValue> export = blockingStub.export(new StreamObserver<ExportResponse>() {
//            @Override
//            public void onNext(ExportResponse value) {
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//        });
//        export.onNext(ExportMetricValue.newBuilder()
//                .setMetricName("metricName")
//                .setTimeBucket(System.currentTimeMillis())
//                .setLongValue(1)
//                .setTypeValue(ValueType.LONG_VALUE)
//                .build());
//    }
//
//    public void subscription() {
//        blockingStub.subscription(SubscriptionReq.getDefaultInstance(),
//                new StreamObserver<SubscriptionsResp>() {
//                    @Override
//                    public void onNext(SubscriptionsResp value) {
//                        System.out.println("onNext: " + value);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        System.out.println("onError: " + t);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("onCompleted");
//                    }
//                });
//    }
//
//    public void close() {
//        try {
//            channel.shutdown().awaitTermination(2, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
