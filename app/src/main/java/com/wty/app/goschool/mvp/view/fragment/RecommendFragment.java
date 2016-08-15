package com.wty.app.goschool.mvp.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wty.app.goschool.R;
import com.wty.app.goschool.adapter.HomeAdapter;
import com.wty.app.goschool.entity.ActionItem;
import com.wty.app.library.adapter.BaseRecyclerViewAdapter;
import com.wty.app.library.fragment.BaseFragment;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.widget.DivItemDecoration;
import com.wty.app.library.widget.xrecyclerview.ProgressStyle;
import com.wty.app.library.widget.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * 主页->推荐
 * @author wty
 */
public class RecommendFragment extends BaseFragment {

    BaseRecyclerViewAdapter adapter;

    @Bind(R.id.listview_life)
    XRecyclerView listview;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        initData();
        adapter = new HomeAdapter(getContext(),mDataList);
        listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        listview.setAdapter(adapter);
        listview.addItemDecoration(new DivItemDecoration(15, true));
        listview.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
        listview.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        listview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                int count = refreshAddData();
                listview.refreshComplete(count + "条新内容");
            }

            @Override
            public void onLoadMore() {
//                LoadMoreAddData();
//                listview.loadMoreComplete();
                listview.setNoMore("亲,已经是最后一页了！");
            }

        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_life;
    }

    @Override
    public void initFragmentActionBar(String title) {
        super.initFragmentActionBar(title);
        activity.getDefaultNavigation().setRightButton("发表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private ArrayList<ActionItem> mDataList;


    private String[] IMG_URL_LIST = {
            "https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg",
            "https://pic4.zhimg.com/fc04224598878080115ba387846eabc3_xll.jpg",
            "https://pic3.zhimg.com/d1750bd47b514ad62af9497bbe5bb17e_xll.jpg",
            "https://pic4.zhimg.com/da52c865cb6a472c3624a78490d9a3b7_xll.jpg",
            "https://pic3.zhimg.com/0c149770fc2e16f4a89e6fc479272946_xll.jpg",
            "https://pic1.zhimg.com/76903410e4831571e19a10f39717988c_xll.png",
            "https://pic3.zhimg.com/33c6cf59163b3f17ca0c091a5c0d9272_xll.jpg",
            "https://pic4.zhimg.com/52e093cbf96fd0d027136baf9b5cdcb3_xll.png",
            "https://pic3.zhimg.com/f6dc1c1cecd7ba8f4c61c7c31847773e_xll.jpg",
    };

    /**
     * 测试数据
     **/
    private void initData() {
        mDataList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            ActionItem item = new ActionItem();
            item.setItemType(ActionItem.IMG);
            List<String> imgUrls = new ArrayList<>();
            imgUrls.addAll(Arrays.asList(IMG_URL_LIST).subList(0, i % 9));
            item.setUrl(imgUrls);
            mDataList.add(item);
        }
    }

    private int refreshAddData(){
        List<ActionItem> data = new ArrayList<>();
        for(int i = 0;i<20;i++){
            ActionItem item = new ActionItem();
            item.setItemType(ActionItem.IMG);
            List<String> imgUrls = new ArrayList<>();
            imgUrls.addAll(Arrays.asList(IMG_URL_LIST).subList(0, i % 9));
            item.setUrl(imgUrls);
            data.add(item);
        }
        adapter.addData(data);
        return data.size();
    }

    private void LoadMoreAddData(){
        List<ActionItem> data = new ArrayList<>();
        for(int i = 0;i<20;i++){
            ActionItem item = new ActionItem();
            item.setItemType(ActionItem.IMG);
            List<String> imgUrls = new ArrayList<>();
            imgUrls.addAll(Arrays.asList(IMG_URL_LIST).subList(0, i % 9));
            item.setUrl(imgUrls);
            data.add(item);
        }
        adapter.addData(data);
    }
}
