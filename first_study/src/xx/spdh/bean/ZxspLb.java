/*
 *@(#)xx.spdh.bean
 *@ZxspLb.java.java  
 *@创建时间:2011-8-20上午10:51:13
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.bean;




import java.util.Date;

import org.hibernate.annotations.Entity;

/**
 * @ZxspLb <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Entity
public class ZxspLb {
	
	private Integer int1;
	private Integer int2;
	private Integer int3;
	private Integer int4;
	private Integer int5;
	private Date dt;
	private String str1;
	private String str2;
	private String str3;
	private String str4;
	private String str5;
	private String str6;
	private String str7;
	private String str8;
	
	public ZxspLb() {
		
	}

	public ZxspLb(Integer int1,Integer int2, Integer int3, Integer int4, Integer int5, Date dt, String str1,String str2 ,String str3,
			String str4, String str5, String str6, String str7, String str8) {
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
		this.int4 = int4;
		this.int5 = int5;
		this.dt = dt;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.str4 = str4;
		this.str5 = str5;
		this.str6 = str6;
		this.str7 = str7;
		this.str8 = str8;
	}
	
	public Integer getInt1() {
		return int1;
	}

	public void setInt1(Integer int1) {
		this.int1 = int1;
	}
	
	public Integer getInt2() {
		return int2;
	}

	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	public Integer getInt3() {
		return int3;
	}

	public void setInt3(Integer int3) {
		this.int3 = int3;
	}

	public Integer getInt4() {
		return int4;
	}

	public void setInt4(Integer int4) {
		this.int4 = int4;
	}

	public Integer getInt5() {
		return int5;
	}

	public void setInt5(Integer int5) {
		this.int5 = int5;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public String getStr5() {
		return str5;
	}

	public void setStr5(String str5) {
		this.str5 = str5;
	}

	public String getStr6() {
		return str6;
	}

	public void setStr6(String str6) {
		this.str6 = str6;
	}

	public String getStr7() {
		return str7;
	}

	public void setStr7(String str7) {
		this.str7 = str7;
	}

	public String getStr8() {
		return str8;
	}

	public void setStr8(String str8) {
		this.str8 = str8;
	}
	
}
