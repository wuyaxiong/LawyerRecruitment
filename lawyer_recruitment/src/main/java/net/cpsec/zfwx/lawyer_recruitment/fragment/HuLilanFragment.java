package net.cpsec.zfwx.lawyer_recruitment.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.activity.MyCenterActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HuLilanFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_action_left,iv_actionbar_right;
private TextView tv_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_hu_lilan, container, false);
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
}
