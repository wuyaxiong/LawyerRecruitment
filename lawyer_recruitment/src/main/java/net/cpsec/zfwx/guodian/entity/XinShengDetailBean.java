package net.cpsec.zfwx.guodian.entity;

/**
 * Created by szh on 2017/6/27.
 */

public class XinShengDetailBean {
    /**
     * code : 200
     * msg : 请求成功
     * infor : {"is_praise":1,"result1":{"ask_id":2,"image":"115.28.77.187/tp5/bbss/public/uploads/329/593a15e5e80a2.jpeg","content":"1zhang tupian","asktime":1496978917,"praise":5,"username":"飞锐","userpic":"115.28.77.187/tp5/bbss/public/uploads/329/595efe17436d5.jpeg","phone":"17600382402","comment":"及诶额覅偶二分二份儿饭开发商咖啡破饿哦日否认foe热热哦","time":1466335556}}
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
         * is_praise : 1
         * result1 : {"ask_id":2,"image":"115.28.77.187/tp5/bbss/public/uploads/329/593a15e5e80a2.jpeg","content":"1zhang tupian","asktime":1496978917,"praise":5,"username":"飞锐","userpic":"115.28.77.187/tp5/bbss/public/uploads/329/595efe17436d5.jpeg","phone":"17600382402","comment":"及诶额覅偶二分二份儿饭开发商咖啡破饿哦日否认foe热热哦","time":1466335556}
         */

        private int is_praise;
        private Result1Bean result1;

        public int getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(int is_praise) {
            this.is_praise = is_praise;
        }

        public Result1Bean getResult1() {
            return result1;
        }

        public void setResult1(Result1Bean result1) {
            this.result1 = result1;
        }

        public static class Result1Bean {
            /**
             * ask_id : 2
             * image : 115.28.77.187/tp5/bbss/public/uploads/329/593a15e5e80a2.jpeg
             * content : 1zhang tupian
             * asktime : 1496978917
             * praise : 5
             * username : 飞锐
             * userpic : 115.28.77.187/tp5/bbss/public/uploads/329/595efe17436d5.jpeg
             * phone : 17600382402
             * comment : 及诶额覅偶二分二份儿饭开发商咖啡破饿哦日否认foe热热哦
             * time : 1466335556
             */

            private int ask_id;
            private String image;
            private String content;
            private int asktime;
            private int praise;
            private String username;
            private String userpic;
            private String phone;
            private String comment;
            private int time;

            public int getAsk_id() {
                return ask_id;
            }

            public void setAsk_id(int ask_id) {
                this.ask_id = ask_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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
//     * msg : 请求成功
//     * infor : {"ask_id":1,"image":"","content":"111111","asktime":1496978738,"praise":4,"username":"飞锐","userpic":"","phone":"17600382402","comment":"惊魂甫定国防科大金额","time":14566555}
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
//         * ask_id : 1
//         * image :
//         * content : 111111
//         * asktime : 1496978738
//         * praise : 4
//         * username : 飞锐
//         * userpic :
//         * phone : 17600382402
//         * comment : 惊魂甫定国防科大金额
//         * time : 14566555
//         */
//
//        private int ask_id;
//        private String image;
//        private String content;
//        private int asktime;
//        private int praise;
//        private String username;
//        private String userpic;
//        private String phone;
//        private String comment;
//        private int time;
//        private int is_praise;
//
//        public int getIs_praise() {
//            return is_praise;
//        }
//
//        public void setIs_praise(int is_praise) {
//            this.is_praise = is_praise;
//        }
//
//        public int getAsk_id() {
//            return ask_id;
//        }
//
//        public void setAsk_id(int ask_id) {
//            this.ask_id = ask_id;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
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
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getUserpic() {
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
