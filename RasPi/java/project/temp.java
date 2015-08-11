import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class temp {
	public static void main(String[] args) {
		ServerSocket serversock = null;
		Socket sock = null;
		BufferedReader reader = null;
		try{
			serversock = new ServerSocket(1126);
			while(true){
				sock = serversock.accept();
				System.out.println("accept");
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				System.out.println(reader.readLine());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
