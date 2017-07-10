/*
 *@(#)xx.xuexi.dzbj.dao
 *@DzbjDaoImpl.java.java  
 *@����ʱ��:2011-11-27����12:44:04
 *@���ߣ�guangge
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.xuexi.dzbj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import xx.collection.bean.Dzbj;

@Repository("jdbcDao")
public class JdbcActorDao extends HibernateDaoSupport implements ActorDao {
	
	
	
	@Resource(name = "sessionFactory")                  //ע������λ��
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	
	    public int batchUpdate( List<Dzbj> actors) {
	        Connection con=null;
	        PreparedStatement pst=null;
	    	try{
	        ConnectionProvider cp =((SessionFactoryImplementor)this.getSessionFactory()).getConnectionProvider();
	        con=cp.getConnection();
	        System.out.println(con);
			con.setAutoCommit(false);
			pst= (PreparedStatement) con.prepareStatement("update dzbj set xssxh =? where UserId =? and classno=?  and tmbh=?");
			for(Dzbj d:actors)
			{
				  pst.setInt(1, (d.getXssxh()));
                  pst.setString(2, (d.getId().getUserId()));
                  pst.setString(3, (d.getId().getClassno()));
                  pst.setInt(4, (d.getId().getTmbh()));
                  pst.addBatch();
                  System.out.println(pst);
			}
			pst.executeBatch();	
			con.commit();
	    	}
	    	catch(SQLException e)
	    	{
	    		logger.info("sql�쳣");
	    	}
	    	finally{
	    	 try {	
				 pst.close();
				 con.close();
			} catch (SQLException e) {
				logger.info("���ӹر�ʧ��");
			}
			catch(NullPointerException e)
			{
				logger.info("sql�쳣����Ŀ�ָ���쳣");
			}
			finally{
				pst=null;
				con=null;	
			}
	    	}
            return 0;
	    }

		/* (non-Javadoc)
		 * @see xx.xuexi.dzbj.dao.ActorDao#batchUpdate(java.util.List)
		 */
		

	    //  ... additional methods
	

   /* private JdbcTemplate jdbcTemplate;

    @Resource(name = "sessionFactory")                  //ע������λ��
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate();
    }
    @Override
    public int[] batchUpdate(final List<Dzbj> actors) {
        int[] updateCounts = jdbcTemplate.batchUpdate(
                "update Dzbj set xssxh = ? where userId = ? and classno= ? and tmbh= ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, ((Dzbj)actors.get(i)).getXssxh());
                        ps.setString(2, ((Dzbj)actors.get(i)).getId().getUserId());
                        ps.setString(3, ((Dzbj)actors.get(i)).getId().getClassno());
                        ps.setInt(4, ((Dzbj)actors.get(i)).getId().getTmbh());
                    }

                    public int getBatchSize() {
                        return actors.size();
                    }

				
                } );
        return updateCounts;
    }*/

	
}
