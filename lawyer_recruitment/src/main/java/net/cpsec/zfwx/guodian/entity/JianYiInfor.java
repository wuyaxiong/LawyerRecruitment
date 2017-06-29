/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.guodian.entity;

import java.io.Serializable;

/**
 * Auto-generated: 2017-05-26 10:59:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JianYiInfor implements Serializable {

    private String username;
    private String userpic;
    private String phone;
    private int time;
    private String image;
    private String content;
    private String  comment;
    private int praise;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
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

    public void setTime(int time) {
         this.time = time;
     }
     public int getTime() {
         return time;
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

}