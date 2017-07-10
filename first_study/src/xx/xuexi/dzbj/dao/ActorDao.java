/*
 *@(#)xx.xuexi.dzbj.dao
 *@DzbjDao.java.java  
 *@创建时间:2011-11-27下午12:27:57
 *@作者：guangge
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.dzbj.dao;


import java.util.List;

import xx.collection.bean.Dzbj;

/**
 * @DzbjDao <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public interface ActorDao {
	
	public int batchUpdate(List<Dzbj> actors);
}
