package net.cpsec.zfwx.guodian.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class ERGongsiBean {

    /**
     * code : 200
     * msg : 请求成功
     * infor : [{"cid":2,"cname":"国电新疆电力有限公司","image":"jQuery.jpg","code":"二维码2","address":"新疆区","class":1,"smallname":"国电"},{"cid":3,"cname":"国电电力邯郸热电厂","image":"QWER.JPG","code":"二维码3","address":"河北省邯郸市电厂街30号","class":1,"smallname":"国电"},{"cid":4,"cname":"国电电力大连开发区热电厂","image":"gfgf.JPG","code":"二维码4","address":"大连市开发区钢铁路90号","class":1,"smallname":"国电"},{"cid":5,"cname":"国电内蒙古晶阳能源有限公司","image":"ijdd.jpg","code":"二维码5","address":"内蒙古自治区鄂尔多斯市准格尔旗大路煤化工基地东工业园区","class":1,"smallname":"国电"},{"cid":6,"cname":"国电电力河北新能源开发有限公司","image":"gfg.jpg","code":"二维码6","address":"河北区","class":1,"smallname":"国电"},{"cid":7,"cname":"国电浙江北仑第一发电有限公司","image":"ddfvd.jpg","code":"二维码7","address":"浙江省宁波市北仑区进港西路66号","class":1,"smallname":"国电"},{"cid":8,"cname":"国电电力发展股份有限公司和禹水电开发公司","image":"dfsdf.jpg","code":"二维码8","address":"辽宁省本溪市桓仁县沿河街167号","class":1,"smallname":"国电"},{"cid":38,"cname":"国电内蒙古东胜热电有限公司","image":"dfsfds.jpg","code":"二维码10","address":"内蒙古东胜","class":1,"smallname":"国电"}]
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
         * cid : 2
         * cname : 国电新疆电力有限公司
         * image : jQuery.jpg
         * code : 二维码2
         * address : 新疆区
         * class : 1
         * smallname : 国电
         */

        private int cid;
        private String cname;
        private String image;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
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
