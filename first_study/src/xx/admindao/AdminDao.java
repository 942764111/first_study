/*
 *@(#)xx.admindao
 *@AdminDao.java.java  
 *@创建时间:2012-3-31下午09:15:26
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.admindao;

import java.util.List;
import java.util.Map;

import xx.collection.bean.Cztxxxx;
import xx.collection.bean.Pd;
import xx.collection.bean.Problemlist;
import xx.collection.bean.Xz;
import xx.kgt.bean.CztSearch;
import xx.testxg.action.Cztzj;

/**
 * @AdminDao <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@SuppressWarnings("unchecked")
public interface AdminDao {
	
	
	public List<List> queryadmin(int roleId);
	public List<List> queryUserFunctions(int roleId);

	public void proc_Sjno(int sjno);
	public List<String> chaTh(int sjno,int leixing);
	public List<Integer> chaTh(int sjno);
	public List<Integer> proc_czt_zsd(int sjno,int zsdbh);
	public List<String> proc_cwth(int sjno,String userid,int lx);
    public List<String> proc_cwstu(int sjno,String th,int lx);
	public List<Integer> proc_fsd(int sjno,int f1,int f2,int f3);
	
	public int querymaxId(String col,String table);
	public List<List> sjxzt(int th);

 	public List<List> sjxzt(int xz_cid,int xz_zbh,int xz_nyd);
	public List<Integer> xzth(int th);
	public Xz xznr(int th);
	public List<Integer> xzzz(int th);
	
	
	public List<Integer> dxzth(int th);
	public List<Integer> finddzz(int zbh);
	public List<Map<String, String>> querytuijian(String nodeid);
	
    public List<List> cksjxzt(int th);///根据试卷编号获得试卷 选择题内容
    public List<Integer> jdxzth(int jdzbh1,int jdzbh2);
    public List<Integer> jdxzthd(int jdzbh1,int jdzbh2);
    public List<Integer> findjdxzzz(int jdzbh1,int jdzbh2);
   
    public List<List> sjpdt(int pd_cid,int pd_zbh,int pd_nyd);
    public List<Integer> pdth(int th);
    public Pd pdnr(int th);
    public List<Integer> pdzz(int th);
    
    public List<List> cksjpdt(int th);//根据试卷编号获得试卷 判断题内容
    
    public List<Integer> jdpdth(int jdpdth1,int jdpdth2);
	public List<Integer> findjdpdzz(int jdpdth1,int jdpdth2);
    
    
      public List<List> sjczt();
	  public List<Integer> czth(int th);
	  public Cztzj cztnr(int th);
	  public List<Integer> czzz();
	  public void delczt(int tihao[]);
	  public List<Cztxxxx> ckcztqk(int row1,int row2);
	  public List<List> cksjczt(int th);//根据试卷编号查操作题
	  public List<Integer> searchcztth(int zbh1,int zbh2);//根据起始章编号和截止章编号查询满足条件的操作题大题题号
	 
  
	public List<Integer> findx(int x_cid,int x_zbh);
	public Xz    xnr(int th);
	public List<Integer> findxzz(int xth1,int xth2);
	
	public List<Integer> findp(int pd_cid,int pd_zbh);
	public Pd    pnr(int th);
	public List<Integer> findpzz(int pth1,int pth2);
	public List<String> zytjccgc(); //资源统计更新功能
	public List<Integer> findrs(int rad,String qw);//搜索结果页面
	public List<String> findsuggest(int rad,String qw);//搜索提示功能
	public List<String> finddmt(int rad,String qw,int value1,int value2);
	public List<String> findczt(int rad,String qw,int value1,int value2);
	
	
	public List<Problemlist> ChushihuaZq(int ts,int type);//用于遗传算法初始化种群时产生个体
	public List<CztSearch> searchczt(int zbh1,int zbh2,int ts);//用来根据操作题题数和范围查找相应的操作题
	public void batchInsert(int zbh1,int zbh2);//用来根据章范围选出选择、判断题，并插入到数据表problemlist中去
	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	
	List<String> findCname(String qw);
	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	
	List<Map<String, String>> findsuggest2(String qw);
	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	
	List<Map<String, String>> querytuijian2(String nodeid, String usertype);
}
