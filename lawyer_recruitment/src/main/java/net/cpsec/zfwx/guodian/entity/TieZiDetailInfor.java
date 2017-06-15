/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.guodian.entity;
import java.util.List;

/**
 * Auto-generated: 2017-06-13 14:23:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TieZiDetailInfor {

    private TieZiForum_info forum_info;
    private int prise_num;
    private int comment_num;
    private List<TieZiComment_info> comment_info;
    public void setForum_info(TieZiForum_info forum_info) {
         this.forum_info = forum_info;
     }
     public TieZiForum_info getForum_info() {
         return forum_info;
     }

    public void setPrise_num(int prise_num) {
         this.prise_num = prise_num;
     }
     public int getPrise_num() {
         return prise_num;
     }

    public void setComment_num(int comment_num) {
         this.comment_num = comment_num;
     }
     public int getComment_num() {
         return comment_num;
     }

    public void setComment_info(List<TieZiComment_info> comment_info) {
         this.comment_info = comment_info;
     }
     public List<TieZiComment_info> getComment_info() {
         return comment_info;
     }

}