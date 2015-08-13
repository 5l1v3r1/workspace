import java.io.*;
import java.net.*;

public class UDP_Server implements Runnable{
    public static void main(String [] args) throws Exception{
        UDP_Server server = new UDP_Server();
        server.run();
    }

    @Override
    public void run(){
        try{
            byte[] inbuf = new byte[255];
            DatagramSocket sock = new DatagramSocket(1127);
            DatagramPacket packet = new DatagramPacket(inbuf, inbuf.length);
            String myip = getAddr();

            sock.receive(packet);
            packet.setData(myip.getBytes());

            sock.send(packet);
            sock.close();
        }catch(Exception e){e.printStackTrace();}
    }

    public static String getAddr() throws Exception{
        String command = "ifconfig";
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line="";
        String l;

        while((l = reader.readLine())!=null)
            line += l+"\n";
        
        String iparr[] = line.split("\n\n");
        String Bcast = null;
        for(String ip : iparr){
            if(ip.contains("wlan")){
                ip = ip.trim();
                int start_index = ip.indexOf("addr:") + 5;
                int end_index = ip.indexOf("Bcast:");
                Bcast = ip.substring(start_index, end_index);
            }
        }
        if(Bcast == null){
            System.out.println("Not Found wlan");
            System.exit(0);
        }
        return Bcast;
    
    
    }
}
