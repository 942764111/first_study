/*
 *@(#)xx.tables.action
 *@Zsdxx.java.java  
 *@创建时间:2011-8-5上午11:48:20
 *@作者：ylj
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables.action;

import javax.persistence.Entity;

/**
 * @Zsdxx <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Entity
public class Zsdxx {
    private int int1;
    private int int2;
    private int int3;
    private int int4;
    private int int5;
    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;
    private String kcmc;


	public Zsdxx(int int1,int int2,int int3,int int4,int int5,String str1,String str2,String str3,String str4,String str5,String kcmc){
    	this.int1=int1;
    	this.int2=int2;
    	this.int3=int3;
    	this.int4=int4;
    	this.int5=int5;
    	
    	this.str1=str1;
    	this.str2=str2;
        this.str3=str3;
        this.str4=str4;
        this.str5=str5;
        this.kcmc=kcmc;
    	
    }
    
    
	/**
	 * 
	 */
	public Zsdxx() {
		
	}


	/**
	 * @return the kcmc
	 */
	public String getKcmc() {
		return kcmc;
	}


	/**
	 * @param kcmc the kcmc to set
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}


	public int getInt1() {
		return int1;
	}
	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public int getInt2() {
		return int2;
	}
	public void setInt2(int int2) {
		this.int2 = int2;
	}
	public int getInt3() {
		return int3;
	}
	public void setInt3(int i) {
		this.int3 = i;
	}
	public int getInt4() {
		return int4;
	}
	public void setInt4(int int4) {
		this.int4 = int4;
	}
	public int getInt5() {
		return int5;
	}
	public void setInt5(int int5) {
		this.int5 = int5;
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

    
}
