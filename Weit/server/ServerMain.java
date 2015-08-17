
public class ServerMain {
    public static void main(String[] args) throws Exception{
        new Thread(new UDP_Server()).start();
        new Thread(new TCP_Check()).start();
    }
}
