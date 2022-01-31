package ClientServer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author MBR
 */
public class Server {

    private ServerSocket serverSock;
    private Socket internalSock;

    private Hashtable<String, ClientHandler> clientsTable = new Hashtable<String, ClientHandler>();

    private class ClientHandler extends Thread {

        Socket internalSockHandler;
        PrintWriter writeToClient;
        BufferedReader readFromClient;
        String clientID;
        // boolean isOn;

        public ClientHandler(Socket _internalSock) {
            internalSockHandler = _internalSock;
            // isOn = true;
            try {

                readFromClient = new BufferedReader(new InputStreamReader(internalSockHandler.getInputStream()));
                writeToClient = new PrintWriter(internalSockHandler.getOutputStream());

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
                    String[] tokens = messageFromClient.split("\\.");

                    // for (Map.Entry<String, ClientHandler> hashEntry : clientsTable.entrySet()) {
                    // System.out.print(hashEntry.getKey() + " " + hashEntry.getValue());
                    // }
                    // System.out.println();

                    // writeToClient.println("hello " + tokens[0]);

                    if (tokens[0].equals(new String("login"))) {
                        clientID = tokens[1];
                        clientsTable.put(tokens[0], this);
                        writeToClient.println(new String("hello " + clientID));

                    }

                    else if (tokens[0].equals(new String("signup"))) {
                        writeToClient.println(new String("signup" + clientID));
                    }

                    else if (tokens[0].equals(new String("invite"))) {
                        if (clientsTable.get(tokens[2]) != null) {
                            clientsTable.get(tokens[2]).writeToClient
                                    .println("message from " + clientID + " : " + tokens[3]);
                        }

                        else {
                            writeToClient.println("not connected yet");
                        }
                    }

                    else {
                        System.out.println("in else");
                        clientsTable.remove(clientID);
                        // isOn = false;
                        break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            writeToClient.close();

            try {
                readFromClient.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            try {
                internalSockHandler.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    Server() {
        System.out.println("tic.tac.toe.Server.<init>()");
        try {
            serverSock = new ServerSocket(7001);
            while (true) {
                internalSock = serverSock.accept();
                new ClientHandler(internalSock).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
