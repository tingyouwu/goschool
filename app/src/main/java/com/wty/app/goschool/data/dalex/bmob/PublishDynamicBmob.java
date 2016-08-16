package com.wty.app.goschool.data.dalex.bmob;

import com.wty.app.library.data.annotation.DatabaseField;
import com.wty.app.library.data.annotation.DatabaseField.FieldType;

/**
 * @Decription 发布动态信息的存储类(bmob)
 * @author wty
 */
public class PublishDynamicBmob extends BaseBmobObject {
	
	@DatabaseField(Type = FieldType.VARCHAR)
	private String gscontent;//填写内容

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gsImage;//图片

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gsdynamictype;//动态类型 通知 吐槽 二手市场 找师兄

	@DatabaseField(Type = FieldType.INT)
	private int gsentitytype;//实体类型 0正常，只有文字  1包含图片  2包含视频

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gsaddress;//地址

	@DatabaseField(Type = FieldType.INT)
	private int gssender;//发送人id

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gssendname; // 发送人名字

	@DatabaseField(Type=FieldType.VARCHAR)
	private String gssenderlogourl;//发送人的头像

	public PublishDynamicBmob(){
		//由于限制  类名不能超过20个字
		super(PublishDynamicBmob.class.getSimpleName());
	}
}
