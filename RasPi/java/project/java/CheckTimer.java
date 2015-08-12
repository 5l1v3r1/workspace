import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class CheckTimer{
    private Hashtable<String, Person> hashtable = null;
    public CheckTimer(Hashtable<String, Person> hashtable){
        this.hashtable = hashtable;
        Thread th = new Thread(new TimerThread());
        th.start();
    }
    
    class TimerThread implements Runnable{
        @Override
        public void run(){
            try {
                while(true){
                    Thread.sleep(3600000);
                    ExecuteShell.Command_Kill("nfc-read");
                    ExecuteShell.Command_Exe("nfc-read");
                    
                    Date now = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("H");
                    String hour = format.format(now);
                    //if(hour.equals("23")){
                        SendEmail();
                    //}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void SendEmail(){
        try{
            Set<Entry<String, Person>> set = hashtable.entrySet();
            for(Entry<String, Person> value : set){
                Person person = value.getValue();
                if(person.Start != null && person.End == null){
                    update_hash();
                    person.End = new Date();
                    person.Check();
                }
            }
            ExecuteShell.Command_Exe("python sendMail.py");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void update_hash(){
        try{
            ObjectOutputStream objout  = new ObjectOutputStream(new FileOutputStream("HashPhone.dat"));
            objout.writeObject(hashtable);
            objout.close();
        }catch(Exception e){e.printStackTrace();}
    }
}
