/*
 *@(#)xx.adminservice
 *@JxjhService.java.java  
 *@����ʱ��:2011-11-20����07:40:42
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.adminservice;

import java.util.List;

import xx.collection.bean.JxjhYck;
import xx.page.module.Conmen;
import xx.xuexi.action.Jxnr_z;



/**
 * @JxjhService <code>{������}</code>
 * @author  {����gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public interface JxjhService {
	public List<Object> proc_jxjh_tj(int page,int rows_s);
	public int proc_jxjh_tjTotal();
	public List<Object> proc_jxjh_tj_search(int kk_ch,String xx_q,int page,int rows_s);
	public int proc_jxjh_tj_search(int kk_ch,String xx_q);
	public int proc_addjxjh(String jsbh,int kch,String xq);
	public List<Jxnr_z> proc_jxnr(int i_d,int ID,int page,int rows_s);
	public int[] batchInsert(final List<JxjhYck> obj);
	public void proc_jxglfz(int jxjhid,String jsbh_2,int kch_2,String xq_2);
	public boolean proc_deljxjh(int jxjhid);

}
