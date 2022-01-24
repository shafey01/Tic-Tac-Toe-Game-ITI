/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author MBR
 */
public class Client {

    Boolean isOn;
    Socket clientSocket;

    public Client() throws ClassNotFoundException {
        isOn = true;

        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);
            this.clientSocket = new Socket(ip, 5000);
            DataInputStream readFromServer = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream writeToServer = new DataOutputStream(clientSocket.getOutputStream());

            Thread t = new Thread() {

                @Override
                public void run() {
                    String str = "";
                    while (isOn) {
                        try {
                            str = readFromServer.readUTF();
                            System.out.println(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            };
            t.start();

            Scanner scan = new Scanner(System.in);
            String str = "";

            while (true) {
                str = scan.nextLine();
                writeToServer.writeUTF("From Client:   " + str);
                if (str.equalsIgnoreCase("exit")) {
                    break;
                }

            }
            isOn = false;
            readFromServer.close();
            writeToServer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            new Client();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

    }

}
