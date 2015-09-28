package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.*;


public class SerialWrite{
	
OutputStream outStream;
InputStream inStream;
	
public SerialWrite(){
	try {
		CommPortIdentifier portId =
				  CommPortIdentifier.getPortIdentifier("COM1");
		SerialPort serialPort =
				  (SerialPort) portId.open("Authentication Server", 5000);
		
		int baudRate = 57600; 
		serialPort.setSerialPortParams(
				baudRate,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);
		
		
		serialPort.setFlowControlMode(
				SerialPort.FLOWCONTROL_NONE);
			
		
		outStream = serialPort.getOutputStream();
		inStream = serialPort.getInputStream();
		
		
		
		
		
		
		
		
		
	} catch (NoSuchPortException | PortInUseException e) {
		System.out.println("portID error or port in use");
		e.printStackTrace();
	} catch (UnsupportedCommOperationException ex) {
		  System.err.println(ex.getMessage());
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	

	
}

public void sendMessage(String message,String number){
	try {
		outStream.write("AT+CMGF=1".getBytes());
		outStream.write(("AT+CMGS="+number).getBytes());
		Thread.sleep(500);	
		outStream.write(message.getBytes());
		
		
	} catch (IOException e) {
		System.out.println("Message sending error.");
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}