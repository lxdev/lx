package net.lx.common.file.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lx.common.Constants;
import net.lx.common.ConstantsIDMap;
import net.lx.common.ConstantsPeopleMap;
import net.lx.common.ConstantsPoliticalMap;
import net.lx.common.date.DateUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * excel导入
 * 
 * @param <T>
 */
public class ImportExcel<T> {
	Class<T> clazz;

	public ImportExcel(Class<T> clazz) {
		this.clazz = clazz;
	}

	public List<T> importExcel(File file, String[] message) {
		List<T> dist = new ArrayList<T>();
		int currentRow = 0;
		// 文件输入流
		if (file != null) {
			try {
				// 得到目标目标类的所有的字段列表
				Field[] fields = clazz.getDeclaredFields();
				// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
				Map<String, Method> fieldMap = new HashMap<String, Method>();
				// 循环读取所有字段
				for (Field field : fields) {
					// 得到单个字段上的Annotation
					ExcelAnnotation excelAnnotation = field
							.getAnnotation(ExcelAnnotation.class);
					// 如果标识了Annotationd
					if (excelAnnotation != null) {
						String fieldName = field.getName();
						// 构造设置了Annotation的字段的Setter方法
						String setMethodName = "set"
								+ fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1);
						// 构造调用的method
						Method setMethod = clazz.getMethod(setMethodName,
								new Class[] { field.getType() });
						// 将这个method以Annotaion的名字为key来存入
						fieldMap.put(excelAnnotation.exportName(), setMethod);
					}
				}

				FileInputStream fileInputStream = new FileInputStream(file);
				// POIFSFileSystem这是POIFS类库的主要类，它管理文件系统的整个生命周期。
				POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
				HSSFWorkbook wb = new HSSFWorkbook(fs);

				// 将标题的文字内容放入到一个map中
				Map<Integer, String> titleMap = new HashMap<Integer, String>();

				// for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				// 获取第一个Sheet
				HSSFSheet sheet = wb.getSheetAt(0);
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					currentRow = r + 1;
					HSSFRow row = sheet.getRow(r);
					if (row != null) {
						int cells = 45;
						// 判断整行单元格是否全为空，true为全为空
						if (!isAllCellNULL(cells, row)) {
							// 得到传入类的实例
							T tObject = clazz.newInstance();
							// 序号
							Method method = clazz.getMethod("setSerialNumber",
									new Class[] { String.class });
							method.invoke(tObject, ((row.getRowNum() + 1) + ""));
							for (int c = 0; c < cells; c++) {
								HSSFCell cell = row.getCell(c);
								if (cell != null) {
									// 获取列名
									if (r == 0) {
										titleMap.put(cell.getColumnIndex(),
												cell.getStringCellValue());
									} else {
										// 这里得到此列的对应的标题
										String titleString = titleMap.get(c);
										if (titleString != null) {
											titleString = titleString.trim();
										}
										// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
										if (fieldMap.containsKey(titleString)) {
											Method setMethod = fieldMap.get(titleString);
											// 得到setter方法的参数
											Type[] types = setMethod.getGenericParameterTypes();
											// 只要一个参数
											String xclass = String.valueOf(types[0]);
											
											// 判断参数类型
											if ("class java.lang.String".equals(xclass)) {
												// 设置所有的cell为文本形式
												cell.setCellType(HSSFCell.CELL_TYPE_STRING);
												if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
													setMethod.invoke(tObject, 
															String.valueOf(cell.getNumericCellValue())
																			.substring(
																					0, String.valueOf(
																							cell.getNumericCellValue()).lastIndexOf(".")));
												} else {
													String _value = cell.getStringCellValue()
															.equals("") ? ""
															: cell.getStringCellValue();//.replace("'", "''");
													_value = _value.trim();
													setMethod.invoke(tObject, _value);
												}
											} else if ("class java.util.Date"
													.equals(xclass)) {
												if (titleString.equals("入学时间")||titleString.equals("推送日期")||titleString.equals("日期")) {
													if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
														
														setMethod
														.invoke(tObject,cell.getDateCellValue());
													}else{
														if (cell.getStringCellValue() != null
																&& !"".equals(cell
																		.getStringCellValue())) {
															try {

																if (cell.getStringCellValue()
																		.trim()
																		.length() == 10) {
																	setMethod
																			.invoke(tObject,
																					DateUtil.StringToDate(
																							cell.getStringCellValue(),
																							"yyyy-MM-dd"));
																} else if (cell
																		.getStringCellValue()
																		.trim()
																		.length() == 8) {
																	setMethod
																			.invoke(tObject,
																					DateUtil.StringToDate(
																							cell.getStringCellValue(),
																							"yyyyMMdd"));

																}else{
																	
																}
															} catch (Exception e) {
																System.out
																		.println(cell
																		.getStringCellValue()+"日期错误");
																setMethod.invoke( tObject, null);
															}

														}
													}
													
												}
											} else if ("class java.lang.Boolean".equals(xclass)) {
												Boolean boolName = false;
												if ("是".equals(cell.getStringCellValue()) || "1".equals(cell.getStringCellValue())) {
													boolName = true;
												}
												setMethod.invoke(tObject, boolName);
											} else if ("class java.lang.Integer".equals(xclass) || "int".equals(xclass)) {
												// 设置所有的cell为文本形式
												//cell.setCellType(HSSFCell.CELL_TYPE_STRING);
												if (titleString.equals("学费（最低）")) {
													cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
													setMethod.invoke(tObject, (int)cell.getNumericCellValue() );
												}
												if (titleString.equals("学费（最高）")) {
													cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
													setMethod.invoke(tObject, (int)cell.getNumericCellValue());
												}
												if (titleString.equals("托福总分")) {
													cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
													setMethod.invoke(tObject, (int)cell.getNumericCellValue());
												}
												if (titleString.equals("GRE总分")) {
													cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
													setMethod.invoke(tObject, (int)cell.getNumericCellValue());
												}
												if (titleString.equals("GMAT总分")) {
													cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
													setMethod.invoke(tObject, (int)cell.getNumericCellValue());
												}
												cell.setCellType(HSSFCell.CELL_TYPE_STRING);
												if (titleString.equals("是否提供语言课程")) {
													if ("是".equals(cell.getStringCellValue()) || "1".equals(cell.getStringCellValue())) {
														setMethod.invoke(tObject, 1);
													}
													else
														setMethod.invoke(tObject, 0);
												}
												if (titleString.equals("专业要求")) {
													if ("是".equals(cell.getStringCellValue()) || "1".equals(cell.getStringCellValue())) {
														setMethod.invoke(tObject, 1);
													}
													else
														setMethod.invoke(tObject, 0);
												}
												if (titleString.equals("工作经验要求")) {
													if ("是".equals(cell.getStringCellValue()) || "1".equals(cell.getStringCellValue())) {
														setMethod.invoke(tObject, 1);
													}
													else
														setMethod.invoke(tObject, 0);
												}
												
												
											}else if("class java.lang.Float".equals(xclass) || "float".equals(xclass)) {
												cell.setCellType(HSSFCell.CELL_TYPE_STRING);
												
												if (titleString.equals("雅思总分")) {
													setMethod.invoke(tObject, Float.parseFloat(cell.getStringCellValue()));
												}
												if (titleString.equals("GPA要求")) {
													setMethod.invoke(tObject, Float.parseFloat(cell.getStringCellValue()));
												}
												
											} else if ("class java.lang.Long".equals(xclass)) {
												setMethod.invoke(tObject, new Long(cell.getStringCellValue()));
											} else {
												//
											}
										}

									}

								}
							}

							if (r != 0) {
								dist.add(tObject);
							}else {
								Object cc = tObject;
							}
						}
					}
				}
				// }
			} catch (Exception e) {
				message[0] = "【第" + currentRow + "行出错】：" + e.getMessage();
				//e.printStackTrace();
				dist.clear();
				return dist;
			}
		}
		return dist;
	}

	/**
	 * 判断整行单元格是否全为空
	 * 
	 * @param cells
	 * @param row
	 * @return false为不全为空,true全为空
	 */
	private boolean isAllCellNULL(int cells, HSSFRow row) {
		if (row == null) {
			return false;
		}
		for (int c = 0; c < cells; c++) {
			HSSFCell cell = row.getCell(c);
			if (cell != null) {
				// 设置所有的cell为文本形式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if (cell.getStringCellValue() != null
						&& !cell.getStringCellValue().trim().equals("")) {
					return false;
				}
			}

		}
		return true;
	}

	public static void main(String[] args) throws IOException, Exception {
	}
}
