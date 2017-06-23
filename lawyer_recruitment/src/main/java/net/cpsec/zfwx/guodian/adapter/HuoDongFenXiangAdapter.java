package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.HuoDongFenXiang;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admina on 2017/5/27.
 */

public class HuoDongFenXiangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener{
    private Context context;
    List<HuoDongFenXiang.InforBean> infor;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;
    public HuoDongFenXiangAdapter (Context context,List<HuoDongFenXiang.InforBean> infor){
        this.context=context;
        this.infor=infor;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_huodong, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //把javabean中的图片地址转化成list集合
        list = new ArrayList<String>();
        list.clear();
        String tupian = infor.get(position).getImage();
        String[] tupians = tupian.split(",");
        for (String substr : tupians) {
            list.add(substr);
        }
        if (!list.get(0).isEmpty()) {
            ((ViewHolder) holder).iv_avadar.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage("http://" + list.get(0), ((ViewHolder) holder).iv_avadar);
        }
     //   else {
//            ((ViewHolder) holder).iv_avadar.setVisibility(View.GONE);
//        }
        ((ViewHolder) holder).tv_title.setText(infor.get(position).getTitle());
        ((ViewHolder) holder).tv_price.setText(infor.get(position).getNum()+"");
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(infor.get(position).getTime()));
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
        return infor.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_title, tv_price, tv_shijian;
        private ImageView iv_avadar;
        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());

            itemView.setOnClickListener((View.OnClickListener) this);

           iv_avadar = (ImageView) itemView.findViewById(R.id.imageView12);
            tv_title = (TextView) itemView.findViewById(R.id.tv_huodong_title);
            tv_price = (TextView) itemView.findViewById(R.id.tv_huodong_price);
            tv_shijian = (TextView) itemView.findViewById(R.id.tv_huodong_time);
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
