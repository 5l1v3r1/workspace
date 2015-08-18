import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseTest {
	public static void main(String[] args) {
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ACADEMY",
					"admin", "academy");

			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();

		    rs = st.executeQuery("SELECT * FROM UID");
            if(rs == null){
                System.out.println("NULL");
                System.exit(0);
            }
            if(!rs.next()){
                System.out.println("NO");
                System.exit(0);
            }
            else{
                System.out.println("YES");
            }
            System.out.println(rs.getString("UID"));

		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
            sqex.printStackTrace();
		}

	}
}
