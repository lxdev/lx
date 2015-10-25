package net.lx.entity.base;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

public class IntegerTransient {

	@Transient
	transient private Map<String, Integer> intParams = null;

	public Map<String, Integer> getIntParams() {
		if(intParams==null){
			intParams = new HashMap<String, Integer>();
		}
		return intParams;
	}

	

}
