package net.cpsec.zfwx.guodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.utils.AlbumHelper;
import net.cpsec.zfwx.guodian.utils.ImageBucket;
import net.cpsec.zfwx.guodian.utils.ImageBucketAdapter;

import java.io.Serializable;
import java.util.List;

public class TestPicActivity extends Activity {
	// ArrayList<Entity> dataList;//阎剑娼电慭锻版祰閺佺增宓佸┃镒留壸掓銆?
	List<ImageBucket> dataList;
	GridView gridView;
	ImageBucketAdapter adapter;// 阏奉亚鐣炬稊澶屾留褒佞铡ら嵛锟?
	AlbumHelper helper;
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	public static Bitmap bimap;
	private ImageView iv_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_bucket);

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		initData();
		initView();
	}

	/**
	 * 壸掓繂颤愰崠镙ㄦ殶阉癸拷
	 */
	private void initData() {

		dataList = helper.getImagesBucketList(false);
		bimap = BitmapFactory.decodeResource(getResources(),
				R.drawable.photo);
	}

	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new ImageBucketAdapter(TestPicActivity.this, dataList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

				Intent intent = new Intent(TestPicActivity.this,
						ImageGridActivity.class);
				intent.putExtra(TestPicActivity.EXTRA_IMAGE_LIST,
						(Serializable) dataList.get(position).imageList);
				startActivity(intent);
				finish();
			}
		});

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
