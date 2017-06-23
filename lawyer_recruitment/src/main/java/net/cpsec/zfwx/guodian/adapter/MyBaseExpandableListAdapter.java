package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.manager.RequestManager;
import com.android.volley.manager.RequestMap;
import com.android.volley.toolbox.Volley;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.FriendDataBean;
import net.cpsec.zfwx.guodian.entity.GetFriendQueueInfoBean;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.List;
import java.util.Map;

/**
 * Created by yang_zzheng on 2016/7/20
 * yangzhizheng2012@163.com
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> gData;
    private List<List<Object>> iData;
    private Context mContext;
    private  RequestQueue requestQueue;


    public MyBaseExpandableListAdapter(List<String> gData, List<List<Object>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
        this.requestQueue= Volley.newRequestQueue(mContext);
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return iData.get(groupPosition).size()==0?0:iData.get(groupPosition).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(R.id.xxx01, groupPosition);
            convertView.setTag(R.id.xxx02, -1); //设置-1表示长按时点击的是父项，到时好判断。
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
            convertView.setTag(R.id.xxx01, groupPosition);
            convertView.setTag(R.id.xxx02, -1); //设置-1表示长按时点击的是父项，到时好判断。
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition));
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView( int groupPosition,  int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = (RoundedImageView) convertView.findViewById(R.id.img_icon);
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            itemHolder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            itemHolder.tv_accept = (TextView) convertView.findViewById(R.id.tv_accept);
            convertView.setTag(R.id.xxx01, groupPosition);
            convertView.setTag(R.id.xxx02, childPosition);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
            convertView.setTag(R.id.xxx01, groupPosition);
            convertView.setTag(R.id.xxx02, childPosition);
        }
        //是否是新好友
        if (iData.get(groupPosition).get(childPosition) instanceof FriendDataBean) {
            final FriendDataBean friendDataBean = (FriendDataBean) iData.get(groupPosition).get(childPosition);
            itemHolder.tv_accept.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load("http://"+friendDataBean.getUserpic()).placeholder(R.mipmap.ic_launcher).into(itemHolder.img_icon);
            itemHolder.tv_name.setText(friendDataBean.getName());
            itemHolder.tv_phone.setText(friendDataBean.getPhone());
            if ("接受".equals(friendDataBean.getStatus())) {
                itemHolder.tv_accept.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                itemHolder.tv_accept.setText(friendDataBean.getStatus());
                itemHolder.tv_accept.setEnabled(true);
                final int uid = friendDataBean.getId();
                itemHolder.tv_accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RequestMap  params  = new RequestMap();
                        //TODO 默认设置一组（未分组好友）
                        params.put("group_id", "1");
                        params.put("person_add_id", ""+uid);
                        RequestManager.getInstance().post(NetUrl.GET_FRIEND_APPLY, params, new RequestManager.RequestListener() {
                            @Override
                            public void onRequest() {

                            }

                            @Override
                            public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
                                try{
                                    if ("200".equals(JSONObject.parseObject(response).getString("code"))){
                                        Toast.makeText(mContext,JSONObject.parseObject(response).getString("msg"), Toast.LENGTH_SHORT).show();
                                        itemHolder.tv_accept.setBackgroundColor(mContext.getResources().getColor(R.color.color_818181));
                                        itemHolder.tv_accept.setText(friendDataBean.getStatus());
                                        itemHolder.tv_accept.setEnabled(false);
                                    }else{
                                        Toast.makeText(mContext,JSONObject.parseObject(response).getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch(Exception e){
                                    Toast.makeText(mContext, "接受好友失败！", Toast.LENGTH_SHORT).show();
                                }

                            }
                            @Override
                            public void onError(String errorMsg, String url, int actionId) {
                                Toast.makeText(mContext, "错误方式！", Toast.LENGTH_SHORT).show();
                            }
                        }, 1);
                    }
                });
            }else{
                itemHolder.tv_accept.setBackgroundColor(mContext.getResources().getColor(R.color.color_818181));
                itemHolder.tv_accept.setText(friendDataBean.getStatus());
                itemHolder.tv_accept.setEnabled(false);
            }
        }else if (iData.get(groupPosition).get(childPosition) instanceof GetFriendQueueInfoBean.InforBean){
            final GetFriendQueueInfoBean.InforBean DataBean = (GetFriendQueueInfoBean.InforBean) iData.get(groupPosition).get(childPosition);
            ImageLoader.getInstance().displayImage("http://" + DataBean.getUserpic(), itemHolder.img_icon);
           // Picasso.with(mContext).load("http://"+DataBean.getUserpic()).placeholder(R.mipmap.ic_launcher).into(itemHolder.img_icon);
            itemHolder.tv_name.setText(DataBean.getUsername());
            itemHolder.tv_phone.setText(DataBean.getPhone());
            itemHolder.tv_accept.setVisibility(View.GONE);
        }
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup{
        private TextView tv_group_name;
    }

    private static class ViewHolderItem{
        private RoundedImageView img_icon;
        private TextView tv_name;
        private TextView tv_phone;
        private TextView tv_accept;

    }

}