package net.cpsec.zfwx.guodian.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.mobileim.contact.IYWCrossContactProfileCallback;
import com.alibaba.mobileim.lib.model.contact.Contact;
import com.android.volley.manager.RequestMap;
import com.google.gson.Gson;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.MainActivity;
import net.cpsec.zfwx.guodian.activity.MyCenterActivity;
import net.cpsec.zfwx.guodian.activity.XiangXiZiLiaoActivity;
import net.cpsec.zfwx.guodian.activity.ZhuzhijiagouActivity;
import net.cpsec.zfwx.guodian.adapter.MyBaseExpandableListAdapter;
import net.cpsec.zfwx.guodian.adapter.ScreenAdapter;
import net.cpsec.zfwx.guodian.entity.GetFriendQueueInfoBean;
import net.cpsec.zfwx.guodian.entity.GetNewFriendQueueInfoBean;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * A simple {@link Fragment} subclass.
 */
public class TongXunLuFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ImageView iv_action_left,iv_actionbar_right;
    private TextView tv_title;
    private SearchView mSearchView;
    private ExpandableListView mListView;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mSearchListView;
    private List<String> gData;
    private  List<List<Object>>  iData ;
    private MyBaseExpandableListAdapter listAdapter;
    private ScreenAdapter searchAdapter;
    private List<Object> notGroupedList;
    private List<Object> thisWorkList;
    private List<Object> otherWorkList;
    private List<Object> GroupedList;
    private List<Object> newFriendList;

    private List<GetFriendQueueInfoBean.InforBean> allDataList;
    private List<GetFriendQueueInfoBean.InforBean> mBackData=new ArrayList<>();
    private List<GetFriendQueueInfoBean.InforBean> middle=new ArrayList<>();
    private View v;
    private final static int  NOTGROUP=1;
    private final static int   THISWORK=2;
    private final static int   OTHERWORK=3;
    private final static int   GROUPED=4;
    private final static int   NEWFRIEND=5;
    private long up;
    private long down;
    private AdapterView.OnItemLongClickListener  longClickListener;
    private ExpandableListView.OnChildClickListener clickListener;
    private String[] groupName=new String[]{"未分组好友","本单位好友","兄弟单位好友","群组"};
    //    private int groupPos;
//    private int childPos;
    String uid;
    private String filterText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        v = inflater.inflate(R.layout.fragment_tong_xun_lu, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        Log.e("123", "onCreateView: "+uid );
        initView(v);

        initData();
        initScachView();

        initListener();
        mListView.setOnItemLongClickListener(longClickListener);
        mListView.setOnChildClickListener(clickListener);
        mListView.setTextFilterEnabled(true);
        MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener(){

            @Override
            public void onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        up = System.currentTimeMillis();
//                        Log.e("123", "onTouchEvent: up"+ up);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        down = System.currentTimeMillis();
//                        Log.e("123", "onTouchEvent: down"+ down);
                }

            }
        };

        // 将myTouchListener注册到分发列表
        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);
        //区分长短监听


        return v;
    }

    private void initScachView() {
        mSearchView = (SearchView) v.findViewById(R.id.sc_search);
        mSearchListView = (ListView) v.findViewById(R.id.lv_search);
        mBackData.addAll(allDataList);
        searchAdapter= new ScreenAdapter(getContext(),mBackData);
        TextView emptyView = new TextView(getContext());
        emptyView.setText("数据为空");
        mSearchListView.setEmptyView(emptyView);
        mSearchListView.setFastScrollEnabled(true);
        mSearchListView.setAdapter(searchAdapter);
        mSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), XiangXiZiLiaoActivity.class);
                intent.putExtra("phone",mBackData.get(position).getPhone());
                getActivity().startActivity(intent);
            }
        });


        // 设置该SearchView默认是否自动缩小为图标
        mSearchView.setIconifiedByDefault(false);
        // 设置该SearchView显示搜索按钮
        mSearchView.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        mSearchView.setQueryHint("查找");
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

//                searchAdapter.notifyDataSetChanged();

                if (TextUtils.isEmpty(newText)) {
                    // 清除ListView的过滤

                    mBackData.clear();
                    mSearchListView.setVisibility(View.GONE);
                    mSwipeLayout.setVisibility(View.VISIBLE);
                } else {
                    mSearchListView.setVisibility(View.VISIBLE);
                    mSwipeLayout.setVisibility(View.GONE);
                    setFilterText(newText);
                }
                return false;
            }
        });
    }

    private void initListener() {
        longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int groupPos = (int) view.getTag(R.id.xxx01);
                final int childPos = (int) view.getTag(R.id.xxx02);
                Log.e("123", "onItemLongClick: " + groupPos + ":" + childPos);
                if (childPos == -1) {//长按的是父项
                    //根据groupPos判断你长按的是哪个父项，做相应处理（弹框等）
                } else {
                    if (groupPos < 4) {
                        GetFriendQueueInfoBean.InforBean inforBean = (GetFriendQueueInfoBean.InforBean) iData.get(groupPos).get(childPos);
                        final int person_id = inforBean.getId();

                        new AlertDialog.Builder(getActivity())
                                .setTitle("请选择")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setSingleChoiceItems(groupName, groupPos,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                if (which + 1 == groupPos) {
                                                    dialog.dismiss();
                                                    return;
                                                }
                                                RequestMap map = new RequestMap();
                                                //TODO uid
                                                map.put("uid", uid);
                                                map.put("group_id", (which + 1) + "");
                                                map.put("person_id", "" + person_id);
                                                Log.e("123", "onClick: " + (which + 1) + ":" + person_id);
                                                setParams(NetUrl.MOVE_FRIEND, map, 6);
                                                mSwipeLayout.setRefreshing(true);
                                                dialog.dismiss();
                                            }
                                        }
                                )
                                .setNegativeButton("取消", null)
                                .show();
                    }
                }
                return false;
            }
        };
        clickListener = new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                final int groupPos = (int) view.getTag(R.id.xxx01);
                final int childPos = (int) view.getTag(R.id.xxx02);
                Log.e("123", "onChildClick: "+groupPos);

                if (groupPos >3 ) {
                    return false;
                }
                if (groupPos<4&& down - up <1000) {


                    final GetFriendQueueInfoBean.InforBean inforBean = (GetFriendQueueInfoBean.InforBean) iData.get(i).get(i1);
                    final String phone = inforBean
                            .getPhone();//消息接收者ID
                    Intent intent = MainActivity.getMyImKit().getChattingActivityIntent(phone + "", "23893323");

                    startActivity(intent);
                }
                return true;
            }
        };
    }

    private void initData() {
        allDataList=new ArrayList<>();
        gData=new ArrayList<>();
        iData = new ArrayList<List<Object>>();

        gData.add(groupName[0]);
        notGroupedList =new ArrayList<>();
        iData.add(notGroupedList);


        gData.add(groupName[1]);
        thisWorkList=new ArrayList<>();
        iData.add(thisWorkList);

        gData.add(groupName[2]);
        otherWorkList=new ArrayList<>();
        iData.add(otherWorkList);

        gData.add(groupName[3]);
        GroupedList=new ArrayList<>();
        iData.add(GroupedList);
        initNetData();

        gData.add("新的好友");
        newFriendList=new ArrayList<>();
        iData.add(newFriendList);
        //获取好友请求列表
        RequestMap    params= new RequestMap();
        // TODO 模拟uid
        params.put("uid", uid);
        setParams(NetUrl.GET_FRIEND_QUEUE, params, NEWFRIEND);
        listAdapter= new MyBaseExpandableListAdapter(gData, iData, getContext());

        mListView.setAdapter(listAdapter);
    }

    private void initNetData() {

        allDataList.clear();
        notGroupedList.clear();
        requestGroupInfo(NOTGROUP);
        thisWorkList.clear();
        requestGroupInfo(THISWORK);
        otherWorkList.clear();
        requestGroupInfo(OTHERWORK);
        GroupedList.clear();
        requestGroupInfo(GROUPED);
    }

    public void requestGroupInfo(int friend_group_id){
        RequestMap params=new RequestMap();
        //TODO 模拟uid
        params.put("uid", uid);
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
        mListView = (ExpandableListView) v.findViewById(R.id.lv_tongxun);
        //下拉刷新
        mSwipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(this);
    }
    public List<Message> msgs=new ArrayList<>();
    public Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            msgs.add(msg);
            if (msgs.size()>=4) {
                msgs.clear();
//                setUseName();

            }
        }
    };
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try{
            mSwipeLayout.setRefreshing(false);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (!"200".equals(jsonObject.getString("code"))){
                Log.e("123", "onSuccesssssss: "+response );
                myHandler.sendEmptyMessage(1);
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
                    List<GetFriendQueueInfoBean.InforBean> infor = infoBean.getInfor();
                    notGroupedList.addAll(infor);
                    allDataList.addAll(infor);

                    myHandler.sendEmptyMessage(1);
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "未分组解析错误", Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;
            case THISWORK:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    thisWorkList.addAll(infoBean.getInfor());
                    allDataList.addAll(infoBean.getInfor());


                    myHandler.sendEmptyMessage(1);
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "本单位解析错误", Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;
            case OTHERWORK:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    otherWorkList.addAll(infoBean.getInfor());
                    allDataList.addAll(infoBean.getInfor());

                    myHandler.sendEmptyMessage(1);
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "兄弟单位解析错误", Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;
            case GROUPED:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    GroupedList.addAll(infoBean.getInfor());
                    allDataList.addAll(infoBean.getInfor());

                    myHandler.sendEmptyMessage(1);
                    listAdapter.notifyDataSetChanged();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "群组解析错误", Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
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
            case 6:
                Log.e("zhuanyi", "onSuccess: "+response );
                try{
                    JSONObject jsonObject = JSONObject.parseObject(response);
                    Toast.makeText(getActivity(),jsonObject.getString("msg") , Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getActivity(), "转移分组失败", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onError(String errorMsg, String url, int actionId) {
        super.onError(errorMsg, url, actionId);
        mSwipeLayout.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        initNetData();
    }
    public void setUseName(){
        final IYWContactService contactService = MainActivity.getMyImKit().getContactService();
        contactService.setCrossContactProfileCallback(new IYWCrossContactProfileCallback() {

            @Override
            public Intent onShowProfileActivity(String userId, String appKey) {

                //这里支持头像点击事件，需要开发者返回一个Intent
                return null;
            }

            @Override
            public void updateContactInfo(Contact contact) {
                Map<String, String> map = new HashMap<String, String>();
            }

            @Override
            public IYWContact onFetchContactInfo(String userId, final String appKey) {
                if (allDataList!=null&&allDataList.size()>0) {
                    for (int i = 0; i < allDataList.size(); i++) {
                        GetFriendQueueInfoBean.InforBean inforBean = allDataList.get(i);
                        if (inforBean.getPhone().equals(userId)) {
                            return inforBean;
                        }
                    }
                }
                return null;

            }
        });
    }

    public void setFilterText(String filterText) {
        middle.clear();

        for (int i = 0; i < allDataList.size(); i++) {
            GetFriendQueueInfoBean.InforBean inforBean = allDataList.get(i);
            if (inforBean.getUsername().indexOf(filterText)!=-1) {
                middle.add(inforBean);
            }
        }

        if (middle.size()>0){
            mBackData.clear();
            mBackData.addAll(middle);
            searchAdapter.notifyDataSetChanged();
        }
    }
}
