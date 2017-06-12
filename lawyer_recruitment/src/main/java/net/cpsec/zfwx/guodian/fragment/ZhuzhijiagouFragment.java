package net.cpsec.zfwx.guodian.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhuzhijiagouFragment extends Fragment {


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
    public ZhuzhijiagouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_zhuzhijiagou, container, false);
        gongsiList=(ListView)v.findViewById(R.id.gongsiListView);
        bumenList=(ListView)v.findViewById(R.id.bumenListView);
        lianxirenList=(ListView)v.findViewById(R.id.lianxirenList);
        xialaImage=(ImageView)v.findViewById(R.id.xialaImage);
        gongsiText=(TextView)v.findViewById(R.id.gongsiText);
        bumenText=(TextView)v.findViewById(R.id.bumenText);
        //模拟联系人的数据
        llist=new ArrayList<String>();
        llist.add("王萌萌1.1");
        llist.add("王萌萌1.2");
        llist.add("王萌萌1.3");

        //模拟公司的假数据
        glist= new ArrayList<String>();
        glist.add("龙源电力1");
        glist.add("龙源电力2");
        glist.add("龙源电力3");
        glist.add("龙源电力4");
        //模拟部门的假数据
        blist= new ArrayList<String>();
        blist.add("龙源电力1.1");
        blist.add("龙源电力2.1");
        blist.add("龙源电力3.1");
        blist.add("龙源电力4.1");
        setListeners();
        setAdapters();
        setAnimation();
        return v;
        
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
        animation_scale=new ScaleAnimation(1.0f,1.0f,0.1f,1.0f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
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
        animationSet_scale=new AnimationSet(true);
        animationSet_scale.addAnimation(animation_scale);//尺寸伸缩

        //部门列表的伸缩动画
        animation_scalebumen=new ScaleAnimation(0.1f,1.0f,1.0f,1.0f, Animation.RELATIVE_TO_SELF,0.1f,Animation.RELATIVE_TO_SELF,1.0f);

        animation_scalebumen.setRepeatCount(0);
        animation_scalebumen.setDuration(1000);//设置时间持续时间为 1000毫秒
        animation_scalebumen.setFillEnabled(true);
        animation_scalebumen.setFillAfter(true);//停在最后
        animationSet_scalebumen=new AnimationSet(true);
        animationSet_scalebumen.addAnimation(animation_scalebumen);//尺寸伸缩


    }

    private void setListeners() {
        xialaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isxiala){
                    xialaImage.setImageResource(R.drawable.icon_xuanxiangsl);
                    lianxirenList.setVisibility(View.INVISIBLE);
                    gongsiList.setVisibility(View.VISIBLE);
                    isxiala=false;
                }else{
                    xialaImage.setImageResource(R.drawable.icon_xuanxiangzk);
                    lianxirenList.setVisibility(View.VISIBLE);
                    bumenList.setVisibility(View.INVISIBLE);
                    gongsiList.setVisibility(View.INVISIBLE);
                    isxiala=true;

                }

              //  xialaImage.startAnimation(animationSet);//开始播放


                //xialaImage.clearAnimation();//清除动画

            }
        });

        gongsiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gongsi = glist.get(i);
                gongsiList.setSelector(R.color.color_while);
                //adapterView.getSelectedView().setBackgroundColor(Color.WHITE);
                gongsiText.setText(gongsi.toString());

                bumenList.setVisibility(View.VISIBLE);
                bumenList.startAnimation(animationSet_scalebumen);
            }
        });
        bumenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String bumen = blist.get(i);
                bumenText.setText(bumen.toString());

            }
        });


    }

    private void setAdapters() {

        lianxirenList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,llist));
        gongsiList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,glist));
        bumenList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,blist));

    }


}
