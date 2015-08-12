import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShell {
    public static void Command_Kill(String kill_process){
        try{
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("ps ax");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String line;
            while((line = reader.readLine()) != null){
                String[] ls = line.split(" ");
                for(String l : ls){
                    l.replace(" ", "");
                    if(!l.equals("")){
                        runtime.exec("kill -9 " + l);
                    }   
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void Command_Exe(String command){
        try{
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
