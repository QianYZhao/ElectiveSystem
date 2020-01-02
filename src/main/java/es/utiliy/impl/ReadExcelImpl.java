package es.utiliy.impl;

import es.utiliy.ReadExcel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelImpl implements ReadExcel {
//    public static void main(String[] args) {
//        List<Map<String, String>> list= Read("./data/grade.xlsx");
//
//    }
    public List<Map<String, String>> Read(String filePath) {
        try {
            FileInputStream inputStream =new FileInputStream(new File(filePath));
            XSSFWorkbook xssfWorkbook =new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            List<Map<String, String>> list =new ArrayList<>();
            XSSFRow firstLine = xssfSheet.getRow(0);//第一行
            List<String> title= new ArrayList<>();
            for (int i= firstLine.getFirstCellNum();i<firstLine.getLastCellNum();i++){
                XSSFCell xssfCell = firstLine.getCell(i);
                title.add(xssfCell.getStringCellValue());
            }
            for(int rowNum=1;rowNum<=xssfSheet.getLastRowNum();rowNum++){
                XSSFRow row = xssfSheet.getRow(rowNum);
                Map<String ,String> map= new HashMap<>();
                for(int colIx = row.getFirstCellNum(); colIx < row.getLastCellNum(); colIx++){
<<<<<<< HEAD

                    XSSFCell cell = row.getCell(colIx);
                    cell.setCellType(CellType.STRING);
                    String value= cell.getStringCellValue();
                    map.put(title.get(colIx),value);
=======
                    XSSFCell cell = row.getCell(colIx);
                    String value = "";
                    cell.setCellType(CellType.STRING);
//                    if(cell.getCellType()== CellType.NUMERIC){
//                        Double x= cell.getNumericCellValue();
//                        value = String.valueOf(value);
//                    }
//                    else {
                    value= cell.getStringCellValue();
//                    }
                    String key = title.get(colIx);
//                    System.out.println(key + " "+value);
                    map.put(key,value);
>>>>>>> 23d3492c59694a68ce7f548e32237ce157f76d85
                }
                list.add(map);
            }
            return list;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
