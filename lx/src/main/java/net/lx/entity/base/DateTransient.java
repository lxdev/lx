package net.lx.entity.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

public class DateTransient {

	@Transient
	transient private Map<String, Date> dateParams = null;

	public Map<String, Date> getDateParams() {
		if(dateParams==null){
			 dateParams = new HashMap<String, Date>();
		}
		return dateParams;
	}

}
