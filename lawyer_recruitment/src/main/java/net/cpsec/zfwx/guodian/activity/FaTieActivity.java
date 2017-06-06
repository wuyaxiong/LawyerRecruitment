package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.loopj.android.http.Base64;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.LabelAdapter;
import net.cpsec.zfwx.guodian.entity.Label;
import net.cpsec.zfwx.guodian.entity.MyDecoration;
import net.cpsec.zfwx.guodian.entity.MyItemClickListener;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.cpsec.zfwx.guodian.R.id.gridview;

public class FaTieActivity extends BaseActivity implements MyItemClickListener {
    private TextView tv_back, tv_complete, tv_label;
    private EditText et_zhuti, et_zhengwen;
    private LinearLayout ll_label;
    private RecyclerView recyclerView;
    List<Label> labelList;
    private LabelAdapter labelAdapter;

    // TextView tvImageNumber;
    private ImageView iv_add_tupian;
    // LinearLayout llImageContainer;
    private GridView gridView_pics;

    private GridAdapter gridAdapter;

    private Context context;

    private static final int REQUEST_PICK = 0;

    //存放所有选择的照片
    private ArrayList<String> allSelectedPicture = new ArrayList<String>();

    //存放从选择界面选择的照片
    private ArrayList<String> selectedPicture = new ArrayList<String>();

    //存放从选择界面选择的照片
    private ArrayList<String> base64Picture = new ArrayList<String>();
    String[] array;

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

        gridView_pics = (GridView) findViewById(gridview);
        gridAdapter = new GridAdapter();
        gridView_pics.setAdapter(gridAdapter);
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_fatie_back:
                finish();
                break;
            case R.id.tv_fatie_complete:
                //TODO
                RequestMap params = new RequestMap();
                params.put("uid", "3");
                params.put("label_id", "1");
                params.put("cid", "1");
                params.put("title", et_zhuti.getText().toString());
                params.put("content", et_zhengwen.getText().toString());
                params.put("images",array.toString());
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
                selectClick();
                //        startActivity(new Intent(this, SelectPictureActivity.class));
                break;
        }
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

    @Override
    public void onItemClick(View view, int postion) {
        Label bean = labelList.get(postion);
        if (bean != null) {
            tv_label.setText(bean.getName());
            recyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * 展示图片的GridView的适配器
     */
    class GridAdapter extends BaseAdapter {

        public LayoutInflater layoutInflater = LayoutInflater.from(context);

        @Override
        public int getCount() {
            return allSelectedPicture.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.childgrid_item, null);
                holder.image = (ImageView) convertView.findViewById(R.id.child_iv);
                holder.btn = (Button) convertView.findViewById(R.id.child_delete);
                holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == allSelectedPicture.size()) {
                holder.image.setVisibility(View.GONE);
                holder.btn.setVisibility(View.GONE);
                if (position == 3) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                ImageLoader.getInstance().displayImage("file://" + allSelectedPicture.get(position),
                        holder.image);

                holder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击后移除图片
                        allSelectedPicture.remove(position);
                        //更新UI
                        gridView_pics.setAdapter(gridAdapter);

                    }
                });

            }
            return convertView;
        }
    }

    public class ViewHolder {
        public ImageView image;
        public Button btn;
    }

    private void selectClick() {
        Intent intent = new Intent();
        intent.setClass(FaTieActivity.this, SelectPictureActivity.class);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("allSelectedPicture", allSelectedPicture);
        intent.putExtras(bundle);

        if (allSelectedPicture.size() < 3) {
            startActivityForResult(intent, REQUEST_PICK);
        }
    }

    //  @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bmp;
        String s;
        if (resultCode == RESULT_OK) {
            selectedPicture = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
            for (String str : selectedPicture) {
                if (!allSelectedPicture.contains(str)) {
                    allSelectedPicture.add(str);
                    gridView_pics.setAdapter(gridAdapter);
                }
            }
        }
        for (int i = 0; i < allSelectedPicture.size(); i++) {

            bmp = getimage(allSelectedPicture.get(i));
            s = bitmapToBase64(bmp);
            Debugging.debugging("SSSSSSS==" + s);
            base64Picture.add(s);
        }
        for(int i=0;i<base64Picture.size();i++){
            array=new String[base64Picture.size()];
            array[i]=(String)base64Picture.get(i);
        }
        Debugging.debugging(allSelectedPicture.toString());
        Debugging.debugging("array======"+array);
    }

    /**
     * 02. * 图片按比例大小压缩方法
     * 03. *
     * 04. * @param srcPath （根据路径获取图片并压缩）
     * 05. * @return
     * 06.
     */
    public static Bitmap getimage(String srcPath) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 720f;// 这里设置高度为800f
        float ww = 420f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        //return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        return Bitmap.createBitmap(100, 100, Bitmap.Config.RGB_565);
        //return bitmap;
    }

    /**
     * 02.  * bitmap转为base64
     * 03.  * @param bitmap
     * 04.  * @return
     * 05.
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}

