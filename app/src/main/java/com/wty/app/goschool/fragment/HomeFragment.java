package com.wty.app.goschool.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.wty.app.goschool.R;
import com.wty.app.goschool.mvp.view.activity.PublishActivity;
import com.wty.app.goschool.mvp.view.fragment.ComplainFragment;
import com.wty.app.goschool.mvp.view.fragment.HelpFragment;
import com.wty.app.goschool.mvp.view.fragment.MarketFragment;
import com.wty.app.goschool.mvp.view.fragment.NoticeFragment;
import com.wty.app.goschool.mvp.view.fragment.PictureFragment;
import com.wty.app.goschool.mvp.view.fragment.RecommendFragment;
import com.wty.app.library.adapter.TabFragmentAdapter;
import com.wty.app.library.fragment.BaseFragment;
import com.wty.app.library.mvp.presenter.BasePresenter;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description 主页面
 * @author wty
 * @Date 2016/7/19
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.fragment_home_viewPager)
    ViewPager viewPager;
    @Bind(R.id.fragment_home_tablayout)
    TabLayout tablayout;

    @OnClick(R.id.iv_home_showmore)
    void ClickImg(){
        tablayout.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

        Map<String,BaseFragment> fragments = new LinkedHashMap<>();
        fragments.put("推荐",new RecommendFragment());
        fragments.put("通知",new NoticeFragment());
        fragments.put("吐槽",new ComplainFragment());
        fragments.put("图片",new PictureFragment());
        fragments.put("寻宝贝",new MarketFragment());
        fragments.put("找师兄", new HelpFragment());
        TabFragmentAdapter adapter = new TabFragmentAdapter(fragments,this.getChildFragmentManager());
//        MyViewPagerScroller scroller = new MyViewPagerScroller(getContext());
//        scroller.setScrollDuration(800);
//        scroller.initViewPagerScroll(viewPager);  //这个是设置切换过渡时间为0毫秒
        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(5);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabTextColors(getResources().getColor(R.color.gray_font_3), getResources().getColor(R.color.colorBrownAccent));
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initFragmentActionBar(String title) {
        super.initFragmentActionBar(title);
        activity.getDefaultNavigation().setRightButton("发表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublishActivity.startPublishActivity(getContext());
            }
        });
    }
}
