import java.io.IOException;
import java.util.Timer;


public class Main {
    public static void main(String[] args) throws IOException {
        Timer timer = new Timer();
        ScheduledTask task = new ScheduledTask();
        timer.schedule(task, 100, 600000);
        System.out.println("~~~~~~~");
    }

}
