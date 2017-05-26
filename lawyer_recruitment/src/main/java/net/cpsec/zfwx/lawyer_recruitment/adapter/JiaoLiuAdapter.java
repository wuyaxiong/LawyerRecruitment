package net.cpsec.zfwx.lawyer_recruitment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.entity.QuanBuInfor;
import net.cpsec.zfwx.lawyer_recruitment.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/26.
 */

public class JiaoLiuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<QuanBuInfor> quanBuInfors;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_name.setText(quanBuInfors.get(position).getUsername());
        ((ViewHolder) holder).tv_title.setText(quanBuInfors.get(position).getContent());
       // ((ViewHolder) holder).tv_answer.setText(null);
        ((ViewHolder) holder).tv_shijian.setText(quanBuInfors.get(position).getTime()+"");
         ((ViewHolder) holder).tv_label.setText(quanBuInfors.get(position).getName());
        //((ViewHolder) holder).tv_dianzan.setText(quanBuInfors.get(position).getPraise()+"");
    }

    @Override
    public int getItemCount() {
        return quanBuInfors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RoundedImageView riv_avadar;
        private TextView tv_name, tv_title, tv_label, tv_shijian, tv_dianzan,tv_huifu;

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
            tv_huifu=(TextView) itemView.findViewById(R.id.tv_jiaoliu_huifu);
        }

        @Override
        public void onClick(View v) {
//TODO
        }
    }
}
