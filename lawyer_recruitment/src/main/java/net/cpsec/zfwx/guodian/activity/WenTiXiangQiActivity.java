package net.cpsec.zfwx.guodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.WenTiXiangQingAdapter;
import net.cpsec.zfwx.guodian.entity.QuanBuInfor;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题详情frangment
 * */

public class WenTiXiangQiActivity extends Activity implements View.OnClickListener {

    private ListView listView;
    private List<QuanBuInfor> mDatas;

    private ImageView iv_back;
    private TextView tv_title,tv_name,tv_time,tv_content;
    private RoundedImageView riv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_ti_xiang_qi_ctivity);
        initView();
        initDatas();

        Bundle bundle = getIntent().getExtras();
        String tupian = bundle.getString("image");
        Log.d("传到问题详情页面的图片地址", "image: "+tupian);

        setListViewHeightBasedOnChildren(listView);


    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.toolbar_nav_iv);
        iv_back.setOnClickListener( this);
        tv_title= (TextView) findViewById(R.id.tv_xiangqing_title);
        tv_name= (TextView) findViewById(R.id.tv_xiangqing_name);
        tv_time= (TextView) findViewById(R.id.tv_xiangqing_time);
        tv_content= (TextView) findViewById(R.id.tv_xiangqing_content);
        riv_head= (RoundedImageView) findViewById(R.id.roundedImageView2);

        //获取Bundle的信息
        Bundle b = getIntent().getExtras();
        Intent intent=getIntent();
        String str = intent.getExtras().getString("from");
        String tupian = intent.getExtras().getString("image");
        String userpic=intent.getExtras().getString("userpic");
        ImageLoader.getInstance().displayImage("http://"+userpic,riv_head);
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
            tv_content.setText("正文:"+b.getString("content"));
            tv_title.setText( "标题:"+b.getString("title"));
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

        WenTiXiangQingAdapter adapter = new WenTiXiangQingAdapter(this, imageUrls);
        listView.setFocusable(false);
        listView.setAdapter(adapter);


    }

    /**
     * 解决scrollview中用listview的冲突问题
     * 先行测量listview 的高
     * */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        WenTiXiangQingAdapter listAdapter = (WenTiXiangQingAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }



    private void initDatas() {

    }
    public class ViewHolder {
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
