package net.cpsec.zfwx.lawyer_recruitment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cpsec.zfwx.lawyer_recruitment.R;

/**
 * Created by szh on 2017/5/15.
 */

public class QingChunJiaoLiuAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    public QingChunJiaoLiuAdapter (Context context){
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_fenxiang01, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
