package net.cpsec.zfwx.guodian.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.JuzhenGridAdapter;
import net.cpsec.zfwx.guodian.entity.JuzhenBean;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class JuZhenFragment extends BaseFragment {


    private JuzhenBean juzhenBean;
    private GridView yijiGrid;
    private GridView erjiGrid;
    private GridView sanjiGrid;
    List<JuzhenBean.InforBean> list;
    List<JuzhenBean.InforBean> yijijuzhenList=new ArrayList<JuzhenBean.InforBean>();
    List<JuzhenBean.InforBean> erjijuzhenList=new ArrayList<JuzhenBean.InforBean>();
    List<JuzhenBean.InforBean> sanjijuzhenList=new ArrayList<JuzhenBean.InforBean>();
    public JuZhenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_ju_zhen, container, false);
        yijiGrid = (GridView) v.findViewById(R.id.yijigongsiGrid);
        erjiGrid = (GridView) v.findViewById(R.id.erjibumenGrid);
        sanjiGrid = (GridView) v.findViewById(R.id.sanjibumenGrid);
        initData();
        return v;
    }
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINGCHUNFENXIANG_JUZHEN, params, 1);

    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId){
            case 1:
               // try{
                    juzhenBean = JSON.parseObject(response, JuzhenBean.class);
                    list=juzhenBean.getInfor();
                    Debugging.debugging("list"+list.toString());
                    int arr =juzhenBean.getInfor().size();
                    for (int i=0;i<arr;i++){
                        int classId=juzhenBean.getInfor().get(i).getClasses();
                        Debugging.debugging("classId是多少=="+classId);
                        if (classId==0){
                            yijijuzhenList.add(juzhenBean.getInfor().get(i));
                        }else if(classId==1){
                            erjijuzhenList.add(juzhenBean.getInfor().get(i));
                        }else if(classId!=0&&classId!=1){
                            sanjijuzhenList.add(juzhenBean.getInfor().get(i));
                        }
                    }
                    Debugging.debugging("二级单位=="+erjijuzhenList.toString());
                    Debugging.debugging("三级单位=="+sanjijuzhenList.toString());


                    setAdapter();
               // }catch(Exception e){
                //    Toast.prompt(getActivity(), "数据异常");
               // }
                break;
        }
    }
    private void setAdapter() {
       if(yijijuzhenList!=null) {JuzhenGridAdapter adapter1 = new JuzhenGridAdapter(getActivity(), yijijuzhenList);   yijiGrid.setAdapter(adapter1);}
       if (erjijuzhenList!=null) {JuzhenGridAdapter adapter2 = new JuzhenGridAdapter(getActivity(), erjijuzhenList);erjiGrid.setAdapter(adapter2);}
        if(sanjijuzhenList!=null){JuzhenGridAdapter adapter3 = new JuzhenGridAdapter(getActivity(), sanjijuzhenList);sanjiGrid.setAdapter(adapter3);}
    }
}

