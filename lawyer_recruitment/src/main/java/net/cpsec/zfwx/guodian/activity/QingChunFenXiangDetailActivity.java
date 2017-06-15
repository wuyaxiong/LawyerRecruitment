package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.PingLunAdapter;
import net.cpsec.zfwx.guodian.entity.Comment_info;
import net.cpsec.zfwx.guodian.entity.Goods_article_all;
import net.cpsec.zfwx.guodian.entity.MeiWenPingLunBean;
import net.cpsec.zfwx.guodian.entity.MeiWenPingLunInfor;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

public class QingChunFenXiangDetailActivity extends BaseActivity implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener {
    private TextView tv_title, tv_name, tv_time, tv_content;
    private ImageView iv_back;
    private YRecycleview yrv_list;
    private PingLunAdapter adapter;
    String gid;
    private List<Comment_info> coment_list;
    private MeiWenPingLunBean pinglunBean;
    private Goods_article_all good_artical;
    private MeiWenPingLunInfor pinglunInfor;
    private boolean isRefreshState = true;//是否刷新
    private EditText et_pinglun;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qing_chun_fen_xiang_detail);
        initView();
    }

    private void initView() {
        et_pinglun= (EditText) findViewById(R.id.et_meiwen_pinglun);
        btn= (Button) findViewById(R.id.btn_meiwen_pinglun);
        btn.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.toolbar_nav_iv);
        tv_title = (TextView) findViewById(R.id.tv_meiwendetail_title);
        tv_name = (TextView) findViewById(R.id.tv_meiwendetail_username);
        tv_time = (TextView) findViewById(R.id.tv_meiwendetail_time);
        tv_content = (TextView) findViewById(R.id.tv_meiwendetail_content);
        yrv_list = (YRecycleview) findViewById(R.id.yrv_pinglun_list);
        yrv_list.setRefreshAndLoadMoreListener(this);
        iv_back.setOnClickListener(this);
        Intent intent = getIntent();
        gid = intent.getExtras().getString("gid");
        initData();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("gid", gid + "");
        setParams(NetUrl.QINCHUN_MEIWEN_DETAIL, params, 0);
    }

    private void setAdapter() {
        if (isRefreshState && null != coment_list) {
            adapter = new PingLunAdapter(this, coment_list);
            yrv_list.setLayoutManager(new LinearLayoutManager(this));
            yrv_list.setNestedScrollingEnabled(false);
            yrv_list.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId){
            case 0:
                try {
                    pinglunBean = JSON.parseObject(response, MeiWenPingLunBean.class);
                    pinglunBean = JSON.parseObject(response, MeiWenPingLunBean.class);
                    Debugging.debugging("position      =      " + (null == pinglunBean));
                    coment_list = pinglunBean.getInfor().getComment_info();
                    good_artical = pinglunBean.getInfor().getGoods_article_all();
                    tv_title.setText(good_artical.getTitle());
                    tv_name.setText(good_artical.getUsername());
                    tv_time.setText(DateUtil.converTime(good_artical.getTime()));
                    tv_content.setText(good_artical.getContent());
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                setAdapter();
                break;
            case 1:
                try {
                    if (!"发表成功".equals(JSON.parseObject(response).getString("msg"))) {
                        Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                        return;
                    } else {
                        initData();
                        et_pinglun.setText("");
                        android.widget.Toast.makeText(this,"评论成功", android.widget.Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_nav_iv:
                finish();
                break;
            case R.id.btn_meiwen_pinglun:
                RequestMap params = new RequestMap();
                params.put("gid", gid + "");
                params.put("uid","329");
                params.put("status",1+"");
                params.put("comment",et_pinglun.getText().toString());
                setParams(NetUrl.QINGCHUN_MEIWEN_PINGLUN, params, 1);
                break;
        }
    }

    @Override
    public void onRefresh() {
        isRefreshState = true;
        yrv_list.setReFreshComplete();
        initData();
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yrv_list.setNoMoreData(true);
    }
}
