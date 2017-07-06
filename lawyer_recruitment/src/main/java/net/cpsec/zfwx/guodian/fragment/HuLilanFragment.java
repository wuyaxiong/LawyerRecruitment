package net.cpsec.zfwx.guodian.fragment;


import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.MyCenterActivity;
import net.cpsec.zfwx.guodian.utils.Constant;

import static com.alibaba.tcms.client.ClientRegInfo.APP_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class HuLilanFragment extends BaseFragment implements View.OnClickListener {
    View v;
    protected MyLocalActivityManager mLocalActivityManager;
    private FrameLayout mBoday;
    private Bundle savedInstanceState;
    private MyIMReceiver receiver;
    private Activity activity;
    private ImageView iv_action_left,iv_actionbar_right;
    private TextView tv_title;
    private String phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_hu_lilan, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("phone", Context.MODE_PRIVATE);
        phone= sp.getString("phone","");
        initView(v);
        return v;
    }

    private void initView(View v) {
        iv_action_left= (ImageView) v.findViewById(R.id.iv_back);
        iv_actionbar_right= (ImageView) v.findViewById(R.id.iv_more);
        tv_title= (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("线上互联");
        iv_action_left.setImageResource(R.drawable.icon_people);
        iv_actionbar_right.setImageResource(R.drawable.icon_jiar);
        iv_action_left.setOnClickListener(this);
        iv_actionbar_right.setOnClickListener(this);
    }

    @SuppressWarnings("deprecation")
    private void open(Bundle savedInstanceState) {
        final String userid = phone;//用户手机号
        YWIMKit mIMKit = YWAPI.getIMKitInstance(userid, APP_KEY);

        mLocalActivityManager = new MyLocalActivityManager(getActivity(), true);

        mLocalActivityManager.dispatchCreate(savedInstanceState);
        Log.e("TAG", "oncreate.....................");
        // 打开会话列表：
        Intent intent = mIMKit.getConversationActivityIntent();
        intent.setAction("android.settings.SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        View v = mLocalActivityManager.startActivity("one", intent)
                .getDecorView();
        mBoday.removeAllViews();
        mBoday.addView(v);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent = new Intent(getActivity(), MyCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_more:
                break;
        }
    }

    private void initView() {
        mBoday = (FrameLayout) v.findViewById(R.id.frame);
    }





    private void registBroadCast() {
        receiver = new MyIMReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ON_LOGIN_ALI_SUCCESS);
        activity.registerReceiver(receiver, filter);
    }
    class MyIMReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.ON_LOGIN_ALI_SUCCESS)) {
                open(savedInstanceState);
            }
        }
    }

    @Override
    public void onDestroy() {
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    public class MyLocalActivityManager extends LocalActivityManager {

        @SuppressWarnings("deprecation")
        public MyLocalActivityManager(Activity parent, boolean singleMode) {
            super(parent, singleMode);
        }

    }
}
