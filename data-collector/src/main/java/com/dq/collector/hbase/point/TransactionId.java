package com.dq.collector.hbase.point;

@SuppressWarnings("unused")
public class TransactionId {
    private final String agentId;
    private final long agentStartTime;
    private final long transactionSequence;

    public TransactionId(String agentId, long agentStartTime, long transactionSequence) {
        this.agentId = agentId;
        this.agentStartTime = agentStartTime;
        this.transactionSequence = transactionSequence;
    }

    public String getAgentId() {
        return agentId;
    }

    public long getAgentStartTime() {
        return agentStartTime;
    }

    public long getTransactionSequence() {
        return transactionSequence;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionId that = (TransactionId) o;

        if (agentStartTime != that.agentStartTime) return false;
        if (transactionSequence != that.transactionSequence) return false;
        return agentId.equals(that.agentId);
    }

    @Override
    public int hashCode() {
        int result = agentId.hashCode();
        result = 31 * result + (int) (agentStartTime ^ (agentStartTime >>> 32));
        result = 31 * result + (int) (transactionSequence ^ (transactionSequence >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TransactionId{" + "agentId='" + agentId + '\'' +
                ", agentStartTime=" + agentStartTime +
                ", transactionSequence=" + transactionSequence +
                '}';
    }
}
