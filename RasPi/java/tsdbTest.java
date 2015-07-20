import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class tsdbTest {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://127.0.0.1:4242/api/put");
			URLConnection conn = url.openConnection();
			
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			double time = (double)System.currentTimeMillis()/1000.0;
			String metric = "my.test.java";
			int value = 10;
			String host = "mgpark";
			
			String request = "metric=" + metric + "&" +
							"timestamp=" + time + "&" +
							"value=" + value + "&";
			
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(request);
			
		} catch (Exception e) {
		}
	}
}
