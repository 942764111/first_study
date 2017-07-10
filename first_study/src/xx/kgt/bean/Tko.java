/*
 *@(#)xx.kgt.bean
 *@Tko.java.java  
 *@创建时间:2011-8-15上午09:36:36
 *@作者：zxl
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.bean;

import javax.persistence.Entity;

/**
 * @Tko <code>{类名称}</code>
 * @author  {zxl}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Entity
public class Tko {
	private Integer int1;
	private Integer int2;
	private Integer int3;
	private Integer int4;
	private String str1;
	private String str2;
	private String str3;
	private Integer int5;
	private Integer int6;
	private Integer int7;


		
	public Tko(){
			
		}
	
	public Tko(Integer int1,Integer int2,Integer int3,Integer int4,String str1,String str2,
		String str3,Integer int5,Integer int6,Integer int7){
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
		this.int4 = int4;
		this.int5 = int5;
		this.int6 = int6;
		this.int7 = int7;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
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

	public Integer getInt5() {
		return int5;
	}

	public void setInt5(Integer int5) {
		this.int5 = int5;
	}

	public Integer getInt6() {
		return int6;
	}

	public void setInt6(Integer int6) {
		this.int6 = int6;
	}

	public Integer getInt7() {
		return int7;
	}

	public void setInt7(Integer int7) {
		this.int7 = int7;
	}

	
	
}
