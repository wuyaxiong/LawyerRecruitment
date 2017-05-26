/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.lawyer_recruitment.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2017-05-25 9:26:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ShengBean implements Serializable {

    private int code;
    private String msg;
    private List<ShengDetail> infor;

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

    public List<ShengDetail> getInfor() {
        return infor;
    }

    public void setInfor(List<ShengDetail> infor) {
        this.infor = infor;
    }
}