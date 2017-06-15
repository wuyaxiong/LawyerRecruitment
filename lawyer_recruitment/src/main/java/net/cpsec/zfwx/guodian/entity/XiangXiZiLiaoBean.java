/**
 * Copyright 2017 bejson.com
 */
package net.cpsec.zfwx.guodian.entity;

/**
 * Auto-generated: 2017-06-14 15:37:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class XiangXiZiLiaoBean {

    private int code;
    private String msg;
    private Infor infor;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setInfor(Infor infor) {
        this.infor = infor;
    }

    public Infor getInfor() {
        return infor;
    }

    public class Infor {

        private Re re;
        private int num;

        public void setRe(Re re) {
            this.re = re;
        }

        public Re getRe() {
            return re;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public class Re {

            private int id;
            private String username;
            private String phone;
            private String userpic;
            private String background;
            private String cname;
            private int sex;
            private String address;
            private String introduction;
            private String birth;

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUsername() {
                return username;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhone() {
                return phone;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }

            public String getUserpic() {
                return userpic;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getBackground() {
                return background;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getCname() {
                return cname;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getSex() {
                return sex;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddress() {
                return address;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public String getBirth() {
                return birth;
            }

        }
    }
}