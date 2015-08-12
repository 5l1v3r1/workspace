import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class TCP_Accept implements Runnable{
    // Port Number for TCP communication
    private final static int PORT_NUMBER = 1126;
    
    private boolean delstate = false;       // is deleting?
    private boolean state = false;          // read? register?
    
    private ServerSocket serversock = null; 
    private Socket sock = null;
    private BufferedReader reader = null;
    
    private String UID = null;
    
    private Hashtable<String, Person> hashtable = null; // key is UID, value is Person Object
    private Weit_Management manager = null;             // variable for handle mainframe
    
    public TCP_Accept(Weit_Management manager){
        this.manager = manager;
        
        // Read Hashtable
        File obj = new File("HashPhone.dat");
        if(obj.exists()){
            try {
                ObjectInputStream objin = new ObjectInputStream(new FileInputStream(obj));
                hashtable = (Hashtable<String, Person>)objin.readObject();
                objin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            hashtable = new Hashtable<String, Person>();
        }
    }
    
    public void update_hash(){
        File obj = new File("HashPhone.dat");
        if(obj.exists()){
            try {
                ObjectInputStream objin = new ObjectInputStream(new FileInputStream(obj));
                hashtable = (Hashtable<String, Person>)objin.readObject();
                objin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            hashtable = new Hashtable<String, Person>();
        }
    }
    
    public void State_Delete(){
        delstate = true;
    }
    public void State_Register(){
        state = true;
    }
    public boolean Current_State(){
        return state;
    }
    
    @Override
    public void run(){
        try{
            serversock = new ServerSocket(PORT_NUMBER);
            while(true){
                sock = serversock.accept();
                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                UID = reader.readLine();
                System.out.println(UID);
                Reading();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void Reading(){
        try{
            if(delstate){
                // If it's delete state, read uid and delete information
                if(hashtable.get(UID) == null){
                    JOptionPane.showMessageDialog(null, "이 카드는 등록되지 않았습니다.", "삭제 실패", JOptionPane.ERROR_MESSAGE);
                    manager.setValue("", "", "", "등록된 수강생이 없습니다.");
                    delstate = false;
                    return;
                }
                Person person = hashtable.get(UID);
                
                boolean issuc;
                if(person.IsInstructor)
                    issuc = ExcelWriter.Delete("We_IT.xlsx", "강사", person.Phone);
                else
                    issuc = ExcelWriter.Delete("We_IT.xlsx", "수강생", person.Phone);
                
                if(!issuc){
                    return;
                }
                hashtable.remove(UID);
                
                ObjectOutputStream objout  = new ObjectOutputStream(new FileOutputStream("HashPhone.dat"));
                objout.writeObject(hashtable);
                objout.close();

                JOptionPane.showMessageDialog(null, "삭제에 성공하였습니다.", "삭제 성공", JOptionPane.INFORMATION_MESSAGE);
                delstate = false;
                return;
            }
            if(state){
                System.out.println("Read UID, Regiter Start...");
                state = false;
                return;
            }
            if(hashtable.get(UID) == null)
                manager.setValue("", "", "", "등록된 수강생이 없습니다.");
            else{
                Person person = hashtable.get(UID);
                String check = "";
                if(!person.IsInstructor){
                    person.Check();
                    if(person.State){
                        check = "정상 등원처리 되었습니다.";
                    }
                    else{
                        check = "정상 하원처리 되었습니다.";
                    }
                    manager.setValue(person.Name, person.Subject, person.ClassName, check);
                }
                else{
                    person.Check();
                    if(!person.State){
                        check = person.Name + "님 정상 출근처리 되었습니다.";
                    }
                    else{
                        check = person.Name + "님 정상 퇴근처리 되었습니다.";
                    }
                    manager.setValue("", "", "", check);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean Student_Register(Person person){
        if(hashtable.get(UID)!=null){
            JOptionPane.showMessageDialog(null, "이 카드는 이미 사용 중입니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        hashtable.put(UID, person);
        person.uid = UID;
        try{
            ArrayList<Object> dataarray = new ArrayList<Object>();
            dataarray.add(person.Name);
            dataarray.add(person.Subject);
            dataarray.add(person.ClassName);
            dataarray.add(person.Phone);
            dataarray.add(person.Parent);
            
            int excelrow = ExcelWriter.Write("We_IT.xlsx", "수강생", dataarray);
            if(excelrow != -1)
                person.excelrow = excelrow;
            else
                return false;
            
            ObjectOutputStream objout  = new ObjectOutputStream(new FileOutputStream("HashPhone.dat"));
            objout.writeObject(hashtable);
            objout.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Instructor_Register(Person person){
        if(hashtable.get(UID)!=null){
            JOptionPane.showMessageDialog(null, "이 카드는 이미 사용 중입니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        hashtable.put(UID, person);
        person.uid = UID;
        try{
            ArrayList<Object> dataarray = new ArrayList<Object>();
            dataarray.add(person.Name);
            dataarray.add(person.ClassName);
            dataarray.add(person.Phone);

            int excelrow = ExcelWriter.Write("We_IT.xlsx", "강사", dataarray);
            if(excelrow != -1)
                person.excelrow = excelrow;
            else
                return false;
            
            ObjectOutputStream objout  = new ObjectOutputStream(new FileOutputStream("HashPhone.dat"));
            objout.writeObject(hashtable);
            objout.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
