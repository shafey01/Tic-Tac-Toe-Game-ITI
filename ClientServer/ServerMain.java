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

        Scanner scan = new Scanner(System.in);
        if (scan.next().equals("server")) {

            new Server();

        } else {
            try {
                new Client(scan.next());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
