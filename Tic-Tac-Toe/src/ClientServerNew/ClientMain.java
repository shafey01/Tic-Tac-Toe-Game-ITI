package ClientServerNew;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import login.Login;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        Application.launch(Login.class, args);

        System.out.println("down thread");

        System.out.println("enter client id");
//            String clientId = scan.next();
//            new Client();

    }
}
