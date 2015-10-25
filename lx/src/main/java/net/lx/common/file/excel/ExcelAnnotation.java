package net.lx.common.file.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel标签导入导出注解
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
	/**
	 * excel导出时标题显示的名字，如果没有设置Annotation属性，将不会被导出和导入
	 * 
	 * @return
	 */
	public String exportName();
}
