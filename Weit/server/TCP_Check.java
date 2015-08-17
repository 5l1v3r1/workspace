import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TCP_Check extends Thread{
    private final int PORT = 1126;
    private final String DBNAME = "ACADEMY";
    private final String USER = "admin";
    private final String PASSWORD = "academy";

    // variables for database
    Mysql_Query db = null;
    ResultSet rs = null;
    ArrayList<Object> conCol = null; 
    ArrayList<Object> conItems = null;
    
    // variables for TCP
    ServerSocket server = null;
    Socket sock = null;
    BufferedReader reader = null;
    
    // variables for process
    String uid = null;
    SimpleDateFormat fday = null;
    SimpleDateFormat ftime = null;
    
    @Override
    public void run(){
        db = new Mysql_Query(DBNAME, USER, PASSWORD);
        conCol = new ArrayList<Object>();
        conItems = new ArrayList<Object>();
        fday = new SimpleDateFormat("yyyy-MM-dd");
        ftime = new SimpleDateFormat("HH:mm:ss");
        
        try {
            server = new ServerSocket(PORT);    //open server socket
        } catch (IOException e) {
            System.out.println("Error : ServerSocker Error");
            e.printStackTrace();
        }
        
        while(true){
            try{
                sock = server.accept();
                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                uid = reader.readLine();
                System.out.println(uid);
            }catch(IOException e){
                System.out.println("Error : BufferedReader Error");
                e.printStackTrace();
            }
            
            conCol.clear();
            conItems.clear();
            conCol.add("UID");
            conItems.add(uid);
            rs = db.Select("UID", conCol, conItems);

            try {
                if(rs == null)
                    continue;
                if(!rs.next())
                    continue;
                if(rs.getBoolean("ISINSTRUCTOR") == false)
                    checkStudent();
                else
                    checkInstructor();
            }catch(SQLException e){
                System.out.println("Error : SQL Error");
                e.printStackTrace();
            }
        }
    }

    public void checkStudent() throws SQLException{
        
        String name = null;
        String inst = null;
        
        ArrayList<Object> columns = new ArrayList<Object>();
        ArrayList<Object> items = new ArrayList<Object>();
        
        
        rs = db.Select("STUDENT", conCol, conItems);
        if(rs == null)
            return;
        if(!rs.next())
            return;
        name = rs.getString("NAME");
        inst = rs.getString("INST");
        
        conCol.clear();
        conItems.clear();
        conCol.add("NAME");
        conItems.add(name);
        conItems.add(name);
        conItems.add(fday.format(new java.util.Date()));
        rs = db.Select("STULOG", conCol, conItems);
        
        if(rs == null)
            return;
        if(!rs.next()){
            items.add(fday.format(new java.util.Date()));
            items.add(name);
            items.add(inst);
            items.add(ftime.format(new java.util.Date()));
            items.add(null);
            db.Insert("STULOG", items);
            return;
        }
        columns.add("CHECKOUT");
        items.add(ftime.format(new java.util.Date()));
        db.Update("STULOG", columns, items, conCol, conItems);
    }
    
    
    public void checkInstructor() throws SQLException{
        
        String name = null;
        Date checkin = null;
        
        ArrayList<Object> columns = new ArrayList<Object>();
        ArrayList<Object> items = new ArrayList<Object>();

        rs = db.Select("INSTRUCTOR", conCol, conItems);
        if(rs == null)
            return;
        if(!rs.next())
        name = rs.getString("NAME");
        
        conCol.clear();
        conItems.clear();
        conCol.add("NAME");
        conCol.add("DAY");
        conItems.add(name);
        conItems.add(fday.format(new java.util.Date()));
        rs = db.Select("INSTLOG", conCol, conItems);
        
        if(rs == null)
            return;
        if(!rs.next()){
            items.add(fday.format(new java.util.Date()));
            items.add(name);
            items.add(ftime.format(new java.util.Date()));
            items.add(null);
            db.Insert("INSTLOG", items);
            return;
        }
        
        checkin = rs.getDate("CHECKIN");
        java.util.Date end = new java.util.Date();
        int worktime = (int)(Math.round((double)(end.getTime() - checkin.getTime()) / 3600000.0));
        
        columns.add("CHECKOUT");
        items.add(ftime.format(end));
        columns.add("WORKTIME");
        items.add(worktime);
        db.Update("INSTLOG", columns, items, conCol, conItems);
    }
}
