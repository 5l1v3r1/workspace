import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
    public static String dir = "../excel/";
    public static boolean Delete(String workbook, String sheet, String phone){
        try{
            workbook += dir;
            XSSFWorkbook wb = new XSSFWorkbook(workbook);
            if(wb==null)
                return false;
            
            System.out.println("workbook : " + workbook + " is opened...");
            
            XSSFSheet sh = wb.getSheet(sheet);
            if(sh==null)
                return false;
            
            System.out.println("sheet : " + sheet + " is opened...");

            if(sheet.equals("강사")){
                for(int i=0; i<=sh.getLastRowNum(); i++){
                    Row row = sh.getRow(i);
                    if(row.getCell(2).getStringCellValue().equals(phone))
                        sh.removeRow(row);
                }
            }
            else if(sheet.equals("수강생")){
                for(int i=0; i<=sh.getLastRowNum(); i++){
                    Row row = sh.getRow(i);
                    System.out.println(row.getCell(3).getStringCellValue());
                    if(row.getCell(3).getStringCellValue().equals(phone))
                        sh.removeRow(row);
                }
            }
            
            wb.write(new FileOutputStream("temp.xlsx"));
            wb.close();
            
            File origin = new File(workbook);
            origin.delete();
            
            File newfile = new File("temp.xlsx");
            newfile.renameTo(new File("We_IT.xlsx"));
            newfile = new File("temp.xlsx");
            if(newfile.exists())
                newfile.delete();
            
            System.out.println("success writing excel file!");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static int Write(String workbook, String sheet, ArrayList<Object> dataarray){
        int rowNumber;
        try{
            workbook += dir;
            XSSFWorkbook wb = new XSSFWorkbook(workbook);
            if(wb==null)
                return -1;
            
            System.out.println("workbook : " + workbook + " is opened...");
            
            XSSFSheet sh = wb.getSheet(sheet);
            if(sh==null)
                return -1;
            
            System.out.println("sheet : " + sheet + " is opened...");
            
            Row row;
            int i;
            
            // Caculate RowIndex
            for(i=0; ; i++){
                row = sh.getRow(i);
                if(row == null) break;
                if(row.getCell(0)==null) break;
                if(row.getCell(0).equals("")) break;
            }
            rowNumber = i;
            row = sh.createRow(rowNumber);
            
            // Insert Data
            Cell cell;
            i=0;
            for(Object data : dataarray){
                cell = row.createCell(i++);
                
                XSSFCellStyle cellStyle = wb.createCellStyle();        
                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
                cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
                cellStyle.setBorderTop(CellStyle.BORDER_THIN);
                cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
                cellStyle.setBorderRight(CellStyle.BORDER_THIN);
                
                if(data instanceof Date){
                    cell.setCellValue((Date)data);
                    if(i==1)
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("mm/dd"));
                    else
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("hh:mm"));
                }
                else if(data instanceof Calendar){
                    cell.setCellValue((Calendar)data);
                    if(i==1)
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("mm/dd"));
                    else
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("hh:mm"));
                }
                else if(data instanceof String)
                    cell.setCellValue((String)data);
                
                else if(data instanceof Boolean)
                    cell.setCellValue((Boolean)data);
                
                else if(data instanceof Double)
                    cell.setCellValue((Double)data);
                
                else if(data instanceof RichTextString)
                    cell.setCellValue((RichTextString)data);
                
                cell.setCellStyle(cellStyle);
            }
            
            wb.write(new FileOutputStream("temp.xlsx"));
            wb.close();
            
            File origin = new File(workbook);
            origin.delete();
            
            File newfile = new File("temp.xlsx");
            newfile.renameTo(new File("We_IT.xlsx"));
            newfile = new File("temp.xlsx");
            if(newfile.exists())
                newfile.delete();
            
            System.out.println("success writing excel file!");
            return rowNumber;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
