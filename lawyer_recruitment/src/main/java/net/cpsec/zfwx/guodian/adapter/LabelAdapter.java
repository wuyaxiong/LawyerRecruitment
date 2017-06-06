package net.cpsec.zfwx.guodian.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.Label;
import net.cpsec.zfwx.guodian.entity.LabelViewHolder;
import net.cpsec.zfwx.guodian.entity.MyItemClickListener;

import java.util.List;

/**
 * Created by szh on 2017/5/16.
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelViewHolder> {
    private List<Label> mData;
    private MyItemClickListener mItemClickListener;

    public LabelAdapter(List<Label> mData) {
        this.mData = mData;
    }

    @Override
    public LabelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_label, parent,false);
        LabelViewHolder vh = new LabelViewHolder(itemView,mItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(LabelViewHolder holder, int position) {
        Label bean = mData.get(position);
        holder.tv.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

}
