package com.wty.app.goschool.widget.myself;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.wty.app.goschool.R;
import com.wty.app.goschool.data.dalex.local.UserDALEx;
import com.wty.app.library.widget.imageview.CircleImageView;

/**
 * @Description 头像布局
 * @author wty
 **/
public class MySelfIconLayout extends LinearLayout implements OnClickListener{

	private LinearLayout layout_publish;
	private LinearLayout layout_feedback;
	private CircleImageView iv_icon;
	private UserDALEx me;
	
	public MySelfIconLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取LAYOUT_INFLATER_SERVICE，实例化LayoutInflater，实现动态加载布局
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.layout_myself_iconlayout, this);
		
		iv_icon = (CircleImageView)findViewById(R.id.iv_myself_icon);
		layout_publish = (LinearLayout)findViewById(R.id.llayout_myself_publish);
		layout_feedback = (LinearLayout)findViewById(R.id.llayout_myself_feedback);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.myself_layout:
			Intent i = new Intent();
			i.putExtra("id", me.getUsernumber());
			getContext().startActivity(i);
			break;
		}
	}
	
}
