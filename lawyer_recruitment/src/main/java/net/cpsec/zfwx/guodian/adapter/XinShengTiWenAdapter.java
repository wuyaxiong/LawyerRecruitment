package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by szh on 2017/5/15.
 */

public class XinShengTiWenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<ShengDetail> shengList;
    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;
    public XinShengTiWenAdapter(Context context, List<ShengDetail> shengList) {
        this.context = context;
        this.shengList = shengList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tiwen, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //把javabean中的图片地址转化成list集合
        list = new ArrayList<String>();
        list.clear();
        String tupian=shengList.get(position).getImage();
        String[] tupians=tupian.split(",");
        for(String substr:tupians){
            list.add(substr);
        }
        ImageLoader.getInstance().displayImage("http://"+shengList.get(position).getUserpic(),((ViewHolder) holder).riv_avadar);
        ((ViewHolder) holder).tv_name.setText(shengList.get(position).getUsername());
        ((ViewHolder) holder).tv_question.setText(shengList.get(position).getContent());
        ((ViewHolder) holder).tv_answer.setText(null);
        ((ViewHolder) holder).tv_shijian.setText(DateUtil.converTime(shengList.get(position).getAsktime()));
        // ((ViewHolder) holder).tv_shijian.setText(shengList.get(position).getAsktime());
        ((ViewHolder) holder).tv_dianzan.setText(shengList.get(position).getPraise() + "");
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


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_question, tv_answer, tv_shijian, tv_dianzan;
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
        }

        @Override
        public void onClick(View v) {
//TODO
        }
    }
}