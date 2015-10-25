package net.lx.model.page;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.lx.common.properties.Config;

/**
 * 分页实体
 * 
 * @param <T>
 */
@SuppressWarnings("serial")
public class PageResult<T> implements Serializable {
	// 排序
	private String order = "id";
	private String sort = "asc"; // asc或者desc
	// 每页记录数
	private int pageSize = Config.getIntProperty("pagesize");
	// 总记录数
	private int recordCount = 0;
	// 实际页号
	private int currentPageIndex = 1;
	// 查询结果
	private List<T> list;
	// 是否分页
	private boolean isPage = true;
	// 排序字段
	private String orderColumns = "";

	public PageResult() {

	}

	// 获取能排序的字段
	public String getOrderColumns() {
	//		if (orderColumns.endsWith(",")) {
	//
	//			orderColumns = orderColumns.substring(0, orderColumns
	//					.lastIndexOf(","));
	//		}
		return orderColumns;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public List<T> getList() {
		if (list == null) {
			list = new ArrayList<T>();
		}
		for (T t : list) {
			for (Field f : t.getClass().getDeclaredFields()) {
//				System.out.println(f.toGenericString());
				if (f.toGenericString().indexOf("transient") == -1) {
					if (f.toGenericString().indexOf("final") == -1) {
						orderColumns += (f.getName()) + ",";
					}
				}
			}
			break;
		}
//		System.out.println(orderColumns);
		return list;
	}

	public void setList(List<T> list) {

		this.list = list;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {

		this.isPage = isPage;
	}

}
