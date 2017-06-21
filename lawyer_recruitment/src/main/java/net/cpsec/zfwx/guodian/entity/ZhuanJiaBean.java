package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by lice on 2017/6/21.
 */

public class ZhuanJiaBean {

    /**
     * code : 200
     * msg : 操作成功
     * infor : [{"id":190,"username":"徐意超"},{"id":191,"username":"张耀鑫"},{"id":192,"username":"冯雷"},{"id":330,"username":"王红甫"}]
     */

    private int code;
    private String msg;
    private List<InforBean> infor;

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

    public List<InforBean> getInfor() {
        return infor;
    }

    public void setInfor(List<InforBean> infor) {
        this.infor = infor;
    }

    public static class InforBean {
        /**
         * id : 190
         * username : 徐意超
         */

        private int id;
        private String username;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
