package com.wty.app.goschool.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wty.app.goschool.R;
import com.wty.app.goschool.activity.ImagePagerActivity;
import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.entity.ImageSize;
import com.wty.app.library.adapter.BaseRecyclerViewMultiItemAdapter;
import com.wty.app.library.adapter.NineGridImageViewAdapter;
import com.wty.app.library.utils.ImageLoaderUtil;
import com.wty.app.library.utils.ScreenUtil;
import com.wty.app.library.viewholder.BaseRecyclerViewHolder;
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
        addItemType(MarketDynamicDALEx.OnlyOne_Picture, R.layout.fragment_market_oneitem);
        addItemType(MarketDynamicDALEx.Multi_Picture,R.layout.fragment_market_multiitem);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, MarketDynamicDALEx item,int position) {
        TextView tv_pricenew =helper.getView(R.id.tv_price_new);
        TextView tv_priceold =helper.getView(R.id.tv_price_old);
        TextView tv_content = helper.getView(R.id.tv_content);
        switch (helper.getItemViewType()){

            case MarketDynamicDALEx.No_Picture:
                break;

            case MarketDynamicDALEx.OnlyOne_Picture:
                final List<String> listOne = Arrays.asList(item.getGsImage().split(","));
                tv_content.setText(item.getGscontent());
                tv_pricenew.setText("¥"+item.getGspricenew());
                tv_priceold.setText("¥"+item.getGspriceold());

                ColorFilterImageView img = helper.getView(R.id.oneImagView);

                ViewGroup.LayoutParams lp = img.getLayoutParams();
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;

                img.setAdjustViewBounds(true);
                img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                img.setMaxWidth(ScreenUtil.dp2px(mContext,200));
                img.setMaxHeight(ScreenUtil.dp2px(mContext,250));
                img.setLayoutParams(lp);

                ImageLoaderUtil.load(mContext,item.getGsImage(),img);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageSize imageSize = new ImageSize(v.getMeasuredWidth(),v.getMeasuredHeight());
                        ImagePagerActivity.startImagePagerActivity(mContext,listOne,0,imageSize);
                    }
                });
                break;

            case MarketDynamicDALEx.Multi_Picture:
                tv_content.setText(item.getGscontent());
                tv_pricenew.setText("¥"+item.getGspricenew());
                tv_priceold.setText("¥"+item.getGspriceold());
                List<String> list = Arrays.asList(item.getGsImage().split(","));

                NineGridImageView imageView = helper.getView(R.id.multiImagView);
                imageView.setAdapter(mAdapter);
                List<String> data = new ArrayList<>();
                data.addAll(list);
                imageView.setImagesData(data);

                break;
            default:
                break;
        }
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String path) {
            ImageLoaderUtil.load(mContext,path,imageView);
        }

        @Override
        public void onItemImageClick(Context context,View v, int index, List<String> list) {
            ImageSize imageSize = new ImageSize(v.getMeasuredWidth(),v.getMeasuredHeight());
            ImagePagerActivity.startImagePagerActivity(context,list,index,imageSize);
        }
    };

}
