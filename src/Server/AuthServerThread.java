package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class AuthServerThread extends Thread {
	
	private Socket socket = null;
	
	public AuthServerThread(Socket socket){
		super("AuthServerThread");
		this.socket = socket;
	}
	
	public void run(){
		try(PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
         ){
			 String inputLine, outputLine;
	            
	            // Initiate conversation with client
	            AuthProtocol kkp = new AuthProtocol(); 
	            outputLine = kkp.processInput(null);
	            out.println(outputLine);

	            while ((inputLine = in.readLine()) != null) {
	                outputLine = kkp.processInput(inputLine);
	                out.println(outputLine);
	                if (outputLine.equals("Login." )|| outputLine.equals("Disconnecting..."))
	                    break;
	            }
	            socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
