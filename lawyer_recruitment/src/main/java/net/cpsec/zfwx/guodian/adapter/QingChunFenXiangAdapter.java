package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.ZhengCeTongZhiInfor;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/15.
 */

public class QingChunFenXiangAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<ZhengCeTongZhiInfor> quanBuInfors;

    public QingChunFenXiangAdapter(Context context, List<ZhengCeTongZhiInfor> quanBuInfors) {
        this.context = context;
        this.quanBuInfors = quanBuInfors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_meiwen, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_companyname.setText(quanBuInfors.get(position).getCname());
        ((ViewHolder) holder).tv_title.setText(quanBuInfors.get(position).getTitle());
        ((ViewHolder) holder).tv_shijian.setText(quanBuInfors.get(position).getTime()+"");
    }

    @Override
    public int getItemCount() {
        return quanBuInfors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_avadar;
        private TextView  tv_title, tv_companyname, tv_shijian;

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
//TODO
        }
    }
}
