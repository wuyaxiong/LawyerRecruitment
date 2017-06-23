package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by 郭新胜 on 2017/6/15.
 */

public class JuzhenBean {


    /**
     * code : 200
     * msg : 操作成功
     * infor : [{"cname":"国电电力发展股份有限公司","code":"二维码1","class":0},{"cname":"国电新疆电力有限公司","code":"二维码2","class":1},{"cname":"国电电力邯郸热电厂","code":"二维码3","class":1},{"cname":"国电电力大连开发区热电厂","code":"二维码4","class":1},{"cname":"国电内蒙古晶阳能源有限公司","code":"二维码5","class":1},{"cname":"国电电力河北新能源开发有限公司","code":"二维码6","class":1},{"cname":"国电浙江北仑第一发电有限公司","code":"二维码7","class":1},{"cname":"国电电力发展股份有限公司和禹水电开发公司","code":"二维码8","class":1},{"cname":"机关团支部","code":"","class":8},{"cname":"太平哨团支部","code":"","class":8},{"cname":"检修一部团支部","code":"","class":8},{"cname":"发电一部团支部","code":"","class":8},{"cname":"运行团支部","code":"","class":3},{"cname":"检修团支部","code":"","class":3},{"cname":"运行团总支","code":"","class":7},{"cname":"检修团支部","code":"","class":7},{"cname":"燃运团支部","code":"","class":7},{"cname":"输灰团支部","code":"","class":7},{"cname":"联合团支部","code":"","class":7},{"cname":"国电内蒙古东胜热电有限公司","code":"二维码10","class":1},{"cname":"发电部团支部","code":"","class":4},{"cname":"检修部团支部","code":"","class":4},{"cname":"机关团支部","code":"","class":4},{"cname":"共享中心支部","code":"","class":4},{"cname":"合成团支部","code":"","class":5},{"cname":"提纯团支部","code":"","class":5},{"cname":"还原团支部","code":"","class":5},{"cname":"尾气回收团支部","code":"","class":5},{"cname":"后处理团支部","code":"","class":5},{"cname":"质量团支部","code":"","class":5},{"cname":"设备团支部","code":"","class":5},{"cname":"技术团支部","code":"","class":5},{"cname":"经营团支部","code":"","class":5},{"cname":"管理团支部","code":"","class":5},{"cname":"机关团支部","code":"","class":6},{"cname":"康保团支部","code":"","class":6},{"cname":"雁门关团支部","code":"","class":6},{"cname":"崇礼团支部","code":"","class":6},{"cname":"管理一团支部","code":"","class":38},{"cname":"管理二团支部","code":"","class":38},{"cname":"发电部团支部","code":"","class":38},{"cname":"国电哈密煤电开发有限公司团委","code":"","class":2},{"cname":"国电新疆红雁池发电有限公司青年委员会","code":"","class":2},{"cname":"国电阿克苏河流域水电开发有限公司团委","code":"","class":2},{"cname":"国电新疆准东煤电有限公司青年工作委员会","code":"","class":2},{"cname":"国电新疆吉林台水电开发有限公司团委","code":"","class":2},{"cname":"国电新疆开都河流域水电开发有限公司团委","code":"","class":2},{"cname":"国电克拉玛依发电有限公司团委","code":"","class":2},{"cname":"国电库车发电有限公司团委","code":"","class":2},{"cname":"国电电力新疆新能源开发有限公司团委","code":"","class":2},{"cname":"国电青松库车矿业开发有限公司团支部","code":"","class":2}]
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
         * cname : 国电电力发展股份有限公司
         * code : 二维码1
         * class1 : 0
         */

        private String cname;
        private String code;

        private int classes;

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getClasses() {
            return classes;
        }

        public void setClasses(int classes) {
            this.classes = classes;
        }

        @Override
        public String toString() {
            return "InforBean{" +
                    "cname='" + cname + '\'' +
                    ", code='" + code + '\'' +
                    ", classes=" + classes +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JuzhenBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", infor=" + infor +
                '}';
    }
}
