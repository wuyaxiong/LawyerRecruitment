package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.JianYiInfor;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/26.
 */

public class JianYiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<JianYiInfor> jianYiInfors;
    private OnItemClickListener mOnItemClickListener = null;
    public JianYiAdapter(Context context, List<JianYiInfor> jianYiInfors) {
        this.context = context;
        this.jianYiInfors = jianYiInfors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tiwen, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_name.setText(jianYiInfors.get(position).getUsername());
        ((ViewHolder) holder).tv_question.setText(jianYiInfors.get(position).getContent());
        ((ViewHolder) holder).tv_answer.setText(null);
        ((ViewHolder) holder).tv_shijian.setText(jianYiInfors.get(position).getTime() + "");
        // ((ViewHolder) holder).tv_shijian.setText(shengList.get(position).getAsktime());
        // ((ViewHolder) holder).tv_dianzan.setText(jianYiInfors.get(position).getPraise()+"");

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
        return jianYiInfors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_question, tv_answer, tv_shijian, tv_dianzan;
        private ImageView iv_dainzan;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener(this);

            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.riv_xinsheng_avadar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_xinsheng_name);
            tv_question = (TextView) itemView.findViewById(R.id.tv_xinsheng_question);
            tv_answer = (TextView) itemView.findViewById(R.id.tv_xinsheng_huida);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_xinsheng_time);
            tv_dianzan = (TextView) itemView.findViewById(R.id.tv_xinsheng_dainzxan);
            iv_dainzan = (ImageView) itemView.findViewById(R.id.iv_xinsheng_dianzan);
            iv_dainzan.setVisibility(View.GONE);
            tv_dianzan.setVisibility(View.GONE);
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
        this.mOnItemClickListener = listener;
    }
}
