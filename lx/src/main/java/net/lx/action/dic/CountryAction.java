package net.lx.action.dic;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.lx.action.BaseAction;
import net.lx.biz.dic.ICountryBiz;
import net.lx.entity.dic.Country;;

/**
 * country
 * 
 * @author lxl
 * 
 */
public class CountryAction  extends BaseAction
{
	
	@Autowired
	private ICountryBiz countryBiz;
	private Country country;
	private List<Country> countrylist = new ArrayList<Country>();
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{
			countrylist = this.countryBiz.findAll();
			Collections.sort(countrylist, new Comparator(){
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
					String name1 = ((Country) arg0).getName();
					String name2 = ((Country) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			return INPUT;
		}
		return SUCCESS;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Country> getCountrylist() {
		return countrylist;
	}

	public void setCountrylist(List<Country> countrylist) {
		this.countrylist = countrylist;
	}
	
}
