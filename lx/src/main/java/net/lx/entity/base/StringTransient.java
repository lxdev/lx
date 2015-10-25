package net.lx.entity.base;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

public class StringTransient {

	@Transient
	transient private Map<String,String> strParams=null;

	public Map<String, String> getStrParams() {
		if(strParams==null){
			strParams=new HashMap<String,String>();
		}
		return strParams;
	}

}
