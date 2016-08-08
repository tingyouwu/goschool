package com.wty.app.goschool.data.dalex.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.wty.app.library.data.AppDBHelper;
import com.wty.app.library.data.annotation.DatabaseField;
import com.wty.app.library.data.annotation.DatabaseField.FieldType;
import com.wty.app.library.data.annotation.SqliteDao;
import com.wty.app.library.data.dalex.SqliteBaseDALEx;

/**
 * 用户基本信息存储类
 * 
 * @author Administrator
 * 
 */
public class UserDALEx extends SqliteBaseDALEx {
	
	private static final long serialVersionUID = 1L;
	private static final String USERNUMBER = "usernumber";			//个人E号 INT4
	private static final String ACCOUNTNO = "accountno";            //帐号
	private static final String LOGINSUCCESS = "loginSuccess";            //帐号
	private static final String ENTERPRISETYPE = "enterprisetype";

	@DatabaseField(Type=FieldType.VARCHAR,primaryKey=true)
	private String usernumber;
	@DatabaseField(Type=FieldType.VARCHAR)
	private String departmentid;
	@DatabaseField(Type=FieldType.VARCHAR)
	private String workcode;
	@DatabaseField(Type= FieldType.VARCHAR)
	private String username;
	@DatabaseField(Type= FieldType.VARCHAR)
	private String sex;
	@DatabaseField(Type=FieldType.VARCHAR)
	private String tel;
	@DatabaseField(Type=FieldType.VARCHAR)
	private String email;
	@DatabaseField(Type=FieldType.VARCHAR)
	private String remark;
	@DatabaseField(Type=FieldType.VARCHAR)
	private String logourl;

	public static UserDALEx get() {
		return SqliteDao.getDao(UserDALEx.class);
	}
	
	/**
	 * 功能描述：保存用户信息至数据库，有相同的e号即更新，没有就做插入操作
	 */
	public void save(UserDALEx user){
		AppDBHelper db = null;
		try {
			db = getDB();
			db.getConnection().beginTransaction();
			ContentValues values = user.tranform2Values();
			if (isExist(USERNUMBER, "" + user.getUsernumber())) {
				db.update(TABLE_NAME, values, USERNUMBER + "=?", new String[] { user.getUsernumber() });
			} else {
				db.save(TABLE_NAME, values);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.getConnection().setTransactionSuccessful();
				db.getConnection().endTransaction();
			}
		}
	}

	/**
	 * 通过获取e号获取用户
	 */
	public UserDALEx getUserByUsernumber(String usernumber) {
		UserDALEx user = null;
		Cursor cursor = null;
		try {
			AppDBHelper db = getDB();
			if (db.isTableExits(TABLE_NAME)) {
				cursor = db.find("select * from "+ TABLE_NAME + "  where " + USERNUMBER + "=? ",new String[] { usernumber });
				if (cursor.moveToNext()) {
					user = new UserDALEx();
					user.setAnnotationField(cursor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return user;
	}

	public String getUsernumber() {
		return usernumber;
	}

	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}
}
