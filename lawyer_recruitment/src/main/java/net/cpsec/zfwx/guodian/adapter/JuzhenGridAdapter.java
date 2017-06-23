package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.JuzhenBean;

import java.util.List;

/**
 * Created by 郭新胜 on 2017/6/15.
 */

public class JuzhenGridAdapter extends BaseAdapter {
    private Context context;
    private List<JuzhenBean.InforBean> juzhen;
    private LayoutInflater inflater;
    public JuzhenGridAdapter(Context context ,List<JuzhenBean.InforBean>juzhen){
        this.context=context;
        this.juzhen=juzhen;
        this.inflater= inflater.from(context);
    }
    @Override
    public int getCount() {
        return juzhen.size();
    }

    @Override
    public JuzhenBean.InforBean getItem(int position) {
        return juzhen.get(position);
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
            convertView=inflater.inflate(R.layout.juzhen_griditem_layout,null);
            holder.danweiName=(TextView) convertView.findViewById(R.id.danweiName);
            convertView.setTag(holder);

        }
        holder=(ViewHolder)convertView.getTag();
        JuzhenBean.InforBean juzhenbean = getItem(position);
        holder.danweiName.setText(juzhenbean.getCname());

        return convertView;
    }
    class ViewHolder{
        TextView danweiName;

    }
}
