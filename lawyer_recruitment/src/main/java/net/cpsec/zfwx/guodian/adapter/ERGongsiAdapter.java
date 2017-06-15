package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.ERGongsiBean;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class ERGongsiAdapter extends BaseAdapter {

    private Context context;
    private List<ERGongsiBean.InforBean> gongsi;
    private LayoutInflater inflater;


    public  ERGongsiAdapter(Context context,List<ERGongsiBean.InforBean>gongsi){
       this.context=context;
       this.gongsi=gongsi;
       this.inflater= inflater.from(context);

   }

    @Override
    public int getCount() {
        return gongsi.size();
    }

    @Override
    public ERGongsiBean.InforBean getItem(int position) {
        return gongsi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.gongsilayout,null);
            holder.gongsiName =(TextView) convertView.findViewById(R.id.gongsiName);
            convertView.setTag(holder);
       }
        holder=(ViewHolder) convertView.getTag();
        ERGongsiBean.InforBean gongsibxean = getItem(position);
        holder.gongsiName.setText(gongsibxean.getCname());
        return convertView;
    }

    class ViewHolder{

        TextView gongsiName;


    }
}
