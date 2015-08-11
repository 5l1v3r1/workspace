import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TCP_Accept implements Runnable{
	private boolean state = false;
	private ServerSocket serversock = null;
	private Socket sock = null;
	private BufferedReader reader = null;
	private String UID = null;
	private ArrayList<Student> students = null;
	private Hashtable<String, String> hashtable = null;
	private Weit_Management manager = null;
	
	public TCP_Accept(Weit_Management manager){
		this.manager = manager;
		File obj = new File("Student.dat");
		if(obj.exists()){
			try {
				ObjectInputStream objin = new ObjectInputStream(new FileInputStream(obj));
				students = (ArrayList<Student>)objin.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				obj.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		obj = new File("HashPhone.dat");
		if(obj.exists()){
			try {
				ObjectInputStream objin = new ObjectInputStream(new FileInputStream(obj));
				hashtable = (Hashtable<String, String>)objin.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				obj.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void State_Register(){
		state = true;
	}
	public void State_Read(){
		state = false;
	}
	public boolean Current_State(){
		return state;
	}
	
	@Override
	public void run(){
		try{
			serversock = new ServerSocket(1126);
			while(true){
				sock = serversock.accept();
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				UID = reader.readLine();
				if(state)
					state = false;
				else{
					String key = hashtable.get(UID);
					if(key == null)
						manager.setValue("", "", "", "등록된 수강생이 없습니다.");
					else{
						for(Student stu : students){
							if(stu.Phone.equals(key)){
								String check;
								if(!stu.State){
									check = "정상 등원처리 되었습니다.";
									stu.State = true;
								}
								else{
									check = "정상 하원처리 되었습니다.";
									stu.State = false;
								}
								manager.setValue(stu.Name, stu.Subject, stu.ClassName, check);
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Register(Student stu){
		hashtable.put(UID, stu.Phone);
		students.add(stu);
		try{
			XSSFWorkbook wb = new XSSFWorkbook("We_IT.xlsx");
			if(wb == null)
				return;
			XSSFSheet sh = wb.getSheet("수강생");
			if(sh==null)
				return;
			int rownumber = sh.getLastRowNum()+1;
			Row row = sh.createRow(rownumber);

			XSSFCellStyle cellStyle = wb.createCellStyle();		
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			
			Cell cellname = row.createCell(0);
			cellname.setCellStyle(cellStyle);
			cellname.setCellValue(stu.Name);
			
			Cell cellsub = row.createCell(1);
			cellsub.setCellStyle(cellStyle);
			cellsub.setCellValue(stu.Subject);
			
			Cell cellclass = row.createCell(2);
			cellclass.setCellStyle(cellStyle);
			cellclass.setCellValue(stu.ClassName);
			
			Cell cellphone = row.createCell(3);
			cellphone.setCellStyle(cellStyle);
			cellphone.setCellValue(stu.Phone);
			
			Cell cellparent = row.createCell(4);
			cellparent.setCellStyle(cellStyle);
			cellparent.setCellValue(stu.Parent);
			
			FileOutputStream out = new FileOutputStream("temp.xlsx");
			wb.write(out);
			out.close();
			File deletefile = new File("temp.xlsx");
			deletefile.delete();
			
			wb.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}