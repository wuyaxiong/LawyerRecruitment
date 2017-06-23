/**
 * Copyright 2017 bejson.com
 */
package net.cpsec.zfwx.guodian.entity;

import java.io.Serializable;

/**
 * Auto-generated: 2017-05-25 9:26:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ShengDetail implements Serializable {
    private int ask_id;
    private String phone;
    private String username;
    private String userpic;
    private String image;
    private String content;
    private int asktime;
    private int praise;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAsk_id() {
        return ask_id;
    }

    public void setAsk_id(int ask_id) {
        this.ask_id = ask_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setAsktime(int asktime) {
        this.asktime = asktime;
    }

    public int getAsktime() {
        return asktime;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getPraise() {
        return praise;
    }

}