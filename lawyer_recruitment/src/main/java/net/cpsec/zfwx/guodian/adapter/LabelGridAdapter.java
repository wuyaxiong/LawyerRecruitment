package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.Label;

import java.util.List;

/**
 * Created by szh on 2017/6/12.
 */

public class LabelGridAdapter extends BaseAdapter {
    private List<Label> labelBeen;
    LayoutInflater inflater;

    public LabelGridAdapter(Context context, List<Label> labelbean) {
        this.labelBeen = labelbean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return labelBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return labelBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_label, null);
            TextView tv_label = (TextView) convertView.findViewById(R.id.tv_label);
//            convertView.setTag(holder);
//        }else {
//            holder= (ViewHolder) convertView.getTag();
//        }
            tv_label.setText(labelBeen.get(position).getName());
        }
        return convertView;
//    public class ViewHolder{
//        private TextView tv_label;
//    }
    }
}
