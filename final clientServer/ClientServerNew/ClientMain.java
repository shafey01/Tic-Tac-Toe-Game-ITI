package ClientServerNew;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("enter client id");
            String clientId = scan.next();
            new Client(clientId);
        }

    }
}
