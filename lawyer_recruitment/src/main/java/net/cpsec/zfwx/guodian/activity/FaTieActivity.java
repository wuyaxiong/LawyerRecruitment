package net.cpsec.zfwx.guodian.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.LabelAdapter;
import net.cpsec.zfwx.guodian.entity.Label;
import net.cpsec.zfwx.guodian.entity.MyDecoration;
import net.cpsec.zfwx.guodian.entity.MyItemClickListener;
import net.cpsec.zfwx.guodian.utils.Bimp;
import net.cpsec.zfwx.guodian.utils.BitmapToBase64;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.FileUtils;
import net.cpsec.zfwx.guodian.utils.ImageUtil;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.PictureUtil;
import net.cpsec.zfwx.guodian.utils.SelectPicPopupWindow;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FaTieActivity extends BaseActivity implements MyItemClickListener {
    private TextView tv_back, tv_complete, tv_label;
    private EditText et_zhuti, et_zhengwen;
    private LinearLayout ll_label;
    private RecyclerView recyclerView;
    List<Label> labelList;
    private LabelAdapter labelAdapter;
    private ImageView iv_add_tupian;
    private GridView gridView_pics;
    private GridAdapter gridAdapter;
    private Context context;
    private SelectPicPopupWindow window;// 弹出图片选择框；
    private String path = "";// 拍照返回的图片路径；
    List<String> stringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_tie);
        context = this;
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)//设置当前线程的优先级
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5对UIL进行加密命名
                .diskCacheSize(100 * 1024 * 1024)//50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(300)// 可以缓存的文件数量
                .tasksProcessingOrder(QueueProcessingType.LIFO)//后进先出
                .build();
        //初始化操作
        ImageLoader.getInstance().init(config);

        initView();
        initData();
    }


    private void initView() {
        tv_label = (TextView) findViewById(R.id.tv_fatie_label);
        tv_back = (TextView) findViewById(R.id.tv_fatie_back);
        tv_complete = (TextView) findViewById(R.id.tv_fatie_complete);
        et_zhuti = (EditText) findViewById(R.id.et_fatie_biaoti);
        et_zhengwen = (EditText) findViewById(R.id.et_fatie_zhengwen);
        ll_label = (LinearLayout) findViewById(R.id.ll_fatie_choose_label);
        recyclerView = (RecyclerView) findViewById(R.id.rv_fatie_label);
        ll_label.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        tv_complete.setOnClickListener(this);
        et_zhuti.setOnClickListener(this);
        et_zhengwen.setOnClickListener(this);
        GridLayoutManager labelManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(labelManager);

        iv_add_tupian = (ImageView) findViewById(R.id.iv_fatie_addtupian);
        iv_add_tupian.setOnClickListener(this);

        gridView_pics = (GridView) findViewById(R.id.gridview);
        gridView_pics.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridAdapter = new GridAdapter(this);
        gridAdapter.update();
        gridView_pics.setAdapter(gridAdapter);

        gridView_pics.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.bmp.size()) {
                    // 弹出选择图片：
                    //隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(FaTieActivity.this.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                    window = PictureUtil.toastChoicePic(FaTieActivity.this,
                            FaTieActivity.this, gridView_pics);
                } else {
                    Intent intent = new Intent(FaTieActivity.this,
                            PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }
    private void initData() {
        this.labelList = new ArrayList<Label>();
        for (int i = 0; i < 8; i++) {
            Label bean = new Label();
            bean.setName("青春" + i);
            labelList.add(bean);
        }
        this.labelAdapter = new LabelAdapter(labelList);
        this.recyclerView.setAdapter(labelAdapter);
        RecyclerView.ItemDecoration decoration = new MyDecoration(this);
        this.recyclerView.addItemDecoration(decoration);
        this.labelAdapter.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(View view, int postion) {
        Label bean = labelList.get(postion);
        if (bean != null) {
            tv_label.setText(bean.getName());
            recyclerView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_fatie_back:
                finish();
                break;
            case R.id.tv_fatie_complete:
                stringList=new ArrayList<String>();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < Bimp.bmp.size(); i++) {
                    Bitmap bt = Bimp.bmp.get(i);
                    sb.append(BitmapToBase64.bitmapToBase64(bt));
                    sb.append(",");
                }
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                Debugging.debugging("sb============"+sb.toString());
                //stringList.add(sb.toString());
//                for (int i = 0; i < Bimp.bmp.size(); i++) {
//                    Bitmap bt = Bimp.bmp.get(i);
//                    String s=BitmapToBase64.bitmapToBase64(bt);
//                    stringList.add(s);
//                    Debugging.debugging("sssssss====+++++++++"+s);
//                }
                Debugging.debugging("stringlist=================="+stringList.size());
              Debugging.debugging("stringlist====+++++++++"+Bimp.bmp.size());
               Debugging.debugging("stringlist+++++++++"+stringList.toString());
                RequestMap params = new RequestMap();
                params.put("uid", "3");
                params.put("label_id", "1");
                params.put("cid", "1");
                params.put("title", et_zhuti.getText().toString());
                params.put("content", et_zhengwen.getText().toString());
                params.put("images",sb.toString());
                setParams(NetUrl.FABIAO_TIEZI, params, 0);
                break;
            //添加标签
            case R.id.ll_fatie_choose_label:
                if (recyclerView.getVisibility() == View.GONE) {
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            //添加图片按钮
            case R.id.iv_fatie_addtupian:
//                if (arg2 == Bimp.bmp.size()) {
//                    // 弹出选择图片：
//                    //隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(FaTieActivity.this.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    window = PictureUtil.toastChoicePic(FaTieActivity.this,
                            FaTieActivity.this, gridView_pics);
//                } else {
//                    Intent intent = new Intent(FaTieActivity.this,
//                            PhotoActivity.class);
//                    intent.putExtra("ID", Bimp.bmp.size());
//                    startActivity(intent);
//
//        }
                //        startActivity(new Intent(this, SelectPictureActivity.class));
                break;
            // 拍照；
            case R.id.view_camero_rl_takephoto:
                window.dismiss();
                path = PictureUtil.paiZhao(window, this);
                break;
            // 从相册选择：
            case R.id.view_camero_rl_selectphoto:
                window.dismiss();
                Intent intent = new Intent(FaTieActivity.this,
                        TestPicActivity.class);
                startActivity(intent);
                break;
        }
    }
    protected void onRestart() {
        gridAdapter.update();
        super.onRestart();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    Bitmap bitmap = ImageUtil.getimage(path);
                    if (Bimp.drr.size() < 9) {
                        Bimp.drr.add(path);
                    }
                }
                break;
        }
    }
    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater; //
        private int selectedPosition = -1;//
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            return (Bimp.bmp.size() + 1);
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        /**
         * ListView Item
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            final int coord = position;
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == Bimp.bmp.size()) {
//                holder.image.setImageBitmap(BitmapFactory.decodeResource(
//                        getResources(), R.drawable.photo_close));
                holder.image.setVisibility(View.GONE);
                if (position == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                try {
                    holder.image.setImageBitmap(Bimp.bmp.get(position));
                } catch (Exception e) {
                    Log.e("TAg", e.getMessage());
                }
            }
            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        for (int i = Bimp.bmp.size(); i < Bimp.drr.size(); i++) {
                            String path = Bimp.drr.get(i);
                            // Bitmap bm = Bimp.revitionImageSize(path);
                            // 注意：必须得压缩，否则极有可能报oom;上传服务器也比较慢；
                            Bitmap bm = ImageUtil.getimage(path);
                            Bimp.bmp.add(bm);
                            Bimp.max += 1;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gridAdapter.notifyDataSetChanged();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public class ViewHolder {
        public ImageView image;
        public Button btn;
    }
    @Override
    protected void onDestroy() {
        initBimp();
        Log.e("TAG", "清除！！！！");
        super.onDestroy();
    }

    private void initBimp() {
        FileUtils.deleteDir();
        Bimp.drr.clear();
        Bimp.bmp.removeAll(Bimp.bmp);
        Bimp.act_bool = true;
        Bimp.max = 0;
    }


    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"发帖成功".equals(JSON.parseObject(response).getString("msg"))) {
                Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                return;
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }
}

