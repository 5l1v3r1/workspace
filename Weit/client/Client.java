import java.io.*;
import java.net.*;

public class Client{
    public static void main(String [] args) throws Exception{
        String bcast = getBcast();
        String serverip = UDPsend(bcast);
        String macaddr = getMACAddr();
        System.out.println(macaddr);
        startNFC();

        ServerSocket server = new ServerSocket(1130);
        Socket sock = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        
        while(true){
            sock = server.accept();
            reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String uid = reader.readLine();
            System.out.println(uid);
            sock = new Socket(serverip, 1126);

            writer = new PrintWriter(sock.getOutputStream());
            writer.println(uid);
            writer.flush();
            writer.println(macaddr);
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String check = reader.readLine();
            System.out.println(check);
            LED(check);
        }
    }

    public static void LED(String check) throws Exception{
        Runtime runtime = Runtime.getRuntime();
        if(check.equals("CHECKIN")){
            killPython();
            System.out.println("Execute checkin.py");
            runtime.exec("checkin");
        }
        else if(check.equals("CHECKOUT")){
            killPython();
            System.out.println("Execute checkout.py");
            runtime.exec("checkout");
        }
    }

    public static void killPython() throws Exception{
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ps ax");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while((line = reader.readLine())!=null){
            if(line.contains("checkin.py") || line.contains("checkout.py")){
                String [] lines = line.split(" ");
                for(String l : lines)
                    if(l.length() > 0){
                        System.out.println("kill " + l);
                        runtime.exec("sudo kill -9 " + l);
                        break;
                    }
            }
        }
    }
    
    public static void startNFC() throws Exception{
        Runtime runtime = Runtime.getRuntime();
        String command = "nfc-read";
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
