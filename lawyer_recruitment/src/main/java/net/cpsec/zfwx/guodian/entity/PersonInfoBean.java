package net.cpsec.zfwx.guodian.entity;

import com.alibaba.mobileim.contact.IYWContact;

import java.io.Serializable;

/**
 * Created by zhaobainian on 2017/6/13.
 */

public class PersonInfoBean implements Serializable {

    /**
     * code : 200
     * msg : 获取信息成功
     * infor : {"re":{"id":340,"username":"魏成","phone":"18811103740","userpic":null,"background":"黨員","cname":"国电","sex":1,"address":"北京","introduction":null,"birth":"199009"},"num":0}
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
         * re : {"id":340,"username":"魏成","phone":"18811103740","userpic":null,"background":"黨員","cname":"国电","sex":1,"address":"北京","introduction":null,"birth":"199009"}
         * num : 0
         */

        private ReBean re;
        private int num;

        public ReBean getRe() {
            return re;
        }

        public void setRe(ReBean re) {
            this.re = re;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public static class ReBean implements IYWContact{
            /**
             * id : 340
             * username : 魏成
             * phone : 18811103740
             * userpic : null
             * background : 黨員
             * cname : 国电
             * sex : 1
             * address : 北京
             * introduction : null
             * birth : 199009
             */

            private int id;
            private String username;
            private String phone;
            private String userpic;
            private String background;
            private String cname;
            private int sex;
            private String address;
            private Object introduction;
            private String birth;
            private String appKey;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUserpic() {
                return userpic;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getIntroduction() {
                return introduction;
            }

            public void setIntroduction(Object introduction) {
                this.introduction = introduction;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            @Override
            public String getUserId() {
                return id+"";
            }
            public void setAppKey(String appKey){
                this.appKey=appKey;
            }
            @Override
            public String getAppKey() {
                return appKey;
            }

            @Override
            public String getAvatarPath() {
                return userpic;
            }

            @Override
            public String getShowName() {
                return username;
            }
        }
    }
}
