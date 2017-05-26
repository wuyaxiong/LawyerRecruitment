/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.lawyer_recruitment.entity;
import java.util.List;

/**
 * Auto-generated: 2017-05-26 15:13:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ZhengCeTongZhiBean {

    private int code;
    private String msg;
    private List<ZhengCeTongZhiInfor> infor;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setInfor(List<ZhengCeTongZhiInfor> infor) {
         this.infor = infor;
     }
     public List<ZhengCeTongZhiInfor> getInfor() {
         return infor;
     }

}