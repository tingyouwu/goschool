package com.wty.app.goschool.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wty.app.goschool.R;
import com.wty.app.library.activity.BaseActivity;
import com.wty.app.library.activity.ImageSelectorActivity;
import com.wty.app.library.adapter.PhotoGridViewAdapter;
import com.wty.app.library.base.AppConstant;
import com.wty.app.library.entity.ImageModel;
import com.wty.app.library.entity.ImageUriEntity;
import com.wty.app.library.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @Decription 发布新内容
 * @author wty
 */
public class PublishActivity extends BaseActivity {

    @Bind(R.id.et_content)
    EditText et_content;
    @Bind(R.id.img_grid_select)
    RecyclerView img_gridview;
    @Bind(R.id.tv_label)
    TextView tv_label;

    PhotoGridViewAdapter adapter;

    public static void startPublishActivity(Context context){
        Intent intent = new Intent(context,PublishActivity.class);
        context.startActivity(intent);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getDefaultNavigation().setTitle("写内容");
        getDefaultNavigation().getLeftButton().setText("主页");
        getDefaultNavigation().setRightButton("发表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        getDefaultNavigation().getRightButton().setEnabled(false);
        et_content.addTextChangedListener(watcher);

        adapter = new PhotoGridViewAdapter(this);
        adapter.setGridItemClickListener(listener);
        img_gridview.setLayoutManager(new GridLayoutManager(this, 4));
        img_gridview.setAdapter(adapter);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_publish;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {//从选择图片页面返回
            if (requestCode == AppConstant.ActivityResult.Request_Image) {
                //拿到返回的图片路径
                boolean isCamera = data.getBooleanExtra(ImageSelectorActivity.OUTPUT_ISCAMERA, false);
                ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                if(isCamera){
                    adapter.addOneImage(images.get(0));
                }else{
                    adapter.bindImagesByPath(images);
                }
            }
        }
    }

    @Override
    protected boolean isEnableStatusBar() {
        return true;
    }

    TextWatcher watcher = new TextWatcher() {

        int MAX_LENGTH = 300;
        int rest_Length = 300;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            rest_Length = MAX_LENGTH - s.length();
            tv_label.setText("(还能输入" + rest_Length + "个字)");
            if(s.length()==0){
                getDefaultNavigation().getRightButton().setEnabled(false);
            }else{
                getDefaultNavigation().getRightButton().setEnabled(true);
            }
        }
    };

    private PhotoGridViewAdapter.OnGridItemClickListener listener = new PhotoGridViewAdapter.OnGridItemClickListener() {
        final int  MAX_NUM = 9;
        @Override
        public void onAddClick() {
            List<ImageUriEntity> selectedImages = new ArrayList<>();
            List<ImageModel> selected = new ArrayList<>();
            selectedImages.addAll(adapter.getSelectImages());
            for(ImageUriEntity item:selectedImages){
                ImageModel model = new ImageModel(item.uri,"");
                selected.add(model);
            }
            ImageSelectorActivity.start(PublishActivity.this,MAX_NUM,ImageSelectorActivity.MODE_MULTIPLE,true,true,false,selected);
        }

        @Override
        public void onReduceClick(int position, ImageUriEntity item) {
            adapter.remove(position,item);
        }

        @Override
        public void onItemClick(View view, int position) {

        }
    };

}
