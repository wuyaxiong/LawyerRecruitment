package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/28.
 */

public class MyWenDaBean {

    /**
     * code : 200
     * msg : 成功
     * infor : [{"id":293,"uid":333,"image":"","status":1,"title":"测试一专家","content":"测试专家","label_id":2,"time":1498618820,"praise":0,"collection":0,"comment":1,"is_hot":1,"classid":null,"cid":66,"eid":327,"is_ok":0,"is_report":0}]
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
         * id : 293
         * uid : 333
         * image :
         * status : 1
         * title : 测试一专家
         * content : 测试专家
         * label_id : 2
         * time : 1498618820
         * praise : 0
         * collection : 0
         * comment : 1
         * is_hot : 1
         * classid : null
         * cid : 66
         * eid : 327
         * is_ok : 0
         * is_report : 0
         */

        private int id;
        private int uid;
        private String image;
        private int status;
        private String title;
        private String content;
        private int label_id;
        private int time;
        private int praise;
        private int collection;
        private int comment;
        private int is_hot;
        private Object classid;
        private int cid;
        private int eid;
        private int is_ok;
        private int is_report;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLabel_id() {
            return label_id;
        }

        public void setLabel_id(int label_id) {
            this.label_id = label_id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public Object getClassid() {
            return classid;
        }

        public void setClassid(Object classid) {
            this.classid = classid;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public int getIs_ok() {
            return is_ok;
        }

        public void setIs_ok(int is_ok) {
            this.is_ok = is_ok;
        }

        public int getIs_report() {
            return is_report;
        }

        public void setIs_report(int is_report) {
            this.is_report = is_report;
        }

        @Override
        public String toString() {
            return "InforBean{" +
                    "id=" + id +
                    ", uid=" + uid +
                    ", image='" + image + '\'' +
                    ", status=" + status +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", label_id=" + label_id +
                    ", time=" + time +
                    ", praise=" + praise +
                    ", collection=" + collection +
                    ", comment=" + comment +
                    ", is_hot=" + is_hot +
                    ", classid=" + classid +
                    ", cid=" + cid +
                    ", eid=" + eid +
                    ", is_ok=" + is_ok +
                    ", is_report=" + is_report +
                    '}';
        }
    }

}
