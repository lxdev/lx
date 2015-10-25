package net.lx.model.base;

/**
 * 认证票
 * 
 */
public class AuthenticationTicket {

	private int userId;		// 拥护ID
	private String userName;// 用户名
	private String fullName;// 真实姓名
	private int type;		//类型  
	private int isManager;	//

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}
}
