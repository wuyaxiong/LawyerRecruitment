package net.cpsec.zfwx.guodian.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class SanjiaBumenBean {


    /**
     * code : 200
     * msg : 请求成功
     * infor : [{"cid":60,"cname":"国电哈密煤电开发有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":61,"cname":"国电新疆红雁池发电有限公司青年委员会","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":62,"cname":"国电阿克苏河流域水电开发有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":63,"cname":"国电新疆准东煤电有限公司青年工作委员会","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":64,"cname":"国电新疆吉林台水电开发有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":65,"cname":"国电新疆开都河流域水电开发有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":66,"cname":"国电克拉玛依发电有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":67,"cname":"国电库车发电有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":68,"cname":"国电电力新疆新能源开发有限公司团委","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"},{"cid":69,"cname":"国电青松库车矿业开发有限公司团支部","image":null,"code":"","address":"新疆区","class":2,"smallname":"国电"}]
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
         * cid : 60
         * cname : 国电哈密煤电开发有限公司团委
         * image : null
         * code :
         * address : 新疆区
         * class : 2
         * smallname : 国电
         */

        private int cid;
        private String cname;
        private Object image;
        private String code;
        private String address;
        @SerializedName("class")
        private int classX;
        private String smallname;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getClassX() {
            return classX;
        }

        public void setClassX(int classX) {
            this.classX = classX;
        }

        public String getSmallname() {
            return smallname;
        }

        public void setSmallname(String smallname) {
            this.smallname = smallname;
        }
    }
}
