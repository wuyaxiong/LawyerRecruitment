package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class ZhengCeTongzhiDetailBean {
    /**
     * code : 200
     * msg : 操作成功
     * infor : {"is_collection":0,"comment_num":2,"notice_comment":[{"comment":"Sw2s2ws","time":1497526092,"image":null,"username":"天霸动霸tua","userpic":"115.28.77.187/tp5/bbss/public/uploads/325/59489caa62049.jpeg","phone":"15313165551"},{"comment":"111","time":1498495508,"image":null,"username":"林卫龙","userpic":null,"phone":"13399711835"}],"notice_info":{"title":"乔保平会见埃塞俄比亚总理","time":1496735800,"comment":"5月13日，集团公司党组书记、董事长乔保平在京会见了来华参加\u201c一带一路\u201d国际合作高峰论坛的埃塞俄比亚总理海尔马里亚姆·德萨莱尼一行。集团公司党组成员、副总经理高嵩，埃塞俄比亚外交部部长等陪同会见。\r\n　　乔保平向海尔马里亚姆·德萨莱尼介绍了国电集团基本情况，着重介绍了集团公司新能源发展特色，特别是践行\u201c一带一路\u201d倡议和海外\u201c走出去\u201d情况，以及国电工作组赴埃塞俄比亚调研情况。希望埃塞俄比亚政府在投资、税收及电价政策等方面给予大力支持，共同开发可再生能源。\r\n　　海尔马里亚姆·德萨莱尼对国电集团在新能源领域取得的成就表示赞赏，并介绍了埃塞俄比亚风资源情况。他表示，国电集团拥有在非洲开发建设风电项目的成熟经验，埃塞俄比亚政府已确定将国电集团列为其战略合作伙伴，将在投资开发等方面提供政策支持。欢迎国电集团到埃塞俄比亚开发建设风电项目，为非洲人民带去更加清洁、高效的能源。\r\n　　埃塞俄比亚电力公司总经理、集团公司国际合作与海外业务部有关负责同志参加会见。","image":null,"cname":"国电电力发展股份有限公司"}}
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
         * is_collection : 0
         * comment_num : 2
         * notice_comment : [{"comment":"Sw2s2ws","time":1497526092,"image":null,"username":"天霸动霸tua","userpic":"115.28.77.187/tp5/bbss/public/uploads/325/59489caa62049.jpeg","phone":"15313165551"},{"comment":"111","time":1498495508,"image":null,"username":"林卫龙","userpic":null,"phone":"13399711835"}]
         * notice_info : {"title":"乔保平会见埃塞俄比亚总理","time":1496735800,"comment":"5月13日，集团公司党组书记、董事长乔保平在京会见了来华参加\u201c一带一路\u201d国际合作高峰论坛的埃塞俄比亚总理海尔马里亚姆·德萨莱尼一行。集团公司党组成员、副总经理高嵩，埃塞俄比亚外交部部长等陪同会见。\r\n　　乔保平向海尔马里亚姆·德萨莱尼介绍了国电集团基本情况，着重介绍了集团公司新能源发展特色，特别是践行\u201c一带一路\u201d倡议和海外\u201c走出去\u201d情况，以及国电工作组赴埃塞俄比亚调研情况。希望埃塞俄比亚政府在投资、税收及电价政策等方面给予大力支持，共同开发可再生能源。\r\n　　海尔马里亚姆·德萨莱尼对国电集团在新能源领域取得的成就表示赞赏，并介绍了埃塞俄比亚风资源情况。他表示，国电集团拥有在非洲开发建设风电项目的成熟经验，埃塞俄比亚政府已确定将国电集团列为其战略合作伙伴，将在投资开发等方面提供政策支持。欢迎国电集团到埃塞俄比亚开发建设风电项目，为非洲人民带去更加清洁、高效的能源。\r\n　　埃塞俄比亚电力公司总经理、集团公司国际合作与海外业务部有关负责同志参加会见。","image":null,"cname":"国电电力发展股份有限公司"}
         */

        private int is_collection;
        private int comment_num;
        private NoticeInfoBean notice_info;
        private List<NoticeCommentBean> notice_comment;

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }

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
             * title : 乔保平会见埃塞俄比亚总理
             * time : 1496735800
             * comment : 5月13日，集团公司党组书记、董事长乔保平在京会见了来华参加“一带一路”国际合作高峰论坛的埃塞俄比亚总理海尔马里亚姆·德萨莱尼一行。集团公司党组成员、副总经理高嵩，埃塞俄比亚外交部部长等陪同会见。
             　　乔保平向海尔马里亚姆·德萨莱尼介绍了国电集团基本情况，着重介绍了集团公司新能源发展特色，特别是践行“一带一路”倡议和海外“走出去”情况，以及国电工作组赴埃塞俄比亚调研情况。希望埃塞俄比亚政府在投资、税收及电价政策等方面给予大力支持，共同开发可再生能源。
             　　海尔马里亚姆·德萨莱尼对国电集团在新能源领域取得的成就表示赞赏，并介绍了埃塞俄比亚风资源情况。他表示，国电集团拥有在非洲开发建设风电项目的成熟经验，埃塞俄比亚政府已确定将国电集团列为其战略合作伙伴，将在投资开发等方面提供政策支持。欢迎国电集团到埃塞俄比亚开发建设风电项目，为非洲人民带去更加清洁、高效的能源。
             　　埃塞俄比亚电力公司总经理、集团公司国际合作与海外业务部有关负责同志参加会见。
             * image : null
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
             * comment : Sw2s2ws
             * time : 1497526092
             * image : null
             * username : 天霸动霸tua
             * userpic : 115.28.77.187/tp5/bbss/public/uploads/325/59489caa62049.jpeg
             * phone : 15313165551
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

//    /**
//     * code : 200
//     * msg : 操作成功
//     * infor : {"comment_num":2,"notice_comment":[{"comment":"dfgfdgfdgghfhgh","time":145552,"image":null,"username":"gfdgg","userpic":"115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg","phone":"13081363699"},{"comment":"ghgfhgfhgfh","time":15555522,"image":null,"username":"陈一南","userpic":null,"phone":"15641447575"}],"notice_info":{"title":"高嵩出席央企助力青海持续健康发展座谈会，并赴青海分公司调研","time":1496735800,"comment":"　6月12日，集团公司党组成员、副总经理高嵩在西宁出席央企助力青海持续健康发展座谈会暨战略合作签约仪式，并赴国电青海分公司调研。\r\n　　在与青海分公司干部员工座谈时，高嵩强调，要继续深入学习习近平总书记系列重要讲话，深入领会精神实质与内涵，认真贯彻落实五大发展理念，自觉助力青海绿色创新发展。要更加自觉践行\u201c一五五\u201d战略，加强市场研究和政策研判，关注技术发展趋势，把握发展节奏，进一步提高企业发展质量。要提升员工能力，切实抓好安全文明生产标准化、精细化管理、市场营销、成本控制等生产经营工作，推动智能化光伏电站建设，提高存量资产经营水平。要切实抓好党建和党风廉政建设，落实全面从严治党，增加\u201c四个意识\u201d。要切实抓好精准扶贫工作，落实好惠民工程，助力全面建成小康社会。要加强学习培训，切实抓好干部员工队伍建设，做好各项工作，迎接党的十九大胜利召开。","image":"115.28.77.187/tp5/bbss/public/uploads/329/20170614163736.jpeg","cname":"国电电力发展股份有限公司"}}
//     */
//
//    private int code;
//    private String msg;
//    private InforBean infor;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public InforBean getInfor() {
//        return infor;
//    }
//
//    public void setInfor(InforBean infor) {
//        this.infor = infor;
//    }
//
//    public static class InforBean {
//        /**
//         * comment_num : 2
//         * notice_comment : [{"comment":"dfgfdgfdgghfhgh","time":145552,"image":null,"username":"gfdgg","userpic":"115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg","phone":"13081363699"},{"comment":"ghgfhgfhgfh","time":15555522,"image":null,"username":"陈一南","userpic":null,"phone":"15641447575"}]
//         * notice_info : {"title":"高嵩出席央企助力青海持续健康发展座谈会，并赴青海分公司调研","time":1496735800,"comment":"　6月12日，集团公司党组成员、副总经理高嵩在西宁出席央企助力青海持续健康发展座谈会暨战略合作签约仪式，并赴国电青海分公司调研。\r\n　　在与青海分公司干部员工座谈时，高嵩强调，要继续深入学习习近平总书记系列重要讲话，深入领会精神实质与内涵，认真贯彻落实五大发展理念，自觉助力青海绿色创新发展。要更加自觉践行\u201c一五五\u201d战略，加强市场研究和政策研判，关注技术发展趋势，把握发展节奏，进一步提高企业发展质量。要提升员工能力，切实抓好安全文明生产标准化、精细化管理、市场营销、成本控制等生产经营工作，推动智能化光伏电站建设，提高存量资产经营水平。要切实抓好党建和党风廉政建设，落实全面从严治党，增加\u201c四个意识\u201d。要切实抓好精准扶贫工作，落实好惠民工程，助力全面建成小康社会。要加强学习培训，切实抓好干部员工队伍建设，做好各项工作，迎接党的十九大胜利召开。","image":"115.28.77.187/tp5/bbss/public/uploads/329/20170614163736.jpeg","cname":"国电电力发展股份有限公司"}
//         */
//
//        private int comment_num;
//        private NoticeInfoBean notice_info;
//        private List<NoticeCommentBean> notice_comment;
//
//        public int getComment_num() {
//            return comment_num;
//        }
//
//        public void setComment_num(int comment_num) {
//            this.comment_num = comment_num;
//        }
//
//        public NoticeInfoBean getNotice_info() {
//            return notice_info;
//        }
//
//        public void setNotice_info(NoticeInfoBean notice_info) {
//            this.notice_info = notice_info;
//        }
//
//        public List<NoticeCommentBean> getNotice_comment() {
//            return notice_comment;
//        }
//
//        public void setNotice_comment(List<NoticeCommentBean> notice_comment) {
//            this.notice_comment = notice_comment;
//        }
//
//        public static class NoticeInfoBean {
//            /**
//             * title : 高嵩出席央企助力青海持续健康发展座谈会，并赴青海分公司调研
//             * time : 1496735800
//             * comment : 　6月12日，集团公司党组成员、副总经理高嵩在西宁出席央企助力青海持续健康发展座谈会暨战略合作签约仪式，并赴国电青海分公司调研。
//             　　在与青海分公司干部员工座谈时，高嵩强调，要继续深入学习习近平总书记系列重要讲话，深入领会精神实质与内涵，认真贯彻落实五大发展理念，自觉助力青海绿色创新发展。要更加自觉践行“一五五”战略，加强市场研究和政策研判，关注技术发展趋势，把握发展节奏，进一步提高企业发展质量。要提升员工能力，切实抓好安全文明生产标准化、精细化管理、市场营销、成本控制等生产经营工作，推动智能化光伏电站建设，提高存量资产经营水平。要切实抓好党建和党风廉政建设，落实全面从严治党，增加“四个意识”。要切实抓好精准扶贫工作，落实好惠民工程，助力全面建成小康社会。要加强学习培训，切实抓好干部员工队伍建设，做好各项工作，迎接党的十九大胜利召开。
//             * image : 115.28.77.187/tp5/bbss/public/uploads/329/20170614163736.jpeg
//             * cname : 国电电力发展股份有限公司
//             */
//
//            private String title;
//            private int time;
//            private String comment;
//            private String image;
//            private String cname;
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public int getTime() {
//                return time;
//            }
//
//            public void setTime(int time) {
//                this.time = time;
//            }
//
//            public String getComment() {
//                return comment;
//            }
//
//            public void setComment(String comment) {
//                this.comment = comment;
//            }
//
//            public String getImage() {
//                return image;
//            }
//
//            public void setImage(String image) {
//                this.image = image;
//            }
//
//            public String getCname() {
//                return cname;
//            }
//
//            public void setCname(String cname) {
//                this.cname = cname;
//            }
//        }
//
//        public static class NoticeCommentBean {
//            /**
//             * comment : dfgfdgfdgghfhgh
//             * time : 145552
//             * image : null
//             * username : gfdgg
//             * userpic : 115.28.77.187/tp5/bbss/public/uploads/1/59324ebe29499.jpeg
//             * phone : 13081363699
//             */
//
//            private String comment;
//            private int time;
//            private Object image;
//            private String username;
//            private String userpic;
//            private String phone;
//
//            public String getComment() {
//                return comment;
//            }
//
//            public void setComment(String comment) {
//                this.comment = comment;
//            }
//
//            public int getTime() {
//                return time;
//            }
//
//            public void setTime(int time) {
//                this.time = time;
//            }
//
//            public Object getImage() {
//                return image;
//            }
//
//            public void setImage(Object image) {
//                this.image = image;
//            }
//
//            public String getUsername() {
//                return username;
//            }
//
//            public void setUsername(String username) {
//                this.username = username;
//            }
//
//            public String getUserpic() {
//                return userpic;
//            }
//
//            public void setUserpic(String userpic) {
//                this.userpic = userpic;
//            }
//
//            public String getPhone() {
//                return phone;
//            }
//
//            public void setPhone(String phone) {
//                this.phone = phone;
//            }
//        }
//    }
}
