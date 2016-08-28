package com.wty.app.goschool.data.dalex.bmob;

import com.wty.app.goschool.data.annotation.bmob.BmobObjectDao;
import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.library.data.annotation.DatabaseField;
import com.wty.app.library.data.annotation.DatabaseField.FieldType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 发布跳蚤市场信息(bmob)
 * @author wty
 */
public class MarketDynamicBmob extends BaseBmobObject {

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

	@DatabaseField(Type = FieldType.REAL)
	private float gssinglesize;//宽高比例  宽/高

	public static MarketDynamicBmob get(){
		return BmobObjectDao.getDao(MarketDynamicBmob.class);
	}

	public void save(final List<MarketDynamicBmob> list){
		List<MarketDynamicDALEx> localdalex = new ArrayList<MarketDynamicDALEx>();
		for(MarketDynamicBmob bmob:list){
			MarketDynamicDALEx dalex = new MarketDynamicDALEx();
			dalex.setAnnotationField(bmob.getAnnotationFieldValue());
			dalex.setGsdynamicid(bmob.getObjectId());
			localdalex.add(dalex);
		}
		MarketDynamicDALEx.get().saveOrUpdate(localdalex);
	}

	public List<MarketDynamicDALEx> saveReturn(final List<MarketDynamicBmob> list){
		List<MarketDynamicDALEx> localdalex = new ArrayList<MarketDynamicDALEx>();
		for(MarketDynamicBmob bmob:list){
			MarketDynamicDALEx dalex = new MarketDynamicDALEx();
			dalex.setAnnotationField(bmob.getAnnotationFieldValue());
			dalex.setGsdynamicid(bmob.getObjectId());
			localdalex.add(dalex);
		}
		MarketDynamicDALEx.get().saveOrUpdate(localdalex);
		return localdalex;
	}
}
