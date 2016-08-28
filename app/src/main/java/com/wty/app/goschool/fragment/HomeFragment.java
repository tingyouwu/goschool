package com.wty.app.goschool.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.HorizontalScrollView;

import com.wty.app.goschool.R;
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

    RecommendFragment recommendFragment;
    NoticeFragment noticeFragment;
    ComplainFragment complainFragment;
    PictureFragment pictureFragment;
    MarketFragment marketFragment;
    HelpFragment helpFragment;

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

        recommendFragment = new RecommendFragment();
        noticeFragment = new NoticeFragment();
        complainFragment = new ComplainFragment();
        pictureFragment = new PictureFragment();
        marketFragment = new MarketFragment();
        helpFragment = new HelpFragment();

        recommendFragment.setActivity(activity);
        noticeFragment.setActivity(activity);
        complainFragment.setActivity(activity);
        pictureFragment.setActivity(activity);
        marketFragment.setActivity(activity);
        helpFragment.setActivity(activity);

        fragments.put("推荐",recommendFragment);
        fragments.put("通知",noticeFragment);
        fragments.put("吐槽",complainFragment);
        fragments.put("图片",pictureFragment);
        fragments.put("跳蚤市场",marketFragment);
        fragments.put("师兄帮忙", helpFragment);
        TabFragmentAdapter adapter = new TabFragmentAdapter(fragments,this.getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabTextColors(getResources().getColor(R.color.gray_font_3), getResources().getColor(R.color.colorBrownAccent));
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }
}
