/**
  * Copyright 2017 bejson.com 
  */
package net.cpsec.zfwx.guodian.entity;
import java.util.List;

/**
 * Auto-generated: 2017-06-12 11:22:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MeiWenPingLunInfor {

    private Goods_article_all goods_article_all;
    private List<Comment_info> comment_info;
    public void setGoods_article_all(Goods_article_all goods_article_all) {
         this.goods_article_all = goods_article_all;
     }
     public Goods_article_all getGoods_article_all() {
         return goods_article_all;
     }

    public void setComment_info(List<Comment_info> comment_info) {
         this.comment_info = comment_info;
     }
     public List<Comment_info> getComment_info() {
         return comment_info;
     }

}