package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class ZhengCeTongzhiDetailBean {

    /**
     * code : 200
     * msg : 操作成功
     * infor : {"comment_num":2,"notice_comment":[{"comment":"dfgfdgfdgghfhgh","time":145552,"image":null,"username":"gfdgg","userpic":"115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg","phone":"13081363699"},{"comment":"ghgfhgfhgfh","time":15555522,"image":null,"username":"陈一南","userpic":null,"phone":"15641447575"}],"notice_info":{"title":"高嵩出席央企助力青海持续健康发展座谈会，并赴青海分公司调研","time":1496735800,"comment":"　6月12日，集团公司党组成员、副总经理高嵩在西宁出席央企助力青海持续健康发展座谈会暨战略合作签约仪式，并赴国电青海分公司调研。\r\n　　在与青海分公司干部员工座谈时，高嵩强调，要继续深入学习习近平总书记系列重要讲话，深入领会精神实质与内涵，认真贯彻落实五大发展理念，自觉助力青海绿色创新发展。要更加自觉践行\u201c一五五\u201d战略，加强市场研究和政策研判，关注技术发展趋势，把握发展节奏，进一步提高企业发展质量。要提升员工能力，切实抓好安全文明生产标准化、精细化管理、市场营销、成本控制等生产经营工作，推动智能化光伏电站建设，提高存量资产经营水平。要切实抓好党建和党风廉政建设，落实全面从严治党，增加\u201c四个意识\u201d。要切实抓好精准扶贫工作，落实好惠民工程，助力全面建成小康社会。要加强学习培训，切实抓好干部员工队伍建设，做好各项工作，迎接党的十九大胜利召开。","image":"115.28.77.187/tp5/bbss/public/uploads/329/20170614163736.jpeg","cname":"国电电力发展股份有限公司"}}
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
         * comment_num : 2
         * notice_comment : [{"comment":"dfgfdgfdgghfhgh","time":145552,"image":null,"username":"gfdgg","userpic":"115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg","phone":"13081363699"},{"comment":"ghgfhgfhgfh","time":15555522,"image":null,"username":"陈一南","userpic":null,"phone":"15641447575"}]
         * notice_info : {"title":"高嵩出席央企助力青海持续健康发展座谈会，并赴青海分公司调研","time":1496735800,"comment":"　6月12日，集团公司党组成员、副总经理高嵩在西宁出席央企助力青海持续健康发展座谈会暨战略合作签约仪式，并赴国电青海分公司调研。\r\n　　在与青海分公司干部员工座谈时，高嵩强调，要继续深入学习习近平总书记系列重要讲话，深入领会精神实质与内涵，认真贯彻落实五大发展理念，自觉助力青海绿色创新发展。要更加自觉践行\u201c一五五\u201d战略，加强市场研究和政策研判，关注技术发展趋势，把握发展节奏，进一步提高企业发展质量。要提升员工能力，切实抓好安全文明生产标准化、精细化管理、市场营销、成本控制等生产经营工作，推动智能化光伏电站建设，提高存量资产经营水平。要切实抓好党建和党风廉政建设，落实全面从严治党，增加\u201c四个意识\u201d。要切实抓好精准扶贫工作，落实好惠民工程，助力全面建成小康社会。要加强学习培训，切实抓好干部员工队伍建设，做好各项工作，迎接党的十九大胜利召开。","image":"115.28.77.187/tp5/bbss/public/uploads/329/20170614163736.jpeg","cname":"国电电力发展股份有限公司"}
         */

        private int comment_num;
        private NoticeInfoBean notice_info;
        private List<NoticeCommentBean> notice_comment;

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public NoticeInfoBean getNotice_info() {
            return notice_info;
        }

        public void setNotice_info(NoticeInfoBean notice_info) {
            this.notice_info = notice_info;
        }

        public List<NoticeCommentBean> getNotice_comment() {
            return notice_comment;
        }

        public void setNotice_comment(List<NoticeCommentBean> notice_comment) {
            this.notice_comment = notice_comment;
        }

        public static class NoticeInfoBean {
            /**
             * title : 高嵩出席央企助力青海持续健康发展座谈会，并赴青海分公司调研
             * time : 1496735800
             * comment : 　6月12日，集团公司党组成员、副总经理高嵩在西宁出席央企助力青海持续健康发展座谈会暨战略合作签约仪式，并赴国电青海分公司调研。
             　　在与青海分公司干部员工座谈时，高嵩强调，要继续深入学习习近平总书记系列重要讲话，深入领会精神实质与内涵，认真贯彻落实五大发展理念，自觉助力青海绿色创新发展。要更加自觉践行“一五五”战略，加强市场研究和政策研判，关注技术发展趋势，把握发展节奏，进一步提高企业发展质量。要提升员工能力，切实抓好安全文明生产标准化、精细化管理、市场营销、成本控制等生产经营工作，推动智能化光伏电站建设，提高存量资产经营水平。要切实抓好党建和党风廉政建设，落实全面从严治党，增加“四个意识”。要切实抓好精准扶贫工作，落实好惠民工程，助力全面建成小康社会。要加强学习培训，切实抓好干部员工队伍建设，做好各项工作，迎接党的十九大胜利召开。
             * image : 115.28.77.187/tp5/bbss/public/uploads/329/20170614163736.jpeg
             * cname : 国电电力发展股份有限公司
             */

            private String title;
            private int time;
            private String comment;
            private String image;
            private String cname;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }
        }

        public static class NoticeCommentBean {
            /**
             * comment : dfgfdgfdgghfhgh
             * time : 145552
             * image : null
             * username : gfdgg
             * userpic : 115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg
             * phone : 13081363699
             */

            private String comment;
            private int time;
            private Object image;
            private String username;
            private String userpic;
            private String phone;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public Object getImage() {
                return image;
            }

            public void setImage(Object image) {
                this.image = image;
            }

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
        }
    }
}
