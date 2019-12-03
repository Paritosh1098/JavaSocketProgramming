import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class CheckConnectionReader implements Runnable {

    BufferedReader in;

    public CheckConnectionReader(BufferedReader in){
        this.in = in;
    }

    @Override
    public void run() {

        while(true) {
            try {
                String message = in.readLine();
                System.out.println(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
