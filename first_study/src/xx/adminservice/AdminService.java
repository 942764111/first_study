
package xx.adminservice;

import java.util.List;
import java.util.Map;

import xx.collection.bean.Czt;
import xx.collection.bean.Cztd;
import xx.collection.bean.Cztxxxx;
import xx.collection.bean.Pd;
import xx.collection.bean.Problemlist;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Xz;
import xx.collection.bean.Zsd;
import xx.kgt.bean.CztSearch;
import xx.testxg.action.Cztzj;
/**
 * @author tlq
 *
 */
public interface AdminService {
	public List<List> findallfunction(int roleId);
	public List<List> findUserFunctions(int roleId);
	public int querymaxId(String col,String table);
	public void proc_Sjno(int sjno);
	public List<Xz> chaTm1(int sjno,int leixing,int page,int rows_s);
	public List<Pd> chaTm2(int sjno,int leixing,int page,int rows_s);
	public List<Czt> chaTm3(int sjno,int leixing,int page,int rows_s);
	public List<Zsd> chaTm4(int sjno,int leixing,int page,int rows_s);
	public int totalAdmin(int sjno,int leixing);
	public int totalStu(int sjno,int lx,String th);
	public List<String> proc_cwth(int sjno,String userid,int lx);
	 public List<Studentifno> proc_cwstu(int sjno,String th,int lx,int page,int rows_s);
	public List<Integer> proc_fsd(int sjno,int f1,int f2,int f3);
	public List<Integer> proc_czt_zsd(int sjno,int zsdbh);
	public List<Map<String, String>> querytuijian(String nodeid);
	public List<Map<String, String>> querytuijian2(String nodeid,String type);
	public List<List> findxzt(int xz_cid,int xz_zbh,int xz_nyd);
	public List<Integer> xzth(int th);
	public Xz    xznr(int th);
	public List<Integer> findzz(int th2);
	
	public List<Integer> dxzth(int th);
	public List<Integer> finddzz(int zbh);
	
	public List<List> cksjxzt(int th);
	public List<Integer> jdxzth(int jdzbh1,int jdzbh2);
	public List<Integer> jdxzthd(int jdzbh1,int jdzbh2);
	public List<Integer> findjdxzzz(int jdzbh1,int jdzbh2);
	
	public List<List> findpdt(int pd_cid,int pd_zbh,int pd_nyd);
	public List<Integer> pdth(int th);
	public Pd  pdnr(int th);
	public List<Integer> findpdzz(int pdth2);
	
	public List<List> cksjpdt(int th);
	public List<Integer> jdpdth(int jdpdth1,int jdpdth2);
	public List<Integer> findjdpdzz(int jdpdth1,int jdpdth2);
	public List<List> findczt();
	public List<Integer> czth(int th);
	public Cztzj cztnr(int th);
    public List<Integer> findczzz();
    public List<List> cksjczt(int th);
    public List<Integer> searchcztth(int zbh1,int zbh2);
    public void delczt(int tihao[]);
    public List<Cztxxxx> ckcztqk(int row1,int row2);
    public List<Cztd>  cxtggjz(int row1,int row2,String tggzj,String tggzj1,String tggjz2);  
    public int  cxtggjztotal(String tggzj,String tggzj1,String tggjz2);  
    public List<Cztd>  cxkc(String value,String value1,String value2,int row1,int row2);
    public int  cxkctotal(String value,String value1,String value2);
    public List<Cztd>  cxzsd(String value,int row1,int row2);
    public int  cxzsdtotal(String value);
    public List<Cztd>  cxzsdkey(String value,int row1,int row2);
    public int  cxzsdkeytotal(String value);

    
  
	public List<Integer> findx(int x_cid,int x_zbh); 
	public Xz    xnr(int th);
	public List<Integer> findxzz(int xth1,int xth2);
	public List<Integer> findp(int pd_cid,int pd_zbh);
	public Pd    pnr(int th);
	public List<Integer> findpzz(int pth1,int pth2);
	public List<String> zytjcc(); 
	public List<Integer> zycxto(int rad,String qw);
	public List<String> findsg(int rad, String qw);
	public List<Map<String, String>> findsg2(String qw);
	public List<String> cztcx(int rad,String qw,int value1,int value2);
	public List<String> dmtcx(int rad,String qw,int value1,int value2);
	
	
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
	
	List<String> findsName(String qw);
	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	
	List<Map<String, String>> list(String sql);
}
