package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.QuanBuInfor;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/5/26.
 */

public class JiaoLiuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<QuanBuInfor> quanBuInfors;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;
    public OnTitleClickListener mListener;
    public OnHeadClickListener mHeadlistener;
    public OnPicClickListener mPiclistener;

    public JiaoLiuAdapter(Context context, List<QuanBuInfor> quanBuInfors) {
        this.context = context;
        this.quanBuInfors = quanBuInfors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_fenxiang01, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).img_01.setVisibility(View.GONE);
        //把javabean中的图片地址转化成list集合
        list = new ArrayList<String>();
        list.clear();
        String tupian = quanBuInfors.get(position).getImage();
        String[] tupians = tupian.split(",");
        for (String substr : tupians) {
            list.add(substr);
        }
        //给图片添加点击事件
        ((ViewHolder) holder).img_01.setOnClickListener(new ClickPicListener(String.valueOf(position), position));
        if (!list.get(0).isEmpty()) {
            ((ViewHolder) holder).img_01.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage("http://" + list.get(0), ((ViewHolder) holder).img_01);
        } else {
            ((ViewHolder) holder).img_01.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage("http://" + quanBuInfors.get(position).getUserpic(), ((ViewHolder) holder).riv_avadar);
        ((ViewHolder) holder).tv_name.setText(quanBuInfors.get(position).getUsername());
        ((ViewHolder) holder).tv_title.setText(quanBuInfors.get(position).getContent());
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(quanBuInfors.get(position).getTime()));
        ((ViewHolder) holder).tv_label.setText(quanBuInfors.get(position).getName());
        ((ViewHolder) holder).tv_title.setOnClickListener(new ClickListener(String.valueOf(position), position));
        ((ViewHolder) holder).riv_avadar.setOnClickListener(new ClickHeadListener(String.valueOf(position), position));
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



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_title, tv_label, tv_shijian, tv_dianzan, tv_huifu;
        private ImageView img_01;
        private LinearLayout layout_tupian;

        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());
            itemView.setOnClickListener(this);
            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.riv_jiaoliu_avater);

            tv_name = (TextView) itemView.findViewById(R.id.tv_jiaoliu_name);
            tv_title = (TextView) itemView.findViewById(R.id.tv_jiaoliu_title);
            tv_label = (TextView) itemView.findViewById(R.id.tv_jiaoliu_label);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_jiaoliu_time);
            tv_dianzan = (TextView) itemView.findViewById(R.id.tv_jiaoliu_dianzan);
            tv_huifu = (TextView) itemView.findViewById(R.id.tv_jiaoliu_huifu);
            img_01 = (ImageView) itemView.findViewById(R.id.img_jialiu_01);
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
