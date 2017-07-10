package xx.share.action;  

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.Share;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class ZanAction extends ActionSupport  
{  

	private int id;
	private String state;
	private int zhichi;
	private int fandui;
	private List<Share>list=new ArrayList<Share>();
	@Resource(name="baseService")
	private BaseService baseservice;


	public int getZhichi() {
		return zhichi;
	}


	public void setZhichi(int zhichi) {
		this.zhichi = zhichi;
	}


	public int getFandui() {
		return fandui;
	}


	public void setFandui(int fandui) {
		this.fandui = fandui;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Share> getList() {
		return list;
	}


	public void setList(List<Share> list) {
		this.list = list;
	}


	@Action(value="dianZan",results={@Result(name="success",type="json")})
	public String uploadnext() throws Exception  
	{  

		HttpSession session1 = ServletActionContext.getRequest().getSession();
		String userId = (String) session1.getAttribute("userid");
		Share share=this.baseservice.find(Share.class, id);
		String zhichiuser=share.getZhichiuser();
		String fanduiuser=share.getFanduiuser();
         zhichi=Integer.parseInt(share.getZhichi());
         fandui=Integer.parseInt(share.getFandui());
		if (state.equals("y")) {//閺�垶鏁撻弬銈嗗

			if(zhichiuser.indexOf(","+userId+",")>=0){//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶徊鐚紃闁跨喐鏋婚幏鐑芥晸鐟欐帒鍤栭幏鐑芥晸閺傘倖瀚归柨鐕傛嫹cd"闁跨喕顢滈崙銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗瀹�京顣幏宄板祦闁跨喐鏋婚幏鐤樁闁跨喐鏋婚幏鐑芥晸閿熶粙鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻懞鍌︾秶閹风兘鏁撳ú銉ㄧ箲娴兼瑦瀚�1
				state="-1";//闁跨喐鏋婚幏鐑芥晸缁愭牗鎷濋幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
			}else {
				//闁跨喐鏋婚幏閿嬬梾闁跨喎褰ㄧ喊澶嬪闁跨喓娼鹃敐蹇斿闁跨喎褰ㄧ拋瑙勫闁跨喓鐓导娆愬闁跨喕顬戦崙銈嗗閺�垶鏁撻弬銈嗗
				if(fanduiuser.indexOf(","+userId+",")>=0){//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶徊鐚紃闁跨喐鏋婚幏鐑芥晸鐟欐帒鍤栭幏鐑芥晸閺傘倖瀚归柨鐕傛嫹cd"闁跨喕顢滈崙銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗瀹�京顣幏宄板祦闁跨喐鏋婚幏鐤樁闁跨喐鏋婚幏鐑芥晸閿熶粙鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻懞鍌︾秶閹风兘鏁撳ú銉ㄧ箲娴兼瑦瀚�1
					fanduiuser=fanduiuser.replace(","+userId+",", ",");
					zhichiuser=zhichiuser+userId+",";
					share.setFanduiuser(fanduiuser);
					share.setZhichiuser(zhichiuser);
					zhichi=zhichi+1;
					fandui=fandui-1;
					share.setZhichi(String.valueOf(zhichi));
					share.setFandui(String.valueOf(fandui));
					state="1";
					
					this.baseservice.update(share);
				}else {
					zhichiuser=zhichiuser+userId+",";
					share.setZhichiuser(zhichiuser);
					share.setZhichi(String.valueOf(zhichi+1));
					zhichi=zhichi+1;
					state="0";
					this.baseservice.update(share);
				}
			}
		} else {
         //闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
			if(fanduiuser.indexOf(","+userId+",")>=0){//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶徊鐚紃闁跨喐鏋婚幏鐑芥晸鐟欐帒鍤栭幏鐑芥晸閺傘倖瀚归柨鐕傛嫹cd"闁跨喕顢滈崙銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗瀹�京顣幏宄板祦闁跨喐鏋婚幏鐤樁闁跨喐鏋婚幏鐑芥晸閿熶粙鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻懞鍌︾秶閹风兘鏁撳ú銉ㄧ箲娴兼瑦瀚�1
				state="-1";//闁跨喐鏋婚幏鐑芥晸缁愭牗鎷濋幏鐑芥晸閺傘倖瀚归弨顖炴晸閺傘倖瀚归柨鐔告灮閹凤拷
			}else {
				//闁跨喐鏋婚幏閿嬬梾闁跨喎褰ㄧ拠褎瀚归弨顖炴晸閺傘倖瀚�
				if(zhichiuser.indexOf(","+userId+",")>=0){//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撶徊鐚紃闁跨喐鏋婚幏鐑芥晸鐟欐帒鍤栭幏鐑芥晸閺傘倖瀚归柨鐕傛嫹cd"闁跨喕顢滈崙銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗瀹�京顣幏宄板祦闁跨喐鏋婚幏鐤樁闁跨喐鏋婚幏鐑芥晸閿熶粙鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻懞鍌︾秶閹风兘鏁撳ú銉ㄧ箲娴兼瑦瀚�1
					zhichiuser=zhichiuser.replace(","+userId+",", ",");
					fanduiuser=fanduiuser+userId+",";
					share.setFanduiuser(fanduiuser);
					share.setZhichiuser(zhichiuser);
					share.setZhichi(String.valueOf(zhichi-1));
					share.setFandui(String.valueOf(fandui+1));
					zhichi=zhichi-1;
					fandui=fandui+1;
					state="1";
					this.baseservice.update(share);
				}else {
					fanduiuser=fanduiuser+userId+",";
					share.setFanduiuser(fanduiuser);
					share.setFandui(String.valueOf(fandui+1));
					fandui=fandui+1;
					state="0";
					this.baseservice.update(share);
				}
			}
		}
		return "success";
	}
}  