package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ImageViewPagerAdapter;
import net.cpsec.zfwx.guodian.view.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

public class PicCheckActivity extends AppCompatActivity {
    ImageViewPagerAdapter adapter;
    HackyViewPager pager;
    List<String> list = new ArrayList<>();
    Intent intent;
    String images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_check);
        intent = getIntent();
        images = intent.getStringExtra("images");
        pager = (HackyViewPager) findViewById(R.id.pager);
        initView();

    }

    private void initView() {
            String[] tupians = images.split(",");
            //每次刷新前清空图片列表
            list.clear();
            for (String substr : tupians) {
                list.add("http://" + substr);
            }
        adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
    }
}
