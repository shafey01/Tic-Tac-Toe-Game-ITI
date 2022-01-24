/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author MBR
 */
public class Client {

    Socket clientSocket;
    Object Oo = new Object(2, "X");
    String s = "X";
    int id = 4;

    public Client() throws ClassNotFoundException {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);
            this.clientSocket = new Socket(ip, 5000);
            ObjectInputStream objIn = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objOut = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {

                ObjectInputStream readFromServer;
                ObjectOutputStream writeToServer;

                try {

                    readFromServer = (ObjectInputStream) objIn.readObject();
                    System.out.println(readFromServer);
                    objOut.writeObject(s);
                    objOut.writeObject(id);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
