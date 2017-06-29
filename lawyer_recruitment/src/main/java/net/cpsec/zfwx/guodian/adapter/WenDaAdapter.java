package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.MyWenDaBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.List;

/**
 * Created by szh on 2017/5/15.
 */

public class WenDaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<MyWenDaBean.InforBean> list;
    private OnItemClickListener mOnItemClickListener = null;
    public WenDaAdapter (Context context,List<MyWenDaBean.InforBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_wotiwen, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tv_time.setText(DateUtil.converTime(list.get(position).getTime()));
        ((ViewHolder) holder).tv_title.setText(list.get(position).getTitle());
        ((ViewHolder) holder).tv_pinglun.setText(list.get(position).getIs_report()+"");
        ((ViewHolder) holder).tv_prise.setText(list.get(position).getPraise()+"");
        int isok=list.get(position).getIs_ok();
        if (isok==0){
            ((ViewHolder) holder).tv_isok.setText("未解决");
        }else {
            ((ViewHolder) holder).tv_isok.setText("已解决");
        }
       if (mOnItemClickListener!=null){
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_title,tv_time,tv_prise,tv_pinglun,tv_isok;
        public ViewHolder(View itemView) {
            super(itemView);
            LocalDisplay.init(itemView.getContext());
            itemView.setOnClickListener(this);
            tv_title= (TextView) itemView.findViewById(R.id.tv_wenda_title);
            tv_isok= (TextView) itemView.findViewById(R.id.tv_isok);
            tv_time= (TextView) itemView.findViewById(R.id.tv_wenda_time);
            tv_pinglun= (TextView) itemView.findViewById(R.id.tv_wenda_huifu);
            tv_prise= (TextView) itemView.findViewById(R.id.tv_wenda_dainzxan);
        }

        @Override
        public void onClick(View v) {

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
