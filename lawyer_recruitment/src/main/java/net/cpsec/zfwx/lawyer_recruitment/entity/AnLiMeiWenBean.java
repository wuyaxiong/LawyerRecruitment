/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.lawyer_recruitment.entity;
import java.util.List;

/**
 * Auto-generated: 2017-05-26 16:11:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AnLiMeiWenBean {

    private int code;
    private String msg;
    private List<AnLiMeiWenInfor> infor;
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

    public void setInfor(List<AnLiMeiWenInfor> infor) {
         this.infor = infor;
     }
     public List<AnLiMeiWenInfor> getInfor() {
         return infor;
     }

}