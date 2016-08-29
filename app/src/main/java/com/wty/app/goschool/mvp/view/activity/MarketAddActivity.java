package com.wty.app.goschool.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.wty.app.goschool.R;
import com.wty.app.goschool.adapter.DialogSelectListAdapter;
import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.mvp.presenter.MarketAddPresenter;
import com.wty.app.goschool.mvp.view.impl.IMarketAddView;
import com.wty.app.library.activity.BaseActivity;
import com.wty.app.library.activity.ImageSelectorActivity;
import com.wty.app.library.adapter.PhotoGridViewAdapter;
import com.wty.app.library.base.AppConstant;
import com.wty.app.library.bean.DialogOptionModel;
import com.wty.app.library.entity.ImageModel;
import com.wty.app.library.entity.ImageUriEntity;
import com.wty.app.library.utils.CommonUtil;
import com.wty.app.library.utils.NetWorkUtils;
import com.wty.app.library.widget.DialogHeaderView;
import com.wty.app.library.widget.MyTextWatcher;
import com.wty.app.library.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;

/**
 * @Decription 跳蚤市场发布新内容
 * @author wty
 */
public class MarketAddActivity extends BaseActivity<MarketAddPresenter> implements IMarketAddView{

    @Bind(R.id.et_content)
    EditText et_content;
    @Bind(R.id.img_grid_select)
    RecyclerView img_gridview;
    @Bind(R.id.tv_label)
    TextView tv_label;
    @Bind(R.id.et_price_new)
    EditText et_pricenew;//售价
    @Bind(R.id.et_price_old)
    EditText et_priceold;//原价
    @Bind(R.id.tb_postage)
    ToggleButton tb_postage;//包邮
    @Bind(R.id.tv_contact_type)
    TextView tv_contacttype;//联系方式
    @Bind(R.id.et_contact_number)
    EditText et_contactnumber;//号码

    PhotoGridViewAdapter adapter;
    boolean isPostageOn;//是否包邮

    public static void startMarketAddActivity(Context context){
        Intent intent = new Intent(context,MarketAddActivity.class);
        context.startActivity(intent);
    }

    @Override
    public MarketAddPresenter getPresenter() {
        return new MarketAddPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        getDefaultNavigation().setTitle("跳蚤市场");
        getDefaultNavigation().getLeftButton().setText("主页");
        getDefaultNavigation().setRightButton("发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        getDefaultNavigation().getRightButton().setEnabled(false);

        adapter = new PhotoGridViewAdapter(this);
        adapter.setGridItemClickListener(listener);
        img_gridview.setLayoutManager(new GridLayoutManager(this, 4));
        img_gridview.setAdapter(adapter);
        registerListener();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_market_add;
    }

    @Override
    protected boolean submit() {
        if(super.submit()){
            mPresenter.submit(getSubmitData());
        }
        return true;
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

    /**
     * @Decription 注册监听
     **/
    private void registerListener(){
        et_content.addTextChangedListener(watcherForContent);
        et_pricenew.addTextChangedListener(watcherForNewPrice);
        et_priceold.addTextChangedListener(watcherForOldPrice);

        tv_contacttype.setOnClickListener(new View.OnClickListener() {

            DialogHeaderView headerView = new DialogHeaderView(MarketAddActivity.this, "选择联系方式");

            @Override
            public void onClick(View v) {

                CommonUtil.keyboardControl(MarketAddActivity.this, false, et_content);

                List<DialogOptionModel> data = new ArrayList<DialogOptionModel>();
                for (MarketDynamicDALEx.MarketContactType item : MarketDynamicDALEx.MarketContactType.values()) {
                    DialogOptionModel model = new DialogOptionModel(item.name, item.code);
                    data.add(model);
                }

                final DialogPlus dialog = DialogPlus.newDialog(MarketAddActivity.this)
                        .setContentHolder(new ListHolder())
                        .setCancelable(true)
                        .setGravity(Gravity.CENTER)
                        .setHeader(headerView)
                        .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setContentBackgroundResource(R.drawable.bg_dialog_list)
                        .setAdapter(new DialogSelectListAdapter(MarketAddActivity.this, data))
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                tv_contacttype.setText(((DialogOptionModel) item).getText());
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        tb_postage.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(ToggleButton toggleButton, boolean on) {
                isPostageOn = on;
            }
        });
    }

    TextWatcher watcherForContent = new MyTextWatcher() {
        int MAX_LENGTH = 300;
        int rest_Length = 300;

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

    TextWatcher watcherForNewPrice = new MyTextWatcher() {
        int MAX_LENGTH = 7;//最多100万  土豪不用android
        int rest_Length = 7;

        @Override
        public void afterTextChanged(Editable s) {
            rest_Length = MAX_LENGTH - s.length();
            if(rest_Length==0){
                AppMsg.makeText(MarketAddActivity.this,"物品价格必须必须在0元和100万元之间",AppMsg.STYLE_INFO).show();
            }
        }
    };

    TextWatcher watcherForOldPrice = new MyTextWatcher() {
        int MAX_LENGTH = 7;//最多100万
        int rest_Length = 7;

        @Override
        public void afterTextChanged(Editable s) {
            rest_Length = MAX_LENGTH - s.length();
            if(rest_Length==0){
                AppMsg.makeText(MarketAddActivity.this,"物品价格必须必须在0元和100万元之间",AppMsg.STYLE_INFO).show();
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
            ImageSelectorActivity.start(MarketAddActivity.this,MAX_NUM,ImageSelectorActivity.MODE_MULTIPLE,true,true,false,selected);
        }

        @Override
        public void onReduceClick(int position, ImageUriEntity item) {
            adapter.remove(position,item);
        }

        @Override
        public void onItemClick(View view, int position) {

        }
    };

    @Override
    public boolean checkNet() {
        return NetWorkUtils.isNetworkConnected(this);
    }

    @Override
    public void showNoNet() {
        showFailed(getString(R.string.network_failed));
    }

    @Override
    public void finishActivity() {
        finish();
    }

    private MarketDynamicDALEx getSubmitData() {
        MarketDynamicDALEx dalex = MarketDynamicDALEx.get();
        dalex.setGsdynamicid(UUID.randomUUID().toString());//主键id
        dalex.setGscontent(et_content.getText().toString());//填写的内容
        dalex.setGspricenew(Integer.parseInt(et_pricenew.getText().toString()));
        dalex.setGspriceold(Integer.parseInt(et_priceold.getText().toString()));
        dalex.setGspostage(isPostageOn ? MarketDynamicDALEx.Postage_True : MarketDynamicDALEx.Postage_False);
        dalex.setGscontacttype(MarketDynamicDALEx.MarketContactType.matchCode(tv_contacttype.getText().toString()));
        dalex.setGscontactdetail(et_contactnumber.getText().toString());

        dalex.setGsImage(adapter.getSelectImagesPath());//图片
        dalex.setGsaddress("");//地址
        dalex.setGssendname("wty");//发送人名
        dalex.setGssenderlogourl("");//发送人头像
        return dalex;
    }
}
