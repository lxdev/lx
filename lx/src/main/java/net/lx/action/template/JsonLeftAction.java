package net.lx.action.template;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.lx.action.BaseAction;
import net.lx.biz.user.UserBiz;
import net.lx.entity.user.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 左侧菜单
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonLeftAction extends BaseAction 
{
	private static final long serialVersionUID = 817750947028944890L;
	
	@Autowired
	private UserBiz userBiz;
	
	List<Object> trslist = new ArrayList<Object>();
	
	@Action(value = "tree_left", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			User user=userBiz.findUserById(getUser().getUserId());
			createLeft(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 创建左侧菜单
	 * @param user
	 * @throws Exception
	 */
	private void createLeft(User user)throws Exception
	{
		
		String subSystemIds="";
		String modualrIds="";
		String pSetIds="";
		String privilegeIds="";
		
		List<String> subSystemIdlst=new ArrayList<String>();
		List<String> modularIdlst=new ArrayList<String>();
		List<String> pSetIdlst=new ArrayList<String>();
		List<String> privialteIdlst=new ArrayList<String>();
		
		
		
		initTreeSubSystem(subSystemIds,modualrIds,pSetIds,privilegeIds);
	}
	
	/**
	 * 生成菜单树
	 * @param subSystemIds
	 * @param modularIds
	 * @param psIds
	 * @param pIds
	 * @throws Exception
	 */
	private void initTreeSubSystem(String subSystemIds,String modularIds,String psIds,String pIds)throws Exception
	{
		int ssId=0;
	
//		subSystem = "{\"subSystem\":{\"id\":1,\"name\":\"攻略\",\"orders\":1}," +
//				"\"mlist\":[" +
//				"{\"modular\":{\"id\":11,\"imageUrl\":\"item_221.gif\",\"name\":\"攻略内容选项\",\"orders\":\"1\",\"subSystem\":null,\"subSystemId\":1}," +
//				"\"pslist\":[" +
//					"{\"privilegeSet\":{\"id\":111,\"modular\":null,\"modularId\":11,\"name\":\"大项设置\",\"orders\":1}," +
//					"\"plist\":[{\"id\":1111,\"name\":\"新建大项\",\"isShow\":1,\"fullPath\":\"/admin/areamanager/settings_area_branch\",\"orders\":1,\"privilegeSet\":null,\"setId\":111}]" +
//					"}," +
//					"{\"privilegeSet\":{\"id\":112,\"modular\":null,\"modularId\":11,\"name\":\"小项设置\",\"orders\":2}," +
//					"\"plist\":[{\"id\":1121,\"name\":\"新建小项\",\"isShow\":1,\"fullPath\":\"/admin/branch/index_branch\",\"orders\":1,\"privilegeSet\":null,\"setId\":112}]" +
//					"}" +
//					"]}" +
//				"]" +
//				"}";
		Object subSystem = "{subSystem:{id:1,name:\"攻略\",orders:1}," +
				"mlist:[" +
				"{modular:{id:11,imageUrl:\"item_221.gif\",name:\"攻略内容选项\",orders:1,subSystem:null,subSystemId:1}," +
				"pslist:[" +
					"{privilegeSet:{id:111,modular:null,modularId:11,name:\"大项设置\",orders:1}," +
					"plist:[{id:1111,name:\"新建大项\",isShow:1,fullPath:\"/admin/areamanager/settings_area_branch\",orders:1,privilegeSet:null,setId:111}]" +
					"}," +
					"{privilegeSet:{id:112,modular:null,modularId:11,name:\"小项设置\",orders:2}," +
					"plist:[{id:1121,name:\"新建小项\",isShow:1,fullPath:\"/admin/branch/index_branch\",orders:1,privilegeSet:null,setId:112}]" +
					"}" +
					"]}" +
				"]" +
				"}";
		
		trslist.add(subSystem);
		
	}

	public List<Object> getTrslist() {
		return trslist;
	}
}
