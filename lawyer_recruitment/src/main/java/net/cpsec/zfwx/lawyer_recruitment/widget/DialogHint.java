package net.cpsec.zfwx.lawyer_recruitment.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.entity.LandDivide;
import net.cpsec.zfwx.lawyer_recruitment.entity.Pickers;
import net.cpsec.zfwx.lawyer_recruitment.utils.Constant;
import net.cpsec.zfwx.lawyer_recruitment.utils.LocalDisplay;

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

//    public void modifyThePicture(Context context, ModifyThePicture modifyThePicture) {
//        this.modifyThePicture = modifyThePicture;
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.modify_the_picture_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_modify_the_picture);
//        linearLayout.setLayoutParams(params);
//        TextView tvPhotograph = (TextView) dialog.findViewById(R.id.tv_photograph);
//        TextView tvPhotoAlbum = (TextView) dialog.findViewById(R.id.tv_photo_album);
//        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
//
//        tvPhotograph.setOnClickListener(this);
//        tvPhotoAlbum.setOnClickListener(this);
//        tvCancel.setOnClickListener(this);
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//    }

//    public Dialog deleteReportDialog(Context context, IDeleteReport iDeleteReport, String flag) {
//        this.iDeleteReport = iDeleteReport;
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.delete_report_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_modify_the_picture);
//        linearLayout.setLayoutParams(params);
//        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title_dialog);
//        TextView tvContent = (TextView) dialog.findViewById(R.id.tv_photo_album);
//        TextView tvConfirm = (TextView) dialog.findViewById(R.id.tv_confirm);
//        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
//
//        if (flag != null) {
//            tvTitle.setText("删除订单");
//            tvContent.setText("确认要删除该订单！");
//        }
//        tvConfirm.setOnClickListener(this);
//        tvCancel.setOnClickListener(this);
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }

//    public Dialog payStateDialog(Context context, IDeleteReport iDeleteReport, String flag) {
//        this.iDeleteReport = iDeleteReport;
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.delete_report_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_modify_the_picture);
//        linearLayout.setLayoutParams(params);
//        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title_dialog);
//        TextView tvConfirmPay = (TextView) dialog.findViewById(R.id.tv_photo_album);
//        TextView tvConfirm = (TextView) dialog.findViewById(R.id.tv_confirm);
//        tvConfirm.setTextColor(tvConfirm.getContext().getResources().getColor(R.color.color_28c0ff));
//        TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
//
//        tvConfirm.setOnClickListener(this);
//        if (flag == null) {
//            tvTitle.setText("支付报告");
//            tvConfirmPay.setText("去支付报告");
//            tvConfirm.setText("确定");
//        } else {
//            tvTitle.setText("客服电话");
//            tvConfirmPay.setText("010-65545560");
//            tvConfirm.setText("拨号");
//        }
//        tvCancel.setOnClickListener(this);
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }

//    public Dialog timeDialog(Context context, TimePicker.OnTimeChangedListener onTimeChangedListener) {
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.time_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_modify_the_picture);
//        linearLayout.setLayoutParams(params);
//        TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.time_picker);
//
//        timePicker.setIs24HourView(true);
//        timePicker.setOnTimeChangedListener(onTimeChangedListener);
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }

//    public Dialog chooseTimeDialog(Context context, ChooseTimeAdapter.IChooseTime iChooseTime) {
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.choose_time_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_modify_the_picture);
//        linearLayout.setLayoutParams(params);
//        RecyclerView rvChooseTime = (RecyclerView) dialog.findViewById(R.id.rv_choose_time);
//        String[] time = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
//        ChooseTimeAdapter adapter = new ChooseTimeAdapter(time, iChooseTime);
//        rvChooseTime.setLayoutManager(new GridLayoutManager(context, 6));
//        rvChooseTime.setAdapter(adapter);
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }

//    public Dialog userGrade(Context context, final IGrade iGrade) {
//        this.iGrade = iGrade;
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.grade_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_modify_the_picture);
//        linearLayout.setLayoutParams(params);
//        TextView tvNoService = (TextView) dialog.findViewById(R.id.tv_no_service);
//        final TextView tvGrade = (TextView) dialog.findViewById(R.id.tv_grade);
//        tvGradeState = (TextView) dialog.findViewById(R.id.tv_grade_state);
//        rbGradeProgressBar = (RatingBar) dialog.findViewById(R.id.rb_grade_progress_bar);
//
//        tvNoService.setOnClickListener(this);
//        tvGrade.setOnClickListener(this);
//
//        rbGradeProgressBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                rbGradeProgressBar.setRating(rating);
//                iGrade.rbGradeProgressBar(rbGradeProgressBar, tvGradeState);
//            }
//        });
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }

//    public void chooseGender(Context context, ModifyThePicture modifyThePicture, boolean isBoyOrGirl) {
//        this.modifyThePicture = modifyThePicture;
//        LocalDisplay.init(context);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS * 5 / 6, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.DialogStyle);
//        } else {
//            dialog = null;
//            dialog = new Dialog(context, R.style.DialogStyle);
//        }
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.choose_gender_dialog);
//        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.ll_gender);
//        linearLayout.setLayoutParams(params);
//
//        tvBoy = (TextView) dialog.findViewById(R.id.tv_boy);
//        tvGirl = (TextView) dialog.findViewById(R.id.tv_girl);
//
//        tvBoy.setOnClickListener(this);
//        tvGirl.setOnClickListener(this);
//        tvBoy.setSelected(isBoyOrGirl);
//        tvGirl.setSelected(!isBoyOrGirl);
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//    }

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
