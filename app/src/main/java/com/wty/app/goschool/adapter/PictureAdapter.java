package com.wty.app.goschool.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wty.app.goschool.R;
import com.wty.app.goschool.activity.ImagePagerActivity;
import com.wty.app.goschool.entity.ActionItem;
import com.wty.app.goschool.entity.ImageSize;
import com.wty.app.library.adapter.BaseRecyclerViewMultiItemAdapter;
import com.wty.app.library.adapter.NineGridImageViewAdapter;
import com.wty.app.library.viewholder.BaseRecyclerViewHolder;
import com.wty.app.library.widget.ItemView;
import com.wty.app.library.widget.imageview.NineGridImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 图片 适配器
 */
public class PictureAdapter extends BaseRecyclerViewMultiItemAdapter<ActionItem> {
    public PictureAdapter(Context context, List<ActionItem> data) {
        super(context,data);
        addItemType(ActionItem.TEXT, R.layout.fragment_dynamic_item);
        addItemType(ActionItem.IMG,R.layout.fragment_dynamic_item);
        addItemType(ActionItem.IMGS,R.layout.fragment_dynamic_item);
    }

    @Override
    protected void convert(BaseRecyclerViewHolder helper, ActionItem item,int position) {
        switch (helper.getItemViewType()){
            case ActionItem.TEXT:
                break;
            case ActionItem.IMG:
                if(item.getUrl().size()!=0){
                    ViewStub viewStub = helper.getView(R.id.viewStub);
                    if(viewStub != null){
                        viewStub.setLayoutResource(R.layout.viewstub_imgbody);
                        viewStub.inflate();
                        //去掉viewstub  防止发生ViewStub must have a non-null ViewGroup viewParent
                        helper.removeView(R.id.viewStub);
                    }
                    NineGridImageView imageView = helper.getView(R.id.multiImagView);
                    imageView.setAdapter(mAdapter);
                    List<String> data = new ArrayList<>();
                    data.addAll(item.getUrl());
                    imageView.setImagesData(data);

                    final ItemView itemview_digg = helper.getView(R.id.itemview_digg);
                    itemview_digg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            itemview_digg.setGoodView();
                            itemview_digg.setSelected(true);
                        }
                    });

                }

                break;
            case ActionItem.IMGS:
                break;
            default:
                break;
        }
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String s) {
            Glide.with(mContext)
                    .load(s)
                    .placeholder(R.drawable.img_default_loading)
                    .error(R.drawable.img_error_fail)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

        @Override
        public void onItemImageClick(Context context,View v, int index, List<String> list) {
            ImageSize imageSize = new ImageSize(v.getMeasuredWidth(),v.getMeasuredHeight());
            ImagePagerActivity.startImagePagerActivity(context,list,index,imageSize);
        }
    };

}
