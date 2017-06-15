package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by zhaobainian on 2017/6/14.
 */

public class GetFriendQueueInfoBean {

    /**
     * code : 200
     * msg : 获取全部成员列表成功
     * infor : [{"username":"于健健","userpic":"115.28.77.187/tp5/bbss/public/uploads/325/593f5f52f2d0b.jpeg","phone":"15313165551","name":"未分组好友","id":325},{"username":"翟帅","userpic":"115.28.77.187/tp5/bbss/public/uploads/327/5937bc01a14d3.jpeg","phone":"17600110213","name":"未分组好友","id":327},{"username":"曾经很纯","userpic":"115.28.77.187/tp5/bbss/public/uploads/326/593b6708c8322.jpeg","phone":"18617631652","name":"未分组好友","id":326}]
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
         * username : 于健健
         * userpic : 115.28.77.187/tp5/bbss/public/uploads/325/593f5f52f2d0b.jpeg
         * phone : 15313165551
         * name : 未分组好友
         * id : 325
         */

        private String username;
        private String userpic;
        private String phone;
        private String name;
        private int id;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
