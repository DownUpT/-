package com.dq.collector.hbase.point;

public class MayBeValue {
    private byte version = 0;
    private long parentSpanId;
    private long startTime;
    private int elapsed;
    private String rpc;
    private short serviceType;
    private String endPoint;
    private int apiId;
    private Short applicationServiceType;
    private String remoteAddr; // optional
    private String acceptorHost;

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getVersion() {
        return version;
    }

    public void setServiceType(short serviceType) {
        this.serviceType = serviceType;
    }

    public short getServiceType() {
        return serviceType;
    }

    public void setApplicationServiceType(short applicationServiceType) {
        this.applicationServiceType = applicationServiceType;
    }

    public short getApplicationServiceType() {
        return applicationServiceType;
    }

    public void setParentSpanId(long parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public long getParentSpanId() {
        return parentSpanId;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getRpc() {
        return rpc;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public int getApiId() {
        return apiId;
    }

    public void setAcceptorHost(String acceptorHost) {
        this.acceptorHost = acceptorHost;
    }

    public String getAcceptorHost() {
        return acceptorHost;
    }

    @Override
    public String toString() {
        return "MayBeValue{" +
                "version=" + version +
                ", parentSpanId=" + parentSpanId +
                ", startTime=" + startTime +
                ", elapsed=" + elapsed +
                ", rpc='" + rpc + '\'' +
                ", serviceType=" + serviceType +
                ", endPoint='" + endPoint + '\'' +
                ", apiId=" + apiId +
                ", applicationServiceType=" + applicationServiceType +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", acceptorHost='" + acceptorHost + '\'' +
                '}';
    }
}
