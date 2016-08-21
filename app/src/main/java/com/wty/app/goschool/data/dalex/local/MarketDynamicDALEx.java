package com.wty.app.goschool.data.dalex.local;

import com.wty.app.library.data.annotation.DatabaseField;
import com.wty.app.library.data.annotation.DatabaseField.FieldType;
import com.wty.app.library.data.annotation.SqliteDao;
import com.wty.app.library.data.dalex.SqliteBaseDALEx;
import com.wty.app.library.entity.IMultiItemEntity;

/**
 * @Decription 跳蚤市场信息存储类
 * @author wty
 */
public class MarketDynamicDALEx extends SqliteBaseDALEx implements IMultiItemEntity{
	
	private static final long serialVersionUID = 1L;
	public static final int Postage_True = 1;//包邮
	public static final int Postage_False = 0;//字符邮费

	public static final int No_Picture = 0;
	public static final int OnlyOne_Picture = 1;
	public static final int Multi_Picture = 2;

	@DatabaseField(primaryKey = true,Type = FieldType.VARCHAR)
	private String gsdynamicid;//动态id

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gscontent;//填写内容

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gsImage;//图片

	@DatabaseField(Type = FieldType.INT)
	private int gspricenew;//新售价

	@DatabaseField(Type = FieldType.INT)
	private int gspriceold;//原价

	@DatabaseField(Type = FieldType.INT)
	private int gspostage;//是否包邮  1包邮 2自费

	@DatabaseField(Type = FieldType.INT)
	private int gscontacttype;//联系方式 Mobile(1,"手机"),WeChat(2,"微信"),QQ(3,"QQ");

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gscontactdetail;//联系方式详情 电话号码 微信号  QQ号

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gsaddress;//地址

	@DatabaseField(Type = FieldType.INT)
	private int gssender;//发送人id

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gssendname; // 发送人名字

	@DatabaseField(Type=FieldType.VARCHAR)
	private String gssenderlogourl;//发送人的头像

	@DatabaseField(Type = FieldType.VARCHAR)
	private String gssendtime; // 发送时间

	public static MarketDynamicDALEx get() {
		return SqliteDao.getDao(MarketDynamicDALEx.class);
	}

	@Override
	public int getItemType() {
		int length = gsImage.split(",").length;

		if(length > 1)
			return Multi_Picture;
		else if (length ==1)
			return OnlyOne_Picture;

		return No_Picture;
	}

	/**
	 * @Decription 联系方式
	 **/
	public enum MarketContactType{
		Mobile(1,"手机"),WeChat(2,"微信"),QQ(3,"QQ");
		public int code;
		public String name;
		MarketContactType(int code,String name) {
			this.name = name;
			this.code = code;
		}

		public static String match(int code){
			String result = "";
			for(MarketContactType type: MarketContactType.values()){
				if(code == type.code){
					result = type.name;
					break;
				}
			}
			return result;
		}

		public static int matchCode(String name){
			int result = 0;
			for(MarketContactType type: MarketContactType.values()){
				if(name.equals(type.name)){
					result = type.code;
					break;
				}
			}
			return result;
		}
	}

	public String getGsdynamicid() {
		return gsdynamicid;
	}

	public void setGsdynamicid(String gsdynamicid) {
		this.gsdynamicid = gsdynamicid;
	}

	public String getGscontent() {
		return gscontent;
	}

	public void setGscontent(String gscontent) {
		this.gscontent = gscontent;
	}

	public String getGsImage() {
		return gsImage;
	}

	public void setGsImage(String gsImage) {
		this.gsImage = gsImage;
	}

	public String getGsaddress() {
		return gsaddress;
	}

	public void setGsaddress(String gsaddress) {
		this.gsaddress = gsaddress;
	}

	public int getGssender() {
		return gssender;
	}

	public void setGssender(int gssender) {
		this.gssender = gssender;
	}

	public String getGssendname() {
		return gssendname;
	}

	public void setGssendname(String gssendname) {
		this.gssendname = gssendname;
	}

	public String getGssenderlogourl() {
		return gssenderlogourl;
	}

	public void setGssenderlogourl(String gssenderlogourl) {
		this.gssenderlogourl = gssenderlogourl;
	}

	public int getGspricenew() {
		return gspricenew;
	}

	public void setGspricenew(int gspricenew) {
		this.gspricenew = gspricenew;
	}

	public int getGspriceold() {
		return gspriceold;
	}

	public void setGspriceold(int gspriceold) {
		this.gspriceold = gspriceold;
	}

	public int getGspostage() {
		return gspostage;
	}

	public void setGspostage(int gspostage) {
		this.gspostage = gspostage;
	}

	public int getGscontacttype() {
		return gscontacttype;
	}

	public void setGscontacttype(int gscontacttype) {
		this.gscontacttype = gscontacttype;
	}

	public String getGscontactdetail() {
		return gscontactdetail;
	}

	public void setGscontactdetail(String gscontactdetail) {
		this.gscontactdetail = gscontactdetail;
	}

	public String getGssendtime() {
		return gssendtime;
	}

	public void setGssendtime(String gssendtime) {
		this.gssendtime = gssendtime;
	}

}
