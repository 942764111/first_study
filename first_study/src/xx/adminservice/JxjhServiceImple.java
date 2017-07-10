/*
 *@(#)xx.adminservice
 *@JxjhServiceImple.java.java  
 *@����ʱ��:2011-11-20����07:42:12
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.adminservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import xx.admindao.JxjhDao;
import xx.collection.bean.JxjhYck;
import xx.collection.bean.Teacher;
import xx.collection.bean.CourseChapter;

import xx.dao.PublicDao;
import xx.page.module.Conmen;
import xx.xuexi.action.Jxnr_z;

/**
 * @JxjhServiceImple <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Service("jxjhservice")
@Transactional
public class JxjhServiceImple implements JxjhService {

	/* (non-Javadoc)
	 * @see xx.adminservice.JxjhService#proc_jxjh_tj()
	 */
	@Resource(name="jxjhdao")
	private JxjhDao jxjhDao;
	@Resource(name="dao")                  
	private PublicDao publicDao;
	
	/**
	 * @return the jxjhDao
	 */
	public JxjhDao getJxjhDao() {
		return jxjhDao;
	}


	/**
	 * @param jxjhDao the jxjhDao to set
	 */
	public void setJxjhDao(JxjhDao jxjhDao) {
		this.jxjhDao = jxjhDao;
	}


	/**
	 * @return the publicDao
	 */
	public PublicDao getPublicDao() {
		return publicDao;
	}


	/**
	 * @param publicDao the publicDao to set
	 */
	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}
	
	public int proc_jxjh_tjTotal(){
		return this.jxjhDao.proc_jxjh_tj();
	}


	public List<Object> proc_jxjh_tj(int page,int rows_s) {
		@SuppressWarnings("unused")
		List<Conmen> list=this.jxjhDao.proc_jxjh_tj(page,rows_s);
		List<Object> listo=new ArrayList<Object>();

		for(int i=list.size()-1;i>=0;i--){//Ϊ����kch���������еģ���Ϊ�Ӵ洢�ṹ��ȡ����������kch�������еģ�
			Conmen con=list.get(i);
			listo.add(con);
			
		}
		
		
		return listo;
	}
	
	public List<Object> proc_jxjh_tj_search(int kk_ch,String xx_q,int page,int rows_s){
		@SuppressWarnings("unused")
		List<Conmen> list=this.jxjhDao.proc_jxjh_tj_search(kk_ch, xx_q, page, rows_s);
		List<Object> listo=new ArrayList<Object>();

		for(int i=list.size()-1;i>=0;i--){//Ϊ����kch���������еģ���Ϊ�Ӵ洢�ṹ��ȡ����������kch�������еģ�
			Conmen con=list.get(i);
			listo.add(con);
			
		}
		
		
		return listo;
	}
	public int proc_jxjh_tj_search(int kk_ch,String xx_q){
		int t=this.jxjhDao.proc_jxjh_tj_search(kk_ch, xx_q);
		return t;
	}
	public int proc_addjxjh(String jsbh,int kch,String xq){
		return this.jxjhDao.proc_addjxjh(jsbh, kch, xq);
	}
	public List<Jxnr_z> proc_jxnr(int i_d,int ID,int page,int rows_s){
		return this.jxjhDao.proc_jxnr(i_d, ID, page, rows_s);
	}
	public int[] batchInsert(final List<JxjhYck> obj){
		return this.jxjhDao.batchInsert(obj);
	}
	public void proc_jxglfz(int jxjhid,String jsbh_2,int kch_2,String xq_2){
		this.jxjhDao.proc_jxglfz(jxjhid, jsbh_2, kch_2, xq_2);
	}
	public boolean proc_deljxjh(int jxjhid){
		return this.jxjhDao.proc_deljxjh(jxjhid);
	}


}
