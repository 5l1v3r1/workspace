import java.io.*;

public class commandTest{
    public static void main(String[] args) throws Exception{
        String command = "python sendMail.py";
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while((line = reader.readLine())!=null){
            System.out.println(line);
        }
    }
}
