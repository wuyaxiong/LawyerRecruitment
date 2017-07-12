/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.guodian.entity;

/**
 * Auto-generated: 2017-06-03 16:52:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MyCenterBean {

    /**
     * code : 200
     * msg : 请求成功
     * infor : {"id":329,"userpic":"115.28.77.187/tp5/bbss/public/uploads/329/59433d01679b7.jpeg","username":"飞锐","sex":1,"introduction":"测试号","birth":"1991.02","background":"22221","cid":66,"label_id":1,"expert":0,"last_login_time":122112,"status":0,"phone":"17600382402","cname":"fifijedfedf","address":"fdfdfddd","is_header":1}
     */

    private int code;
    private String msg;
    private InforBean infor;
private int is_aite;

    public int getIs_aite() {
        return is_aite;
    }

    public void setIs_aite(int is_aite) {
        this.is_aite = is_aite;
    }

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
         * id : 329
         * userpic : 115.28.77.187/tp5/bbss/public/uploads/329/59433d01679b7.jpeg
         * username : 飞锐
         * sex : 1
         * introduction : 测试号
         * birth : 1991.02
         * background : 22221
         * cid : 66
         * label_id : 1
         * expert : 0
         * last_login_time : 122112
         * status : 0
         * phone : 17600382402
         * cname : fifijedfedf
         * address : fdfdfddd
         * is_header : 1
         */

        private int id;
        private String userpic;
        private String username;
        private int sex;
        private String introduction;
        private String birth;
        private String background;
        private int cid;
        private int label_id;
        private int expert;
        private int last_login_time;
        private int status;
        private String phone;
        private String cname;
        private String address;
        private int is_header;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getLabel_id() {
            return label_id;
        }

        public void setLabel_id(int label_id) {
            this.label_id = label_id;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
    }
}