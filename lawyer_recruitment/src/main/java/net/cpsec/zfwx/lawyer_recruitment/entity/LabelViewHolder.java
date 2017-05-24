package net.cpsec.zfwx.lawyer_recruitment.entity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.cpsec.zfwx.lawyer_recruitment.R;

/**
 * Created by szh on 2017/5/16.
 */

public class LabelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tv;
    private MyItemClickListener mListener;

    public LabelViewHolder(View arg0,MyItemClickListener listener) {
        super(arg0);
        tv = (TextView)arg0.findViewById(R.id.tv_label_content);
        this.mListener = listener;
        arg0.setOnClickListener(this);
    }

    /**
     * �������
     */
    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v,getPosition());
        }
    }

}
