package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.JiagouHaoyouBean;

import java.util.List;

/**
 * Created by 郭新胜 on 2017/6/14.
 */

public class JiagouHaoyouAdapter extends BaseAdapter {

    private Context context;
    private List<JiagouHaoyouBean.InforBean> haoyou;
    private LayoutInflater inflater;
   public JiagouHaoyouAdapter(Context context, List<JiagouHaoyouBean.InforBean> haoyou){

       this.context=context;
       this.haoyou=haoyou;
       this.inflater= inflater.from(context);

   }
    @Override
    public int getCount() {
        return haoyou.size();
    }

    @Override
    public JiagouHaoyouBean.InforBean getItem(int position) {
        return haoyou.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.jiagouhaoyoulayout,null);
            holder=new ViewHolder();
            holder.haoyouName=(TextView)convertView.findViewById(R.id.haoyouName);
            convertView.setTag(holder);

        }
        holder=(ViewHolder) convertView.getTag();
        JiagouHaoyouBean.InforBean haoyoubean=getItem(position);
        holder.haoyouName.setText(haoyoubean.getUsername());
        return convertView;
    }
    class ViewHolder{

        TextView haoyouName;
        ImageView haoyouImage;


    }

}
