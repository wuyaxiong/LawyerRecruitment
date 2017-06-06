package net.cpsec.zfwx.guodian.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.LandDivide;
import net.cpsec.zfwx.guodian.entity.Pickers;
import net.cpsec.zfwx.guodian.utils.Constant;
import net.cpsec.zfwx.guodian.utils.LocalDisplay;

import java.util.ArrayList;
import java.util.List;


public class DialogHint implements View.OnClickListener, PickerScrollView.onSelectListener {
    private Dialog dialog;
    private ModifyThePicture modifyThePicture;
    private IRollSelector rollSelector;
    private TextView tvBoy, tvGirl;
    private IGrade iGrade;
    private RatingBar rbGradeProgressBar;
    private TextView tvGradeState;
    private IDeleteReport iDeleteReport;
    private List<Pickers> listEnd;
    private PickerScrollView psvEndTime;
    private boolean isCall;
    private List<LandDivide> allList;


    public Dialog rollSelector(Context context, IRollSelector rollSelector, List<Pickers> list, List<Pickers> listEnd, String selected, String endTime, String title) {
        isCall = false;
        this.listEnd = listEnd;
        this.rollSelector = rollSelector;
        LocalDisplay.init(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        if (dialog == null) {
            dialog = new Dialog(context, R.style.DialogStyle);
        } else {
            dialog = null;
            dialog = new Dialog(context, R.style.DialogStyle);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_roll_selector);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_roll_selector);
        ImageView ivCancel = (ImageView) dialog.findViewById(R.id.iv_cancel);
        ImageView ivDetermine = (ImageView) dialog.findViewById(R.id.iv_determine);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        PickerScrollView pickerScrollView = (PickerScrollView) dialog.findViewById(R.id.pickerscrlllview);
        TextView tvBar = (TextView) dialog.findViewById(R.id.tv_bar);
        PickerScrollView psvEndTime = (PickerScrollView) dialog.findViewById(R.id.psv_end_time);
        if (null != listEnd) {
            psvEndTime.setOnSelectListener(this, Constant.GRADUATE_TIME);
            tvBar.setVisibility(View.VISIBLE);
            psvEndTime.setVisibility(View.VISIBLE);
        } else {
            tvBar.setVisibility(View.GONE);
            psvEndTime.setVisibility(View.GONE);
        }

        ivCancel.setOnClickListener(this);
        ivDetermine.setOnClickListener(this);
        pickerScrollView.setOnSelectListener(this, Constant.EDUCATION_BACKGROUND);
        tvTitle.setText(title);

        pickerScrollView.isCirculation = false;
        psvEndTime.isCirculation = false;
        if (null != listEnd) {
            // 设置数据，默认选择第一条
            psvEndTime.setData(listEnd);
            if (null != endTime && !endTime.isEmpty())
                psvEndTime.setSelected(endTime);
            else
                psvEndTime.setSelected(0);

        }
        // 设置数据，默认选择第一条
        pickerScrollView.setData(list);
        if (null != selected && !selected.isEmpty())
            pickerScrollView.setSelected(selected);
        else
            pickerScrollView.setSelected(0);

        linearLayout.setLayoutParams(params);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public Dialog rollSelector(Context context, IRollSelector rollSelector, List<Pickers> list, List<Pickers> listEnd, List<LandDivide> allList, String selected, String endTime, String title) {
        isCall = true;
        this.allList = allList;
        this.listEnd = listEnd;
        this.rollSelector = rollSelector;
        LocalDisplay.init(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        if (dialog == null) {
            dialog = new Dialog(context, R.style.DialogStyle);
        } else {
            dialog = null;
            dialog = new Dialog(context, R.style.DialogStyle);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_roll_selector);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_roll_selector);
        ImageView ivCancel = (ImageView) dialog.findViewById(R.id.iv_cancel);
        ImageView ivDetermine = (ImageView) dialog.findViewById(R.id.iv_determine);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        PickerScrollView pickerScrollView = (PickerScrollView) dialog.findViewById(R.id.pickerscrlllview);
        TextView tvBar = (TextView) dialog.findViewById(R.id.tv_bar);
        psvEndTime = (PickerScrollView) dialog.findViewById(R.id.psv_end_time);
        if (null != listEnd) {
            psvEndTime.setOnSelectListener(this, Constant.GRADUATE_TIME);
            tvBar.setVisibility(View.VISIBLE);
            psvEndTime.setVisibility(View.VISIBLE);
        } else {
            tvBar.setVisibility(View.GONE);
            psvEndTime.setVisibility(View.GONE);
        }

        ivCancel.setOnClickListener(this);
        ivDetermine.setOnClickListener(this);
        pickerScrollView.setOnSelectListener(this, Constant.EDUCATION_BACKGROUND);
        tvTitle.setText(title);

        pickerScrollView.isCirculation = false;
        psvEndTime.isCirculation = false;
        if (null != listEnd) {
            // 设置数据，默认选择第一条
            psvEndTime.setData(listEnd);
            if (null != endTime && !endTime.isEmpty())
                psvEndTime.setSelected(endTime);
            else
                psvEndTime.setSelected(0);

        }
        // 设置数据，默认选择第一条
        pickerScrollView.setData(list);
        if (null != selected && !selected.isEmpty())
            pickerScrollView.setSelected(selected);
        else
            pickerScrollView.setSelected(0);

        linearLayout.setLayoutParams(params);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cancel:
                if (null != dialog) {
                    dialog.dismiss();
                }
                break;
            case R.id.iv_determine:
                if (null != rollSelector)
                    rollSelector.determine();
                break;
        }
    }

    @Override
    public void onSelect(Pickers pickers, int actionId) {
        switch (actionId) {
            case Constant.EDUCATION_BACKGROUND:
                if (isCall) {
                    List<Pickers> pickerses = new ArrayList<>();
                    List<LandDivide> landDivides = new ArrayList<>();
                    for (LandDivide landDivide : allList) {
                        if (landDivide.getName().equals(pickers.getShowConetnt())) {
                            for (LandDivide landDivide1 : allList) {
                                if (landDivide1.getSuperior().equals(landDivide.getCode()))
                                    landDivides.add(landDivide1);
                            }
                            break;
                        }
                    }
                    for (LandDivide landDivide : landDivides) {
                        pickerses.add(new Pickers(landDivide.getName()));
                    }
                    psvEndTime.setData(pickerses);
                    psvEndTime.setSelected(0);
                }
                if (null != rollSelector) {
                    rollSelector.startTime(pickers.getShowConetnt());
                }
                break;
            case Constant.GRADUATE_TIME:
                if (null != rollSelector)
                    rollSelector.endTime(pickers.getShowConetnt());
                break;
        }
    }

    public interface ModifyThePicture {
        void photograph();

        void photoAlbum();

        void chooseBoy();

        void chooseGirl();
    }

    public interface IRollSelector {
        void startTime(String selector);

        void determine();

        void endTime(String endTime);
    }

    public interface IGrade {
        void rbGradeProgressBar(RatingBar rbGradeProgressBar, TextView tvGradeState);

        void grade(RatingBar rbGradeProgressBar);

        void noService();
    }

    public interface IDeleteReport {
        void delete();
    }
}
