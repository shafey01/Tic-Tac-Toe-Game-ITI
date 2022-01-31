package ClientServer;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            String clientId = scan.next();
            new Client(clientId);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
