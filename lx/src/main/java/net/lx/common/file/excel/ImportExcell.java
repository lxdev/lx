package net.lx.common.file.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * 导入excel
 * 
 */
public class ImportExcell {
	private static final Log log = LogFactory.getLog(ImportExcell.class);

	/**
	 * 
	 * readExcel方法-读取excel，行为list，列为Map.
	 * 
	 * 
	 * @param file
	 *            excel文件
	 * @return excel表数据集合-行为list，列为Map
	 */
	public List<Map<String, String>> readExcel(File file) {
		log.info("读取excel开始...");
		List<Map<String, String>> dataset = new ArrayList<Map<String, String>>();

		try {
			// 将传入的File构造为FileInputStream;
			FileInputStream inputStream = new FileInputStream(file);
			// 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(inputStream);
			// 得到第一页
			HSSFSheet sheet = book.getSheetAt(0);
			// 得到第一面的所有行
			Iterator<Row> rowIterator = sheet.rowIterator();

			// 得到第一行，也就是标题行
			@SuppressWarnings("unused")
			Row titleRow = rowIterator.next();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Map<String, String> map = this.creatObjectByRow(row);
				dataset.add(map);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		log.info("读取excel结束...");
		return dataset;
	}

	/**
	 * <p>
	 * creatObjectByRow方法-将每行的数据装载Map中.
	 * </p>
	 * <p>
	 * 创建人 IT山人 创建时间 2011-4-21 - 下午09:48:17
	 * </p>
	 * 
	 * @param row
	 * @return
	 */
	private Map<String, String> creatObjectByRow(Row row) {
		// 行的所有列
		Iterator<Cell> cellBody = row.cellIterator();
		// 遍历一行的列
		int col = 1;
		Map<String, String> map = new HashMap<String, String>();
		while (cellBody.hasNext()) {
			String field = String.valueOf(col++);
			Cell cell = cellBody.next();
			if (cell != null) {
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING: // 字符
					map.put(field, StringUtils.trim(cell.getStringCellValue()));
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: // 布尔
					map.put(field, StringUtils.trim(cell.getStringCellValue()));
					break;
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// 是否为日期格式
						map.put(field, String.valueOf(cell.getDateCellValue()));
					} else {
						Double cellValue_dob = cell.getNumericCellValue();// 读取cell内数据
						if (String.valueOf(cellValue_dob).length() > 11) { // 如果读取到的是手机号码,需要匹配数字格式
							DecimalFormat format = (DecimalFormat) NumberFormat
									.getInstance();
							// format.applyPattern("00000000000");
							map.put(field, format.format(cellValue_dob));
						} else { // 如果读取到的是比较短的数字，则去掉尾数（.0）后显示
							map.put(field,
									cellValue_dob.toString()
											.substring(
													0,
													cellValue_dob.toString()
															.length() - 2));
						}
					}
					break;
				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					map.put(field, String.valueOf(cell.getNumericCellValue()));
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 空
					map.put(field, StringUtils.trim(cell.getStringCellValue()));
					break;
				case HSSFCell.CELL_TYPE_ERROR: // 异常
					map.put(field, StringUtils.trim(cell.getStringCellValue()));
					break;
				default:
					map.put(field, StringUtils.trim(cell.getStringCellValue()));
					break;
				}
			}
		}
		return map;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImportExcell inport = new ImportExcell();
		File file = new File("/Users/[]/Desktop/demo.xls");
		List<Map<String, String>> mapList = inport.readExcel(file);
		for (Map<String, String> map : mapList) {
			 Iterator<Entry<String, String>> iterator =
			 map.entrySet().iterator();
			 while (iterator.hasNext()) {
			 Map.Entry<String, String> entry = iterator.next();
			 String key = entry.getKey();
			 String value = entry.getValue();
			 System.out.println("key:" + key + "\tvalue:" + value);
			 }
//			 TestVo t = new TestVo();
//			 t.setName(map.get("1"));
//			 System.out.println(t.getName());
		}
	}
}
