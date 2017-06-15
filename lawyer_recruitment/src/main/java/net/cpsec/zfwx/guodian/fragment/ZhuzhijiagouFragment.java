package net.cpsec.zfwx.guodian.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ERGongsiAdapter;
import net.cpsec.zfwx.guodian.adapter.JiagouHaoyouAdapter;
import net.cpsec.zfwx.guodian.adapter.SanjibumenAdapter;
import net.cpsec.zfwx.guodian.entity.ERGongsiBean;
import net.cpsec.zfwx.guodian.entity.JiagouHaoyouBean;
import net.cpsec.zfwx.guodian.entity.SanjiaBumenBean;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhuzhijiagouFragment extends BaseFragment {


    private ListView gongsiList;
    private ListView bumenList;
    private ArrayList<String> glist;
    private ListView lianxirenList;
    private ArrayList<String> llist;
    private ImageView xialaImage;
    private ArrayList<String> blist;
    private RotateAnimation animation_rotate;
    private AnimationSet animationSet;
    private ScaleAnimation animation_scale;
    private AnimationSet animationSet_scale;
    private TextView gongsiText;
    private TextView bumenText;
    private ScaleAnimation animation_scalebumen;
    private AnimationSet animationSet_scalebumen;
    private boolean isxiala;
    private RelativeLayout xiala_layout;
    private JiagouHaoyouBean jiagouBean;
    private ERGongsiBean erjiBean;
    private SanjiaBumenBean sanjiBean;
    private List<ERGongsiBean.InforBean> erjigongsiList;
    private List<SanjiaBumenBean.InforBean> sanjibumenList;
    private JiagouHaoyouBean sanjihaoyouBean;
    private List<JiagouHaoyouBean.InforBean> sanjibumenhaoyouList;
    String two_id;
    String three_id;

    public ZhuzhijiagouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_zhuzhijiagou, container, false);
        gongsiList = (ListView) v.findViewById(R.id.gongsiListView);

        bumenList = (ListView) v.findViewById(R.id.bumenListView);
        lianxirenList = (ListView) v.findViewById(R.id.lianxirenList);
        xialaImage = (ImageView) v.findViewById(R.id.xialaImage);
        xiala_layout = (RelativeLayout) v.findViewById(R.id.xiala_layout);
        gongsiText = (TextView) v.findViewById(R.id.gongsiText);
        bumenText = (TextView) v.findViewById(R.id.bumenText);

        setListeners();

        setAnimation();
        initData();
        return v;

    }

    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.ZUZHIJIAGOU_HUOQUSUOYOUHAOYOU, params, 1);
        RequestMap params2 = new RequestMap();
        setParams(NetUrl.ZUZHIJIAGOU_ERJIGONGSILIEBIAO, params2, 2);


    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 1:
                try {
                    jiagouBean = JSON.parseObject(response, JiagouHaoyouBean.class);
                    List<JiagouHaoyouBean.InforBean> haoyouList = jiagouBean.getInfor();
                    JiagouHaoyouAdapter adapter = new JiagouHaoyouAdapter(getActivity(), haoyouList);
                    lianxirenList.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.prompt(getActivity(), "数据异常");
                }
                break;
            case 2:
                try {
                    erjiBean = JSON.parseObject(response, ERGongsiBean.class);
                    erjigongsiList = erjiBean.getInfor();
                    ERGongsiAdapter adapter2 = new ERGongsiAdapter(getActivity(), erjigongsiList);
                    gongsiList.setAdapter(adapter2);
                } catch (Exception e) {
                    Toast.prompt(getActivity(), "数据异常");
                }
                break;
            case 3:
                try {
                    sanjiBean = JSON.parseObject(response, SanjiaBumenBean.class);
                    sanjibumenList = sanjiBean.getInfor();
                    SanjibumenAdapter adapter3 = new SanjibumenAdapter(getActivity(), sanjibumenList);
                    bumenList.setAdapter(adapter3);

                } catch (Exception e) {
                    Toast.prompt(getActivity(), "数据异常");
                }
                break;
            case 4:
                try {
                    jiagouBean = JSON.parseObject(response, JiagouHaoyouBean.class);
                    List<JiagouHaoyouBean.InforBean> haoyouList = jiagouBean.getInfor();
                    JiagouHaoyouAdapter adapter = new JiagouHaoyouAdapter(getActivity(), haoyouList);
                    lianxirenList.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.prompt(getActivity(), "数据异常");
                }
                break;
            case 5:
                try {
                    jiagouBean = JSON.parseObject(response, JiagouHaoyouBean.class);
                    List<JiagouHaoyouBean.InforBean> haoyouList = jiagouBean.getInfor();
                    JiagouHaoyouAdapter adapter = new JiagouHaoyouAdapter(getActivity(), haoyouList);
                    lianxirenList.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.prompt(getActivity(), "数据异常");
                }
                break;
        }
    }

    private void setAnimation() {
//        animation_rotate = new RotateAnimation(0, 180,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        //第一个参数fromDegrees为动画起始时的旋转角度 //第二个参数toDegrees为动画旋转到的角度
//        //第三个参数pivotXType为动画在X轴相对于物件位置类型 //第四个参数pivotXValue为动画相对于物件的X坐标的开始位置
//        //第五个参数pivotXType为动画在Y轴相对于物件位置类型 //第六个参数pivotYValue为动画相对于物件的Y坐标的开始位置
//      //  animation_rotate.setRepeatCount(1);
//        animation_rotate.setDuration(1000);//设置时间持续时间为 1000毫秒
//        animation_rotate.setInterpolator(new LinearInterpolator());//不停顿
//        animation_rotate.setFillEnabled(true);
//        animation_rotate.setFillAfter(true);//停在最后
//        animation_rotate.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//             //   xialaImage.setImageResource(R.drawable.icon_xuanxiangzk);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                xialaImage.setImageResource(R.drawable.icon_xuanxiangsl);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        animationSet=new AnimationSet(true);
//        animationSet.addAnimation(animation_rotate);//旋转

        //公司列表的尺寸伸缩动画效果 scale
        animation_scale = new ScaleAnimation(1.0f, 1.0f, 0.1f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        //第一个参数fromX为动画起始时 X坐标上的伸缩尺寸
        //第二个参数toX为动画结束时 X坐标上的伸缩尺寸
        //第三个参数fromY为动画起始时Y坐标上的伸缩尺寸
        //第四个参数toY为动画结束时Y坐标上的伸缩尺寸
        /*说明:
                            以上四种属性值
              0.0表示收缩到没有
              1.0表示正常无伸缩
                           值小于1.0表示收缩
                           值大于1.0表示放大
        */
        //第五个参数pivotXType为动画在X轴相对于物件位置类型
        //第六个参数pivotXValue为动画相对于物件的X坐标的开始位置
        //第七个参数pivotXType为动画在Y轴相对于物件位置类型
        //第八个参数pivotYValue为动画相对于物件的Y坐标的开始位置
        animation_scale.setRepeatCount(0);
        animation_scale.setDuration(1000);//设置时间持续时间为 1000毫秒
        animation_scale.setFillEnabled(true);
        animation_scale.setFillAfter(true);//停在最后
        animationSet_scale = new AnimationSet(true);
        animationSet_scale.addAnimation(animation_scale);//尺寸伸缩

        //部门列表的伸缩动画
        animation_scalebumen = new ScaleAnimation(0.1f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 1.0f);

        animation_scalebumen.setRepeatCount(0);
        animation_scalebumen.setDuration(1000);//设置时间持续时间为 1000毫秒
        animation_scalebumen.setFillEnabled(true);
        animation_scalebumen.setFillAfter(true);//停在最后
        animationSet_scalebumen = new AnimationSet(true);
        animationSet_scalebumen.addAnimation(animation_scalebumen);//尺寸伸缩


    }

    private void setListeners() {
        xiala_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isxiala) {
                    xialaImage.setImageResource(R.drawable.icon_backsl);
                    lianxirenList.setVisibility(View.INVISIBLE);
                    gongsiList.setVisibility(View.VISIBLE);
                    bumenList.setVisibility(View.VISIBLE);
                    isxiala = false;
                } else {
                    xialaImage.setImageResource(R.drawable.icon_xuanxiangzk);
                    lianxirenList.setVisibility(View.VISIBLE);
                    bumenList.setVisibility(View.INVISIBLE);
                    gongsiList.setVisibility(View.INVISIBLE);
                    isxiala = true;
                    if(two_id!=null&&three_id==null){
                        RequestMap params5 = new RequestMap();
                        params5.put("two_id", two_id);
                        setParams(NetUrl.ZUZHIJIAGOU_ERJISUOYOUCHENGYUAN, params5, 5);
                    }
                }

                //  xialaImage.startAnimation(animationSet);//开始播放


                //xialaImage.clearAnimation();//清除动画

            }
        });

        gongsiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //   String gongsi = glist.get(i);
                gongsiList.setSelector(R.color.color_while);
                RequestMap params3 = new RequestMap();
                two_id= erjigongsiList.get(i).getCid()+"";
                params3.put("two_id", two_id);
                setParams(NetUrl.ZUZHIJIAGOU_SANJIBUMENLIEBIAO, params3, 3);
                //adapterView.getSelectedView().setBackgroundColor(Color.WHITE);
                gongsiText.setText(erjigongsiList.get(i).getCname());
                bumenList.setVisibility(View.VISIBLE);
                bumenList.startAnimation(animationSet_scalebumen);
            }
        });
        bumenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                bumenText.setText(sanjibumenList.get(i).getCname());
                RequestMap params4 = new RequestMap();
                three_id=sanjibumenList.get(i).getCid()+"";
                params4.put("three_id",three_id);
                setParams(NetUrl.ZUZHIJIAGOU_SANJISUOYOUCHENGYUAN, params4, 4);
                bumenList.setVisibility(View.GONE);
                gongsiList.setVisibility(View.GONE);
                lianxirenList.setVisibility(View.VISIBLE);
            }
        });


    }

}
