package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/27.
 */

public class JianYiDetailBean {
    /**
     * code : 200
     * msg : 成功
     * infor : {"is_praise":0,"advice_detail":[{"username":"王栋晖","userpic":null,"phone":"13591513476","id":8,"content":"二二恶UI粉丝分为覅潍坊IE覅而","asktime":14566632,"praise":30,"comment":"俄尔委托人TT热风问题","time":1456656}]}
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
         * is_praise : 0
         * advice_detail : [{"username":"王栋晖","userpic":null,"phone":"13591513476","id":8,"content":"二二恶UI粉丝分为覅潍坊IE覅而","asktime":14566632,"praise":30,"comment":"俄尔委托人TT热风问题","time":1456656}]
         */

        private int is_praise;
        private List<AdviceDetailBean> advice_detail;

        public int getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(int is_praise) {
            this.is_praise = is_praise;
        }

        public List<AdviceDetailBean> getAdvice_detail() {
            return advice_detail;
        }

        public void setAdvice_detail(List<AdviceDetailBean> advice_detail) {
            this.advice_detail = advice_detail;
        }

        public static class AdviceDetailBean {
            /**
             * username : 王栋晖
             * userpic : null
             * phone : 13591513476
             * id : 8
             * content : 二二恶UI粉丝分为覅潍坊IE覅而
             * asktime : 14566632
             * praise : 30
             * comment : 俄尔委托人TT热风问题
             * time : 1456656
             */

            private String username;
            private Object userpic;
            private String phone;
            private int id;
            private String content;
            private int asktime;
            private int praise;
            private String comment;
            private int time;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getUserpic() {
                return userpic;
            }

            public void setUserpic(Object userpic) {
                this.userpic = userpic;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getAsktime() {
                return asktime;
            }

            public void setAsktime(int asktime) {
                this.asktime = asktime;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

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
        }
    }

//
//    /**
//     * code : 200
//     * msg : 成功
//     * infor : [{"username":"王栋晖","userpic":null,"phone":"13591513476","id":8,"content":"二二恶UI粉丝分为覅潍坊IE覅而","asktime":14566632,"praise":30,"comment":"俄尔委托人TT热风问题","time":1456656}]
//     */
//
//    private int code;
//    private String msg;
//    private List<InforBean> infor;
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
//    public List<InforBean> getInfor() {
//        return infor;
//    }
//
//    public void setInfor(List<InforBean> infor) {
//        this.infor = infor;
//    }
//
//    public static class InforBean {
//        /**
//         * username : 王栋晖
//         * userpic : null
//         * phone : 13591513476
//         * id : 8
//         * content : 二二恶UI粉丝分为覅潍坊IE覅而
//         * asktime : 14566632
//         * praise : 30
//         * comment : 俄尔委托人TT热风问题
//         * time : 1456656
//         */
//
//        private String username;
//        private String userpic;
//        private String phone;
//        private int id;
//        private String content;
//        private int asktime;
//        private int praise;
//        private String comment;
//        private int time;
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public Object getUserpic() {
//            return userpic;
//        }
//
//        public void setUserpic(String userpic) {
//            this.userpic = userpic;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public int getAsktime() {
//            return asktime;
//        }
//
//        public void setAsktime(int asktime) {
//            this.asktime = asktime;
//        }
//
//        public int getPraise() {
//            return praise;
//        }
//
//        public void setPraise(int praise) {
//            this.praise = praise;
//        }
//
//        public String getComment() {
//            return comment;
//        }
//
//        public void setComment(String comment) {
//            this.comment = comment;
//        }
//
//        public int getTime() {
//            return time;
//        }
//
//        public void setTime(int time) {
//            this.time = time;
//        }
//    }
}
