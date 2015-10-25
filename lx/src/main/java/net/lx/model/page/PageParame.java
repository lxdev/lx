package net.lx.model.page;

/**
 * 分页参数
 * 
 */
public class PageParame {
	// 排序
	private String order = "id";

	private String sort = "asc"; // asc或者desc

	// where 子句后面表达式，表达式 如: name=? and age>?
	private String hqlConditionExpression;
	// 表达式参数：如{"张三",18}
	private Object[] values;
	// 实际页码
	private int currentPageIndex;
	// 每页记录数
	private int pageSize;
	// 是否排序
	private boolean isPage = true;

	public PageParame() {
		this.isPage = false;
	}

	public PageParame(String order) {
		this.isPage = false;
		this.order = order;
	}

	public PageParame(String order, String sort) {
		this.isPage = false;
		this.order = order;
		this.sort = sort;
	}

	public PageParame(PageResult pr) {
		this.currentPageIndex = pr.getCurrentPageIndex();
		this.pageSize = pr.getPageSize();
		this.order = pr.getOrder();
		this.sort = pr.getSort();
		// 分页或者不分页
		this.isPage = pr.isPage();
	}

	public String getOrder() {
		return order;
	}

	// public void setOrder(String order) {
	// this.order = order;
	// }

	public String getHqlConditionExpression() {
		return hqlConditionExpression;
	}

	public void setHqlConditionExpression(String hqlConditionExpression) {
		this.hqlConditionExpression = hqlConditionExpression;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	// public void setCurrentPageIndex(int currentPageIndex) {
	// this.currentPageIndex = currentPageIndex;
	// }

	public int getPageSize() {
		return pageSize;
	}

	// public void setPageSize(int pageSize) {
	// this.pageSize = pageSize;
	// }

	public String getSort() {
		return sort;
	}

	// public void setSort(String sort) {
	// this.sort = sort;
	// }

	public boolean isPage() {
		return isPage;
	}
	//
	// public void setPage(boolean isPage) {
	// this.isPage = isPage;
	// }

}
