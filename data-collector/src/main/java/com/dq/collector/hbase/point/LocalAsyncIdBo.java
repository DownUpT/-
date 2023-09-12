package com.dq.collector.hbase.point;

public class LocalAsyncIdBo {
    private final int asyncId;
    private final int sequence;

    public LocalAsyncIdBo(int asyncId, int sequence) {
        this.asyncId = asyncId;
        this.sequence = sequence;
    }

    public int getAsyncId() {
        return asyncId;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalAsyncIdBo)) return false;

        LocalAsyncIdBo that = (LocalAsyncIdBo) o;

        if (asyncId != that.asyncId) return false;
        return sequence == that.sequence;
    }

    @Override
    public int hashCode() {
        int result = asyncId;
        result = 31 * result + sequence;
        return result;
    }

    @Override
    public String toString() {
        return "LocalAsyncIdBo{" +
                "asyncId=" + asyncId +
                ", sequence=" + sequence +
                '}';
    }
}
