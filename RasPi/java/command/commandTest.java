import java.io.*;

public class commandTest{
    public static void main(String[] args) throws Exception{
        String command = "ps ax";
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while((line = reader.readLine())!=null){
            if(line.contains("/exe/project")){
                String [] lines = line.split(" ");
                for(String l : lines){
                    l = l.replace(" ","");
                    if(!l.equals("")){
                        command = "kill -9 " + l;
                        runtime.exec(command);
                    }
                }
            }
        }
    }
}
