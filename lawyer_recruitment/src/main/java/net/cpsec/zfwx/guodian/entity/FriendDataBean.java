package net.cpsec.zfwx.guodian.entity;

/**
 * Created by zhaobainian on 2017/6/14.
 */

public class FriendDataBean {
    /**
     * id : 572
     * uid : 329
     * friend_id : 340
     * type : null
     * addtime : 2017-06-14 15:12:14
     * status : 接受
     * friend_group_id : 1
     * name : 飞锐
     * userpic : 115.28.77.187/tp5/bbss/public/uploads/329/593a7db78687f.jpeg
     * phone : 17600382402
     */

    private int id;
    private int uid;
    private int friend_id;
    private Object type;
    private String addtime;
    private String status;
    private int friend_group_id;
    private String name;
    private String userpic;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFriend_group_id() {
        return friend_group_id;
    }

    public void setFriend_group_id(int friend_group_id) {
        this.friend_group_id = friend_group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
