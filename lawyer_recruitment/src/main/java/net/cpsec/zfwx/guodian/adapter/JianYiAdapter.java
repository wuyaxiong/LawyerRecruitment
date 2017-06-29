package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.JianYiInfor;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/26.
 */

public class JianYiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<JianYiInfor> jianYiInfors;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;
    public OnTitleClickListener mListener;
    public OnHeadClickListener mHeadlistener;
    public OnPicClickListener mPiclistener;
    public JianYiAdapter(Context context, List<JianYiInfor> jianYiInfors) {
        this.context = context;
        this.jianYiInfors = jianYiInfors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_jianyi, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage("http://" + jianYiInfors.get(position).getUserpic(), ((ViewHolder) holder).riv_avadar);
        ((ViewHolder) holder).tv_name.setText(jianYiInfors.get(position).getUsername());
        ((ViewHolder) holder).tv_question.setText(jianYiInfors.get(position).getContent());
        ((ViewHolder) holder).tv_dianzan.setText(jianYiInfors.get(position).getPraise()+"");
//        if (jianYiInfors.get(position).getComment() == null) {
//            ((ViewHolder) holder).linearLayout.setVisibility(View.GONE);
//        } else {
//            ((ViewHolder) holder).tv_answer.setText(jianYiInfors.get(position).getComment());
//        }
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(jianYiInfors.get(position).getTime()));

        ((ViewHolder) holder).tv_question.setOnClickListener(new ClickListener(String.valueOf(position), position));
        ((ViewHolder) holder).riv_avadar.setOnClickListener(new ClickHeadListener(String.valueOf(position), position));
        ((ViewHolder) holder).linearLayout.setOnClickListener(new ClickPicListener(String.valueOf(position), position));
//        if (mOnItemClickListener != null) {
//            //为ItemView设置监听器
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    mOnItemClickListener.onItemClick(holder.itemView, position);
//                }
//            });
//
//        }
    }

    @Override
    public int getItemCount() {
        return jianYiInfors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_question, tv_answer, tv_shijian, tv_dianzan;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener(this);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_jianyi_bottom);
            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.riv_xinsheng_avadar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_xinsheng_name);
            tv_question = (TextView) itemView.findViewById(R.id.tv_xinsheng_question);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_xinsheng_time);
            tv_dianzan = (TextView) itemView.findViewById(R.id.tv_xinsheng_dainzxan);
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


    //给文本添加点击事件
    public class ClickListener implements View.OnClickListener {//在这里我们重写了点击事件
        private String id;
        private int pos;

        public ClickListener(String id, int postion) {
            this.id = id;
            this.pos = postion;
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onTitleClick(id, pos);
            }
        }
    }

    //给头像添加点击事件
    public class ClickHeadListener implements View.OnClickListener {//在这里我们重写了点击事件
        private String id;
        private int pos;

        public ClickHeadListener(String id, int postion) {
            this.id = id;
            this.pos = postion;
        }

        @Override
        public void onClick(View view) {

            if (mHeadlistener != null) {
                mHeadlistener.onHeadClick(id, pos);
            }
        }
    }
    //给图片添加点击事件
    public class ClickPicListener implements View.OnClickListener {//在这里我们重写了点击事件
        private String id;
        private int pos;

        public ClickPicListener(String id, int postion) {
            this.id = id;
            this.pos = postion;
        }

        @Override
        public void onClick(View view) {

            if (mPiclistener != null) {
                mPiclistener.onPicClick(id, pos);
            }
        }
    }

    public void setOnTitleClickListener(OnTitleClickListener listener) {//自己写了一个方法，用上我们的接口
        mListener = listener;
    }


    public interface OnTitleClickListener {//自己写了一个点击事件的接口

        void onTitleClick(String id, int position);
    }

    public void setHeadClickListener(OnHeadClickListener listener) {//自己写了一个方法，用上我们的接口
        mHeadlistener = listener;
    }


    public interface OnHeadClickListener {//自己写了一个点击事件的接口

        void onHeadClick(String id, int position);
    }
    public void setOnPicClickListener(OnPicClickListener listener) {//自己写了一个方法，用上我们的接口
        mPiclistener = listener;
    }


    public interface OnPicClickListener {//自己写了一个点击事件的接口

        void onPicClick(String id, int position);
    }
}
