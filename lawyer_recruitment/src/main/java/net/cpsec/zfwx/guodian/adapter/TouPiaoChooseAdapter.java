package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.TouPiaoDetailBean;

import java.util.List;

/**
 * Created by szh on 2017/6/21.
 */

public class TouPiaoChooseAdapter extends BaseAdapter {
    Context context;
    List<TouPiaoDetailBean.InforBean.QuestionsBean> list;

    public TouPiaoChooseAdapter(Context context, List<TouPiaoDetailBean.InforBean.QuestionsBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_toupiao_choose, null);
            holder.textView= (TextView) convertView.findViewById(R.id.tv_toupiao_xuanxiang);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getInfo());
        return convertView;
    }

    class ViewHolder {
        private TextView textView;
    }
}