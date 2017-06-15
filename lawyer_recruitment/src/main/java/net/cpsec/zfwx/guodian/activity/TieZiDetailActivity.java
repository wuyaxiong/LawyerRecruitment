package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.TieZiPIngLunAdapter;
import net.cpsec.zfwx.guodian.adapter.WenTiXiangQingAdapter;
import net.cpsec.zfwx.guodian.entity.TieZiComment_info;
import net.cpsec.zfwx.guodian.entity.TieZiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TieZiDetailActivity extends BaseActivity {
    private ImageView iv_back;
    private RoundedImageView head;
    private TextView tv_name, tv_time, tv_content, tv_title, tv_label, tv_dainzan, tv_pinglun;
    private ListView list_image, lv_pinglun;
    String artical_id;
    private TieZiDetailBean tieZiDetailBean;
    String images;
    private EditText et_pinglun;
    private Button btn_pinglun;
    //初始化（模拟）数据
    final ArrayList imageUrls = new ArrayList<String>();
    List<TieZiComment_info> coment_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tie_zi_detail);
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("article_id", artical_id);
        setParams(NetUrl.TIEZI_DETAIL, params, 0);
    }

    private void initView() {
        Intent intent = getIntent();
        artical_id = intent.getStringExtra("artical_id");
        Debugging.debugging("artical_id" + artical_id);
        iv_back = (ImageView) findViewById(R.id.toolbar_nav_iv);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        head = (RoundedImageView) findViewById(R.id.riv_tiezidetail_head);
        tv_name = (TextView) findViewById(R.id.tv_tiezixiangqing_name);
        tv_title = (TextView) findViewById(R.id.tv_tiezixiangqing_title);
        tv_time = (TextView) findViewById(R.id.tv_tiezixiangqing_time);
        tv_content = (TextView) findViewById(R.id.tv_tiezixiangqing_content);
        list_image = (ListView) findViewById(R.id.list_tizi_tupian);
        lv_pinglun = (ListView) findViewById(R.id.lv_tiezi_pinglun);
        et_pinglun = (EditText) findViewById(R.id.et_tiezi_pinglun);
        btn_pinglun = (Button) findViewById(R.id.btn_tiezi_pinglun);
        //  tv_label= (TextView) findViewById(R.id.tv_tiezi_label);
        tv_dainzan = (TextView) findViewById(R.id.tv_tiezi_dianzan);
        tv_pinglun = (TextView) findViewById(R.id.tv_tiezi_huifu);
        et_pinglun = (EditText) findViewById(R.id.et_tiezi_pinglun);
        btn_pinglun = (Button) findViewById(R.id.btn_tiezi_pinglun);
        btn_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pinglun.getText().toString().trim()==null){
                    android.widget.Toast.makeText(TieZiDetailActivity.this,"评论内容不能为空！",android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    RequestMap params = new RequestMap();
                    params.put("aid", artical_id);
                    params.put("uid","329");
                    params.put("content",et_pinglun.getText().toString());
                    setParams(NetUrl.TIEZI_PINGLUN, params, 1);
                }
                }
        });
        initData();
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 0:
                try {
                    tieZiDetailBean = JSON.parseObject(response, TieZiDetailBean.class);
                    Debugging.debugging("position      =      " + (null == tieZiDetailBean));
                    String userpic = tieZiDetailBean.getInfor().getForum_info().getUserpic();
                    String username = tieZiDetailBean.getInfor().getForum_info().getUsername();
                    String title = tieZiDetailBean.getInfor().getForum_info().getTitle();
                    String content = tieZiDetailBean.getInfor().getForum_info().getContent();
                    // String label=tieZiDetailBean.getInfor().getForum_info().get
                    // String images = tieZiDetailBean.getInfor().getForum_info().getImage();
                    int prise_num = tieZiDetailBean.getInfor().getPrise_num();
                    int coment_num = tieZiDetailBean.getInfor().getComment_num();
                    ImageLoader.getInstance().displayImage("http://" + userpic, head);
                    int time = tieZiDetailBean.getInfor().getForum_info().getTime();
                    tv_title.setText(title);
                    tv_name.setText(username);
                    tv_content.setText(content);
                    tv_time.setText(DateUtil.converTime(time));
                    tv_dainzan.setText(prise_num + "");
                    tv_pinglun.setText(coment_num + "");
                    images = tieZiDetailBean.getInfor().getForum_info().getImage().toString();
                    if (images.isEmpty() || images == null) {
                        list_image.setVisibility(View.GONE);
                    } else {
                        String[] tupians = images.split(",");
                        Debugging.debugging("images==============" + images);
                        for (String substr : tupians) {
                            imageUrls.add("http://" + substr);
                            list_image.setVisibility(View.VISIBLE);
                        }
                    }
                    //初始化适配器
                    WenTiXiangQingAdapter adapter = new WenTiXiangQingAdapter(this, imageUrls);
                    //list_image.setFocusable(false);
                    list_image.setAdapter(adapter);
                    Utility.setListViewHeightBasedOnChildren(list_image);
                    Utility.setListViewHeightBasedOnChildren(lv_pinglun);
                    coment_info = tieZiDetailBean.getInfor().getComment_info();
                    TieZiPIngLunAdapter pinlunAdapter = new TieZiPIngLunAdapter(this, coment_info);
                    lv_pinglun.setAdapter(pinlunAdapter);
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 1:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "评论失败，稍后重试！");
                } else {
                    images=null;
                    initData();
                    Toast.prompt(this, "评论成功！");
                    et_pinglun.setText("");
                }
                break;
        }
    }
}
