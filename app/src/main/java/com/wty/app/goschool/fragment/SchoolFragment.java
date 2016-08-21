package com.wty.app.goschool.fragment;

import android.os.Bundle;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.wty.app.goschool.R;
import com.wty.app.library.fragment.BaseFragment;
import com.wty.app.library.mvp.presenter.BasePresenter;

import rx.functions.Action1;

/**
 * 统计
 * @author wty
 */
public class SchoolFragment extends BaseFragment {

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

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_market_add;
    }

    @Override
    public void initFragmentActionBar(String title) {
        super.initFragmentActionBar(title);
    }
}
