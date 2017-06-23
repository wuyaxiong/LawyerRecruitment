package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by szh on 2017/6/21.
 */

public class TouPiaoDetailBean {

    /**
     * code : 200
     * msg : 成功
     * infor : {"vote_id":1,"admin_id":1,"title":"最美国电人","time":1496735800,"description":"各分（子）公司、研究院、基层企业员工：\n　　目前，由国资委宣传局开展的\u201c最美国电人\u201d征集活动已进入公众投票阶段。在本次评选活动中，以下员工成功入选。为宣传具有国电特色的先进典型，以典型宣传讲好\u201c国电故事\u201d，请按照有关要求，积极参与投票。","questions":[{"select_id":1,"vote_id":1,"question_id":1,"info":"龙源西藏新能源公司李白"},{"select_id":2,"vote_id":1,"question_id":1,"info":"国电贵州电力有限公司杜甫"},{"select_id":3,"vote_id":1,"question_id":1,"info":"国电广西电力有限公司王昌龄"},{"select_id":4,"vote_id":1,"question_id":1,"info":"国电重庆分公司张欢"},{"select_id":5,"vote_id":1,"question_id":1,"info":"国电电力发展股份有限公司李强"}]}
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
         * vote_id : 1
         * admin_id : 1
         * title : 最美国电人
         * time : 1496735800
         * description : 各分（子）公司、研究院、基层企业员工：
         　　目前，由国资委宣传局开展的“最美国电人”征集活动已进入公众投票阶段。在本次评选活动中，以下员工成功入选。为宣传具有国电特色的先进典型，以典型宣传讲好“国电故事”，请按照有关要求，积极参与投票。
         * questions : [{"select_id":1,"vote_id":1,"question_id":1,"info":"龙源西藏新能源公司李白"},{"select_id":2,"vote_id":1,"question_id":1,"info":"国电贵州电力有限公司杜甫"},{"select_id":3,"vote_id":1,"question_id":1,"info":"国电广西电力有限公司王昌龄"},{"select_id":4,"vote_id":1,"question_id":1,"info":"国电重庆分公司张欢"},{"select_id":5,"vote_id":1,"question_id":1,"info":"国电电力发展股份有限公司李强"}]
         */

        private int vote_id;
        private int admin_id;
        private String title;
        private int time;
        private String description;
        private List<QuestionsBean> questions;

        public int getVote_id() {
            return vote_id;
        }

        public void setVote_id(int vote_id) {
            this.vote_id = vote_id;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<QuestionsBean> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuestionsBean> questions) {
            this.questions = questions;
        }

        public static class QuestionsBean {
            /**
             * select_id : 1
             * vote_id : 1
             * question_id : 1
             * info : 龙源西藏新能源公司李白
             */

            private int select_id;
            private int vote_id;
            private int question_id;
            private String info;

            public int getSelect_id() {
                return select_id;
            }

            public void setSelect_id(int select_id) {
                this.select_id = select_id;
            }

            public int getVote_id() {
                return vote_id;
            }

            public void setVote_id(int vote_id) {
                this.vote_id = vote_id;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }
    }
}
