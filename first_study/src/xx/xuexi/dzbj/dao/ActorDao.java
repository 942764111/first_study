/*
 *@(#)xx.xuexi.dzbj.dao
 *@DzbjDao.java.java  
 *@����ʱ��:2011-11-27����12:27:57
 *@���ߣ�guangge
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.xuexi.dzbj.dao;


import java.util.List;

import xx.collection.bean.Dzbj;

/**
 * @DzbjDao <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public interface ActorDao {
	
	public int batchUpdate(List<Dzbj> actors);
}
