package com.wty.app.goschool.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.wty.app.goschool.R;
import com.wty.app.goschool.activity.ImagePagerActivity;
import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.entity.ImageSize;
import com.wty.app.library.adapter.BaseRecyclerViewMultiItemAdapter;
import com.wty.app.library.adapter.NineGridImageViewAdapter;
import com.wty.app.library.utils.ImageLoaderUtil;
import com.wty.app.library.viewholder.BaseRecyclerViewHolder;
import com.wty.app.library.widget.ItemView;
import com.wty.app.library.widget.imageview.ColorFilterImageView;
import com.wty.app.library.widget.imageview.NineGridImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Decription 跳蚤市场 适配器
 */
public class MarketAdapter extends BaseRecyclerViewMultiItemAdapter<MarketDynamicDALEx> {
    public MarketAdapter(Context context, List<MarketDynamicDALEx> data) {
        super(context, data);
        addItemType(MarketDynamicDALEx.No_Picture,R.layout.fragment_market_item);
        addItemType(MarketDynamicDALEx.OnlyOne_Picture, R.layout.fragment_market_item);
        addItemType(MarketDynamicDALEx.Multi_Picture,R.layout.fragment_market_item);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, MarketDynamicDALEx item,int position) {
        switch (helper.getItemViewType()){

            case MarketDynamicDALEx.No_Picture:
                break;

            case MarketDynamicDALEx.OnlyOne_Picture:
                ViewStub oneStub = helper.getView(R.id.viewStub);
                if(oneStub != null){
                    oneStub.setLayoutResource(R.layout.viewstub_oneimg_body);
                    oneStub.inflate();
                    //去掉viewstub  防止发生ViewStub must have a non-null ViewGroup viewParent
                    helper.removeView(R.id.viewStub);
                }

                ColorFilterImageView img = helper.getView(R.id.oneImagView);
                ImageLoaderUtil.load(mContext,item.getGsImage(),R.drawable.img_default_loading,img);
                break;

            case MarketDynamicDALEx.Multi_Picture:
                List<String> list = Arrays.asList(item.getGsImage().split(","));

                ViewStub multiStub = helper.getView(R.id.viewStub);
                if(multiStub != null){
                    multiStub.setLayoutResource(R.layout.viewstub_multiimg_body);
                    multiStub.inflate();
                    //去掉viewstub  防止发生ViewStub must have a non-null ViewGroup viewParent
                    helper.removeView(R.id.viewStub);
                }

                NineGridImageView imageView = helper.getView(R.id.multiImagView);
                imageView.setAdapter(mAdapter);
                List<String> data = new ArrayList<>();
                data.addAll(list);
                imageView.setImagesData(data);

                final ItemView itemview_digg = helper.getView(R.id.itemview_digg);
                itemview_digg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            itemview_digg.setGoodView();
                            itemview_digg.setSelected(true);
                        }
                });

                break;
            default:
                break;
        }
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String path) {
            ImageLoaderUtil.load(mContext,path,R.drawable.img_default_loading,imageView);
        }

        @Override
        public void onItemImageClick(Context context,View v, int index, List<String> list) {
            ImageSize imageSize = new ImageSize(v.getMeasuredWidth(),v.getMeasuredHeight());
            ImagePagerActivity.startImagePagerActivity(context,list,index,imageSize);
        }
    };

}
