package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.ShouCang;
import net.cpsec.zfwx.guodian.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/7/11.
 */

public class ShouCangTongZhiAdapter extends BaseAdapter {
    private Context context;
    private ShouCang.InforBean inforBeen;
    List<String> list;
    public ShouCangTongZhiAdapter(Context context,ShouCang.InforBean inforBeen){
        this.context=context;
        this.inforBeen=inforBeen;
    }
    @Override
    public int getCount() {
        return inforBeen.getNoticelist().size();
    }

    @Override
    public Object getItem(int position) {
        return inforBeen.getNoticelist().get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_meiwen,null);
            holder.iv_avadar = (ImageView) convertView.findViewById(R.id.iv_tongzhi);
            holder.tv_companyname = (TextView) convertView.findViewById(R.id.tv_tongzhi_companyname);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_tongzhi_title);
            holder.tv_shijian = (TextView) convertView.findViewById(R.id.tv_tongzhi_time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if (inforBeen.getNoticelist().get(position)==null||inforBeen.getNoticelist().get(position).isEmpty()){
            inforBeen.getNoticelist().get(position).remove(position);
        }else {
            //把javabean中的图片地址转化成list集合
            list = new ArrayList<String>();
            list.clear();
            String tupian = inforBeen.getNoticelist().get(position).get(0).getImage();
            if (tupian!=null){
                String[] tupians = tupian.split(",");
                for (String substr : tupians) {
                    list.add(substr);
                }
                if (!list.get(0).isEmpty()) {
                    ((ViewHolder) holder).iv_avadar.setVisibility(View.VISIBLE);
                    ImageLoader.getInstance().displayImage("http://" + list.get(0), ((ViewHolder) holder).iv_avadar);
                }
            }
            ((ViewHolder) holder).tv_companyname.setText(inforBeen.getNoticelist().get(position).get(0).getCname());
            ((ViewHolder) holder).tv_title.setText(inforBeen.getNoticelist().get(position).get(0).getTitle());
            ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(inforBeen.getNoticelist().get(position  ).get(0).getTime()));
        }

        return convertView;
    }
    class ViewHolder{
        private ImageView iv_avadar;
        private TextView  tv_title, tv_companyname, tv_shijian;

    }
}
