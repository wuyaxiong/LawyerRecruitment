/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.guodian.entity;

/**
 * Auto-generated: 2017-06-13 14:23:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TieZiDetailBean {

    private int code;
    private String msg;
    private TieZiDetailInfor infor;
    private int is_collection;

    public int getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(int is_collection) {
        this.is_collection = is_collection;
    }

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

    public void setInfor(TieZiDetailInfor infor) {
         this.infor = infor;
     }
     public TieZiDetailInfor getInfor() {
         return infor;
     }

    @Override
    public String toString() {
        return "TieZiDetailBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", infor=" + infor +
                '}';
    }
}