import java.io.*;
import java.net.*;

public class UDP_Client{
    public static void main(String [] args) throws Exception{
        String bcast = getBcast();
        String serverip = UDPsend(bcast);
        String macaddr = getMACAddr();
        System.out.println(macaddr);
        startNFC(serverip, macaddr);
    }
    
    public static void startNFC(String ip, String macaddr) throws Exception{
        Runtime runtime = Runtime.getRuntime();
        ip = ip.trim();
        macaddr = macaddr.trim();
        String command = "nfc-read " + ip + " " + macaddr;
        System.out.println(command);
        runtime.exec(command);
    }

    public static String UDPsend(String bcast) throws Exception{
        byte[] inbuf = new byte[255];
        byte[] outbuf = "I want to connect you".getBytes();

        DatagramSocket sock = new DatagramSocket();
        DatagramPacket pack = new DatagramPacket(outbuf, outbuf.length, InetAddress.getByName(bcast), 1127);

        sock.send(pack);

        pack.setData(inbuf);
        sock.receive(pack);

        String serverip= new String(pack.getData());
        
        sock.close();
        
        System.out.println("server ipaddress : " + serverip);
        return serverip;
    }

    public static String getMACAddr() throws Exception{
        String command = "ifconfig";
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line="";
        String l;

        while((l = reader.readLine())!=null)
            line += l+"\n";

        String iparr[] = line.split("\n\n");
        String MACAddr = null;
        for(String ip : iparr){
            if(ip.contains("wlan")){
                ip = ip.replace("\n","");
                ip = ip.replace("\t","");
                ip = ip.replace(" ","");
                int start_index = ip.indexOf("HWaddr") + 6;
                int end_index = ip.indexOf("inetaddr");
                MACAddr = ip.substring(start_index, end_index);
            }
        }
        if(MACAddr == null){
            System.out.println("Not Found wlan");
            System.exit(0);
        }
        return MACAddr;
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
}
