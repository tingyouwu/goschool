package com.wty.app.goschool.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wty.app.goschool.R;
import com.wty.app.goschool.fragment.HomeFragment;
import com.wty.app.goschool.fragment.LifeFragment;
import com.wty.app.goschool.fragment.MySelfFragment;
import com.wty.app.goschool.fragment.SchoolFragment;
import com.wty.app.library.activity.BaseActivity;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.widget.TabStripView;


import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.navigateTabBar)
    TabStripView navigateTabBar;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        //对应xml中的containerId
        navigateTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.colorPrimary));
        //对应xml中的navigateTabSelectedTextColor
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.colorPrimary));

        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(HomeFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_home_normal, R.drawable.ic_tab_home_pressed, "主页"));
        navigateTabBar.addTab(LifeFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_audit_normal, R.drawable.ic_tab_audit_pressed, "生活圈"));
        navigateTabBar.addTab(SchoolFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_office_normal, R.drawable.ic_tab_office_pressed, "校园"));
        navigateTabBar.addTab(MySelfFragment.class, new TabStripView.TabParam(R.drawable.ic_tab_myself_normal, R.drawable.ic_tab_myself_pressed, "设置"));
        navigateTabBar.setTabSelectListener(new TabStripView.OnTabSelectedListener(){
            @Override
            public void onTabSelected(Fragment fragment) {
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        navigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    protected boolean isEnableStatusBar() {
        return true;
    }
}
