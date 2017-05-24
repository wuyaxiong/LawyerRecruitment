package net.cpsec.zfwx.lawyer_recruitment.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.activity.MyCenterActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TongXunLuFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_action_left,iv_actionbar_right;
    private TextView tv_title;
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
    private SearchView mSearchView;
    private ListView mListView;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        v = inflater.inflate(R.layout.fragment_tong_xun_lu, container, false);
        mSearchView = (SearchView) v.findViewById(R.id.sc_search);
        mListView = (ListView) v.findViewById(R.id.lv_tongxun);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    mListView.setFilterText(newText);
                } else {
                    mListView.clearTextFilter();
                }
                return false;
            }
        });
        initView(v);
        return v;
    }

    private void initView(View v) {
        iv_action_left= (ImageView) v.findViewById(R.id.iv_back);
        iv_actionbar_right= (ImageView) v.findViewById(R.id.iv_more);
        iv_action_left.setImageResource(R.drawable.icon_people);
        iv_actionbar_right.setImageResource(R.drawable.icon_zuzhi);
        tv_title= (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("通讯录");
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
