package net.cpsec.zfwx.guodian.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import net.cpsec.zfwx.guodian.R;

/**
 * Created by zhaomin on 2016/6/2.
 */
public class HeadDiaLog {
    WindowManager windowManager;
    private Context context;
    public static int screenWidth;
    public static int screenHeitht;
    private LinearLayout llXiangCePayment;
    private LinearLayout llPaiZhaoPayment;
    private Picture picture;

//    private ModifyThePicture modifyThePicture;
//    private TextView tvBoy, tvGirl;
//    private Dialog dialog;

    public HeadDiaLog(Context context, Picture picture) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeitht = windowManager.getDefaultDisplay().getHeight();
        this.context = context;
        this.picture = picture;
    }

    public void HeadPortraitDialog() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_head_portrait);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.dialog_head_portrait_dialog);
        linearLayout.setLayoutParams(params);
        llXiangCePayment = (LinearLayout) dialog.findViewById(R.id.ll_xiangce_payment);
        llPaiZhaoPayment = (LinearLayout) dialog.findViewById(R.id.ll_paizhao_payment);
        Button btnCancelPayment = (Button) dialog.findViewById(R.id.btn_head_portrait);
        llXiangCePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//相册
                picture.xiangce(llXiangCePayment);
                dialog.dismiss();
            }
        });
        llPaiZhaoPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//拍照
                picture.paizhao(llPaiZhaoPayment);
                dialog.dismiss();
            }
        });
        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//取消
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public interface Picture{
        void paizhao(LinearLayout llPaiZhaoPayment);

        void xiangce(LinearLayout llXiangCePayment);
    }

}