package Server;


import java.net.*;
import java.io.*;

public class AuthServer {
    public static void main(String[] args) throws IOException {
        

        int portNumber = 4444;
while(true){
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber)){
            while(true){
            	new AuthServerThread(serverSocket.accept()).start();
            }
            
        
           
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    }
}