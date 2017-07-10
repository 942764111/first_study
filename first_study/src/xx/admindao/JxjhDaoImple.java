/*
 *@(#)xx.admindao
 *@JxjhDaoImple.java.java  
 *@创建时间:2011-11-20下午06:32:01
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
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import xx.collection.bean.JxjhYck;
import xx.page.module.Conmen;
import xx.xuexi.action.Jxnr_z;

/**
 * @JxjhDaoImple <code>{类名称}</code>
 * @author  {作者gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Repository("jxjhdao")
public class JxjhDaoImple extends HibernateDaoSupport implements JxjhDao {
	
	
	private SimpleJdbcTemplate simpleJdbcTemplate;

	/* (non-Javadoc)
	 * @see xx.admindao.JxjhDao#proc_jxjh_tj()
	 */
	@Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	protected static Log log = LogFactory.getLog(JxjhDaoImple.class); 

	private static Connection connection = null;
	/**
	 * 
	 * @{方法名:getConnection()}
	 * @param 
	 * @return {返回参数名:connection} 
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
			  log.error("JxjhDaoImple.java链接数据库失败！");
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
				log.error("JxjhDaoImple.java关闭链接异常！");
			}
		}
	 
	//存储过程且实现了分页；
	public List<Conmen> proc_jxjh_tj(int page,int rows_s) {
		List<Conmen> list = new ArrayList<Conmen>();
		Connection connection = JxjhDaoImple.getConnection();
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int no=0;
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_jxjh_tj()}");
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
			   if(no>=fromIndex&&no<=toIndex){
				   rs = proc.getResultSet();
			         while(rs.next()){
			        	 Conmen con = new Conmen();
			        	
			        	 con.setInt1(rs.getInt(1));//教学次数
			        	 con.setStr1(rs.getString(2));//教师编号
			        	 con.setInt2(rs.getInt(3));//课程编号
			        	 con.setStr2(rs.getString(4));//学期
			        	 con.setInt3(rs.getInt(5));//教学计划编号
			        	 con.setStr5(rs.getString(6));//教师名称
			        	 con.setStr4(rs.getString(7));//课程名称
			        	list.add(con);
			         }
			         if(no==toIndex){
				        	break;
				        }
			   }
		         
                 no+=1;
		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(rs, proc, connection);
		  }
		  
		return list;
	}
	
	public int proc_jxjh_tj() {
		Connection connection = JxjhDaoImple.getConnection();
		int no=0;
		
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_jxjh_tj()}");
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
			            
                 no+=1;
		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(null, proc, connection);
		  }
		  
		return no;
	}
	//存储过程且实现了分页；
	public List<Conmen> proc_jxjh_tj_search(int kk_ch,String xx_q,int page,int rows_s){
		List<Conmen> list = new ArrayList<Conmen>();
		Connection connection = JxjhDaoImple.getConnection();
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int no=0;
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_jxjh_tj_search(?,?)}");
		   proc.setInt(1,kk_ch);
		   proc.setString(2, xx_q);
		   
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
			   
				   if(no>=fromIndex&&no<=toIndex){
					   rs = proc.getResultSet();
				         while(rs.next()){
				        	 Conmen con = new Conmen();
				        	
				        	 con.setInt1(rs.getInt(1));
				        	 con.setStr1(rs.getString(2));
				        	 con.setInt2(rs.getInt(3));
				        	 con.setStr2(rs.getString(4));
				        	 con.setInt3(rs.getInt(5));
				        	 con.setStr5(rs.getString(6));//教师名称
				        	 con.setStr4(rs.getString(7));//课程名称
				        	list.add(con);
				         }
				        if(no==toIndex){
				        	break;
				        } 
				   }
		         no+=1;
                 hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(rs, proc, connection);
		  }
		  
		return list;
	}
	
	public int proc_jxjh_tj_search(int kk_ch,String xx_q){
		
		Connection connection = JxjhDaoImple.getConnection();
		int no=0;
		
		CallableStatement proc=null;
		
		  try{
		  
		   proc = connection.prepareCall("{ call proc_jxjh_tj_search(?,?)}");
		   proc.setInt(1,kk_ch);
		   proc.setString(2, xx_q);
		   
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
			     no+=1;
                 hasResult = proc.getMoreResults();
                 
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(null, proc, connection);
		  }
		  
		return no;
	}
	public int proc_addjxjh(String jsbh,int kch,String xq){
		Connection connection = JxjhDaoImple.getConnection();
		int no=0;
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_addjxjh(?,?,?)}");
		   proc.setString(1, jsbh);
		   proc.setInt(2,kch);		   
		   proc.setString(3, xq);
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
		         rs = proc.getResultSet();
		         while(rs.next()){
		        	 no=rs.getInt(1);
		         }
		         
		         hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(rs, proc, connection);
		  }
		  
		return no;
	}
	
	public void proc_jxglfz(int jxjhid,String jsbh_2,int kch_2,String xq_2){
		Connection connection = JxjhDaoImple.getConnection();
		int no=0;
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
			  //第一步
		   proc = connection.prepareCall("{ call proc_jxglfz(?,?,?,?)}");
		   proc.setString(2, jsbh_2);
		   proc.setInt(1,jxjhid);		   
		   proc.setInt(3, kch_2);
		   proc.setString(4, xq_2);
		   proc.execute();
		   boolean hasResult = true;
		   while (hasResult) {
		         rs = proc.getResultSet();
		         while(rs.next()){
		        	 no=rs.getInt(1);
		         }
		         
		         hasResult = proc.getMoreResults();
		   }
		   //第二步
		   
		   CallableStatement proc2 = connection.prepareCall("{ call proc_jxglfz2(?)}");
		   proc2.setInt(1,no);		   
		   proc2.execute();
		  
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(rs, proc, connection);
		  }
		  
		
	}
	
	public boolean proc_deljxjh(int jxjhid){
		Connection connection = JxjhDaoImple.getConnection();
		boolean tip=false;
		
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_deljxjh(?)}");
		   proc.setInt(1,jxjhid);		   
		   proc.execute();
		   tip=true;
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(null, proc, connection);
		  }
		  return tip;
	}
	
	public List<Jxnr_z> proc_jxnr(int i_d,int ID,int page,int rows_s){
		List<Jxnr_z> list = new ArrayList<Jxnr_z>();
		Connection connection = JxjhDaoImple.getConnection();
		int fromIndex=rows_s*(page - 1);
		int toIndex=page*rows_s - 1;
		int no=0;
		ResultSet rs = null;
		CallableStatement proc=null;
		
		  try{
		   proc = connection.prepareCall("{ call proc_jxnr(?,?)}");
		   proc.setInt(1,i_d);
		   proc.setInt(2, ID);
		   
		   proc.execute();
		   boolean hasResult = true;
		   
		   while (hasResult) {
			   
				   if(no>=fromIndex&&no<=toIndex){
					   rs = proc.getResultSet();
				         while(rs.next()){
				        	 Jxnr_z j = new Jxnr_z();
				        	
				        	 j.setId(rs.getInt(1));//
				 			
				 			j.setNr(rs.getString(2));
				 			j.setWjms(rs.getString(3));
				 			j.setFpath(rs.getString(4));
				 			j.setZlid(rs.getInt(5));
				 			j.setZlmc(rs.getString(6));
				        	list.add(j);
				         }
				        if(no==toIndex){
				        	break;
				        } 
				   }
		         no+=1;
                 hasResult = proc.getMoreResults();
		   }
		   
		  }catch (SQLException e){
		   e.printStackTrace();

		  }finally{
			  JxjhDaoImple.close(rs, proc, connection);
		  }
		  
		return list;
	}
	//批量插入jxjhyck
	public void setDataSource(DataSource dataSource) {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
	public int[] batchInsert(final List<JxjhYck> obj){
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(obj.toArray());
        	       
	    String  sql="insert into jxjh_yck(no,yckms,xsh,zbh,jxjh_sz) values (:no,:yckms,:xsh,:courseChapter.zbh,:jxjhSz)";
	      
     int[] insertCounts = simpleJdbcTemplate.batchUpdate(sql,batch);
     return insertCounts;
	}

}
