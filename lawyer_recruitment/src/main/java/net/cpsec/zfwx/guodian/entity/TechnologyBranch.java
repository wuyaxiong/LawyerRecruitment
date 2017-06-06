package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class TechnologyBranch {
    private List<TechnologyBranchList> technologybranchs;
    private String description;
    private String flag;

    public List<TechnologyBranchList> getTechnologybranchs() {
        return technologybranchs;
    }

    public void setTechnologybranchs(List<TechnologyBranchList> technologybranchs) {
        this.technologybranchs = technologybranchs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "TechnologyBranch{" +
                "technologybranchs=" + technologybranchs +
                ", description='" + description + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
