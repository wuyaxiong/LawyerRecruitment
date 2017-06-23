package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.TouPiaoList;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/6/20.
 */

public class TouPiaoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<TouPiaoList.InforBean> touPiaoInforBeen;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;
    public TouPiaoListAdapter(Context context, List<TouPiaoList.InforBean> inforBeen) {
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
        ((ViewHolder) holder).tv_title.setText(touPiaoInforBeen.get(position).getTitle());
        ((ViewHolder) holder).tv_time.setText(DateUtil.converTime(touPiaoInforBeen.get(position).getTime()));
        ((ViewHolder) holder).tv_num.setText(touPiaoInforBeen.get(position).getCount()+"");

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
        private TextView  tv_title,tv_time,tv_num;
        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener(this);
            tv_title = (TextView) itemView.findViewById(R.id.item_mytoupiao_title);
            tv_time= (TextView) itemView.findViewById(R.id.item_mytoupiao_time);
            tv_num= (TextView) itemView.findViewById(R.id.item_mytoupiao_num);
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
