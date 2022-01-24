/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.util.Scanner;

/**
 *
 * @author MBR
 */
public class ServerMain {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
if(scan.next().equals("server"))
{

Server s = new Server();


}
else
{
try{
Client c = new Client();
}
catch(ClassNotFoundException e)
{
e.printStackTrace();
}
}
        




    }

}
