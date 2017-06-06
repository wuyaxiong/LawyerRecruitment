package net.cpsec.zfwx.guodian.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.ui.HeadDiaLog;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.widget.DialogHint;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class XiuGaiXinXiActivity extends BaseActivity implements View.OnClickListener,DialogHint.IRollSelector, HeadDiaLog.Picture, RadioGroup.OnCheckedChangeListener {
    private ImageView iv_back;
    private RoundedImageView riv_header;
    private EditText etName, etJianjie, etBirth, etMianmao, etDanwei, etaAddress;
    TextView tv_complete;
    RadioButton rbt_man, rbt_wuman;
    RadioGroup rgender;
    private String gender;
    String sex;
    private Bitmap head;
    private HeadDiaLog headDiaLog;
    private Dialog dialog;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_xin_xi);
        initView();
        initHead();
    }


    private void initView() {
        rgender= (RadioGroup) findViewById(R.id.rg_gender);
        rgender.setOnCheckedChangeListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_complete = (TextView) findViewById(R.id.tv_xinxi_complete);
        if (" 男".equals(gender)) {
            sex=0+"";
        } else {
            sex=1+"";
        }
        tv_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMap params = new RequestMap();
                params.put("uid",3+"");
                params.put("userpic",bitmapToBase64(head));
                Debugging.debugging("userpic=="+bitmapToBase64(head));
                params.put("username", etName.getText().toString());
                params.put("introduction", etJianjie.getText().toString());
                params.put("sex", sex);
                Debugging.debugging("sex=="+sex);
                params.put("birth",etBirth.getText().toString());
                params.put("background",etMianmao.getText().toString());
                params.put("address",etaAddress.getText().toString());
                params.put("cname",etDanwei.getText().toString());
                setParams(NetUrl.XIUGAI_XINXI, params, 0);
            }
        });
        riv_header = (RoundedImageView) findViewById(R.id.riv_header);
        riv_header.setOnClickListener(this);
        rbt_man = (RadioButton) findViewById(R.id.rbt_man);
        rbt_wuman = (RadioButton) findViewById(R.id.rbt_woman);
        etName = (EditText) findViewById(R.id.et_xinxi_name);
        etJianjie = (EditText) findViewById(R.id.et_xinxi_jianjie);
        etBirth = (EditText) findViewById(R.id.et_birth);
        etMianmao = (EditText) findViewById(R.id.et_xinxi_mianmao);
        etDanwei = (EditText) findViewById(R.id.et_xinxi_danwei);
        etaAddress = (EditText) findViewById(R.id.et_xinxi_address);

        Intent intent=getIntent();
        etName.setText(intent.getStringExtra("username"));
        etJianjie.setText((intent.getStringExtra("ins")));
        etBirth.setText((intent.getStringExtra("birth")));
        etMianmao.setText((intent.getStringExtra("mianmao")));
        etDanwei.setText((intent.getStringExtra("danwei")));
        etaAddress.setText((intent.getStringExtra("address")));
        String s=intent.getStringExtra("pic");
        ImageLoader.getInstance().displayImage("http://"+s,riv_header);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
//            case R.id.tv_fatie_complete:
//
//                break;
            case R.id.riv_header:
                headDiaLog.HeadPortraitDialog();
                break;
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //获取变更后的选中项的ID
        int radioButtonId = group.getCheckedRadioButtonId();
        //根据ID获取RadioButton的实例
        RadioButton rb = (RadioButton) findViewById(radioButtonId);
        gender = rb.getText().toString().toString();
    }
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"修改成功".equals(JSON.parseObject(response).getString("msg"))) {
                Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                return;
            } else {
                Intent intent = new Intent(this, MyCenterActivity.class);
//                intent.putExtra("head",bitmapToBase64(head));
//                intent.putExtra("instruction",etJianjie.getText().toString());
//                intent.putExtra("name",etName.getText().toString());
//                intent.putExtra("sex",sex);
//                intent.putExtra("cname",etDanwei.getText().toString());
//                intent.putExtra("address",etaAddress.getText().toString());
//                intent.putExtra("birth",etBirth.getText().toString());
//                intent.putExtra("mianmao",etMianmao.getText().toString());
                //setResult(8,intent);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 || requestCode == 6 || requestCode == 7) {
            switch (requestCode) {
                case 5:
                    if (resultCode == RESULT_OK) {
                        cropPhoto(data.getData());//裁剪图片
                    }
                    break;
                case 6:
                    if (resultCode == RESULT_OK) {
                        File temp = new File(Environment.getExternalStorageDirectory()
                                + "/head.jpg");
                        cropPhoto(Uri.fromFile(temp));//裁剪图片
                    }
                    break;
                case 7:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        head = extras.getParcelable("data");
                        riv_header.setImageBitmap(head);
                    }
                    break;
            }
        }
    }
    private void initHead() {
        headDiaLog = new HeadDiaLog(this, this);
    }
    @Override
    public void paizhao(LinearLayout llPaiZhaoPayment) {
        if (Build.VERSION.SDK_INT >= 23) { //Android M 处理Runtime Permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {//检查是否有写入SD卡的授权
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                startActivityForResult(intent2, 6);//采用ForResult打开
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                    "head.jpg")));
            startActivityForResult(intent2, 6);//采用ForResult打开
        }

    }

    @Override
    public void xiangce(LinearLayout llXiangCePayment) {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, 5);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            for (int index = 0; index < permissions.length; index++) {
                String permission = permissions[index];
                if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "head.jpg")));
                        startActivityForResult(intent2, 6);//采用ForResult打开
                    } else {
                        showMissingPermissionDialog();
                    }
                }
            }
        }
    }
    /**
     * 显示打开权限提示的对话框
     */
    private void showMissingPermissionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("帮助");
        builder.setMessage("当前权限被禁用，建议到设置界面开启权限！");

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.prompt(XiuGaiXinXiActivity.this, "启动相机失败！");
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turnOnSettings();
            }
        });
        builder.show();
    }
    /**
     * 启动系统权限设置界面
     */
    @SuppressLint("InlinedApi")
    private void turnOnSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 7);
    }

    public String bitmaptoString(Bitmap bitmap) {
        String string;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
    @Override
    public void startTime(String selector) {

    }

    @Override
    public void determine() {

    }

    @Override
    public void endTime(String endTime) {

    }
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
