package net.cpsec.zfwx.guodian.entity;

/**
 * Created by szh on 2017/6/20.
 */

public class DianZanBean {

    /**
     * code : 200
     * msg : 点赞成功，+1成功
     * infor : {"res":0,"res1":1,"article_result":1}
     */

    private int code;
    private String msg;
    private InforBean infor;

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

    public InforBean getInfor() {
        return infor;
    }

    public void setInfor(InforBean infor) {
        this.infor = infor;
    }

    public static class InforBean {
        /**
         * res : 0
         * res1 : 1
         * article_result : 1
         */

        private int res;
        private int res1;
        private int article_result;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public int getRes1() {
            return res1;
        }

        public void setRes1(int res1) {
            this.res1 = res1;
        }

        public int getArticle_result() {
            return article_result;
        }

        public void setArticle_result(int article_result) {
            this.article_result = article_result;
        }
    }
}
