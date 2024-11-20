package utils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public static List<Map<String,String>> read(String path, String sheetName) {
        List<Map<String, String>> excelData = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(utils.Constants.EXCEL_FILE_PATH);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheet("Sheet1");
            Row header = sheet.getRow(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Map <String, String> map = new LinkedHashMap<>();
                Row row = sheet.getRow(i);

                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {

                    String key = header.getCell(j).toString();
                    String value = row.getCell(j).toString();
                    map.put(key,value);
                }
                excelData.add(map);
            }

        } catch (FileNotFoundException nf) {
            nf.printStackTrace();
        } catch (IOException io){
            io.printStackTrace();
        }

        for (int i = 0; i < excelData.size() ; i++) {
            System.out.println(excelData.get(i));
        }
        return excelData;
    }
    public static List<Map<String, String>> read (String sheetName) throws IOException{
        return read (utils.Constants.EXCEL_FILE_PATH, sheetName);
    }
    public static List<Map<String, String>> read() throws IOException{
        return read (utils.Constants.EXCEL_FILE_PATH, "Sheet1");
    }
}
