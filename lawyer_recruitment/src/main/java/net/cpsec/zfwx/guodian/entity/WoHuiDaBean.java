package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/7/11.
 */

public class WoHuiDaBean {

    /**
     * code : 200
     * msg : 列表显示成功
     * infor : [[{"id":10,"uid":329,"image":"115.28.77.187/tp5/bbss/public/uploads/329/593660388dc7c.jpeg,115.28.77.187/tp5/bbss/public/uploads/329/593660388e159.jpeg","status":1,"title":"22222","content":"11111","label_id":2,"time":1496735800,"praise":10,"collection":0,"comment":0,"is_hot":1,"classid":null,"cid":1,"eid":null,"is_ok":1,"is_report":0,"name":"水电技术","username":"飞锐","userpic":"115.28.77.187/tp5/bbss/public/uploads/329/59634ac845599.jpeg","phone":"17600382402"}],[{"id":383,"uid":337,"image":"","status":1,"title":"今天下雨吗？","content":"下不下？","label_id":1,"time":1499304927,"praise":0,"collection":0,"comment":0,"is_hot":1,"classid":null,"cid":66,"eid":337,"is_ok":0,"is_report":0,"name":"火电技术","username":"白宇汉","userpic":"115.28.77.187/tp5/bbss/public/uploads/337/595224228df04.jpeg","phone":"13681100338"}],[{"id":409,"uid":333,"image":"","status":1,"title":"测试","content":"好好干","label_id":1,"time":1499421297,"praise":2,"collection":1,"comment":1,"is_hot":1,"classid":null,"cid":66,"eid":337,"is_ok":0,"is_report":0,"name":"火电技术","username":"han","userpic":"115.28.77.187/tp5/bbss/public/uploads/333/595dda915fae2.jpeg","phone":"15210191039"}],[{"id":412,"uid":333,"image":"","status":1,"title":"测试@","content":"11","label_id":1,"time":1499422144,"praise":2,"collection":1,"comment":1,"is_hot":1,"classid":null,"cid":66,"eid":337,"is_ok":0,"is_report":0,"name":"火电技术","username":"han","userpic":"115.28.77.187/tp5/bbss/public/uploads/333/595dda915fae2.jpeg","phone":"15210191039"}]]
     */

    private int code;
    private String msg;
    private List<List<InforBean>> infor;

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

    public List<List<InforBean>> getInfor() {
        return infor;
    }

    public void setInfor(List<List<InforBean>> infor) {
        this.infor = infor;
    }

    public static class InforBean {
        /**
         * id : 10
         * uid : 329
         * image : 115.28.77.187/tp5/bbss/public/uploads/329/593660388dc7c.jpeg,115.28.77.187/tp5/bbss/public/uploads/329/593660388e159.jpeg
         * status : 1
         * title : 22222
         * content : 11111
         * label_id : 2
         * time : 1496735800
         * praise : 10
         * collection : 0
         * comment : 0
         * is_hot : 1
         * classid : null
         * cid : 1
         * eid : null
         * is_ok : 1
         * is_report : 0
         * name : 水电技术
         * username : 飞锐
         * userpic : 115.28.77.187/tp5/bbss/public/uploads/329/59634ac845599.jpeg
         * phone : 17600382402
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
        private Object eid;
        private int is_ok;
        private int is_report;
        private String name;
        private String username;
        private String userpic;
        private String phone;

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

        public Object getEid() {
            return eid;
        }

        public void setEid(Object eid) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserpic() {
            return userpic;
        }

        public void setUserpic(String userpic) {
            this.userpic = userpic;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
