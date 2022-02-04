package ClientServerNew;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import login.Login;

public class ClientMain {

    public static void main(String[] args) throws IOException {

new Thread() {
            @Override
            public void run() {

               Application.launch(Login.class, args);

        }}.start();

        System.out.println("down thread");
        

        
            System.out.println("enter client id");
//            String clientId = scan.next();
//            new Client();
        

    }
}
