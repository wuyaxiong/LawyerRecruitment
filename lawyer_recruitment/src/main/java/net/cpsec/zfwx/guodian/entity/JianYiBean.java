/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.guodian.entity;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2017-05-26 10:59:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JianYiBean implements Serializable{

    private int code;
    private String msg;
    private List<JianYiInfor> infor;
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

    public void setInfor(List<JianYiInfor> infor) {
         this.infor = infor;
     }
     public List<JianYiInfor> getInfor() {
         return infor;
     }

}