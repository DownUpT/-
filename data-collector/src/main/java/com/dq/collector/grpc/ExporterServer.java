package com.dq.collector.grpc;

public class ExporterServer {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExporterServer.class);
//    private Server server;
//
//    private void start() throws IOException {
//        int port = 50051;
//        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
//                .addService(new MetricExportServiceImpl())
//                .build()
//                .start();
//        LOGGER.info("Server started, listening on " + port);
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
//            LOGGER.info("*** shutting down gRPC server since JVM is shutting down");
//            try {
//                ExporterServer.this.stop();
//            } catch (InterruptedException e) {
//                e.printStackTrace(System.err);
//            }
//            LOGGER.info("*** server shut down");
//        }));
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        ExporterServer server = new ExporterServer();
//        server.start();
//        server.blockUntilShutdown();
//    }
//
//    private void stop() throws InterruptedException {
//        if (server != null) {
//            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
//        }
//    }
//
//    private void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//    }
//
//
//    static class MetricExportServiceImpl extends MetricExportServiceGrpc.MetricExportServiceImplBase {
//
//
//        @Override
//        public StreamObserver<ExportMetricValue> export(StreamObserver<ExportResponse> responseObserver) {
//            return new StreamObserver<ExportMetricValue>() {
//                @Override
//                public void onNext(ExportMetricValue value) {
//                    LOGGER.info("=========================================");
//                    LOGGER.info("指标名称:  {}.", value.getMetricName());
//                    LOGGER.info("实体名称:  {}.", value.getEntityName());
//                    LOGGER.info("指标时间:  {}.", value.getTimeBucket());
//                    if (value.getType() == ValueType.DOUBLE) {
//                        LOGGER.info("指标值:  {}.", value.getDoubleValue());
//                    } else {
//                        LOGGER.info("指标值:  {}.", value.getLongValue());
//                    }
//                    LOGGER.info("=========================================");
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    LOGGER.error(t.getMessage(), t);
//                }
//
//                @Override
//                public void onCompleted() {
//                    responseObserver.onNext(ExportResponse.getDefaultInstance());
//                    responseObserver.onCompleted();
//                }
//            };
//        }
//
//        @Override
//        public void subscription(SubscriptionReq request, StreamObserver<SubscriptionsResp> responseObserver) {
//            SubscriptionMetric build = SubscriptionMetric.newBuilder()
//                    .setMetricName("test")
//                    .setEventType(EventType.INCREMENT)
//                    .build();
//            responseObserver.onNext(SubscriptionsResp.newBuilder().addMetrics(build).build());
//            responseObserver.onCompleted();
//        }
//    }
}
