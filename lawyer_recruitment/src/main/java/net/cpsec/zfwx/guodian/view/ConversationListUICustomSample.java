package net.cpsec.zfwx.guodian.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.AddFriendActivity;
import net.cpsec.zfwx.guodian.activity.MyCenterActivity;

/**
 * Created by zhaobainian on 2017/6/13.
 */

public class ConversationListUICustomSample  extends IMConversationListUI implements View.OnClickListener {
    private ImageView iv_action_left,iv_actionbar_right;
    private TextView tv_title;
    private Context context;
    public ConversationListUICustomSample(Pointcut pointcut) {
        super(pointcut);
    }

    @Override
    public View getCustomConversationListTitle(Fragment fragment, Context context, LayoutInflater inflater) {
        this.context=context;
        View view = inflater.inflate(R.layout.title_bar_layout, null, false);
            initView(view);
        return view;
    }
    /**
     * 是否隐藏无网络提醒View
     * @param fragment
     * @return true: 隐藏无网络提醒，false：不隐藏无网络提醒
     */
    @Override
    public boolean needHideNullNetWarn(Fragment fragment) {
        return false;
    }
    /**
     * 该方法可以构造一个会话列表为空时的展示View
     * @return
     *      empty view
     */
    @Override
    public View getCustomEmptyViewInConversationUI(Context context) {
        /** 以下为示例代码，开发者可以按需返回任何view*/
        ImageView imageView=new ImageView(context);
       imageView.setImageResource(R.drawable.pic_xiaoxi);
        return imageView;
    }
    private void initView(View v) {
        iv_action_left= (ImageView) v.findViewById(R.id.iv_back);
        iv_actionbar_right= (ImageView) v.findViewById(R.id.iv_more);
        iv_action_left.setImageResource(R.drawable.icon_people);
        iv_actionbar_right.setImageResource(R.drawable.icon_jiar);
        tv_title= (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("线上互联");
        iv_action_left.setOnClickListener(this);
        iv_actionbar_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent = new Intent(context, MyCenterActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_more:
                context.startActivity(new Intent(context,AddFriendActivity.class));
                break;
        }
    }
}
