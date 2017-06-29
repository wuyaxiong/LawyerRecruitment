package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by lice on 2017/6/16.
 */

//个人中心的我回复过的帖子的实体类，个人中心的帖子都可以用这个类
public class ShouCangBean {

    /**
     * code : 200
     * msg : 成功
     * infor : [{"id":52,"title":"安卓测试","image":"115.28.77.187/tp5/bbss/public/uploads/3/5937a1318ba27.jpeg","content":"1","comment":11,"praise":3,"time":1496817969,"collection":0,"username":"gfdgg","userpic":"115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg","phone":"13081363699","name":"火电技术"},{"id":70,"title":"图片一","image":"115.28.77.187/tp5/bbss/public/uploads/3/5938bcd6ae9fe.jpeg","content":"阿坝","comment":0,"praise":2,"time":1496890582,"collection":0,"username":"李鑫","userpic":"115.28.77.187/tp5/bbss/public/uploads/3/59439b8fd828e.jpeg","phone":"15641446512","name":"火电技术"},{"id":204,"title":"111","image":"","content":"3333","comment":0,"praise":5,"time":1497443300,"collection":0,"username":"飞锐","userpic":"","phone":"17600382402","name":"火电技术"},{"id":206,"title":"sss","image":"","content":"Dddd","comment":14,"praise":1,"time":1497444115,"collection":0,"username":"飞锐","userpic":"","phone":"17600382402","name":"火电技术"},{"id":226,"title":"hi","image":"115.28.77.187/tp5/bbss/public/uploads/329/4057e04261612f0dbd482d2e42f8eee8.jpeg","content":"hi","comment":12,"praise":1,"time":1497856668,"collection":0,"username":"飞锐","userpic":"","phone":"17600382402","name":"火电技术"},{"id":297,"title":"@外部专家，翟帅","image":"115.28.77.187/tp5/bbss/public/uploads/330/eb9585235c8ae6f5e2790565eeb8f1d1.jpeg","content":"74646478244744","comment":1,"praise":1,"time":1498633765,"collection":0,"username":"王红甫","userpic":null,"phone":"18601979290","name":"风电技术"},{"id":270,"title":"版本更新提示","image":"115.28.77.187/tp5/bbss/public/uploads/330/9a1c948d0502a91e40e30b7fceb793c7.jpeg","content":"AAP Store已经更新版本1.0.18，下一个版本纠正类别为社交，谢谢！","comment":14,"praise":2,"time":1498007560,"collection":0,"username":"王红甫","userpic":null,"phone":"18601979290","name":"闪电技术"}]
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
         * id : 52
         * title : 安卓测试
         * image : 115.28.77.187/tp5/bbss/public/uploads/3/5937a1318ba27.jpeg
         * content : 1
         * comment : 11
         * praise : 3
         * time : 1496817969
         * collection : 0
         * username : gfdgg
         * userpic : 115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg
         * phone : 13081363699
         * name : 火电技术
         */

        private int id;
        private String title;
        private String image;
        private String content;
        private int comment;
        private int praise;
        private int time;
        private int collection;
        private String username;
        private String userpic;
        private String phone;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
