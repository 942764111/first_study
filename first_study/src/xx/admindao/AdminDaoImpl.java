/*
 *@(#)xx.admindao
 *@AdminDaoImpl.java.java  
 *@创建时间:2012-3-31下午09:22:09
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.admindao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import xx.collection.bean.Cztd;
import xx.collection.bean.Cztxxxx;
import xx.collection.bean.Pd;
import xx.collection.bean.Problemlist;
import xx.collection.bean.Xz;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdId;
import xx.kgt.bean.CztSearch;
import xx.testxg.action.Cztzj;

/**
 * @AdminDaoImpl <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Repository("admindao")
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	@Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	protected static Log log = LogFactory.getLog(AdminDaoImpl.class); 

	private static Connection connection = null;
	/**
	 * 
	 * @{方法名：getConnection()}
	 * @param 
	 * @return {connection} {数据库的链接}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	 public static Connection getConnection(){
	  try { 
		  
		  ResourceBundle bundle = ResourceBundle.getBundle("data");  
		  String server = bundle.getString("DB.server"); 
		  String username = bundle.getString("DB.username");
		  String password = bundle.getString("DB.password");
		
	   DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	   connection = DriverManager.getConnection(server, username, password);
	  
	  } catch (SQLException e) {
		  e.printStackTrace();
		  log.error("AdminDaoImpl.java链接数据库失败！");
	  }
	  return connection;
	 }
	 
	 /**
	  * 
	  * @{方法名:close(ResultSet rs, CallableStatement proc, Connection conn)}
	  * @param {rs,proc,conn} 
	  * @{方法的功能/动作描述}
	  * @exception {说明在某情况下,将发生什么异常}
	  */
	public static void close(ResultSet rs, CallableStatement proc, Connection connection) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (proc != null) {
				proc.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("AdminDaoImpl.java关闭链接异常！");
		}
	}


	
	public List<List> queryadmin(int roleId) {
		List<List> adminlist = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		  try{
		
		   proc= connection.prepareCall("{ call query_admin4(?)}");
		   
		   proc.setInt(1,roleId);
		 
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	List<String> list = new ArrayList();
		        	
		        	list.add(rs.getString(1));
		        	list.add(rs.getString(2));
		        	list.add(rs.getString(3));
		        	list.add(rs.getString(4));
		        	
		        	adminlist.add(list);
		         }
                 
		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return adminlist;

	}
	
	public List<Map<String, String>> querytuijian(String nodeid) {
		List<Map<String, String>> adminlist = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		  try{
		
		   proc= connection.prepareCall("{ call tuijian(?)}");
		   
		   proc.setString(1,nodeid);
		 
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	Map<String, String>map=new HashMap<String, String>();
		        	map.put("zlid", rs.getString(1));
		        	map.put("zlmc", rs.getString(2));
		        	map.put("zlms", rs.getString(4));
		        	map.put("filepath", rs.getString(3));
		        	String type=rs.getString(5);
					String tubiao="";
					if (type==null||type=="") {
						tubiao="/assets/avatars/word.jpg";
					}
					else if (type.equals("1")) {
						tubiao="/assets/avatars/word.jpg";
					}else if (type.equals("2")) {
						tubiao="/assets/avatars/shipin.jpg";
					}else if (type.equals("3")) {
						tubiao="/assets/avatars/tupian.jpg";
					}else if (type.equals("4")) {
						tubiao="/assets/avatars/tuzhi.jpg";
					}else if (type.equals("5")) {
						tubiao="/assets/avatars/yinpin.jpg";
					}
		        	map.put("tubiao", tubiao);
		        	map.put("userid", rs.getString(6));
		        	map.put("type", type);
		        	adminlist.add(map);
		         }
                 
		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return adminlist;

	}
	
	public int querymaxId(String col,String table){
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		int max=0;
		  try{
		   proc = connection.prepareCall("{ call querymaxID(?,?)}");
		   
		   proc.setString(1,col);
		   proc.setString(2, table);
		  
		   proc.execute();
		  boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	 max=rs.getInt(1);
		         }

		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();
		  }finally{
			  AdminDaoImpl.close(rs,proc,connection);
		  }
		return max;
	}

	public List<List> queryUserFunctions(int roleId){
		List<List> userList = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		  try{
		   proc = connection.prepareCall("{ call queryByRoleid(?)}");
		   
		   proc.setInt(1,roleId);
		   proc.execute();
		  
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	List<String> list = new ArrayList();
		        	
		        	list.add(rs.getString(1));
		        	list.add(rs.getString(2));
		        	list.add(rs.getString(3));
		        	list.add(rs.getString(4));
		        	
		        	userList.add(list);
		         }

		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return userList;
	}

	public void proc_Sjno(int sjno){
		Connection connection = AdminDaoImpl.getConnection();
		CallableStatement proc=null;
		  try{
		   
		   proc = connection.prepareCall("{ call proc_Sjno22(?)}");
		   
		   proc.setInt(1,sjno);
		 
		   proc.execute();
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(null, proc, connection);
		  }
	
	}
	public List<String> chaTh(int sjno,int leixing){
		List<String> th = new ArrayList<String>();;
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call JGJ1(?,?)}");
		  
		   proc.setInt(1,sjno);
		   proc.setInt(2,leixing);
		   proc.execute();
		  
		    boolean hasResult = true;
		    while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	 th.add(rs.getString(1));

		         }

		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return th;
	}
	
	public List<Integer> chaTh(int sjno){
		List<Integer> th = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call JGJ2(?)}");
		   proc.setInt(1,sjno);
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	 th.add(rs.getInt(1));

		         }

		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return th;
	}
	public List<Integer> proc_czt_zsd(int sjno,int zsdbh){
		List<Integer> zsdth = new ArrayList<Integer>();;
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_czt_zsd(?,?)}");
		   
		   proc.setInt(1,sjno);
		   proc.setInt(2, zsdbh);
		  
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	 zsdth.add(rs.getInt(1));
                     zsdth.add(rs.getInt(2));
		         }

		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return zsdth;
	}

	//************************???????************************
	
	public List<List> sjxzt(int xz_cid, int xz_zbh, int xz_nyd) {
		List<List> xztnr = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection
					.prepareCall("{ call SJ_XZT1(?)}");
		
			proc.setInt(1, xz_zbh);

			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					List<String> list = new ArrayList();
					list.add(rs.getString(1));
					xztnr.add(list);
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return xztnr;
	}
	
	
	public List<Integer> xzzz(int th2) {
		List<Integer> xzth = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call get_maxth(?)}");
			
			proc.setInt(1, th2);
			
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					xzth.add(rs.getInt(1));
					xzth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return xzth;
	}
	
	//************************?ж?????************************

	
	public List<List> sjpdt(int pd_cid, int pd_zbh, int pd_nyd) {
		List<List> pdtnr = new ArrayList<List>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call SJ_PDT1(?)}");
			proc.setInt(1, pd_zbh);
            proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					List<String> list = new ArrayList<String>();
					list.add(rs.getString(1));
					pdtnr.add(list);
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return pdtnr;
	}
	
	
	
	
	
	
	public List<Integer> pdzz(int pdth2) {
		List<Integer> pdth = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call get_pdmaxth(?)}");
			proc.setInt(1, pdth2);
			
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					pdth.add(rs.getInt(1));
					pdth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
			
		}
		return pdth;
	}
	
	
	//************************????????************************
	public List<Integer> czth(int th) {
		List<Integer> cth = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call SJ_CZT1(?)}");
			
			proc.setInt(1, th);
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					cth.add(rs.getInt(1));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return cth;
	}

	public List<Integer> czzz() {
		List<Integer> czth = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call get_czmaxth()}");
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					czth.add(rs.getInt(1));
					czth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return czth;
	}


	public List<List> sjczt() {
		List<List> cztnr = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call SJ_CZT1()}");
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					List<String> list = new ArrayList();
					list.add(rs.getString(1));
					cztnr.add(list);
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
					
		}
		return cztnr;
	}

		
	public List<String> proc_cwth(int sjno,String userid,int lx){
		List<String> list = new ArrayList<String>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		  proc = connection.prepareCall("{ call proc_cwth(?,?,?)}");
		  
		   proc.setInt(1,sjno);
		   proc.setString(2,userid);
		   proc.setInt(3,lx);
		  
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	list.add(rs.getString(1));
		        }

		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return list;
	}
	
	public List<Integer> proc_fsd(int sjno,int f1,int f2,int f3){
		List<Integer> list = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_fsd(?,?,?,?)}");
		   
		   proc.setInt(1,sjno);
		   proc.setInt(2,f1);
		   proc.setInt(3,f2);
		   proc.setInt(4,f3);
		  
		   proc.execute();
		  boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         
		         while(rs.next()){
		        	list.add(rs.getInt(1));
		        	list.add(rs.getInt(2));
		        	list.add(rs.getInt(3));
		        	list.add(rs.getInt(4));
		        	list.add(rs.getInt(5));
		        	list.add(rs.getInt(6));
		        }


		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  AdminDaoImpl.close(rs, proc, connection);
		  }
		  
		return list;
		
	}
	
	 public List<String> proc_cwstu(int sjno,String th,int lx){
		 List<String> list = new ArrayList<String>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			  try{
			   proc = connection.prepareCall("{ call proc_cwstu(?,?,?)}");
			   
			   proc.setInt(1,sjno);
			   proc.setInt(3,lx);
			   proc.setString(2, th);
			  
			   proc.execute();
			   boolean hasResult = true;
			   
			   while (hasResult) {
			         rs = proc.getResultSet();
			         
			         while(rs.next()){
			        	list.add(rs.getString(1));
			        	
			        }


			         hasResult = proc.getMoreResults();
			   }
			   
			  }catch (SQLException e){
			   e.printStackTrace();

			  }finally{
				  AdminDaoImpl.close(rs, proc, connection);
			  }
			  
			return list;
	 }


	

	public List<Integer> tkzz(int th) {
		
		return null;
	}

	

	public List<List> sjxzt(int th) {
		
		return null;
	}

	
	//**********************zxl********************

	/* (non-Javadoc)
	 * @see xx.admindao.AdminDao#findx(int, int)
	 */
	public List<Integer> findx(int x_cid, int x_zbh) {
		List<Integer> xth = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call danx1(?,?)}");
			
			proc.setInt(1, x_cid);
			proc.setInt(2, x_zbh);
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					xth.add(rs.getInt(1));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return xth;
	}


	/* (non-Javadoc)
	 * @see xx.admindao.AdminDao#xnr(int)
	 */
	public Xz xnr(int th) {
		Xz x = new Xz();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call danx2(?)}");
			
			proc.setInt(1, th);
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					Zsd zsd = new Zsd();
					ZsdId zid = new ZsdId();
					x.setTg(rs.getString(1));
					x.setXx1(rs.getString(2));
					x.setXx2(rs.getString(3));
					x.setXx3(rs.getString(4));
					x.setXx4(rs.getString(5));
					x.setDa(rs.getString(6));
					zid.setZsdbh(rs.getInt(7));
					zsd.setId(zid);
					x.setZsd(zsd);
					x.setDdx(rs.getInt(8));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			AdminDaoImpl.close(rs, proc, connection);
		}
		return x;
	}
	
	
	/* (non-Javadoc)
	 * @see xx.admindao.AdminDao#findxzz(int, int)
	 */
	public List<Integer> findxzz(int xth1, int xth2) {
		List<Integer> xth = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call xzm(?,?)}");
			proc.setInt(1, xth1);
			proc.setInt(2, xth2);
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					xth.add(rs.getInt(1));
					xth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return xth;
	}
	

//********************zxl***************************
	/* (non-Javadoc)
	 * @see xx.admindao.AdminDao#findp(int, int)
	 */
	public List<Integer> findp(int pd_cid, int pd_zbh) {
		List<Integer> pnr = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call pd1(?,?)}");
			
			proc.setInt(1, pd_cid);
			proc.setInt(2, pd_zbh);
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					pnr.add(rs.getInt(1));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return pnr;
	}
	

	public Pd pnr(int th) {
		Pd x = new Pd();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call pd2(?)}");
			
			proc.setInt(1, th);
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					
					Zsd zsd = new Zsd();
					ZsdId zid = new ZsdId();
					x.setTg(rs.getString(1));
					x.setDa(rs.getInt(2));
					zid.setZsdbh(rs.getInt(3));
					zsd.setId(zid);
					x.setZsd(zsd);
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return x;
	}
	

	public List<Integer> findpzz(int pth1, int pth2) {
		List<Integer> pth = new ArrayList<Integer>();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call pdm(?,?)}");
			
			proc.setInt(1, pth1);
			proc.setInt(2, pth2);
			
			proc.execute();
			
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					pth.add(rs.getInt(1));
					pth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return pth;
	}
	
	public List<Integer> findjdxzzz(int jdzbh1, int jdzbh2) {
		List<Integer> jdxzth = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call get_jdmaxth(?,?)}");
			
			proc.setInt(1, jdzbh1);
			proc.setInt(2, jdzbh2);
			
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					jdxzth.add(rs.getInt(1));
					jdxzth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return jdxzth;
	}


	
	public List<Integer> jdpdth(int jdpdth1, int jdpdth2) {
		List<Integer> pdth = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call SJ_JDPDT1(?,?)}");
			proc.setInt(1, jdpdth1);
			proc.setInt(2, jdpdth2);
			
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					List<Integer> list = new ArrayList();
					list.add(rs.getInt(1));
					
					pdth.add(1);
					
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return pdth;
	}



	public List<Integer> findjdpdzz(int jdpdth1, int jdpdth2) {
		List<Integer> jdpdth = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call get_jdpdmaxth(?,?)}");
			
			proc.setInt(1, jdpdth1);
			proc.setInt(2, jdpdth2);
			
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					jdpdth.add(rs.getInt(1));
					jdpdth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return jdpdth;
	}





	public List<Integer> finddzz(int zbh) {
		List<Integer> dxzth = new ArrayList();
		Connection connection = AdminDaoImpl.getConnection();
		ResultSet rs = null;
		CallableStatement proc=null;
		
		try {
			proc = connection.prepareCall("{ call get_dmaxth(?)}");
			proc.setInt(1, zbh);
			
			proc.execute();
			boolean hasResult = true;
			while (hasResult) {
				rs = proc.getResultSet();
				while (rs.next()) {
					dxzth.add(rs.getInt(1));
					dxzth.add(rs.getInt(2));
				}
				hasResult = proc.getMoreResults();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			AdminDaoImpl.close(rs, proc, connection);
		}
		return dxzth;
	}
	
	 public  void delczt(int tihao[])
		{
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
				try {
					
					connection.setAutoCommit(false); 
					
					proc = connection.prepareCall("{ call delczt(?)}");
					for(int i=0;i<tihao.length;i++)
					{	
					proc.setInt(1, tihao[i]);
					
					proc.addBatch();
					}
					proc.executeBatch();
					connection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}


		}

		 public List<Cztxxxx> ckcztqk(int row1,int row2)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
			 List<Cztxxxx> list=new ArrayList<Cztxxxx>();
			 ResultSet rs = null;
				CallableStatement proc=null;
			 
			try {
				
				proc = connection.prepareCall("{ call cztqk(?,?)}");
				proc.setInt(1,row1);
				proc.setInt(2,row2);
				proc.execute();
			    rs = proc.getResultSet();
				while (rs.next()) {
					Cztxxxx c=new Cztxxxx();
					c.setTh(rs.getInt(1));
					c.setTgxx(rs.getString(2));
					c.setXtcount(rs.getInt(3));
					c.setNoda("");
					list.add(c);
				}
	            proc.getMoreResults();
	            rs = proc.getResultSet();
	            int id=0;
	            while(rs.next())
	            {
	                Cztxxxx c=new Cztxxxx();
	                c=list.get(id);
	                if(rs.getInt(1)==1)
	                {
	                c.setFile("包含文件");
	                }
	                else
	                {
	                	c.setFile("不包含文件");
	                }
	                list.set(id,c); 	
	                id++;
	            }
	            proc.getMoreResults();
	            rs = proc.getResultSet();
	            while(rs.next())
	            {
	            	for(int i=0;i<list.size();i++)
	            	{
	            		if(rs.getInt(1)==list.get(i).getTh())
	            		{
	            			if(rs.getInt(3)==0)
	            			{
	            				  Cztxxxx c=new Cztxxxx();
	            				  c=list.get(i);
	            				  c.setNoda(c.getNoda()+" "+rs.getInt(2));
	            				  list.set(i,c);
	            			}
	            		}
	            	}
	            }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return list;
		}
		 @SuppressWarnings("finally")
		public List<Cztd>  cxtggjz(int row1,int row2,String tggzj,String tggzj1,String tggjz2)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
			 List<Cztd>  list=new ArrayList<Cztd>();
			 ResultSet rs = null;
				CallableStatement proc=null;
			 
				try {
					
					proc = connection.prepareCall("{ call cxtggjz(?,?,?,?,?)}");
					proc.setString(1, tggzj);
					proc.setString(2, tggzj1);
					proc.setString(3,tggjz2);
					proc.setInt(4, row1);
					proc.setInt(5, row2);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						Cztd cztd=new Cztd();
						cztd.setTh(rs.getInt(1));
						cztd.setTg(rs.getString(2));
						cztd.setDtfz(rs.getInt(3));
						list.add(cztd);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return list;

		 }
		 public int  cxtggjztotal(String tggzj,String tggzj1,String tggjz2)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
				int total=0;
				ResultSet rs = null;
				CallableStatement proc=null;
				
				try {
					
					proc = connection.prepareCall("{ call cxtggjz1(?,?,?)}");
					proc.setString(1, tggzj);
					proc.setString(2, tggzj1);
					proc.setString(3, tggjz2);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						total=rs.getRow();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return total;
		 }
		
		 public List<Cztd>  cxkc(String value,String value1,String value2,int row1,int row2)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
			 List<Cztd>  list=new ArrayList<Cztd>();
			 ResultSet rs = null;
				CallableStatement proc=null;
			 
				try {
					
					proc = connection.prepareCall("{ call cxkc(?,?,?,?,?)}");
					proc.setString(1, value);
					proc.setString(2, value1);
					proc.setString(3, value2);
					proc.setInt(4, row1);
					proc.setInt(5, row2);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						Cztd cztd=new Cztd();
						cztd.setTh(rs.getInt(1));
						cztd.setTg(rs.getString(2));
						cztd.setDtfz(rs.getInt(3));
						list.add(cztd);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return list;

		 }
		 public int  cxkctotal(String value,String value1,String value2)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
				int total=0;
				ResultSet rs = null;
				CallableStatement proc=null;
				
				try {
					
					proc = connection.prepareCall("{ call cxkc1(?,?,?)}");
					proc.setString(1, value);
					proc.setString(2, value1);
					proc.setString(3, value2);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						total=rs.getRow();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return total;
		 }
		 
		 public List<Cztd>  cxzsd(String value,int row1,int row2)
		 { 
	     Connection connection = AdminDaoImpl.getConnection();
		 List<Cztd>  list=new ArrayList<Cztd>();
		 ResultSet rs = null;
			CallableStatement proc=null;
		 
			try {
				
				proc = connection.prepareCall("{ call cxzsd(?,?,?)}");
				proc.setString(1, value);
				proc.setInt(2, row1);
				proc.setInt(3, row2);
				proc.execute();
				rs = proc.getResultSet();
				while (rs.next()) {
					Cztd cztd=new Cztd();
					cztd.setTh(rs.getInt(1));
					cztd.setTg(rs.getString(2));
					cztd.setDtfz(rs.getInt(3));
					list.add(cztd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return list;
			 
		 }
		 
		 public int  cxzsdtotal(String value)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
				int total=0;
				ResultSet rs = null;
				CallableStatement proc=null;
				
				try {
					
					proc = connection.prepareCall("{ call cxzsd1(?)}");
					proc.setString(1, value);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						total=rs.getRow();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return total;
		 }
		 
		 public List<Cztd>  cxzsdkey(String value,int row1,int row2)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
			 List<Cztd>  list=new ArrayList<Cztd>();
			 ResultSet rs = null;
				CallableStatement proc=null;
			 
				try {
					
					proc = connection.prepareCall("{ call cxzsdkey(?,?,?)}");
					proc.setString(1, value);
					proc.setInt(2, row1);
					proc.setInt(3, row2);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						Cztd cztd=new Cztd();
						cztd.setTh(rs.getInt(1));
						cztd.setTg(rs.getString(2));
						cztd.setDtfz(rs.getInt(3));
						list.add(cztd);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return list;
		 }

		 public int  cxzsdkeytotal(String value)
		 {
			 Connection connection = AdminDaoImpl.getConnection();
				int total=0;
				ResultSet rs = null;
				CallableStatement proc=null;
				
				try {
					
					proc = connection.prepareCall("{ call cxzsdkey1(?)}");
					proc.setString(1, value);
					proc.execute();
					rs = proc.getResultSet();
					while (rs.next()) {
						total=rs.getRow();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					AdminDaoImpl.close(rs, proc, connection);
				}
				return total;
		 }
		 

	 
	 
	 //*************************章组卷调用存储过程***************************

		//调用存储过程获得章组卷的多选题号
		public List<Integer> dxzth(int th) {
			List<Integer> dxzth = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_DXZT1(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						List<Integer> list = new ArrayList();
						list.add(rs.getInt(1));
						dxzth.addAll(list);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return dxzth;
		}
	//进行章组卷调用存储过程获得符合条件的单选题题号	
		public List<Integer> xzth(int th) {
			List<Integer> xzth = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_XZT1(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(rs.getInt(1));
						xzth.addAll(list);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
				
			}
			return xzth;
		}
		
		//进行章、阶段组卷时根据题号调用存储过程获得相应的选择题的内容
		public Xz xznr(int th) {
			Xz x = new Xz();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_XZT2(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						//List<String> list = new ArrayList();
						x.setTg(rs.getString(1));
						x.setXx1(rs.getString(2));
						x.setXx2(rs.getString(3));
						x.setXx3(rs.getString(4));
						x.setXx4(rs.getString(5));
						x.setDa(rs.getString(6));
						x.setDdx(rs.getInt(7));
						x.setTh(rs.getInt(8));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return x;
		}
		
		//进行章组卷时调用存储过程获得符合条件的判断题题号	
		public List<Integer> pdth(int th) {
			List<Integer> pth = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_PDT1(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						pth.add(rs.getInt(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return pth;
		}	
		
	//根据题号调用存储过程获得相应的判断题内容	
		public Pd pdnr(int th) {
			Pd x = new Pd();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				 proc = connection.prepareCall("{ call SJ_PDT2(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						//List<String> list = new ArrayList();
						x.setTg(rs.getString(1));
						x.setDa(rs.getInt(2));
						x.setTh(rs.getInt(3));
					
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
				
			}
			return x;
		}
		
	//根据题号调用存储过程获得相应的操作题内容	
		public Cztzj cztnr(int th) {
			Cztzj x = new Cztzj();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_CZT2(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					List<String> xttg=new ArrayList<String>();
					List<Integer> xtth=new ArrayList<Integer>();
					while (rs.next()) {
						x.setDttg(rs.getString(2));
						x.setDtth(th);
						xttg.add(rs.getString(1));
						xtth.add(rs.getInt(3));
						x.setXtth(xtth);
						x.setXttg(xttg);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return x;
		}
		
//***********************阶段组卷    调用存储过程**********************
		//阶段组卷获得单选题题号
		public List<Integer> jdxzth(int jdzbh1, int jdzbh2) {
			List<Integer> xzth = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_JDXZT1(?,?)}");
				proc.setInt(1, jdzbh1);
				proc.setInt(2, jdzbh2);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						xzth.add(rs.getInt(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return xzth;
		}

	//阶段章组卷获得符合条件的多选题题号	
		public List<Integer> jdxzthd(int jdzbh1, int jdzbh2) {
			List<Integer> xzth = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_JDXZT2(?,?)}");
				proc.setInt(1, jdzbh1);
				proc.setInt(2, jdzbh2);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						xzth.add(rs.getInt(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return xzth;
		}
		
//*****************查看试卷***********************
		//根据试卷编号查出试卷中的选择题
		public List<List> cksjxzt(int th) {
			List<List> sjxzt = new ArrayList<List>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_ST(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) { 
					rs = proc.getResultSet();
					while (rs.next()) {
						List<String> list = new ArrayList<String>();
						list.add(rs.getString(1));
						list.add(rs.getString(2));
						list.add(rs.getString(3));
						list.add(rs.getString(4));
						list.add(rs.getString(5));
						list.add(rs.getString(6));
						list.add(rs.getString(7));
						list.add(rs.getString(8));
						sjxzt.add(list);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return sjxzt;
		}

		
		//根据试卷编号查出试卷中的判断题
		public List<List> cksjpdt(int th) {
			List<List> sjpdt = new ArrayList<List>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_ST1(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) { 
					rs = proc.getResultSet();
					while (rs.next()) {
						List<String> list = new ArrayList<String>();
						list.add(rs.getString(1));
						list.add(rs.getString(2));
						list.add(rs.getString(3));
						sjpdt.add(list);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return sjpdt;
		}


		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#searchcztth(int, int)
		 */
		//根据起始、截止章编号查询满足条件的操作题题号
		@Override
		public List<Integer> searchcztth(int zbh1, int zbh2) {
			List<Integer> cztth = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call searchcztth(?,?)}");
				proc.setInt(1, zbh1);
				proc.setInt(2, zbh2);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) { 
					rs = proc.getResultSet();
					while (rs.next()) {
						cztth.add(rs.getInt(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return cztth;
		}
		
		//根据试卷编号查出试卷中的操作题
		public List<List> cksjczt(int th) {
			List<List> sjczt = new ArrayList<List>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call SJ_ST2(?)}");
				proc.setInt(1, th);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) { 
					rs = proc.getResultSet();
					while (rs.next()) {
						List<String> list = new ArrayList<String>();
						list.add(rs.getString(1));
						list.add(rs.getString(2));
						list.add(rs.getString(3));
						list.add(rs.getString(4));
						sjczt.add(list);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return sjczt;
		}
		
		//关于资源统计更新功能的存储过程调用
		public List<String> zytjccgc() {
			List<String> zytjlist = new ArrayList<String>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				 proc = connection.prepareCall("{ call tj()}");
				//proc.setInt(2, th);
				proc.execute();
				boolean hasResult = true;
				int id=0;
				while (hasResult) {
					id++;
					int num=0;
					rs = proc.getResultSet();
					while (rs.next()) {
						if(id==1)
						{
							String s="";
							s+=rs.getString(1)+","+rs.getString(2)+","+rs.getInt(3);
							zytjlist.add(s);
						}
						else
						{
							String s=zytjlist.get(num);
							s+=","+rs.getInt(3);
							zytjlist.set(num,s);
							num++;
						}
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return zytjlist;
		}

		//关于资源查询功能的存储过程调用
		public List<Integer> findrs(int rad,String qw) {
			List<Integer> findlist = new ArrayList<Integer>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call zycxcc(?,?)}");
				proc.setInt(1, rad);
				proc.setString(2, qw);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						findlist.add(rs.getInt(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return findlist;
		}


		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#findsuggest(int, java.lang.String)
		 */
		@Override
		public List<String> findsuggest(int rad, String qw) {
			List<String> sugglist = new ArrayList<String>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call cxsuggest(?,?)}");
				proc.setInt(1, rad);
				proc.setString(2, qw);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						sugglist.add(rs.getString(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return sugglist;
		}

		@Override
		public List<Map<String, String>> findsuggest2(String qw) {
			List<Map<String, String>> sugglist = new ArrayList<Map<String,String>>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call cxsuggest2(?)}");
			 
			    
			    System.out.println("测试蔬菜出:"+qw);
				proc.setString(1, qw);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						Map<String, String> map=new HashMap<String, String>();
						map.put("zsdid", rs.getString(1));
						map.put("zsdmc", rs.getString(2));
						sugglist.add(map);
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			  System.out.println("测试蔬菜出2:"+sugglist);
			return sugglist;
		}
		@Override
		public List<String> findCname(String qw) {
			List<String> sugglist = new ArrayList<String>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call cnamesuggest(?)}");
				proc.setString(1, qw);
				proc.execute();
				boolean hasResult = true;
				while (hasResult) {
					rs = proc.getResultSet();
					while (rs.next()) {
						sugglist.add(rs.getString(1));
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return sugglist;
		}
		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#findczt(int, java.lang.String, int, int)
		 */
		@Override
		public List<String> findczt(int rad, String qw, int value1, int value2) {
			List<String> cztlist = new ArrayList<String>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				proc = connection.prepareCall("{ call cztcx(?,?,?,?)}");
				proc.setInt(1, rad);
				proc.setString(2, qw);
				proc.setInt(3, value1);
				proc.setInt(4, value2);
				proc.execute();
				boolean hasResult = true;
				int id=0;
				while (hasResult) {
					id++;
					rs = proc.getResultSet();
					while (rs.next()) {
						if(id==1)
						{
							String s="";
							s+=rs.getInt(1)+","+rs.getString(2)+","+rs.getInt(3)+","+rs.getString(4);
							cztlist.add(s);
						}
						else
						{
							String s="";
							s+=rs.getInt(1);
							cztlist.add(s);
						}
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return cztlist;
		}


		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#finddmt(int, java.lang.String, int, int)
		 */
		@Override
		public List<String> finddmt(int rad, String qw, int value1, int value2) {
			List<String> dmtlist = new ArrayList<String>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			
			try {
				 proc = connection.prepareCall("{ call dmtcx(?,?,?,?)}");
				proc.setInt(1, rad);
				proc.setString(2, qw);
				proc.setInt(3, value1);
				proc.setInt(4, value2);
				proc.execute();
				boolean hasResult = true;
				int id=0;
				while (hasResult) {
					id++;
					rs = proc.getResultSet();
					while (rs.next()) {
						if(id==1)
						{
							String time10 = new String();
							time10 = rs.getString(10).substring(0,19);
							String s="";
							s+=rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+time10+","+rs.getInt(11)+","+rs.getInt(12)+","+rs.getInt(13)+","+rs.getString(14);
							dmtlist.add(s);
						}
						else
						{
							String s="";
							s+=rs.getInt(1);
							dmtlist.add(s);
						}
					}
					hasResult = proc.getMoreResults();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return dmtlist;
		}

		
		
		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#ChushihuaZq(int, int)
		 * 用于遗传算法初始化种群时产生个体
		 */
		@Override
		public List<Problemlist> ChushihuaZq(int ts, int type) {
			List<Problemlist> prolist = new ArrayList<Problemlist>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs =null;
			CallableStatement proc = null;
			try{
				proc = connection.prepareCall("{call cszq(?,?)}");
				proc.setInt(1, ts);
				proc.setInt(2, type);
				proc.execute();
				boolean hasResult = true;
				while(hasResult){
					rs=proc.getResultSet();
					while(rs.next()){
						Problemlist pb = new Problemlist();
						pb.setProblemno(rs.getInt(1));
						pb.setProblemzsdh(rs.getString(2));
						pb.setProblemnyd(rs.getInt(3));
						pb.setProblemtime(rs.getInt(4));
						pb.setProblemscore(rs.getInt(5));
						pb.setProblemtype(rs.getInt(6));
						pb.setProblempgd(rs.getInt(7));
						pb.setProblemid(rs.getInt(8));
						prolist.add(pb);
					}
					hasResult=proc.getMoreResults();
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return prolist;
		}

		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#searchczt(int, int, int)
		 * 用来根据操作题题数和范围查找相应的操作题
		 */
		@Override
		public List<CztSearch> searchczt(int zbh1, int zbh2, int ts) {
			List<CztSearch> czt = new ArrayList<CztSearch>();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs =null;
			CallableStatement proc = null;
			try{
				proc = connection.prepareCall("{call searchczt(?,?,?)}");
				proc.setInt(1, zbh1);
				proc.setInt(2, zbh2);
				proc.setInt(3, ts);
				proc.execute();
				boolean hasResult = true;
				while(hasResult){
					rs=proc.getResultSet();
					while(rs.next()){
						CztSearch cz=new CztSearch();
						cz.setTh(rs.getInt(1));
						cz.setPuguangdu(rs.getInt(2));
						cz.setDatitime(rs.getInt(3));
						cz.setNyd(rs.getInt(4));
						cz.setZsdbh(rs.getInt(5));
						cz.setXtfz(rs.getInt(6));
						czt.add(cz);
					}
					hasResult=proc.getMoreResults();
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			return czt;
		}

		/* (non-Javadoc)
		 * @see xx.admindao.AdminDao#batchInsert(int, int)
		 */
		@Override
		public void batchInsert(int zbh1, int zbh2) {
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs =null;
			CallableStatement proc = null;
			try{
				proc = connection.prepareCall("{call batchInsert(?,?)}");
				proc.setInt(1, zbh1);
				proc.setInt(2, zbh2);
				proc.execute();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				AdminDaoImpl.close(rs, proc, connection);
			}
			
		}

		/**
		 * @{方法名}
		 * @param {引入参数名} {引入参数说明}
		 * @return {返回参数名} {返回参数说明}
		 * @{方法的功能/动作描述}
		 * @exception {说明在某情况下,将发生什么异常}
		*/
		@Override
		public List<Map<String, String>> querytuijian2(String nodeid,String usertype) {
			List<Map<String, String>> adminlist = new ArrayList();
			Connection connection = AdminDaoImpl.getConnection();
			ResultSet rs = null;
			CallableStatement proc=null;
			  try{
			
			   proc= connection.prepareCall("{ call tuijian2(?,?)}");
			   
			   proc.setString(1,nodeid);
			   proc.setString(2, usertype);
			   proc.execute();
			   boolean hasResult = true;
			   
			   while (hasResult) {
			         rs = proc.getResultSet();
			         
			         while(rs.next()){
			        	Map<String, String>map=new HashMap<String, String>();
			        	map.put("zlid", rs.getString(1));
			        	map.put("zlmc", rs.getString(2));
			        	map.put("zlms", rs.getString(4));
			        	map.put("filepath", rs.getString(3));
			        	String type=rs.getString(5);
						String tubiao="";
						if (type==null||type=="") {
							tubiao="/assets/avatars/word.jpg";
						}
						else if (type.equals("1")) {
							tubiao="/assets/avatars/word.jpg";
						}else if (type.equals("2")) {
							tubiao="/assets/avatars/shipin.jpg";
						}else if (type.equals("3")) {
							tubiao="/assets/avatars/tupian.jpg";
						}else if (type.equals("4")) {
							tubiao="/assets/avatars/tuzhi.jpg";
						}else if (type.equals("5")) {
							tubiao="/assets/avatars/yinpin.jpg";
						}
			        	map.put("tubiao", tubiao);
			        	map.put("userid", rs.getString(6));
			        	map.put("type", type);
			        	adminlist.add(map);
			         }
	                 
			         hasResult = proc.getMoreResults();
			   }
			   
			  }catch (SQLException e){
			   e.printStackTrace();

			  }finally{
				  AdminDaoImpl.close(rs, proc, connection);
			  }
			  
			return adminlist;

		}

}
