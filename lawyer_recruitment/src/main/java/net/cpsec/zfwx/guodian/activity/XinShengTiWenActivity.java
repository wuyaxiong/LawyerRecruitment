package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import net.cpsec.zfwx.guodian.R;

import java.util.ArrayList;

import static net.cpsec.zfwx.guodian.R.id.gridview_tiwen;

public class XinShengTiWenActivity extends BaseActivity {
    private TextView tv_back, tv_complete;
    private EditText et_zhuti, et_zhengwen;
    private ImageView iv_addpic;
    private GridView gridView_pics;

    private GridAdapter gridAdapter;

    private Context context;

    private static final int REQUEST_PICK = 0;

    //存放所有选择的照片
    private ArrayList<String> allSelectedPicture = new ArrayList<String>();

    //存放从选择界面选择的照片
    private ArrayList<String> selectedPicture = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_sheng_ti_wen);
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
    }

    private void initView() {
        iv_addpic = (ImageView) findViewById(R.id.iv_tiwen_addtupian);
        iv_addpic.setOnClickListener(this);
        tv_back = (TextView) findViewById(R.id.tv_tiwen_back);
        et_zhuti = (EditText) findViewById(R.id.et_tiwen_zhuti);
        et_zhengwen = (EditText) findViewById(R.id.et_tiwen_zhengwen);
        tv_back.setOnClickListener(this);

        gridView_pics = (GridView) findViewById(gridview_tiwen);
        gridAdapter = new GridAdapter();
        gridView_pics.setAdapter(gridAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_tiwen_back:
                finish();
                break;
            case R.id.iv_tiwen_addtupian:
                selectClick();
                break;
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

                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectClick();
                    }
                });
                if (position == 3) {
                    // holder.image.setImageBitmap(null);
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
        intent.setClass(XinShengTiWenActivity.this, SelectPictureActivity.class);
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

        if (resultCode == RESULT_OK) {
            selectedPicture = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
            for (String str : selectedPicture) {
                if (!allSelectedPicture.contains(str)) {
                    allSelectedPicture.add(str);

                    gridView_pics.setAdapter(gridAdapter);
                }
            }
        }
    }
}
