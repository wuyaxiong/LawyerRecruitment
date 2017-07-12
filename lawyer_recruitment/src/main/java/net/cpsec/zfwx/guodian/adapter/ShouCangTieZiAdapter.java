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
import net.cpsec.zfwx.guodian.entity.ShouCang;
import net.cpsec.zfwx.guodian.utils.DateUtil;

/**
 * Created by szh on 2017/7/11.
 */

public class ShouCangTieZiAdapter extends BaseAdapter{
    private Context context;
    private ShouCang.InforBean inforBeen;
    public ShouCangTieZiAdapter(Context context,ShouCang.InforBean inforBeen){
        this.context=context;
        this.inforBeen=inforBeen;
    }
    @Override
    public int getCount() {
        return inforBeen.getArticlelist().size();
    }

    @Override
    public Object getItem(int position) {
        return inforBeen.getArticlelist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_tiezi_shoucang,null);
            holder.riv_avadar = (RoundedImageView) convertView.findViewById(R.id.riv_jiaoliu_avater);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_jiaoliu_name);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_jiaoliu_title);
            holder.tv_label = (TextView) convertView.findViewById(R.id.tv_jiaoliu_label);
            holder.tv_shijian = (TextView) convertView.findViewById(R.id.tv_jiaoliu_time);
            holder.tv_dianzan = (TextView) convertView.findViewById(R.id.tv_jiaoliu_dianzan);
            holder.tv_huifu = (TextView) convertView.findViewById(R.id.tv_jiaoliu_huifu);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage("http://" + inforBeen.getArticlelist().get(position).get(0).getUserpic(), ((ViewHolder) holder).riv_avadar);
        ((ViewHolder) holder).tv_name.setText(inforBeen.getArticlelist().get(position).get(0).getUsername());
        ((ViewHolder) holder).tv_title.setText(inforBeen.getArticlelist().get(position).get(0).getContent());
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(inforBeen.getArticlelist().get(position).get(0).getTime()));
        ((ViewHolder) holder).tv_label.setText(inforBeen.getArticlelist().get(position).get(0).getName());
        ((ViewHolder) holder).tv_dianzan.setText(inforBeen.getArticlelist().get(position).get(0).getPraise()+"");
        ((ViewHolder) holder).tv_huifu.setText(inforBeen.getArticlelist().get(position).get(0).getComment()+"");
        return convertView;
    }
    class ViewHolder{
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_title, tv_label, tv_shijian, tv_dianzan, tv_huifu;
    }
}
