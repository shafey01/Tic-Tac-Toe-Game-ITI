package ClientServer;

import java.io.BufferedReader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

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

        public ClientHandler(Socket clientSocket) {
            internalSockHandler = clientSocket;

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
                    // System.out.println(messageFromClient);
                    String[] tokens = messageFromClient.split("\\.");

                    clientsTable.put(tokens[0], this);
                    writeToClient.println("hello " + tokens[0]);

                    if (clientsTable.get(tokens[1]) != null) {
                        clientsTable.get(tokens[1]).writeToClient.println("message is " + tokens[2]);
                    } else {
                        writeToClient.println("not connected yet");
                    }
                    // if (messageFromClient.equals(new String("login"))) {
                    // writeToClient.println("you will login");
                    // }

                    // else {
                    // writeToClient.println();
                    // }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    Server() {
        System.out.println("tic.tac.toe.Server.<init>()");
        try {
            serverSock = new ServerSocket(6001);
            while (true) {
                internalSock = serverSock.accept();
                new ClientHandler(internalSock).start();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
