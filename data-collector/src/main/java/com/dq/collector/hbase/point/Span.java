package com.dq.collector.hbase.point;

public class Span {
    String applicationID;
    String agentId;
    long agentStartTime;
    LocalAsyncIdBo localAsyncIdBo;

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public long getAgentStartTime() {
        return agentStartTime;
    }

    public void setAgentStartTime(long agentStartTime) {
        this.agentStartTime = agentStartTime;
    }

    public LocalAsyncIdBo getLocalAsyncIdBo() {
        return localAsyncIdBo;
    }

    public void setLocalAsyncIdBo(LocalAsyncIdBo localAsyncIdBo) {
        this.localAsyncIdBo = localAsyncIdBo;
    }

    @Override
    public String toString() {
        return "Span{" +
                "applicationID='" + applicationID + '\'' +
                ", agentId='" + agentId + '\'' +
                ", agentStartTime=" + agentStartTime +
                ", localAsyncIdBo=" + localAsyncIdBo +
                '}';
    }
}
