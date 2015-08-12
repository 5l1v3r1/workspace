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
                    JOptionPane.showMessageDialog(null, "�� ī��� ��ϵ��� �ʾҽ��ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
                    manager.setValue("", "", "", "��ϵ� �������� �����ϴ�.");
                    delstate = false;
                    return;
                }
                Person person = hashtable.get(UID);
                
                boolean issuc;
                if(person.IsInstructor)
                    issuc = ExcelWriter.Delete("We_IT.xlsx", "����", person.Phone);
                else
                    issuc = ExcelWriter.Delete("We_IT.xlsx", "������", person.Phone);
                
                if(!issuc){
                    return;
                }
                hashtable.remove(UID);
                
                ObjectOutputStream objout  = new ObjectOutputStream(new FileOutputStream("HashPhone.dat"));
                objout.writeObject(hashtable);
                objout.close();

                JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.", "���� ����", JOptionPane.INFORMATION_MESSAGE);
                delstate = false;
                return;
            }
            if(state){
                System.out.println("Read UID, Regiter Start...");
                state = false;
                return;
            }
            if(hashtable.get(UID) == null)
                manager.setValue("", "", "", "��ϵ� �������� �����ϴ�.");
            else{
                Person person = hashtable.get(UID);
                String check = "";
                if(!person.IsInstructor){
                    person.Check();
                    if(person.State){
                        check = "���� ���ó�� �Ǿ����ϴ�.";
                    }
                    else{
                        check = "���� �Ͽ�ó�� �Ǿ����ϴ�.";
                    }
                    manager.setValue(person.Name, person.Subject, person.ClassName, check);
                }
                else{
                    person.Check();
                    if(!person.State){
                        check = person.Name + "�� ���� ���ó�� �Ǿ����ϴ�.";
                    }
                    else{
                        check = person.Name + "�� ���� ���ó�� �Ǿ����ϴ�.";
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
            JOptionPane.showMessageDialog(null, "�� ī��� �̹� ��� ���Դϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
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
            
            int excelrow = ExcelWriter.Write("We_IT.xlsx", "������", dataarray);
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
            JOptionPane.showMessageDialog(null, "�� ī��� �̹� ��� ���Դϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        hashtable.put(UID, person);
        person.uid = UID;
        try{
            ArrayList<Object> dataarray = new ArrayList<Object>();
            dataarray.add(person.Name);
            dataarray.add(person.ClassName);
            dataarray.add(person.Phone);

            int excelrow = ExcelWriter.Write("We_IT.xlsx", "����", dataarray);
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