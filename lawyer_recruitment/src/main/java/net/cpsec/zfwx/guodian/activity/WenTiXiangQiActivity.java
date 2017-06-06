package net.cpsec.zfwx.guodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.QuanBuInfor;

import java.util.ArrayList;
import java.util.List;

public class WenTiXiangQiActivity extends Activity implements View.OnClickListener {

    private ListView listView;
    private List<QuanBuInfor> mDatas;

    private ImageView iv_back;
    private TextView tv_title,tv_name,tv_time,tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_ti_xiang_qi_ctivity);
        initView();
        initDatas();

        Bundle bundle = getIntent().getExtras();
        String tupian = bundle.getString("image");
        Log.d("传到问题详情页面的图片地址", "image: "+tupian);




    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.toolbar_nav_iv);
        iv_back.setOnClickListener( this);
        tv_title= (TextView) findViewById(R.id.tv_xiangqing_title);
        tv_name= (TextView) findViewById(R.id.tv_xiangqing_name);
        tv_time= (TextView) findViewById(R.id.tv_xiangqing_time);
        tv_content= (TextView) findViewById(R.id.tv_xiangqing_content);

        //获取Bundle的信息
        Bundle b = getIntent().getExtras();
        Intent intent=getIntent();
        String str = intent.getExtras().getString("from");
        String tupian = intent.getExtras().getString("image");
        Log.d("取到的图片", "tupian: "+tupian);
        if ("1".equals(str)) {
            String username = b.getString("username");
            String content = b.getString("content");
            String asktime = b.getString("asktime");
            tv_name.setText(username);
            tv_time.setText(asktime);
            tv_content.setText(content);
        }else if ("2".equals(str)){
            String username = b.getString("username");
            String content = b.getString("content");
            String asktime = b.getString("time");
            tv_name.setText(username);
            tv_time.setText(asktime);
            tv_content.setText(content);
        }else if ("3".equals(str)){
            tv_name.setText( b.getString("username"));
            tv_time.setText( b.getString("time"));
            tv_content.setText(b.getString("content"));
            tv_title.setText( b.getString("title"));
        }
        else if ("4".equals(str)){
            tv_name.setText( b.getString("username"));
            tv_time.setText( b.getString("time"));
            tv_content.setText(b.getString("content"));
            tv_title.setText( b.getString("title"));
        }
        else if ("5".equals(str)){
            tv_name.setText( b.getString("username"));
            tv_time.setText( b.getString("time"));
            tv_content.setText(b.getString("content"));
            tv_title.setText( b.getString("title"));
        }





        //初始化（模拟）数据
        final ArrayList imageUrls  = new ArrayList<String>();
        String[] tupians=tupian.split(",");
        for(String substr:tupians){
            imageUrls.add("http://"+substr);
        }

        //得到控件
        listView = (ListView) findViewById(R.id.list_tupian);
        if(tupian.isEmpty()){
            listView.setVisibility(View.GONE);
        }

        //初始化适配器

        listView.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = getLayoutInflater().inflate(R.layout.item_wentixiangqing,
                            null);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.id_index_gallery_item_image);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                ImageLoader.getInstance().displayImage((String) imageUrls.get(position), holder.imageView);
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
        });
    }

    private void initDatas() {

    }
    class ViewHolder {
        ImageView imageView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_nav_iv:
                finish();
                break;
        }
    }
}
