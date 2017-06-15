package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.android.volley.manager.RequestMap;
import com.google.gson.Gson;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.MyCenterActivity;
import net.cpsec.zfwx.guodian.activity.ZhuzhijiagouActivity;
import net.cpsec.zfwx.guodian.adapter.MyBaseExpandableListAdapter;
import net.cpsec.zfwx.guodian.entity.GetFriendQueueInfoBean;
import net.cpsec.zfwx.guodian.entity.GetNewFriendQueueInfoBean;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TongXunLuFragment extends BaseFragment implements View.OnClickListener {
    private ImageView iv_action_left,iv_actionbar_right;
    private TextView tv_title;
    private SearchView mSearchView;
    private ExpandableListView mListView;
    private List<String> gData;
    private  List<List<Object>>  iData ;
    private MyBaseExpandableListAdapter listAdapter;
    private List<Object> notGroupedList;
    private List<Object> thisWorkList;
    private List<Object> otherWorkList;
    private List<Object> GroupedList;
    private List<Object> newFriendList;
    private View v;
    private final static int  NOTGROUP=1;
    private final static int   THISWORK=2;
    private final static int   OTHERWORK=3;
    private final static int   GROUPED=4;
    private final static int   NEWFRIEND=5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        v = inflater.inflate(R.layout.fragment_tong_xun_lu, container, false);
        mSearchView = (SearchView) v.findViewById(R.id.sc_search);
        mListView = (ExpandableListView) v.findViewById(R.id.lv_tongxun);
        mListView.setTextFilterEnabled(true);
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i>=4){
                    return false;
                }
                //TODO 模拟uid
                final String userid = "329";
                final String appkey = "23893323"; //消息接收者appKey
//此对象获取到后，保存为全局对象，供APP使用
//此对象跟用户相关，如果切换了用户，需要重新获取
                YWIMKit mIMKit = YWAPI.getIMKitInstance(userid, appkey);
                final int target = ((GetFriendQueueInfoBean.InforBean)iData.get(i).get(i1)).getId(); //消息接收者ID
                Intent intent = mIMKit.getChattingActivityIntent(target+"", appkey);
                startActivity(intent);
                return true;
            }
        });
        initData();

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

    private void initData() {

        gData=new ArrayList<>();
        iData = new ArrayList<List<Object>>();

        gData.add("未分组好友");
        notGroupedList =new ArrayList<>();
        iData.add(notGroupedList);
        //TODO 模拟uid
        requestGroupInfo("329",NOTGROUP);

        gData.add("本单位好友");
        thisWorkList=new ArrayList<>();
        iData.add(thisWorkList);
        requestGroupInfo("329",THISWORK);

        gData.add("兄弟单位好友");
        otherWorkList=new ArrayList<>();
        iData.add(otherWorkList);
        requestGroupInfo("329",OTHERWORK);

        gData.add("群组");
        GroupedList=new ArrayList<>();
        iData.add(GroupedList);
        requestGroupInfo("329",GROUPED);

        gData.add("新的好友");
        newFriendList=new ArrayList<>();
        iData.add(newFriendList);
        //获取好友请求列表
        RequestMap params= new RequestMap();
        // TODO 模拟uid
        params.put("uid", "329");
        setParams(NetUrl.GET_FRIEND_QUEUE, params, NEWFRIEND);
        listAdapter= new MyBaseExpandableListAdapter(gData, iData, getContext());
        mListView.setAdapter(listAdapter);
    }
    public void requestGroupInfo(String uid,int friend_group_id){
        RequestMap params=new RequestMap();
        params.put("uid", "uid");
        params.put("friend_group_id",friend_group_id+"");
        setParams(NetUrl.GET_FRIEND, params, friend_group_id);
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
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try{
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (!"200".equals(jsonObject.getString("code"))){
                return;
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), "回复结果错误", Toast.LENGTH_SHORT).show();
        }
        switch (actionId) {
            case NOTGROUP:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{

                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    notGroupedList.addAll(infoBean.getInfor());
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "未分组解析错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case THISWORK:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    thisWorkList.addAll(infoBean.getInfor());
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "本单位解析错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case OTHERWORK:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    otherWorkList.addAll(infoBean.getInfor());
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "兄弟单位解析错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case GROUPED:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    GroupedList.addAll(infoBean.getInfor());
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "群组解析错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case NEWFRIEND:
                Log.e("newFriendList", "onSuccess: "+response );
                try{
                    GetNewFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetNewFriendQueueInfoBean.class);
                    newFriendList.addAll(infoBean.getData());
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "新好友解析错误", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent = new Intent(getActivity(), MyCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_more:
                startActivity(new Intent(getActivity(),ZhuzhijiagouActivity.class));
                break;
        }
    }
}
