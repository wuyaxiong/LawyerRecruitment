package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.SanjiaBumenBean;

import java.util.List;

/**
 * Created by szh on 2017/6/15.
 */

public class SanjibumenAdapter extends BaseAdapter {
    private Context context;
    private List<SanjiaBumenBean.InforBean> sanbumen;
    private LayoutInflater inflater;
public SanjibumenAdapter(Context context,List<SanjiaBumenBean.InforBean> sanbumen){
    this.context=context;
    this.sanbumen=sanbumen;
    this.inflater=inflater.from(context);
}
    @Override
    public int getCount() {
        return sanbumen.size();

    }

    @Override
    public SanjiaBumenBean.InforBean getItem(int position) {
        return sanbumen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.gongsilayout,null);
            holder.bumenName=(TextView)convertView.findViewById(R.id.gongsiName);
            convertView.setTag(holder);
        }
        holder=(ViewHolder) convertView.getTag();
        SanjiaBumenBean.InforBean bumen = getItem(position);
        holder.bumenName.setText(bumen.getCname());
        return convertView;
    }
    class ViewHolder{
     TextView bumenName;
    }
}
