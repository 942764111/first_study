package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.MapNode;
import xx.collection.bean.MindMap;


import xx.mindMap.bean.Node2;
import xx.quanxian.service.BaseService;

import com.lowagie.tools.handout_pdf;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetBijiMapAction extends ActionSupport  
{  
	private String state;

	private String datas;
    private String kcmc;
	@Resource(name="baseService")
	private BaseService baseservice;

	public String getState() {
		return state;
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
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the datas
	 */
	public String getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(String datas) {
		this.datas = datas;
	}
	@Action(value="getbijidata2",results={@Result(name="success",type="json")})
	public String getmindJson2() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String uid = (String)hs.getAttribute("uid");
		
		String[] keys7={"nodeid"};
		Object[] values7={kcmc};
		List<MapNode> listkcmc=this.baseservice.find(MapNode.class, "MapNode", keys7, values7);
		
		if (listkcmc==null||listkcmc.size()<=0) {
			return SUCCESS;
		}else {
			kcmc=listkcmc.get(0).getType();
		}
		
		String[] keys={"userid","type"};
		Object[] values={uid,kcmc};
		List<MapNode> list=this.baseservice.find(MapNode.class, "MapNode", keys, values);

		if (list==null||list.size()<=0) {
			return SUCCESS;
		}


		Map<String, Object> data=new HashMap<String, Object>();//总数据
		Map<String, Object> meta=new HashMap<String, Object>();
		meta.put("name", "jsMind remote");
		meta.put("author", "hizzgdev@163.com");
		meta.put("version", "0.2");
		data.put("meta", meta);
		data.put("format", "node_tree");

		String nodeid=list.get(0).getNodeid().substring(0,4);
		String[] keys1={"userid","type"};
		Object[] values1={uid,kcmc};
		List<MapNode> list2=this.baseservice.find(MapNode.class, "MapNode", keys1, values1);

		List<Map<String, String>> list3=new ArrayList<Map<String,String>>();
		for (int i = 0; i < list2.size(); i++) {
			Map<String, String> map=new HashMap<String, String>();
			MapNode mapNode=list2.get(i);
			map.put("id", mapNode.getNodeid());
			map.put("topic", mapNode.getNodename());
			map.put("parentid", mapNode.getParentid());

			list3.add(map);
		}


		List dataList = list3;   

		// 节点列表（散列表，用于临时存储节点对象）  
		HashMap nodeList = new HashMap();  
		// 根节点  
		Node2 root=null;

		// 根据结果集构造节点列表（存入散列表）  
		for (Iterator it = dataList.iterator(); it.hasNext();) {  
			Map dataRecord = (Map) it.next();  
			Node2 node = new Node2();  
			node.id = (String) dataRecord.get("id");  
			node.topic = (String) dataRecord.get("topic");  
			node.parentid = (String) dataRecord.get("parentid");  
			nodeList.put(node.id, node);  
		}  
		// 构造无序的多叉树  
		Set entrySet = nodeList.entrySet();  
		for (Iterator it = entrySet.iterator(); it.hasNext();) {  
			Node2 node = (Node2) ((Map.Entry) it.next()).getValue();  
			if (node.parentid == null || node.parentid.equals("")) {  
				root = node;  
			} else {  
				((Node2) nodeList.get(node.parentid)).addChild(node);  
			}  
		}  
		// 输出无序的树形菜单的JSON字符串  
		System.out.println("sdfsdf :"+root.toString()); 
		System.out.println("sdsfsdfds:"+root);
		state="0";
		
		
		
		
		data.put("data", root.toString());
		JSONObject jsonObject=JSONObject.fromObject(data);
		System.out.println("Jsssss:"+jsonObject);
		
		datas=jsonObject.toString();
		
		System.out.println("datatatat:"+datas);
		datas=datas.replace("\"", "\'");
		datas=datas.replace(" ", "");
		datas=datas.replace("'{", "{");
		datas=datas.replace("}'", "}");
//		datas="{'data':{'id' : '1001', 'topic' : 'C语言程序设计', 'children' : [{'id' : '10011001', 'topic' : '数据类型'}]},'format':'node_tree','meta':{'author':'hizzgdev@163.com','name':'jsMind remote','version':'0.2'}}";
		System.out.println("datatatat2:"+datas);
		return SUCCESS;   

	}

}  