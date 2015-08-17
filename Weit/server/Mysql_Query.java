import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Mysql_Query {
    private Connection con = null;
    private Statement st = null;
    
    /** input database name, user name, password when you create this object **/
    public Mysql_Query(String database, String user, String pwd){
        try{
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + database, user, pwd);
            st = con.createStatement();
        }catch(SQLException e){
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQLState : " + e.getSQLState());
            e.printStackTrace();
        }
    }
    
    /** Insert database **/
    public void Insert(String table, ArrayList<Object> items){
        
        // setting insert query
        String query = "INSERT INTO " + table + " VALUES (";
        for(Object item : items){
            if(item == null)
                query += "NULL, ";
            else if(item instanceof Integer || item instanceof Long)
                query += item + ", ";
            else
                query += "'" + item + "', ";
        }
        query = query.substring(0, query.length()-2) + ")";
        
        
        // execute query
        System.out.println(query);
        try{
            st.execute(query);
        }catch(SQLException e){
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQLState : " + e.getSQLState());
            e.printStackTrace();
        }
    }
    
    /** Update database **/
    public void Update(String table, ArrayList<Object> columns,ArrayList<Object> items, ArrayList<Object> conCol, ArrayList<Object> conItems){
        Object item;        // check object type (integer? or others?)
        String query = "UPDATE " + table + " SET ";

        
        // setting update query
        int length = columns.size();
        for(int i=0; i<length; i++){
            query += columns.get(i) + "=";
            
            item = items.get(i);
            if(item == null)
                query += "NULL, ";
            else if(item instanceof Integer || item instanceof Long)
                query += item + ", ";
            else
                query += "'" + item + "', ";
        }
        
        
        // setting condition
        length = conCol.size();
        if(length > 0){
            query = query.substring(0, query.length()-2) + " WHERE ";
            for(int i=0; i<length; i++){
                query += conCol.get(i) + "=";
                
                item = conItems.get(i);
                if(item == null)
                    query += "NULL, ";
                else if(item instanceof Integer || item instanceof Long)
                    query += item + ", ";
                else
                    query += "'" + item + "', ";
            }
        }
        query = query.substring(0, query.length()-2);
        
        
        // execute query
        System.out.println(query);
        try{
            st.execute(query);
        }catch(SQLException e){
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQLState : " + e.getSQLState());
            e.printStackTrace();
        }
    }
    
    /** Delete database **/
    public void Delete(String table, ArrayList<Object>conCol, ArrayList<Object>conItems){
        Object item;        // check object type (integer? or others?)
        String query = "DELETE FROM " + table;
        
        
        // setting delete query
        int length = conCol.size();
        if(length > 0){
            query += " WHERE ";
            for(int i=0; i<length; i++){
                query += conCol.get(i) + "=";
                
                item = conItems.get(i);
                if(item == null)
                    query += "NULL, ";
                else if(item instanceof Integer || item instanceof Long)
                    query += item + ", ";
                else
                    query += "'" + item + "', ";
            }
            query = query.substring(0, query.length()-2);
        }
        
        
        // execute query
        System.out.println(query);
        try{
            st.execute(query);
        }catch(SQLException e){
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQLState : " + e.getSQLState());
            e.printStackTrace();
        }
    }
    
    /** Select query **/
    public ResultSet Select(String table, ArrayList<Object>conCol, ArrayList<Object>conItems){
        Object item;        // check object type (integer? or others?)
        ResultSet rs = null;
        String query = "SELECT * FROM " + table;;
        
        
        // setting select query
        int length = conCol.size();
        if(length > 0){
            query += " WHERE ";
            for(int i=0; i<length; i++){
                query += conCol.get(i) + "=";
                
                item = conItems.get(i);
                if(item == null)
                    query += "NULL, ";
                else if(item instanceof Integer || item instanceof Long)
                    query += item + ", ";
                else
                    query += "'" + item + "', ";
            }
            query = query.substring(0, query.length()-2);
        }
        
        // execute query
        System.out.println(query);
        try{
            rs = st.executeQuery(query);
        }catch(SQLException e){
            System.out.println("SQLException : " + e.getMessage());
            System.out.println("SQLState : " + e.getSQLState());
            e.printStackTrace();
        }
        
        return rs;
    }

    public Statement getStat(){
        return st;
    }
}
