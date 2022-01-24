/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

//    private Hashtable<Integer, ClientHandler> clientsTable = new Hashtable<Integer, ClientHandler>();
    Server() {

        System.out.println("tic.tac.toe.Server.<init>()");
        try {
            serverSock = new ServerSocket(5000);
            Socket clientSocket = serverSock.accept();
            ClientHandler clientHandler1 = new ClientHandler(clientSocket);
            clientHandler1.start();

            clientSocket = serverSock.accept();
            ClientHandler clientHandler2 = new ClientHandler(clientSocket);
            clientHandler2.start();
            ArrayList<String> message;
            while (true) {

                message = clientHandler1.getMessages();

                if (!message.isEmpty()) {

                    synchronized (message) {
                        for (int i = 0; i < message.size(); i++) {
                            clientHandler2.sendMessage(message.get(i));

                        }
                        message.clear();

                    }

                }

                message = clientHandler2.getMessages();

                if (!message.isEmpty()) {

                    synchronized (message) {
                        for (int i = 0; i < message.size(); i++) {
                            clientHandler1.sendMessage(message.get(i));

                        }
                        message.clear();

                    }

                }

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

//    private class ClientHandler extends Thread {
//
//        ObjectInputStream objIn;
//        ObjectOutputStream objOut;
//
//        public ClientHandler(Socket internalSock) {
//            try {
//                objIn = new ObjectInputStream(internalSock.getInputStream());
//                objOut = new ObjectOutputStream(internalSock.getOutputStream());
//                clientsTable.put(1, this);
//                start();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                Object clientRequest;
//                try {
//                    clientRequest = (Object) objIn.readObject();
//
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
//    public void sendToAll() {
//        for (Entry<Integer, ClientHandler> client : clientsTable.entrySet()) {
//            client.objOut.writeObject();
//        }
//    }
}
