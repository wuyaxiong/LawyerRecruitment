package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.GetFriendQueueInfoBean;

import java.util.List;

/**
 * Created by zhaobainian on 2017/7/4.
 */

public class ScreenAdapter extends BaseAdapter {
    private Context mContext;
    private List<GetFriendQueueInfoBean.InforBean> data;

    public ScreenAdapter(Context mContext, List<GetFriendQueueInfoBean.InforBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public GetFriendQueueInfoBean.InforBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.item_screen,parent,false);
            holder.content=(TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        }else{

            holder=(ViewHolder) convertView.getTag();
        }

        holder.content.setText(data.get(position).getUsername());

        return convertView;
    }
    class ViewHolder{
        TextView content;


    }
}
