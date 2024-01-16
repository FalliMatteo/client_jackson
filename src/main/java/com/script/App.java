package com.script;

import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try{
            Scanner scanner = new Scanner(System.in);
            Socket socket = null;
            boolean kek = false;
            while(!kek){
                kek = true;
                System.out.println("\nInsert the server's IP address");
                String address = scanner.nextLine();
                try{
                    socket = new Socket(address, 3000);
                }catch(UnknownHostException e){
                    System.out.println("\nServer not found");
                    kek = false;
                }catch(ConnectException e){
                    System.out.println("\nServer not found");
                    kek = false;
                }
            }
            ClientThread thread = new ClientThread(socket);
            thread.start();
            System.out.println("\nConnection established");
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
