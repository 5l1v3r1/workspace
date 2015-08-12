import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckTimer{
    class TimerThread implements Runnable{
        @Override
        public void run(){
            try {
                while(true){
                    Thread.sleep(3600000);
                    ExecuteShell.Command_Kill("nfc-read");
                    ExecuteShell.Command_Exe("nfc-read");
                    
                    Date now = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("hh");
                    String hour = format.format(now);
                    if(hour.equals("23")){
                        
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void SendEmail(){
        try{
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
