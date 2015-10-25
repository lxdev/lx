package net.lx.model.base;

public class SessionUser {

	@SuppressWarnings("unchecked")
	private static final ThreadLocal sessionUser = new ThreadLocal();
	
	@SuppressWarnings("unchecked")
	public static void setSessionUser(AuthenticationTicket authenticationTicket) {
		sessionUser.set(authenticationTicket);
	}
	
	public static AuthenticationTicket getSessionUser(){
		return (AuthenticationTicket )sessionUser.get();
	}
	
	public static int getSessionUserId(){
		return getSessionUser().getUserId();
	}
	
	public static String getSessionFullName(){
		if(getSessionUser()==null){
			return null;
		}
		return getSessionUser().getFullName();
	}
}

