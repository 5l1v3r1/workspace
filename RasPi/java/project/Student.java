import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Student {
	public String Name;
	public String Subject;
	public String ClassName;
	public String Instructor;
	public String Phone;
	public String Parent;
	public boolean State;
	public Date Start;
	public Date End;
	
	// Initialize Student Object.
	public Student(String Name, String Subject, String ClassName, String Parent){
		this.Name = Name;
		this.Subject = Subject;
		this.ClassName = ClassName;
		this.Parent = Parent;
		this.State = false;
	}
	
	// Check Start or End
	public void Check(){
		if(!State){
			Start = new Date();
			return;
		}
		End = new Date();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("We_IT.xlsx"));
			if(wb != null){
				XSSFSheet sheet = wb.getSheet("수강생출결");
				if(sheet != null){
					int rownum = sheet.getLastRowNum()+1;
					Row row = sheet.createRow(rownum);
					XSSFCellStyle cellStyle = wb.createCellStyle();		
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
					cellStyle.setBorderTop(CellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
					cellStyle.setBorderRight(CellStyle.BORDER_THIN);
					
					Cell cellname = row.createCell(0);
					Cell cellsubject = row.createCell(1);
					Cell cellclass = row.createCell(2);
					Cell cellstart = row.createCell(3);
					Cell cellend = row.createCell(4);
					Cell cellnote = row.createCell(5);
					
					Thread.sleep(10000);
					
					cellname.setCellValue(this.Name);
					cellname.setCellStyle(cellStyle);
					
					cellsubject.setCellValue(this.Subject);
					cellsubject.setCellStyle(cellStyle);
					
					cellclass.setCellValue(this.ClassName);
					cellclass.setCellStyle(cellStyle);
					
					cellnote.setCellStyle(cellStyle);
					
					cellStyle.setDataFormat(wb.createDataFormat().getFormat("hh:mm"));

					cellstart.setCellValue(Start);
					cellstart.setCellStyle(cellStyle);
					
					cellend.setCellValue(End);
					cellend.setCellStyle(cellStyle);
					
					FileOutputStream out = new FileOutputStream("temp.xlsx");
					wb.write(out);
					out.close();
					File deletefile = new File("temp.xlsx");
					deletefile.delete();
				}
				wb.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
