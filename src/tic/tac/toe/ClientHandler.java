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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.SynchronousQueue;

/**
 *
 * @author MBR
 */
public class ClientHandler extends Thread {

    Socket clientSocket;
    DataInputStream readFromServer;
    DataOutputStream writeToServer;
//private Hashtable<Integer, ClientHandler> clientsTable = new Hashtable<Integer, ClientHandler>();
    final ArrayList<String> messageList = new ArrayList<String>();

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;

        try {

            readFromServer = new DataInputStream(clientSocket.getInputStream());
            writeToServer = new DataOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void run() {

        try {
            String message;
            while (true) {

                message = readFromServer.readUTF();
                synchronized (messageList) {
                    messageList.add(message);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (writeToServer != null) {
                writeToServer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (readFromServer != null) {
                readFromServer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    

}

public void sendMessage(String str) {

try{
writeToServer.writeUTF(str);
}

catch(IOException e)
{
e.printStackTrace();
}

    }

public ArrayList<String> getMessages()
{

return messageList;

}















}
