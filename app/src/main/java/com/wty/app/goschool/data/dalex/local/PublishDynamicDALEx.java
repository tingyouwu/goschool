package com.wty.app.goschool.data.dalex.local;

import com.wty.app.library.data.annotation.DatabaseField;
import com.wty.app.library.data.annotation.DatabaseField.FieldType;
import com.wty.app.library.data.annotation.SqliteDao;
import com.wty.app.library.data.dalex.SqliteBaseDALEx;

/**
 * @Decription 发布动态信息的存储类
 * @author wty
 */
public class PublishDynamicDALEx extends SqliteBaseDALEx {
	
	private static final long serialVersionUID = 1L;

	@DatabaseField(primaryKey = true,Type = FieldType.VARCHAR)
	private String gsdynamicid;//动态id

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

	public static PublishDynamicDALEx get() {
		return SqliteDao.getDao(PublishDynamicDALEx.class);
	}

	/**
	 * @Decription 实体类型
	 **/
	public enum PublishEntityType{
		OnlyText(1,"文字"),Picture(2,"图片"),Video(3,"视频");
		public int code;
		public String name;
		PublishEntityType(int code,String name) {
			this.name = name;
			this.code = code;
		}

		public static String match(int code){
			String result = "";
			for(PublishEntityType entity:PublishEntityType.values()){
				if(code == entity.code){
					result = entity.name;
				}
			}
			return result;
		}
	}

	/**
	 * @Decription 动态类型
	 **/
	public enum PublishDynamicType{
		Notic("1","通知"),Complain("2","吐槽"),Market("3","寻宝贝"),Help("4","找师兄");
		public String code;
		public String name;
		PublishDynamicType(String code,String name) {
			this.name = name;
			this.code = code;
		}

		public static String matchName(String code){
			String result = "";
			for(PublishDynamicType type:PublishDynamicType.values()){
				if(code.equals(type.code)){
					result = type.name;
				}
			}
			return result;
		}

		public static String matchCode(String name){
			String result = "";
			for(PublishDynamicType type:PublishDynamicType.values()){
				if(name.equals(type.name)){
					result = type.code;
				}
			}
			return result;
		}
	}

}
