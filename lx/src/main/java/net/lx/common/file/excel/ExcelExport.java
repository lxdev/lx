package net.lx.common.file.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * excel导出
 * 
 * @param <T>
 */
public class ExcelExport<T> {
	// 格式化日期
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * exportExcel方法-poi Excel导出.
	 * 
	 * @param title
	 *            工作簿名称
	 * @param dataset
	 *            导出的数据集
	 * @param out
	 *            输出流
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, Collection<T> dataset,
			OutputStream out) {
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			Iterator<T> iterator = dataset.iterator();
			if (dataset == null || !iterator.hasNext() || title == null
					|| out == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T tObject = iterator.next();
			Class<T> clazz = (Class<T>) tObject.getClass();

			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为20个字节
			sheet.setDefaultColumnWidth(20);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置标题样式
			style = ExcelStyle.setHeadStyle(workbook, style);
			// 得到所有字段
			Field filed[] = tObject.getClass().getDeclaredFields();

			// 标题
			List<String> exportfieldtile = new ArrayList<String>();
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			// 遍历整个filed
			for (int i = 0; i < filed.length; i++) {
				Field field = filed[i];
				ExcelAnnotation excelAnnotation = field
						.getAnnotation(ExcelAnnotation.class);
				// 如果设置了annottion
				if (excelAnnotation != null) {
					String exprot = excelAnnotation.exportName();
					// 添加到标题
					exportfieldtile.add(exprot);
					// 添加到需要导出的字段的方法
					String fieldname = field.getName();
					String getMethodName = "get"
							+ fieldname.substring(0, 1).toUpperCase()
							+ fieldname.substring(1);
					Method getMethod = clazz.getMethod(getMethodName,
							new Class[] {});
					methodObj.add(getMethod);
				}
			}

			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < exportfieldtile.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i));
				cell.setCellValue(text);
			}

			// 循环整个集合
			int index = 0;
			iterator = dataset.iterator();
			while (iterator.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				T t = (T) iterator.next();
				for (int k = 0; k < methodObj.size(); k++) {
					HSSFCell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = getValue(value);
					cell.setCellValue(textValue);
				}
			}
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * exportExcel方法-poi Excel导出.
	 * 
	 * @param title
	 *            工作簿名称
	 * @param dataset
	 *            导出的数据集
	 * @param out
	 *            输出流
	 * @param startRow
	 *            起始行（用于单元格填充颜色）
	 * @param endRow
	 *            结束行（用于单元格填充颜色）
	 * @param startCol
	 *            起始列（用于单元格填充颜色）
	 * @param endCol
	 *            结束列（用于单元格填充颜色）
	 * @param color
	 *            颜色（用于单元格填充颜色） 如：HSSFColor.BLUE.index
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, Collection<T> dataset,
			OutputStream out,int startRow,int endRow,int startCol,int endCol,short color) {
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			Iterator<T> iterator = dataset.iterator();
			if (dataset == null || !iterator.hasNext() || title == null
					|| out == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T tObject = iterator.next();
			Class<T> clazz = (Class<T>) tObject.getClass();

			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			// 设置表格默认列宽度为20个字节
			sheet.setDefaultColumnWidth(20);
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置标题样式
			style = ExcelStyle.setHeadStyle(workbook, style);
			// 得到所有字段
			Field filed[] = tObject.getClass().getDeclaredFields();

			// 标题
			List<String> exportfieldtile = new ArrayList<String>();
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			// 遍历整个filed
			for (int i = 0; i < filed.length; i++) {
				Field field = filed[i];
				ExcelAnnotation excelAnnotation = field
						.getAnnotation(ExcelAnnotation.class);
				// 如果设置了annottion
				if (excelAnnotation != null) {
					String exprot = excelAnnotation.exportName();
					// 添加到标题
					exportfieldtile.add(exprot);
					// 添加到需要导出的字段的方法
					String fieldname = field.getName();
					String getMethodName = "get"
							+ fieldname.substring(0, 1).toUpperCase()
							+ fieldname.substring(1);
					Method getMethod = clazz.getMethod(getMethodName,
							new Class[] {});
					methodObj.add(getMethod);
				}
			}

			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < exportfieldtile.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(
						exportfieldtile.get(i));
				cell.setCellValue(text);
			}

			// 循环整个集合
			int index = 0;
			iterator = dataset.iterator();
			while (iterator.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				T t = (T) iterator.next();
				int index2 = 1;
				for (int k = 0; k < methodObj.size(); k++) {
					HSSFCell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = getValue(value);
					cell.setCellValue(textValue);
					// 规定行和列内附加颜色样式
					if(index>=startRow && index<=endRow && index2>=startCol && index2<=endCol){
						HSSFCellStyle style2 = workbook.createCellStyle();
						style2.setFillForegroundColor(color);
						style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style2.setWrapText(true);
						cell.setCellStyle(style2);
					}
					index2++;
				}
			}
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue(Object value) {
		String textValue = "";
		if (value == null) {
			return textValue;
		}
		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "是";
			if (!bValue) {
				textValue = "否";
			}
		} else if (value instanceof Date) {
			Date date = (Date) value;
			textValue = sdf.format(date);

		} else {
			textValue = value.toString();
		}
		return textValue;
	}

	public static void main(String[] args) throws IOException {
//		 OutputStream out = new FileOutputStream("D:\\testOne1.xls");
//		 ExcelExport<TestVo> ex = new ExcelExport<TestVo>();
//		 ImportExcel<TestVo> test = new ImportExcel<TestVo>(TestVo.class);
//		 File file = new File("D:\\testOne.xls");
//		 List<TestVo> results = (List<TestVo>) test.importExcel(file);
//		
//		 ex.exportExcel("测试", results, out);
//		 out.close();
	}
}
