package net.cpsec.zfwx.lawyer_recruitment.entity;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class QartersList {
    private int quartersId;
    private String quartersName;
    private int technologyBranchId;

    public int getQuartersId() {
        return quartersId;
    }

    public void setQuartersId(int quartersId) {
        this.quartersId = quartersId;
    }

    public String getQuartersName() {
        return quartersName;
    }

    public void setQuartersName(String quartersName) {
        this.quartersName = quartersName;
    }

    public int getTechnologyBranchId() {
        return technologyBranchId;
    }

    public void setTechnologyBranchId(int technologyBranchId) {
        this.technologyBranchId = technologyBranchId;
    }

    @Override
    public String toString() {
        return "QartersList{" +
                "quartersId=" + quartersId +
                ", quartersName='" + quartersName + '\'' +
                ", technologyBranchId=" + technologyBranchId +
                '}';
    }
}
