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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.SynchronousQueue;

/**
 *
 * @author MBR
 */
public class ClientHandler extends Thread {

    Socket internalSocket;
    PrintWriter writeToClient;
    BufferedReader readFromClient;
    // private Hashtable<Integer, ClientHandler> clientsTable = new
    // Hashtable<Integer, ClientHandler>();
    // final ArrayList<String> messageList = new ArrayList<String>();

    public ClientHandler(Socket clientSocket) {
        this.internalSocket = clientSocket;

        try {

            readFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToClient = new PrintWriter(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void run() {

        try {
            // String messageToClient;
            String messageFromClient;
            while (true) {
                messageFromClient = readFromClient.readLine();
                System.out.println(messageFromClient);
                if (messageFromClient.equals(new String("login"))) {
                    writeToClient.println("you will login");
                }

                else {
                    writeToClient.println();
                }
                // synchronized (messageList) {
                // messageList.add(message);

                // }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // try {
        // if (writeToServer != null) {
        // writeToServer.close();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // try {
        // if (readFromServer != null) {
        // readFromServer.close();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // try {
        // if (clientSocket != null) {
        // clientSocket.close();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

    public void sendMessage(String messageToClient) {

        writeToClient.println(messageToClient);

    }

    // public ArrayList<String> getMessages() {

    // return messageList;

    // }

}
