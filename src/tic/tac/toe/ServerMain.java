package ClientServer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;

/**
 *
 * @author MBR
 */
public class ServerMain {

    public static void main(String[] args) {

System.out.println("Blocking0");
        Scanner scan = new Scanner(System.in);
System.out.println("Blocking1");
String s = scan.next();
        if (s.equals("server")) {

            new Server();

        } else {
            try {
                System.out.println("Blocking2");
                new Client(s);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
