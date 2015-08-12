import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Person implements Serializable{
    // Variable about this person's information
    public String Name;
    public String Subject;
    public String ClassName;
    public String Instructor;
    public String Phone;
    public String Parent;
    public String uid;
    
    public Date Start;              // check in time
    public Date End;                // check out time
    
    public int excelrow;            // this person's lacation in excel file
    
    public boolean State;           // check in? check out?      
    public boolean IsInstructor;    // Is instructor?
    
    // Initialize Student Object.
    public Person(String Name, String Subject, String ClassName, String Phone, String Parent){
        // If this person has subject and parent variable, this person will be student
        this.IsInstructor = false;
        
        this.Name = Name;
        this.Subject = Subject;
        this.ClassName = ClassName;
        this.Phone = Phone;
        this.Parent = Parent;
        this.State = false;
    }
    public Person(String Name, String ClassName, String Phone){
        // If this person has not subject and parent variable, this person will be instructor
        this.IsInstructor = true;
        
        this.Name = Name;
        this.ClassName = ClassName;
        this.Phone = Phone;
        this.State = false;
    }
    
    // Check Start or End
    public void Check(){
        if(!State){
            Start = new Date();
            System.out.println(Name + " : check in");
            State = !State;
        }
        else{
            End = new Date();
            System.out.println(Name + " : check out");
            ArrayList<Object> dataarray = new ArrayList<Object>();
            
            Start = null;
            End = null;
            
            if(!IsInstructor){
                // Case student
                dataarray.add(new Date());
                dataarray.add(Name);
                dataarray.add(Subject);
                dataarray.add(ClassName);
                dataarray.add(Start);
                dataarray.add(End);
                dataarray.add("");
                
                ExcelWriter.Write("We_IT.xlsx", "수강생출결", dataarray);
                
                State = !State;
            }
            else{
                // Case instructor
                dataarray.add(new Date());
                dataarray.add(Name);
                dataarray.add(Start);
                dataarray.add(End);
                
                SimpleDateFormat format = new SimpleDateFormat("hh");
                double hour = Double.parseDouble(format.format(End)) - Double.parseDouble(format.format(Start));
                format = new SimpleDateFormat("mm");
                double minute = Double.parseDouble(format.format(End)) - Double.parseDouble(format.format(Start));

                System.out.println(hour + "//////"+ minute);
                
                dataarray.add((double)Math.round(hour + minute/60));
                System.out.println(Math.round(hour + minute/60));
                
                ExcelWriter.Write("We_IT.xlsx", "강사출결", dataarray);
            }
        }
    }
}
