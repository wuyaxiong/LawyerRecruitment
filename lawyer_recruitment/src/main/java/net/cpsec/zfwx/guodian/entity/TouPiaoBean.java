package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by admina on 2017/6/14.
 */

public class TouPiaoBean {

    /**
     * code : 200
     * msg : 请求成功
     * infor : [{"vote_id":1,"admin_id":1,"title":"投票1","time":123,"description":"1"},{"vote_id":2,"admin_id":1,"title":"投票2","time":54321,"description":""}]
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
         * vote_id : 1
         * admin_id : 1
         * title : 投票1
         * time : 123
         * description : 1
         */

        private int vote_id;
        private int admin_id;
        private String title;
        private int time;
        private String description;

        public int getVote_id() {
            return vote_id;
        }

        public void setVote_id(int vote_id) {
            this.vote_id = vote_id;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "InforBean{" +
                    "vote_id=" + vote_id +
                    ", admin_id=" + admin_id +
                    ", title='" + title + '\'' +
                    ", time=" + time +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
