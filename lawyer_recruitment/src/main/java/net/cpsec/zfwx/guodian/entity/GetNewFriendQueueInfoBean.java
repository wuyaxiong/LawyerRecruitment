package net.cpsec.zfwx.guodian.entity;

import java.util.List;

/**
 * Created by zhaobainian on 2017/6/14.
 */

public class GetNewFriendQueueInfoBean {

    /**
     * code : 200
     * msg : 获取好友申请信息成功
     * data : [{"id":572,"uid":329,"friend_id":340,"type":null,"addtime":"2017-06-14 15:12:14","status":"接受","friend_group_id":1,"name":"飞锐","userpic":"115.28.77.187/tp5/bbss/public/uploads/329/593a7db78687f.jpeg","phone":"17600382402"}]
     */

    private int code;
    private String msg;
    private List<FriendDataBean> data;

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

    public List<FriendDataBean> getData() {
        return data;
    }

    public void setData(List<FriendDataBean> data) {
        this.data = data;
    }


}
