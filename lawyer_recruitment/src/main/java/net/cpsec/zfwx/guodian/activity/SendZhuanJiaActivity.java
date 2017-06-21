package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ZhuanJiaAdapter;
import net.cpsec.zfwx.guodian.entity.ZhuanJiaBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

public class SendZhuanJiaActivity extends BaseActivity implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener{
    private YRecycleview yRecycleview;
    private ZhuanJiaAdapter adapter;
    private ImageView iv_back;
    private int resultCode = 3;
    private boolean isRefreshState = true;//是否刷新
    private List<ZhuanJiaBean.InforBean> zhuanJiaInfor;
    private ZhuanJiaBean zhuanJiaBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_zhuan_jia);
        initView();
        initData();
    }
    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        yRecycleview= (YRecycleview) findViewById(R.id.sendzj_zhuanjia);
        yRecycleview.setRefreshAndLoadMoreListener(this);

    }
    //请求数据
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.ZHUANJIA, params, 0);
    }
    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            zhuanJiaBean = JSON.parseObject(response, ZhuanJiaBean.class);
            Log.e("专家页面", "zhuanJiaBean"+zhuanJiaBean.toString() );

            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                zhuanJiaInfor = zhuanJiaBean.getInfor();
            } else {
                zhuanJiaInfor = zhuanJiaBean.getInfor();
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }


    private void setAdapter() {
        if (isRefreshState && null != zhuanJiaInfor) {
            adapter = new ZhuanJiaAdapter(this, zhuanJiaInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(this));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        //touPiaoInfor  =  [InforBean{vote_id=1, admin_id=1, title='最美国电人', time=1496735800, description='各分（子）公司、研究院、基层企业员工：
        //目前，由国资委宣传局开展的“最美国电人”征集活动已进入公众投票阶段。在本次评选活动中，以下员工成功入选。为宣传具有国电特色的先进典型，
        // 以典型宣传讲好“国电故事”，请按照有关要求，积极参与投票。'},
        // InforBean{vote_id=2, admin_id=1, title='发表意见', time=1496735800, description='各部门积极发表意见'}]

        adapter.setOnItemClickListener(new ZhuanJiaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Intent intent=new Intent(SendZhuanJiaActivity.this, FaTieActivity.class);
                ZhuanJiaBean.InforBean infor= zhuanJiaInfor.get(position-1);
               // Bundle bundle=new Bundle();
                Log.e("专家页面", "infor.getUsername()"+infor.getUsername() );
               // bundle.putString("zhuanjia_name",infor.getUsername());
                //intent.putExtras(bundle);

                Intent mIntent = new Intent();
                mIntent.putExtra("zhuanjia_name", infor.getUsername());
                // 设置结果，并进行传送
                //SendZhuanJiaActivity.this.setResult(RESULT_OK, mIntent);//RESULT_OK为自定义常量
                SendZhuanJiaActivity.this.setResult(resultCode, mIntent);
                SendZhuanJiaActivity.this.finish();
            }
        });
    }


    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
        //Toast.prompt(this, "刷新完成。测试阶段");
    }

    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
       // Toast.prompt(this, "没有更多数据。测试阶段");
    }
}
