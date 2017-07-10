/*
 *@(#)xx.kgt.bean
 *@PanDuan.java.java  
 *@创建时间:2011-8-8下午02:05:03
 *@作者：张晓莉
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.bean;

import javax.persistence.Entity;

/**
 * @PanDuan <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Entity
public class PanDuan {
	private Integer int1;
	private Integer int2;
	private String kcmc;
	private Integer int4;
	private String str1;
	private String str2;
	private String str3;
	private Integer int5;
	private String zmc;
	private Integer int7;
	private String zsdmc;

		
	public PanDuan(){
			
		}
	
	public PanDuan(Integer int1,Integer int2,String kcmc,Integer int4,String str1,String str2,
		String str3,Integer int5,String zmc,Integer int7,String zsdmc){
		this.int1 = int1;
		this.int2 = int2;
		this.kcmc = kcmc;
		this.int4 = int4;
		this.int5 = int5;
		this.zmc = zmc;
		this.int7 = int7;
		this.zsdmc = zsdmc;
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
	public Integer getInt7() {
		return int7;
	}

	public void setInt7(Integer int7) {
		this.int7 = int7;
	}

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	public String getZmc() {
		return zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	public String getZsdmc() {
		return zsdmc;
	}

	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}

	
}
