package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by lice on 2017/6/16.
 */

//个人中心的我回复过的帖子的实体类，个人中心的帖子都可以用这个类
public class HuiFuBean {


    /**
     * code : 200
     * msg : 成功
     * infor : [{"id":3,"uid":3,"image":"115.28.77.187/tp5/bbss/public/uploads/3/5938a28c7d4a5.jpeg","status":0,"title":"哈哈哈","content":"@李鑫","label_id":1,"time":1496883852,"praise":0,"collection":0,"comment":0,"is_hot":1,"classid":null,"cid":9,"eid":null,"is_ok":1,"is_report":0,"userpic":"115.28.77.187/tp5/bbss/public/uploads/3/593e881b1c293.jpeg","username":"李鑫","sex":1,"introduction":"工人","birth":"1987.03","background":"党员","expert":0,"last_login_time":0,"phone":"15641446512","cname":"国电东胜公司","address":"东胜","is_header":1,"name":"火电技术"},{"id":329,"uid":329,"image":"","status":1,"title":"changwenben","content":"111111111111111111111112131313sd1f31asd3f132ads1f3a1sd32f1as3df1a2sd1f3as1df31asd3f1a32sd1f32as1df321asd3f13asd1f32a1sd32f1a32sd1f32a1sdf321a32sd1f3a1sd3f1a3sd1f3a1sd3f1as3d2f132asd1f3a1sd3f1a3sd1f32a1sdf31asd3f13a2d1f32a1df321a3sd2f123asd1f32asd1f23ad1f2a1sdf3as1df3a1sdf31ads1fasdf4asd4fasdf6asd46asd465asd4f65a4sd6f4asd6f4ads65f46as5df465sd4f65asd4f65asd4f65a4dsf6asd4f6a5sd4f6asd4f65asd4f6asd4f65asd4f65asd4f6as5d4f6ads4f65asd4f6adsf4a6dsf4asd65f4asd56f46asdf46asd5f4s56df46asd5f4a6sd5f46asd4f6a5sd465ads4f65asd4f65asd4f65ads4f6ads4f6a5ds4f56ads4f65asd4f65a4df6a4sdf64asd6f4asd6f54asd6f4as6df46ads4f6asd4f6as464d6sf4a6d4f6ad4f6as4df6a4sd6f4ads6f4a6sdf4a6sdf46a5sd4f6asd46asd4f6asd4f6asd4f65asd4f6a5sd4f65asd4f6as4df6asd4f6a4sd6f4asdf64asd6f4a5sdf4ads5fa4sd6f4as6df4asd6f4as6d5f4a6sd5f46a5sdf465sd4f6as5d4f65as4df6ds4f65asd4f6asd4f654df65ad4f65a4dsf65a4sdfa56sdf46asd4f65asd4f6a5sd4f65asdf465asdf456asd4f65asd4f56asd4f56asd4f56asd4f6as4df65sd4fasd45f6ads4f6as5df465asd4f65asd4f65ads4f4adsf65a4sd6fasd46fa65sdf4asd56f4asd56f46a5sdf465sd4f5asd4f6a5ds4f65asd4f65asd4f5ad6f46a5sd4f6a5ds4f65as4df65as4df65asd4f65as4df65a4dsf65a4dsf654asd6f54asd6f4as6d5f4a6sd5f46a5sdf46asd4f65asd4f65as4df6asd4f6asd4f6a4sdf64asdf654asd6f4as6d5f46sd5f46as5d4f6a5sd4f6a5sd4f65asd4f6a5sd4f65asd4f6as4df6as4df6a4sdf654asd65f4as6d5f4a6ds4fa65sdf4asd4f6ad4sf6asdf6","label_id":2,"time":1496887347,"praise":0,"collection":0,"comment":0,"is_hot":1,"classid":null,"cid":66,"eid":null,"is_ok":1,"is_report":0,"userpic":"115.28.77.187/tp5/bbss/public/uploads/329/59433d01679b7.jpeg","username":"飞锐","sex":1,"introduction":"测试号","birth":"1991.02","background":"22221","expert":0,"last_login_time":122112,"phone":"17600382402","cname":"fifijedfedf","address":"fdfdfddd","is_header":1,"name":"水电技术"}]
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
         * id : 3
         * uid : 3
         * image : 115.28.77.187/tp5/bbss/public/uploads/3/5938a28c7d4a5.jpeg
         * status : 0
         * title : 哈哈哈
         * content : @李鑫
         * label_id : 1
         * time : 1496883852
         * praise : 0
         * collection : 0
         * comment : 0
         * is_hot : 1
         * classid : null
         * cid : 9
         * eid : null
         * is_ok : 1
         * is_report : 0
         * userpic : 115.28.77.187/tp5/bbss/public/uploads/3/593e881b1c293.jpeg
         * username : 李鑫
         * sex : 1
         * introduction : 工人
         * birth : 1987.03
         * background : 党员
         * expert : 0
         * last_login_time : 0
         * phone : 15641446512
         * cname : 国电东胜公司
         * address : 东胜
         * is_header : 1
         * name : 火电技术
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
        private String userpic;
        private String username;
        private int sex;
        private String introduction;
        private String birth;
        private String background;
        private int expert;
        private int last_login_time;
        private String phone;
        private String cname;
        private String address;
        private int is_header;
        private String name;

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

        public String getUserpic() {
            return userpic;
        }

        public void setUserpic(String userpic) {
            this.userpic = userpic;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public int getExpert() {
            return expert;
        }

        public void setExpert(int expert) {
            this.expert = expert;
        }

        public int getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(int last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_header() {
            return is_header;
        }

        public void setIs_header(int is_header) {
            this.is_header = is_header;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
                    ", userpic='" + userpic + '\'' +
                    ", username='" + username + '\'' +
                    ", sex=" + sex +
                    ", introduction='" + introduction + '\'' +
                    ", birth='" + birth + '\'' +
                    ", background='" + background + '\'' +
                    ", expert=" + expert +
                    ", last_login_time=" + last_login_time +
                    ", phone='" + phone + '\'' +
                    ", cname='" + cname + '\'' +
                    ", address='" + address + '\'' +
                    ", is_header=" + is_header +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HuiFuBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", infor=" + infor +
                '}';
    }
}
