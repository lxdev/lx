package net.lx.model.crm;

import java.util.ArrayList;
import java.util.List;

public class ImportResult<T> {

	private int successCount;// 导入成功数
	private int errorCount;// 导入失败数
	private List<T> successList = new ArrayList<T>();// 成功列表
	private List<T> errorList = new ArrayList<T>();// 失败实体

	public ImportResult(){
		
	}
	
	public ImportResult(int successCount, int errorCount, List<T> successList,
			List<T> errorList) {
		super();
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.successList = successList;
		this.errorList = errorList;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public List<T> getSuccessList() {
		return successList;
	}

	public List<T> getErrorList() {
		return errorList;
	}

	public void addSuccessObj(T t) {
		successList.add(t);
	}

	public void addErrorObj(T t) {
		errorList.add(t);
	}

}
