package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.ZhuanJiaBean;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by lice on 2017/6/21.
 */

public class ZhuanJiaAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private Context context;
    private List<ZhuanJiaBean.InforBean> zhuanJiaInforBeen;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;

    public ZhuanJiaAdapter(Context context, List<ZhuanJiaBean.InforBean> inforBeen) {
        this.context = context;
        this.zhuanJiaInforBeen = inforBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_zhuanjia, null);
        return new ZhuanJiaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ((ZhuanJiaAdapter.ViewHolder) holder).tv_name.setText(zhuanJiaInforBeen.get(position).getUsername());

        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return zhuanJiaInforBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_name;
        private LinearLayout layout_toupiao;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());
            itemView.setOnClickListener(this);
            tv_name = (TextView)itemView.findViewById(R.id.tv_zhuanjia_name);

        }

        @Override
        public void onClick(View v) {

        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }
    public void setOnItemClickListener(ZhuanJiaAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener =listener;
    }
}
