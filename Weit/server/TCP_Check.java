import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    PrintWriter writer = null;
    
    // variables for process
    String uid = null;
    String macaddr = null;
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
                writer = new PrintWriter(sock.getOutputStream());
                uid = reader.readLine();
                macaddr = reader.readLine();
                System.out.println(uid);
                System.out.println(macaddr);
            }catch(IOException e){
                System.out.println("Error : BufferedReader Error");
                e.printStackTrace();
            }
            
            try {
                checkClassroom();
            } catch (SQLException e1) {
                System.out.println("Error : SQL Error");
                e1.printStackTrace();
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
                if(rs.getBoolean("ISINSTRUCTOR") == false){
                    int checkACK=0;
                    checkACK = checkStudent();
                    if(checkACK==0){
                        writer.println("CHECKIN");
                        writer.flush();
                    }
                    else if(checkACK==1){
                        writer.println("CHECKOUT");
                        writer.flush();
                    }
                    else{
                        writer.println("ERROR");
                        writer.flush();
                    }
                }
                else{
                    int checkACK=0;
                    checkACK = checkInstructor();
                    if(checkACK==0){
                        writer.println("CHECKIN");
                        writer.flush();
                    }
                    else if(checkACK==1){
                        writer.println("CHECKOUT");
                        writer.flush();
                    }
                    else{
                        writer.println("ERROR");
                        writer.flush();
                    }
                }
            }catch(SQLException e){
                System.out.println("Error : SQL Error");
                e.printStackTrace();
            }
        }
    }

    public int checkStudent() throws SQLException{
        
        String name = null;
        
        ArrayList<Object> columns = new ArrayList<Object>();
        ArrayList<Object> items = new ArrayList<Object>();
        
        conCol.clear();
        conItems.clear();
        conCol.add("UID");
        conItems.add(uid);
        rs = db.Select("STUDENT", conCol, conItems);
        if(rs == null)
            return -1;
        if(!rs.next())
            return -1;
        name = rs.getString("NAME");
        
        conCol.clear();
        conItems.clear();
        conCol.add("UID");
        conCol.add("DAY");
        conCol.add("MACADDR");
        conItems.add(uid);
        conItems.add(fday.format(new java.util.Date()));
        conItems.add(macaddr);
        rs = db.Select("STULOG", conCol, conItems);
        
        if(rs == null)
            return -1;
        if(!rs.next()){
            items.add(0);
            items.add(uid);
            items.add(fday.format(new java.util.Date()));
            items.add(name);
            items.add(ftime.format(new java.util.Date()));
            items.add(null);
            items.add(macaddr);
            db.Insert("STULOG", items);
            return 0;
        }

        rs.last();
        if(rs.getDate("CHECKOUT") != null){
            items.add(0);
            items.add(uid);
            items.add(fday.format(new java.util.Date()));
            items.add(name);
            items.add(ftime.format(new java.util.Date()));
            items.add(null);
            items.add(macaddr);
            db.Insert("STULOG", items);
            return 0;
        }
        conCol.clear();
        conItems.clear();
        conCol.add("NO");
        conItems.add(rs.getInt("NO"));
        columns.add("CHECKOUT");
        items.add(ftime.format(new java.util.Date()));
        db.Update("STULOG", columns, items, conCol, conItems);
        return 1;
    }
    
    
    public int checkInstructor() throws SQLException{
        
        String name = null;
        Date checkin = null;
        
        ArrayList<Object> columns = new ArrayList<Object>();
        ArrayList<Object> items = new ArrayList<Object>();

        conCol.clear();
        conItems.clear();
        conCol.add("UID");
        conItems.add(uid);
        rs = db.Select("INSTRUCTOR", conCol, conItems);
        if(rs == null)
            return -1;
        if(!rs.next())
        name = rs.getString("NAME");
        
        conCol.clear();
        conItems.clear();
        conCol.add("UID");
        conCol.add("DAY");
        conItems.add(uid);
        conItems.add(fday.format(new java.util.Date()));
        rs = db.Select("INSTLOG", conCol, conItems);
        
        if(rs == null)
            return -1;
        if(!rs.next()){
            items.add(0);
            items.add(uid);
            items.add(fday.format(new java.util.Date()));
            items.add(name);
            items.add(ftime.format(new java.util.Date()));
            items.add(null);
            db.Insert("INSTLOG", items);
            return 0;
        }

        rs.last();
        if(rs.getDate("CHECKOUT") != null){
            items.add(0);
            items.add(uid);
            items.add(fday.format(new java.util.Date()));
            items.add(name);
            items.add(ftime.format(new java.util.Date()));
            items.add(null);
            items.add(macaddr);
            db.Insert("STULOG", items);
            return 0;
        }
        
        conCol.clear();
        conItems.clear();
        conCol.add("NO");
        conItems.add(rs.getInt("NO"));
        
        checkin = rs.getDate("CHECKIN");
        java.util.Date end = new java.util.Date();
        int worktime = (int)(Math.round((double)(end.getTime() - checkin.getTime()) / 3600000.0));
        
        columns.add("CHECKOUT");
        items.add(ftime.format(end));
        columns.add("WORKTIME");
        items.add(worktime);
        db.Update("INSTLOG", columns, items, conCol, conItems);
        return 1;
    }


    public void checkClassroom() throws SQLException{
        conCol.clear();
        conItems.clear();
        conCol.add("MACADDR");
        conItems.add(macaddr);
        
        rs = db.Select("CLASSROOM", conCol, conItems);
        if(!rs.next()){
            conItems.add(ftime.format(new java.util.Date()));
            db.Insert("CLASSROOM", conItems);
        }
    }
}
