/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½:2011-11-12ï¿½ï¿½ï¿½ï¿½08:43:29
 *@ï¿½ï¿½ï¿½ß£ï¿½ZYK
 *@Copyright 2009-2010 ï¿½Ó±ï¿½ï¿½ï¿½ï¿½ï¿½Ñ§Ôºï¿½ï¿½Ï¢ï¿½ï¿½Ñ§ï¿½ë¹¤ï¿½ï¿½Ñ§Ôºï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 
 */

package xx.luxian.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Collect;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Scwj;
import xx.collection.bean.Zsd2;

import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class StudyLuxianAction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseservice;
	private String kcmc;
	private String zcid;
	private List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
	
	private List<Map<String, Object>> rows=new ArrayList<Map<String,Object>>();
	
	public List<Map<String, Object>> getRows() {
		return rows;
	}


	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}


	/**
	 * @return the kcmc
	 */
	public String getKcmc() {
		return kcmc;
	}

	
	/**
	 * @param kcmc the kcmc to set
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	/**
	 * @return the zcid
	 */
	public String getZcid() {
		return zcid;
	}

	/**
	 * @param zcid the zcid to set
	 */
	public void setZcid(String zcid) {
		this.zcid = zcid;
	}

	@Action(value="/getroute",results={@Result(name="success",type="json")})
	public String getCollect(){
		
		kcmc="CÓïÑÔ³ÌÐòÉè¼Æ";
		String[] keys={"TCName"};
		Object[] values={kcmc};
		List<CourseChapter> list2=this.baseservice.find(CourseChapter.class, "CourseChapter", keys, values);
		if (list2==null||list2.size()<=0) {
			return SUCCESS;
		}
		
		for (int i = 0; i < list2.size(); i++) {
			CourseChapter chapter=list2.get(i);
			System.out.println("ÕÂ´ËÃû³Æ:"+chapter.getCName());
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("zcmc", chapter.getCName());
			
			String[] keys2={"courseChapter.zbh"};
			Object[] values2={chapter.getZbh()};
			List<Jie> list3=this.baseservice.find(Jie.class, "Jie", keys2, values2);
			if (list3==null||list3.size()<=0) {
				map.put("jie", "");
				Map<String, Object> data=new HashMap<String, Object>();//xin
				data.put("field1", kcmc+"|"+chapter.getCName());//xin
				data.put("field2", "");//xin
				data.put("field3", "");
				data.put("filed4", "");
				rows.add(data);
			} else {
				
				List<Map<String, Object>>listjie=new ArrayList<Map<String,Object>>();
				
				for (int j = 0; j < list3.size(); j++) {
					Jie jie=list3.get(j);
					
					System.out.println("½Ú´ÎÃû³Æ:"+jie.getZmc());
					Map<String, Object> map2=new HashMap<String, Object>();
					map2.put("jiemc", jie.getZmc());
					
					
					String[] keys3={"jie.id.zbh","jie.id.CId"};
					Object[] values3={jie.getId().getZbh(),jie.getId().getCId()};
					List<Zsd2> list4=this.baseservice.find(Zsd2.class, "Zsd2", keys3, values3);
					
					
					
					
					if (list4==null||list4.size()<=0) {
						map2.put("zsd", "");
						Map<String, Object> data=new HashMap<String, Object>();//xin
						data.put("field1", kcmc+"|"+chapter.getCName());//xin
						data.put("field2", jie.getZmc());//xin
						data.put("field3", "");
						data.put("filed4", "");
						rows.add(data);
					} else {
						
						List<Map<String, Object>>listzsd=new ArrayList<Map<String,Object>>();
                        for (int k = 0; k < list4.size(); k++) {
							Zsd2 zsd2=list4.get(k);
							
							System.out.println("ÖªÊ¶µãÃû³Æ£º"+zsd2.getZsdmc());
							Map<String, Object> map3=new HashMap<String, Object>();
							map3.put("zsdmc", zsd2.getZsdmc());

							String[] keys4={"zsdid"};
							Object[] values4={zsd2.getZsdid()};
							List<Scwj> list5=this.baseservice.find(Scwj.class, "Scwj", keys4, values4);
							
							
							if (list5==null||list5.size()<=0) {
								map3.put("zl", "");
								Map<String, Object> data=new HashMap<String, Object>();//xin
    							data.put("field1", kcmc+"|"+chapter.getCName());//xin
    							data.put("field2", jie.getZmc());//xin
    							data.put("field3", zsd2.getZsdmc());
    							data.put("filed4", "");
    							rows.add(data);
							} else {

								List<Map<String, Object>>listzl=new ArrayList<Map<String,Object>>();
                                for (int l = 0; l < list5.size(); l++) {
                                	Map<String, Object> data=new HashMap<String, Object>();//xin
        							data.put("field1", kcmc+"|"+chapter.getCName());//xin
        							data.put("field2", jie.getZmc());//xin
        							data.put("field3", zsd2.getZsdmc());
                                	String zl="";
									Scwj scwj=list5.get(l);
									System.out.println("×ÊÁÏÃû³Æ:"+scwj.getFilename());

									zl=zl+scwj.getFilename()+"|";
									zl=zl+scwj.getDmtzl().getZlbh()+"|";
									zl=zl+scwj.getFilepath();
									data.put("field4", zl);
									rows.add(data);
									Map<String, Object> map4=new HashMap<String, Object>();
									
									map4.put("zlmc", scwj.getFilename());
									map4.put("zlid", scwj.getDmtzl().getZlbh());
									map4.put("path", scwj.getFilepath());
									listzl.add(map4);
								}
                                map3.put("zl", listzl);
                             
							}
							listzsd.add(map3);
						}
                      
                        map2.put("zsd", listzsd);
					}
					
					listjie.add(map2);
				}
				
				map.put("jie", listjie);
			}
			
			list.add(map);
			
		}
		
		return SUCCESS;
	}
	
	
	
}
