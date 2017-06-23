package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.ShengDetail;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/6/16.
 */

public class XinShengAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<ShengDetail> shengList;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;
    public OnTitleClickListener mListener;
    public OnHeadClickListener mHeadlistener;

    public XinShengAdapter(Context context, List<ShengDetail> shengList) {
        this.context = context;
        this.shengList = shengList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_xinsheng, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).img_01.setVisibility(View.GONE);
        //把javabean中的图片地址转化成list集合
        list = new ArrayList<String>();
        list.clear();
        String tupian = shengList.get(position).getImage();
        String[] tupians = tupian.split(",");
        for (String substr : tupians) {
            list.add(substr);
        }
        if (!list.get(0).isEmpty()) {
            ((ViewHolder) holder).img_01.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage("http://" + list.get(0), ((ViewHolder) holder).img_01);
        } else {
            ((ViewHolder) holder).img_01.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage("http://" + shengList.get(position).getUserpic(), ((ViewHolder) holder).riv_avadar);
        ((ViewHolder) holder).tv_name.setText(shengList.get(position).getUsername());
        ((ViewHolder) holder).tv_title.setText(shengList.get(position).getContent());
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(shengList.get(position).getAsktime()));
        //((ViewHolder) holder).tv_dianzan.setText(shengList.get(position).getPraise()+"");
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
        return shengList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_title, tv_shijian, tv_dianzan;
        private ImageView img_01;
        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());
            itemView.setOnClickListener(this);
            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.riv_xinsheng_avater);
            tv_name = (TextView) itemView.findViewById(R.id.tv_xinsheng_name);
            tv_title = (TextView) itemView.findViewById(R.id.tv_xinsheng_title);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_xinsheng_time);
            tv_dianzan = (TextView) itemView.findViewById(R.id.tv_xinsheng_dainzxan);
            img_01 = (ImageView) itemView.findViewById(R.id.img_xinsheng_01);
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

    public class ClickHeadListener implements View.OnClickListener {//在这里我们重写了点击事件
        private String id;
        private int pos;

        public ClickHeadListener(String id, int postion) {
            this.id = id;
            this.pos = postion;
        }

        @Override
        public void onClick(View view) {

            if (mHeadlistener!=null){
                mHeadlistener.onHeadClick(id,pos);
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
}