import java.io.*;
import java.net.*;

public class UDP_Client{
    public static void main(String [] args) throws Exception{
        String bcast = getBcast();
        UDPsend(bcast);
    }

    public static void UDPsend(String bcast) throws Exception{
        byte[] inbuf = new byte[255];
        byte[] outbuf = "I want to connect you".getBytes();

        DatagramSocket sock = new DatagramSocket();
        DatagramPacket pack = new DatagramPacket(outbuf, outbuf.length, InetAddress.getByName(bcast), 1127);

        sock.send(pack);

        pack.setData(inbuf);
        sock.receive(pack);

        String sendip= new String(pack.getData());
        
        sock.close();
        
        System.out.println(sendip);
    }

    public static String getBcast() throws Exception{
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
                int start_index = ip.indexOf("Bcast:") + 6;
                int end_index = ip.indexOf("Mask:");
                Bcast = ip.substring(start_index, end_index);
            }
        }
        if(Bcast == null){
            System.out.println("Not Found wlan");
            System.exit(0);
        }
        return Bcast;
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
                int start_index = ip.indexOf("Addr:") + 5;
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
