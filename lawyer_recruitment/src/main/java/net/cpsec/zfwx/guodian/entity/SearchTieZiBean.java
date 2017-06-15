package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/14.
 */

public class SearchTieZiBean {

    /**
     * code : 200
     * msg : 成功
     * infor : [{"username":"李鑫","userpic":"115.28.77.187/tp5/bbss/public/uploads/3/593e881b1c293.jpeg","phone":"15641446512","content":"156","title":"x","id":82,"time":1496986963,"image":"","praise":0,"comment":0,"collection":0,"label_id":3,"name":"风电技术"},{"username":"飞锐","userpic":"115.28.77.187/tp5/bbss/public/uploads/329/593a7db78687f.jpeg","phone":"17600382402","content":"333","title":"333","id":135,"time":1497250941,"image":"","praise":0,"comment":0,"collection":0,"label_id":3,"name":"风电技术"},{"username":"飞锐","userpic":"115.28.77.187/tp5/bbss/public/uploads/329/593a7db78687f.jpeg","phone":"17600382402","content":"过点","title":"啦啦啦","id":183,"time":1497407182,"image":"","praise":5,"comment":5,"collection":0,"label_id":3,"name":"风电技术"}]
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
         * username : 李鑫
         * userpic : 115.28.77.187/tp5/bbss/public/uploads/3/593e881b1c293.jpeg
         * phone : 15641446512
         * content : 156
         * title : x
         * id : 82
         * time : 1496986963
         * image :
         * praise : 0
         * comment : 0
         * collection : 0
         * label_id : 3
         * name : 风电技术
         */

        private String username;
        private String userpic;
        private String phone;
        private String content;
        private String title;
        private int id;
        private int time;
        private String image;
        private int praise;
        private int comment;
        private int collection;
        private int label_id;
        private String name;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getLabel_id() {
            return label_id;
        }

        public void setLabel_id(int label_id) {
            this.label_id = label_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
