package net.cpsec.zfwx.lawyer_recruitment.entity;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class TechnologyBranchList {
    private int technologyBranchId;
    private String technologyBranchName;
    private int technologyId;

    public int getTechnologyBranchId() {
        return technologyBranchId;
    }

    public void setTechnologyBranchId(int technologyBranchId) {
        this.technologyBranchId = technologyBranchId;
    }

    public String getTechnologyBranchName() {
        return technologyBranchName;
    }

    public void setTechnologyBranchName(String technologyBranchName) {
        this.technologyBranchName = technologyBranchName;
    }

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }

    @Override
    public String toString() {
        return "TechnologyBranchList{" +
                "technologyBranchId=" + technologyBranchId +
                ", technologyBranchName='" + technologyBranchName + '\'' +
                ", technologyId=" + technologyId +
                '}';
    }
}
