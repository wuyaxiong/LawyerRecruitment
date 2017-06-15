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
import net.cpsec.zfwx.guodian.entity.Comment_info;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/6/12.
 */

public class PingLunAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Comment_info> infors;
//    private OnItemClickListener mOnItemClickListener = null;
    List<String> list;

    public PingLunAdapter(Context context, List<Comment_info> quanBuInfors) {
        this.context = context;
        this.infors = quanBuInfors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pinglun1, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage("http://"+infors.get(position).getUserpic(),((ViewHolder) holder).riv_avadar);
        ((ViewHolder) holder).tv_name.setText(infors.get(position).getUsername());
        ((ViewHolder) holder).tv_content.setText(infors.get(position).getComment());
       ((ViewHolder) holder).tv_time.setText(DateUtil.converTime(infors.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return infors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_content, tv_time;
        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());
            //itemView.setOnClickListener(this);
            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.iv_pinglun_head);
            tv_name = (TextView) itemView.findViewById(R.id.tv_pinglun_username);
            tv_content = (TextView) itemView.findViewById(R.id.tv_pinglun_content);
            tv_time = (TextView) itemView.findViewById(R.id.tv_pinglun_time);

        }
        }


    }

//    public static interface OnItemClickListener {
//        void onItemClick(View view, int position);
//    }

//    @Override
//    public void onClick(View v) {
//        if (mOnItemClickListener != null) {
//            //注意这里使用getTag方法获取position
//            mOnItemClickListener.onItemClick(v, (int) v.getTag());
//        }
//    }

//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }
//}
//    private Context context;
//    private List<Comment_info> infors;
//    List<String> list;
//    public PingLunAdapter(Context context, List<Comment_info> quanBuInfors) {
//        this.context = context;
//        this.infors = quanBuInfors;
//    }
//}
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item_pinglun1, null);
//        return new ViewHolder(v);
//    }

//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//        ImageLoader.getInstance().displayImage("http://"+infors.get(position).getUserpic(),((ViewHolder) holder).riv_avadar);
//        ((ViewHolder) holder).tv_name.setText(infors.get(position).getUsername());
//        ((ViewHolder) holder).tv_content.setText(infors.get(position).getComment());
//        ((ViewHolder) holder).tv_time.setText(infors.get(position).getTime());
//((ViewHolder) holder).tv_dianzan.setText(quanBuInfors.get(position).getPraise()+"");
//        if (mOnItemClickListener != null) {
//            //为ItemView设置监听器
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    mOnItemClickListener.onItemClick(holder.itemView, position);
//                }
//            });

        //}
//
//    public class ViewHolder extends RecyclerView.ViewHolder  {
//        private RoundedImageView riv_avadar;
//        private TextView tv_name, tv_content, tv_time;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            LocalDisplay.init(itemView.getContext());
//            //itemView.setOnClickListener(this);
//            riv_avadar = (RoundedImageView) itemView.findViewById(R.id.iv_pinglun_head);
//            tv_name = (TextView) itemView.findViewById(R.id.tv_pinglun_username);
//            tv_content = (TextView) itemView.findViewById(R.id.tv_pinglun_content);
//            tv_time = (TextView) itemView.findViewById(R.id.tv_pinglun_time);
//
//        }
//
//    }
//}
//    public static interface OnItemClickListener {
//        void onItemClick(View view, int position);
//    }

//    @Override
//    public void onClick(View v) {
//        if (mOnItemClickListener != null) {
//            //注意这里使用getTag方法获取position
//            mOnItemClickListener.onItemClick(v, (int) v.getTag());
//        }
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }
//}
