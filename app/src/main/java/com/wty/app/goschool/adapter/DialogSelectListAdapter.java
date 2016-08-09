package com.wty.app.goschool.adapter;

import android.content.Context;
import android.widget.TextView;

import com.wty.app.goschool.R;
import com.wty.app.library.adapter.BaseViewCommonAdapter;
import com.wty.app.library.bean.DialogOptionModel;
import com.wty.app.library.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @Decription Dialog选项 适配器
 */
public class DialogSelectListAdapter extends BaseViewCommonAdapter<DialogOptionModel> {
    public DialogSelectListAdapter(Context context, List data) {
        super(context,R.layout.item_dialog_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DialogOptionModel item) {
        TextView tv = helper.getView(R.id.tv_item_dialog);
        tv.setText(item.getText());
    }

}
