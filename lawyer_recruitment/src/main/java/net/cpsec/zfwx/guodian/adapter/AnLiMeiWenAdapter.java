package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.AnLiMeiWenInfor;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/26.
 */

public class AnLiMeiWenAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<AnLiMeiWenInfor> quanBuInfors;
    private OnItemClickListener mOnItemClickListener = null;
    public AnLiMeiWenAdapter(Context context, List<AnLiMeiWenInfor> quanBuInfors) {
        this.context = context;
        this.quanBuInfors = quanBuInfors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_meiwen, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_companyname.setText(quanBuInfors.get(position).getNum()+"条评论");
        ((ViewHolder) holder).tv_title.setText(quanBuInfors.get(position).getTitle());
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(quanBuInfors.get(position).getTime()));
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
        return quanBuInfors.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_avadar;
        private TextView tv_title, tv_companyname, tv_shijian;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener(this);

            iv_avadar = (ImageView) itemView.findViewById(R.id.iv_tongzhi);
            tv_companyname = (TextView) itemView.findViewById(R.id.tv_tongzhi_companyname);
            tv_title = (TextView) itemView.findViewById(R.id.tv_tongzhi_title);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_tongzhi_time);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取position
                mOnItemClickListener.onItemClick(v, (int) v.getTag());
            }
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener =listener;
    }
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
