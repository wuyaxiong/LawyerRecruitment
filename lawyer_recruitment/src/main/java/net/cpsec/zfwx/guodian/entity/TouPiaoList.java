package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/20.
 */

public class TouPiaoList {


    /**
     * code : 200
     * msg : 成功
     * infor : [{"vote_id":1,"title":"最美国电人","time":1496735800,"last_time":0,"count":1},{"vote_id":2,"title":"发表意见","time":1496735800,"last_time":0,"count":0},{"vote_id":3,"title":"投票3","time":6789,"last_time":0,"count":0}]
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
         * title : 最美国电人
         * time : 1496735800
         * last_time : 0
         * count : 1
         */

        private int vote_id;
        private String title;
        private int time;
        private int last_time;
        private int count;

        public int getVote_id() {
            return vote_id;
        }

        public void setVote_id(int vote_id) {
            this.vote_id = vote_id;
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

        public int getLast_time() {
            return last_time;
        }

        public void setLast_time(int last_time) {
            this.last_time = last_time;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
