import java.io.*;

public class commandTest{
    public static void main(String[] args) throws Exception{
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
        System.out.println(Bcast);
    }
}
