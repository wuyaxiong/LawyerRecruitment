package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.TouPiaoBean;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/26.
 */

public class TouPiaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<TouPiaoBean.InforBean> touPiaoInforBeen;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;

    public TouPiaoAdapter(Context context, List<TouPiaoBean.InforBean> inforBeen) {
        this.context = context;
        this.touPiaoInforBeen = inforBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_toupiao, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        ((ViewHolder) holder).tv_time.setText(touPiaoInforBeen.get(position).getTime()+"");
        ((ViewHolder) holder).tv_title.setText(touPiaoInforBeen.get(position).getTitle());
        //这个是有多少人参与的,接口目前没有这个数据，
        //((ViewHolder) holder).tv_num.setText(touPiaoInforBeen.get(position). + "");


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
        return touPiaoInforBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_time, tv_title, tv_num;
        private LinearLayout layout_toupiao;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener(this);
            tv_time = (TextView) itemView.findViewById(R.id.item_mytoupiao_time);
            tv_title = (TextView) itemView.findViewById(R.id.item_mytoupiao_title);
            tv_num = (TextView) itemView.findViewById(R.id.item_mytoupiao_num);
        }

        @Override
        public void onClick(View v) {
//TODO
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
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener =listener;
    }
}
