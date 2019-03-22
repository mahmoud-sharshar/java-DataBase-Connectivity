package eg.edu.alexu.csd.oop.cs30;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class timeElapsed {
int currenttime=0;
long startTime;
	public timeElapsed(long nanoTime) {
 startTime = nanoTime/1000000;

}
	public long count() throws InterruptedException{
		long endTime = System.nanoTime()/1000000;
		long timeElapsed = endTime - startTime;
	return timeElapsed ;
	}
		
		
	

}
