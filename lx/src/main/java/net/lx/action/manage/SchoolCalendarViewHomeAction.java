package net.lx.action.manage;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import net.lx.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院历--查看
 * 
 * @author 
 * 
 */
public class SchoolCalendarViewHomeAction extends BaseAction {

	private int id;

	@Override
	public String execute() throws Exception {
		// 查询所有院校
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
