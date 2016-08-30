package com.wty.app.goschool.entity;

import java.util.List;

/**
 * 
* @ClassName: ActionItem 
* @Description: 弹窗内部子类项（绘制标题和图标） 
* @author yiw
* @date 2015-12-28 下午3:43:30 
*
 */
public class ActionItem{

	public static final int TEXT = 1;
	public static final int IMG = 2;
	public static final int IMGS = 3;

	private List<String> url;
	private int itemType;

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
}
