package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.ZhengCeTongzhiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class TongZhiPingLunAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    List<ZhengCeTongzhiDetailBean.InforBean.NoticeCommentBean> infors;
    public TongZhiPingLunAdapter(Context context,List<ZhengCeTongzhiDetailBean.InforBean.NoticeCommentBean> infors){
        this.context=context;
        this.infors=infors;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return infors.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView=inflater.inflate(R.layout.item_pinglun1,null);
            holder.head= (RoundedImageView) convertView.findViewById(R.id.iv_pinglun_head);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_pinglun_username);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_pinglun_time);
            holder.tv_content= (TextView) convertView.findViewById(R.id.tv_pinglun_content);
            convertView.setTag(holder); //绑定ViewHolder对象
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage("http://" + infors.get(position).getUserpic(), ((ViewHolder) holder).head);
        holder.tv_name.setText(infors.get(position).getUsername());
        holder.tv_time.setText(DateUtil.converTime(infors.get(position).getTime()));
        holder.tv_content.setText(infors.get(position).getComment());
        return convertView;
    }
    class ViewHolder{
        private RoundedImageView head;
        private TextView tv_name,tv_time,tv_content;
    }
}
