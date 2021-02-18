package util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelWSheet;
    private static XSSFCell cell;

    public static Object[][] getTableArry(String filepath, String sheetname, int countCol) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream excelFile = new FileInputStream(filepath);
            ExcelWBook = new XSSFWorkbook(excelFile);
            int sheets = ExcelWBook.getNumberOfSheets();
            System.out.println(sheets);
            ExcelWSheet = ExcelWBook.getSheet(sheetname);
            ExcelWSheet = ExcelWBook.getSheetAt(0);
            int startRow = 1;
            int startCol = 0;

            int ci, cj;

            int totalRows = ExcelWSheet.getLastRowNum();
            //TODO: AutoRead column count
            int totalCols = countCol;
            tabArray = new String[totalRows][totalCols];
            ci =0;
            for (int i=startRow; i<=totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol;j<totalCols; j++, cj++) {
                    tabArray[ci][cj]=getCellData(i,j);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tabArray;
    }

    public static String getCellData(int rowNum, int colNum) throws Exception {
        try {
            cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
            int dataType = cell.getCellType();
            if (dataType == 3) {
                return "";
            } else if (dataType == 0) {
                double v = cell.getNumericCellValue();
                String celldata = String.format("%.0f", v);
                System.out.println(celldata);
                return celldata;
            } else {
                System.out.println(cell.getCellType());
                String celldata = cell.getStringCellValue();
                System.out.println(celldata);
                return celldata;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
