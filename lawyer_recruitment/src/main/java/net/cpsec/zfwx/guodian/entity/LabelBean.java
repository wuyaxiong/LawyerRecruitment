package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/12.
 */

public class LabelBean {
    private int code;
    private String msg;
    private List<Label> infor;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Label> getInfor() {
        return infor;
    }

    public void setInfor(List<Label> infor) {
        this.infor = infor;
    }


}

