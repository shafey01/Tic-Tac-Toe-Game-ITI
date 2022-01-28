package ClientServer;

import java.io.BufferedReader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
    String id;
    String password;
    PrintWriter writeToServer;
    BufferedReader readFromServer;

    public Client(String _id) throws ClassNotFoundException {
        isOn = true;
        id = _id;
        String messageToServer;
        Scanner scan = new Scanner(System.in);
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);
            this.clientSocket = new Socket(ip, 7001);
            readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToServer = new PrintWriter(clientSocket.getOutputStream());

            Thread t = new Thread() {

                @Override
                public void run() {

                    String messageFromServer;
                    while (isOn) {
                        try {
                            // writeToServer.writeUTF("login");
                            messageFromServer = readFromServer.readLine();
                            System.out.println(messageFromServer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            };
            t.start();

            while (true) {
                System.out.println("enter your input: ");
                String clientInput = scan.next();
                if (clientInput.equals(new String("login"))) {
                    messageToServer = new String("login" + "." + id);
                    writeToServer.println(messageToServer);
                    // System.out.println("sent " + messageToServer);
                }

                else if (clientInput.equals(new String("signup"))) {
                    messageToServer = new String("signup" + "." + id);
                    writeToServer.println(messageToServer);
                }

                else if (clientInput.equals(new String("invite"))) {
                    String idToInvite = scan.next();
                    messageToServer = new String("invite." + id + "." + idToInvite + ".hello client3");
                    writeToServer.println(messageToServer);
                }

                else if (clientInput.equals(new String("exit"))) {
                    messageToServer = "exit";
                    writeToServer.println(messageToServer);
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

    // public static void main(String[] args) {
    // try {
    // new Client();
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();

    // }

    // }

}
