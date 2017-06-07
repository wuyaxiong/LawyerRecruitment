package net.cpsec.zfwx.guodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import net.cpsec.zfwx.guodian.R;

import java.util.List;

/**
 * Created by admina on 2017/6/5.
 */

public class WenTiXiangQingAdapter extends BaseAdapter {
    private  List<String> imageUrls;
    private LayoutInflater mInflater;
    public WenTiXiangQingAdapter(Context context, List<String> imageUrls) {
        mInflater = LayoutInflater.from(context);
        this.imageUrls = imageUrls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.item_wentixiangqing,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.id_index_gallery_item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage( imageUrls.get(position), holder.imageView);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }
    public class ViewHolder {
        ImageView imageView;
    }

}


