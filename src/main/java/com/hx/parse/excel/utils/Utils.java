package com.hx.parse.excel.utils;

import com.hx.parse.excel.model.City;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jxlgcmh
 * @date 2019-10-20 18:11
 */
public class Utils {
    public static List<City> excelToMysql(String filePath) {
        Workbook wb;
        Sheet sheet;
        Row row;
        List<City> list = null;
        String cellData;
        // 需要解析的Excel文件
        //String filePath = "D:\\Chrome\\weather_areaid_data.xlsx";
        String[] columns = {"AREAID", "NAMEEN", "NAMECN", "DISTRICTEN", "DISTRICTCN", "PROVEN", "PROVCN", "NATIONEN", "NATIONCN"};
        wb = readExcel(filePath);
        if (wb != null) {
            // 用来存放表中数据
            list = new ArrayList<>();
            // 获取第一个sheet
            sheet = wb.getSheetAt(1);
            // 获取最大行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            // 获取第一行
            row = sheet.getRow(0);
            // 获取最大列数
            int column = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rowNum; i++) {
                Map<String, String> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < column; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                City city = new City();
                city.setAreaid(map.get(columns[0]));
                city.setNameen(map.get(columns[1]));
                city.setNamecn(map.get(columns[2]));
                city.setDistricten(map.get(columns[3]));
                city.setDistrictcn(map.get(columns[4]));
                city.setProven(map.get(columns[5]));
                city.setProvcn(map.get(columns[6]));
                city.setNationen(map.get(columns[7]));
                city.setNationcn(map.get(columns[8]));
                list.add(city);
            }
        }
        return list;
    }

    /**
     * 读取Excel
     *
     * @param filePath
     * @return
     */
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        String extendXls = ".xls";
        String extendXlsx = ".xlsx";
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        try (InputStream is = new FileInputStream(filePath)) {
            if (extendXls.equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (extendXlsx.equals(extString)) {
                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 格式化数据
     *
     * @param cell
     * @return
     */
    private static Object getCellFormatValue(Cell cell) {
        Object cellValue ;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    /*
                    * 更正数据的显示问题
                    *
                    * */
                    double d = cell.getNumericCellValue();
                    NumberFormat nf = NumberFormat.getInstance();
                    String s = nf.format(d);
                    if (s.contains(",")) {
                        s = s.replace(",", "");
                    }
                    cellValue = s;
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}
