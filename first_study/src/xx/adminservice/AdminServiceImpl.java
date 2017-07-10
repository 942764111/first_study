
package xx.adminservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xx.admindao.AdminDaoImpl;
import xx.collection.bean.Czt;
import xx.collection.bean.Cztd;
import xx.collection.bean.Cztxxxx;
import xx.collection.bean.Problemlist;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Xz;
import xx.collection.bean.Pd;
import xx.collection.bean.Zsd;
import xx.dao.PublicDao;
import xx.kgt.bean.CztSearch;
import xx.testxg.action.Cztzj;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	/* (non-Javadoc)
	 * @see xx.admindao.AdminDao#queryadmin()
	 */
	@Resource(name="admindao")
	private AdminDaoImpl adminDaoImpl;
	@Resource(name="dao")                  
	private PublicDao publicDao;
	
	
	public PublicDao getPublicDao() {
		return publicDao;
	}

	
	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}

	public AdminDaoImpl getAdminDaoImpl() {
		return adminDaoImpl;
	}

	public void setAdminDaoImpl(AdminDaoImpl adminDaoImpl) {
		this.adminDaoImpl = adminDaoImpl;
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#findallfunction()
	 */
	public List<List> findallfunction(int roleId) {
		// TODO Auto-generated method stub
		return this.adminDaoImpl.queryadmin(roleId);			
	}
	public List<List> findUserFunctions(int roleId){
		return this.adminDaoImpl.queryUserFunctions(roleId);
	}

	public void proc_Sjno(int sjno){
		this.adminDaoImpl.proc_Sjno(sjno);
	}

	public int totalAdmin(int sjno,int leixing){
		int total=0;
		if(leixing<4){
			List<String> th=new ArrayList<String>();
			th=this.adminDaoImpl.chaTh(sjno, leixing);
			 total=th.size();
		}else if(leixing==4){
			List<Integer> th=new ArrayList<Integer>();
			th=this.adminDaoImpl.chaTh(sjno);
			 total=th.size();
		}
		return total;
	}
	public  List<Xz> chaTm1(int sjno,int leixing,int page,int rows_s){
		  
		List<Xz> rows=new ArrayList<Xz>();
		List<String> th=new ArrayList<String>();
		th=this.adminDaoImpl.chaTh(sjno, leixing);
		
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int length=th.size()- 1;
		int mq=th.size() - fromIndex;
		List<String> th2=new ArrayList<String>();
		if(toIndex<=length){
			 th2=th.subList(fromIndex, rows_s);
			 
		}else{
			if(length>fromIndex){
				
				th2=th.subList(fromIndex, length+1);
				
			}else if(length==fromIndex){
				th2.add(th.get(fromIndex));
			}
			 
		}

	 String hql="from Xz where th in(";
		for(int i=0;i<th2.size();i++){
			if(i<th2.size()-1){
				hql=hql+"'"+th2.get(i)+"',";
			}else{
				hql=hql+"'"+th2.get(i)+"') order by th asc";
			}

		}
		rows=this.publicDao.findHql(Xz.class, hql);
		return rows;
	}
	
	
	public  List<Pd> chaTm2(int sjno,int leixing,int page,int rows_s){
		  
		List<Pd> rows=new ArrayList<Pd>();
		List<String> th=new ArrayList<String>();
		th=this.adminDaoImpl.chaTh(sjno, leixing);
		
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int length=th.size()- 1;
		int mq=th.size() - fromIndex;
		List<String> th2=new ArrayList<String>();
		if(toIndex<=length){
			 th2=th.subList(fromIndex, rows_s);
			 
		}else{
			if(length>fromIndex){
				
				th2=th.subList(fromIndex, length+1);
				
			}else if(length==fromIndex){
				th2.add(th.get(fromIndex));
			}
			 
		}

		String hql="from Pd where th in(";
		for(int i=0;i<th2.size();i++){
			if(i<th2.size()-1){
				hql=hql+"'"+th2.get(i)+"',";
			}else{
				hql=hql+"'"+th2.get(i)+"') order by th asc";
			}

		}
		rows=this.publicDao.findHql(Pd.class, hql);
		return rows;
	}
	
	
	public  List<Czt> chaTm3(int sjno,int leixing,int page,int rows_s){
		  
		List<Czt> rows=new ArrayList<Czt>();
		List<String> th=new ArrayList<String>();
		th=this.adminDaoImpl.chaTh(sjno, leixing);
		
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int length=th.size()- 1;
		int mq=th.size() - fromIndex;
		List<String> th2=new ArrayList<String>();
		if(toIndex<=length){
			 th2=th.subList(fromIndex, rows_s);
			 
		}else{
			if(length>fromIndex){
				
				th2=th.subList(fromIndex, length+1);
				
			}else if(length==fromIndex){
				th2.add(th.get(fromIndex));
			}
			 
		}
		for(int i=0;i<th2.size();i++){
			String y=th2.get(i);
			int index=y.indexOf("|");
			String str1=y.substring(0, index);//�������
			String str2=y.substring(index+1, y.length());//С�����
			
			String hql="from Czt where id.sxh='"+str2+"' and id.dtTh='"+str1+"'";
			List<Czt> czt=this.publicDao.findHql(Czt.class, hql);
			rows.add(czt.get(0));
		}
		return rows;
	}
	
	
	public  List<Zsd> chaTm4(int sjno,int leixing,int page,int rows_s){
		  
		List<Zsd> rows=new ArrayList<Zsd>();
		List<Integer> th=new ArrayList<Integer>();
		th=this.adminDaoImpl.chaTh(sjno);
		
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int length=th.size()- 1;
		int mq=th.size() - fromIndex;
		List<Integer> th2=new ArrayList<Integer>();
		if(toIndex<=length){
			 th2=th.subList(fromIndex, rows_s);
			 
		}else{
			if(length>fromIndex){
				
				th2=th.subList(fromIndex, length+1);
				
			}else if(length==fromIndex){
				th2.add(th.get(fromIndex));
			}
			 
		}
		
		String hql="from Zsd where id.zsdbh in(";
		for(int i=0;i<th2.size();i++){
			if(i<th2.size()-1){
				hql=hql+"'"+th2.get(i)+"',";
			}else{
				hql=hql+"'"+th2.get(i)+"') order by id.zsdbh asc";
			}

		}
		rows=this.publicDao.findHql(Zsd.class, hql);
		
		return rows;
	}
	public List<Integer> proc_czt_zsd(int sjno,int zsdbh){
		return this.adminDaoImpl.proc_czt_zsd(sjno, zsdbh);
	}
	public List<String> proc_cwth(int sjno,String userid,int lx){
		return this.adminDaoImpl.proc_cwth(sjno, userid, lx);
	}
	public List<Integer> proc_fsd(int sjno,int f1,int f2,int f3){
		return this.adminDaoImpl.proc_fsd(sjno, f1, f2, f3);
	}
	
	public int totalStu(int sjno,int lx,String th){
		return this.adminDaoImpl.proc_cwstu(sjno,th,lx).size();
	}
	 public List<Studentifno> proc_cwstu(int sjno,String th,int lx,int page,int rows_s){
		 List<Studentifno> rows=new ArrayList<Studentifno>();
			List<String> userid=new ArrayList<String>();
			userid=this.adminDaoImpl.proc_cwstu(sjno,th, lx);
			if(userid.size()!=0){
				
				int fromIndex=rows_s*(page - 1);
				int toIndex=page*rows_s - 1;
				int length=userid.size()- 1;
				int mq=userid.size() - fromIndex;
				List<String> th2=new ArrayList<String>();
				if(toIndex<=length){
					 th2=userid.subList(fromIndex, rows_s);
					 
				}else{
					if(length>fromIndex){
						
						th2=userid.subList(fromIndex, length+1);
						
					}else if(length==fromIndex){
						th2.add(userid.get(fromIndex));
					}
					 
				}
				for(int i=0;i<th2.size();i++){
					String id =th2.get(i);
					String[] keys=new String[1];
					keys[0]="UserId";
					String[] values=new String[1];
					values[0]=id;
					List<Studentifno> stu=this.publicDao.find(Studentifno.class, "Studentifno", keys, values);
					rows.add(stu.get(0));
				}
				
			}else{rows=null;}
			        
			return rows;
		 
	 }


	public List<Integer> findzz(int th2) {
		
		return this.adminDaoImpl.xzzz(th2);
	}

	public Xz xznr(int th) {
		
		return this.adminDaoImpl.xznr(th);
	}

	public List<Integer> xzth(int th) {
		
		return this.adminDaoImpl.xzth(th);
	}

	public List<List> findxzt(int xz_cid, int xz_zbh, int xz_nyd) {
		
		return this.adminDaoImpl.sjxzt(xz_cid, xz_zbh, xz_nyd);
	}
	
	
	public List<Integer> findpdzz( int pdth2) {
		
		return this.adminDaoImpl.pdzz( pdth2);
	}

	public Pd pdnr(int th) {
		
		return this.adminDaoImpl.pdnr(th);
	}

	public List<Integer> pdth(int th) {
		
		return this.adminDaoImpl.pdth(th);
	}

	public List<List> findpdt(int pd_cid, int pd_zbh, int pd_nyd) {
		
		return this.adminDaoImpl.sjpdt(pd_cid, pd_zbh, pd_nyd);
	}
	
	

	public List<Integer> czth(int th) {
		
		return this.adminDaoImpl.czth(th);
	}
//
//
//
	public Cztzj cztnr(int th) {
		
		return this.adminDaoImpl.cztnr(th);
	}
//
//
//	
	public List<List> findczt() {
		
		return this.adminDaoImpl.sjczt();
	}
//
//
	public List<Integer> findczzz() {
		
		return this.adminDaoImpl.czzz();
	}
	
	public void delczt(int tihao[])
	{
		this.adminDaoImpl.delczt(tihao);
	}
	
	public List<Cztxxxx> ckcztqk(int row1,int row2)
	{
		return this.adminDaoImpl.ckcztqk(row1, row2);
	}
	public List<Cztd>  cxtggjz(int row1,int row2,String tggzj,String tggzj1,String tggjz2)
	{
		return this.adminDaoImpl.cxtggjz(row1, row2, tggzj,tggzj1,tggjz2);
	}
	public int  cxtggjztotal(String tggzj,String tggzj1,String tggjz2)
	{
		return this.adminDaoImpl.cxtggjztotal(tggzj, tggzj1, tggjz2);
	}
	 public List<Cztd>  cxkc(String value,String value1,String value2,int row1,int row2)
	 {
	   return this.adminDaoImpl.cxkc(value, value1, value2, row1, row2);
	 }
	 public int  cxkctotal(String value,String value1,String value2)
	 {
	   return this.adminDaoImpl.cxkctotal(value, value1, value2);
	 }
	 public List<Cztd>  cxzsd(String value,int row1,int row2)
	 {
		 return this.adminDaoImpl.cxzsd(value, row1, row2);
	 }
	 public int  cxzsdtotal(String value)
	 {
		 return this.adminDaoImpl.cxzsdtotal(value);
	 }
	 public List<Cztd>  cxzsdkey(String value,int row1,int row2)
	 {
		 return this.adminDaoImpl.cxzsdkey(value, row1, row2);
	 }
	 public int  cxzsdkeytotal(String value)
	 {
		 return this.adminDaoImpl.cxzsdkeytotal(value);
	 }

	

	
	public List<Integer> findx(int xth1, int xth2) {
		return this.adminDaoImpl.findx(xth1, xth2);
	}

	
	public Xz xnr(int th) {
		return this.adminDaoImpl.xnr(th);
	}
	
	
	public List<Integer> findxzz(int xth1, int xth2) {
		return this.adminDaoImpl.findxzz(xth1, xth2);
	}
	
	public List<Integer> findp(int pd_cid, int pd_zbh) {
		return this.adminDaoImpl.findp(pd_cid, pd_zbh);
	}


	
	public List<Integer> findpzz(int pth1, int pth2) {
		return this.adminDaoImpl.findpzz(pth1, pth2);
	}


	public Pd pnr(int th) {
		return this.adminDaoImpl.pnr(th);
	}



	public int querymaxId(String col, String table) {
		return this.adminDaoImpl.querymaxId(col, table);
	}



	
	public List<List> cksjxzt(int th) {
		return this.adminDaoImpl.cksjxzt(th);
	}





	
	public List<List> cksjpdt(int th) {
		
		return this.adminDaoImpl.cksjpdt(th);
	}






	
	public List<List> cksjczt(int th) {
		
		return this.adminDaoImpl.cksjczt(th);
	}



	
	public List<Integer> jdxzth(int jdzbh1, int jdzbh2) {
		
		return this.adminDaoImpl.jdxzth(jdzbh1, jdzbh2);
	}


	
	public List<Integer> findjdxzzz(int jdzbh1, int jdzbh2) {
		
		return this.adminDaoImpl.findjdxzzz(jdzbh1, jdzbh2);
	}


	
	public List<Integer> jdpdth(int jdpdth1, int jdpdth2) {
		
		return this.adminDaoImpl.jdpdth(jdpdth1, jdpdth2);
	}



	public List<Integer> findjdpdzz(int jdpdth1, int jdpdth2) {
		
		return this.adminDaoImpl.findjdpdzz(jdpdth1, jdpdth2);
	}



	public List<Integer> dxzth(int th) {
		
		return this.adminDaoImpl.dxzth(th);
	}



	public List<Integer> finddzz(int zbh) {
		
		return this.adminDaoImpl.finddzz(zbh);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#jdxzthd(int, int)
	 */
	@Override
	public List<Integer> jdxzthd(int jdzbh1, int jdzbh2) {
		return this.adminDaoImpl.jdxzthd(jdzbh1, jdzbh2);
	}


	public List<String> zytjcc(){
		return this.adminDaoImpl.zytjccgc();
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#zycxto()
	 */
	@Override
	public List<Integer> zycxto(int rad,String qw) {
		
		return this.adminDaoImpl.findrs(rad, qw);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#findsg(int, java.lang.String)
	 */
	@Override
	public List<String> findsg(int rad, String qw) {
		return this.adminDaoImpl.findsuggest(rad, qw);
	}
	@Override
	public List<Map<String, String>> findsg2(String qw) {
		return this.adminDaoImpl.findsuggest2(qw);
	}
	@Override
	public List<String> findsName(String qw) {
		return this.adminDaoImpl.findCname(qw);
	}
	@Override
	public List<Map<String, String>> list(String sql) {
		return this.publicDao.list(sql);
	}

	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#cztcx(int, java.lang.String, int, int)
	 */
	@Override
	public List<String> cztcx(int rad, String qw, int value1, int value2) {
		return this.adminDaoImpl.findczt(rad, qw, value1, value2);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#dmtcx(int, java.lang.String, int, int)
	 */
	@Override
	public List<String> dmtcx(int rad, String qw, int value1, int value2) {
		return this.adminDaoImpl.finddmt(rad, qw, value1, value2);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#searchcztth(int, int)
	 */
	@Override
	public List<Integer> searchcztth(int zbh1, int zbh2) {
		return this.adminDaoImpl.searchcztth(zbh1, zbh2);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#ChushihuaZq(int, int)
	 */
	@Override
	public List<Problemlist> ChushihuaZq(int ts, int type) {
		return this.adminDaoImpl.ChushihuaZq(ts, type);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#searchczt(int, int, int)
	 */
	@Override
	public List<CztSearch> searchczt(int zbh1, int zbh2, int ts) {
		return this.adminDaoImpl.searchczt(zbh1, zbh2, ts);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#batchInsert(int, int)
	 */
	@Override
	public void batchInsert(int zbh1, int zbh2) {
		this.adminDaoImpl.batchInsert(zbh1, zbh2);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#querytuijian(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> querytuijian(String nodeid) {
		// TODO Auto-generated method stub
		return this.adminDaoImpl.querytuijian(nodeid);
	}


	/* (non-Javadoc)
	 * @see xx.adminservice.AdminService#querytuijian2(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, String>> querytuijian2(String nodeid, String type) {
		// TODO Auto-generated method stub
		return this.adminDaoImpl.querytuijian2(nodeid,type);
	}	
	


	
}
