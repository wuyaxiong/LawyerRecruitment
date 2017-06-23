package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class HuoDongFenXiang {

    /**
     * code : 200
     * msg : 请求列表成功
     * infor : [{"userpic":null,"username":"王栋晖","id":4,"num":0,"uid":9,"title":"回复解决方法加大力度奋斗奋斗奋斗的地方负担","image":"115.28.77.187/tp5/bbss/public/uploads/329/20170614164815.jpeg,115.28.77.187/tp5/bbss/public/uploads/329/20170614164815.jpeg","time":1495673580,"content":"法国德国公开公开热裤高热量，汇入肉肉日日天日认购我如果认购日加热我热你日如题让他热痛热帖今天热日。欧日肉肉我日日日突然刮刮卡破天公开普通股票提供图片个人股热让他人体人挤人通融通融突然提高人体感染突然   ","is_share":1,"comments":1}]
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
         * userpic : null
         * username : 王栋晖
         * id : 4
         * num : 0
         * uid : 9
         * title : 回复解决方法加大力度奋斗奋斗奋斗的地方负担
         * image : 115.28.77.187/tp5/bbss/public/uploads/329/20170614164815.jpeg,115.28.77.187/tp5/bbss/public/uploads/329/20170614164815.jpeg
         * time : 1495673580
         * content : 法国德国公开公开热裤高热量，汇入肉肉日日天日认购我如果认购日加热我热你日如题让他热痛热帖今天热日。欧日肉肉我日日日突然刮刮卡破天公开普通股票提供图片个人股热让他人体人挤人通融通融突然提高人体感染突然
         * is_share : 1
         * comments : 1
         */

        private Object userpic;
        private String username;
        private int id;
        private int num;
        private int uid;
        private String title;
        private String image;
        private int time;
        private String content;
        private int is_share;
        private int comments;

        public Object getUserpic() {
            return userpic;
        }

        public void setUserpic(Object userpic) {
            this.userpic = userpic;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIs_share() {
            return is_share;
        }

        public void setIs_share(int is_share) {
            this.is_share = is_share;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }
    }
}
