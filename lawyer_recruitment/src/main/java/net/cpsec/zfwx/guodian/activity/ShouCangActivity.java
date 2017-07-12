package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ShouCangMeiWenAdapter;
import net.cpsec.zfwx.guodian.adapter.ShouCangTieZiAdapter;
import net.cpsec.zfwx.guodian.adapter.ShouCangTongZhiAdapter;
import net.cpsec.zfwx.guodian.entity.ShouCang;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.view.NoScrollListView;

import java.util.Map;

import static net.cpsec.zfwx.guodian.R.id.iv_wenda;

/**
 * 我收藏的帖子页面
 */
public class ShouCangActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    int pos;
    String uid;
    private ImageView iv_NO;
    private NoScrollListView lv_tiezi, lv_tongzhi, lv_meiwen;
    private ShouCangTieZiAdapter adapterTiezi;
    private ShouCangTongZhiAdapter adapterTongzhi;
    private ShouCangMeiWenAdapter adapterMeiWen;
    private ShouCang infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView();
        initData();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_NO = (ImageView) findViewById(iv_wenda);
        lv_tiezi = (NoScrollListView) findViewById(R.id.nolv_tiezi);
        lv_tongzhi = (NoScrollListView) findViewById(R.id.nolv_tongzhi);
        lv_meiwen = (NoScrollListView) findViewById(R.id.nolv_meiwen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //数据请求
    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid);
        //因为接口问题，先用全部帖子接口   CENTER_GUANZHU_TIEZI
        setParams(NetUrl.CENTER_SHOUCANG, params, 1);
    }

    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                iv_NO.setVisibility(View.GONE);
            } else {
                infor = JSON.parseObject(response, ShouCang.class);
                if (infor.getInfor().getArticlelist() == null && infor.getInfor().getNoticelist() == null && infor.getInfor().getGoodslist() == null) {
                    lv_tiezi.setVisibility(View.GONE);
                    lv_tongzhi.setVisibility(View.GONE);
                    lv_tongzhi.setVisibility(View.GONE);
                    iv_NO.setVisibility(View.GONE);
                }else {
                    iv_NO.setVisibility(View.GONE);
                    if (infor.getInfor().getArticlelist() == null) {
                        lv_tiezi.setVisibility(View.GONE);
                    } else {
                        lv_tiezi.setVisibility(View.VISIBLE);
                        adapterTiezi = new ShouCangTieZiAdapter(this, infor.getInfor());
                        lv_tiezi.setAdapter(adapterTiezi);
                        lv_tiezi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ShouCangActivity.this, TieZiDetailActivity.class);
                                pos = infor.getInfor().getArticlelist().get(position).get(0).getId();
                                Bundle bundle = new Bundle();
                                bundle.putString("artical_id", pos + "");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                    if (infor.getInfor().getNoticelist() == null) {
                        lv_tongzhi.setVisibility(View.GONE);
                    } else {
                        lv_tongzhi.setVisibility(View.VISIBLE);
                        adapterTongzhi = new ShouCangTongZhiAdapter(this, infor.getInfor());
                        lv_tongzhi.setAdapter(adapterTongzhi);
                        lv_tongzhi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ShouCangActivity.this, ZhengCeTongZhiDetailActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("from", "1");
                                bundle.putString("aid", infor.getInfor().getNoticelist().get(position).get(0).getId() + "");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                    if (infor.getInfor().getGoodslist() == null) {
                        lv_meiwen.setVisibility(View.GONE);
                    } else {
                        lv_meiwen.setVisibility(View.VISIBLE);
                        adapterMeiWen = new ShouCangMeiWenAdapter(this, infor.getInfor());
                        lv_meiwen.setAdapter(adapterMeiWen);
                        lv_meiwen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ShouCangActivity.this, QingChunFenXiangDetailActivity.class);
                                //  AnLiMeiWenInfor infor=anLiMeiWenInfors.get(position-1);
                                Bundle bundle = new Bundle();
                                bundle.putString("from", "1");
                                bundle.putString("gid", infor.getInfor().getGoodslist().get(position).get(0).getId() + "");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }
}
