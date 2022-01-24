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
import java.util.Hashtable;

/**
 *
 * @author MBR
 */
public class Server {

    private ServerSocket serverSock;
    private Socket internalSock;

    private Hashtable<Integer, ClientHandler> clientsTable = new Hashtable<Integer, ClientHandler>();

 Server() {

        System.out.println("tic.tac.toe.Server.<init>()");
        try {
            serverSock = new ServerSocket(5000);
            while (true) {
                internalSock = serverSock.accept();
//                new ClientHandler(internalSock);
                ObjectInputStream readFromClient = new ObjectInputStream(internalSock.getInputStream());
                ObjectOutputStream writeToClient = new ObjectOutputStream(internalSock.getOutputStream());

                while (true) {
                    writeToClient.writeUTF("Hi From Server");
                    writeToClient.writeObject(this);

                    String str = readFromClient.readUTF();
                    System.err.println(str);
                    try {
                        Object o = (Object) readFromClient.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    readFromClient.close();
                    writeToClient.close();
                    internalSock.close();

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
