/*
 *@(#)xx.tables.dao
 *@ExcelDao.java.java  
 *@创建时间:2011-12-7上午09:53:44
 *@作者：Administrator
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.XmlWebApplicationContext;

import xx.collection.bean.Cztda;






@Controller("exceldao")
public class ExcelDao  extends HibernateDaoSupport {

	  private JdbcTemplate jdbcTemplate;

	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	    public void batchUpdate(String sql,final Object obj[][]) {
	    	if(sql==null||obj==null||obj.length==0||obj[0].length==0)
	    	{
	    		return;
	    	}
	    	final int length=obj[0].length;
	        int[] updateCounts = jdbcTemplate.batchUpdate(
	                sql,
	                new BatchPreparedStatementSetter() {
	                    public void setValues(PreparedStatement ps, int i) throws SQLException {
	                    	for(int i1=0;i1<length;i1++)
	                    	{
	                    		ps.setObject(i1+1, obj[i][i1]);
	                    	}
	                    }

	                    public int getBatchSize() {
	                        return obj.length;
	                    }
	                } );
	    }



}
