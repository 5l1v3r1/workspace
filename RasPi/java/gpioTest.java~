import java.io.IOException;

import com.pi4j.io.serial.*;
import net.opentsdb.*;

public class gpioTest {
	public static void main(String[] args) {
		Serial serial = SerialFactory.createInstance();
        TSDB tsdb
        serial.open("/dev/ttyAMA0",38400);
        while(true){
            for(int i=1; i<=12; i++)
                System.out.printf("[%2d] : %s\n",i,serial.read());
        }
	}
}
