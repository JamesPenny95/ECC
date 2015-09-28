package Server;
import java.sql.*;
import java.util.*;
 
public class AuthProtocol {
    
	 private static final int INIT = 0;
	    private static final int GOTUSER = 1;
	    private static final int GOTPASS = 3;
	    private static final int GOTCODE = 4;
	    
 int cState = INIT;

 Connection c;
 Statement stmt;
 String theOutput = "uninit";
 String pass = null;
 String code = null;
 String inUser = null;

 //SerialWrite ser = new SerialWrite();
 
 String sDriverName = "org.sqlite.JDBC";{
 try {
     Class.forName("org.sqlite.JDBC");
     c = DriverManager.getConnection("jdbc:sqlite:deets.db");
   } catch ( Exception e ) {
     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     System.exit(0);
   }
 System.out.println("DB Opened Successfully");
 }

     
 
 
 
 
 
 
public String processInput(String theInput) {
	
	
if(theInput != null){
	if(theInput.equals("Exit")){
		theOutput = "Disconnecting...";
		return theOutput;
	}
}
if(cState == INIT){
	theOutput = "Enter Username, or Exit to quit: ";
	cState = GOTUSER;
	
}else if(cState == GOTUSER){
	inUser = theInput;{
    	try{
    			stmt = c.createStatement();
    			ResultSet rs = stmt.executeQuery("SELECT passwords FROM users WHERE users = '" + inUser + "';");
    			
    			while(rs.next()){
    				pass = rs.getString(1);
    				System.out.println(pass);
    			}
    		
    			
    			if(pass != null){
    				theOutput = "Enter Password for " + inUser;
    				cState = GOTPASS;
    			}else{
    				theOutput = "No user found! "; cState= GOTUSER;
    				}
    			
    			
    			
    			
    	
    	}catch (Exception e){
    		System.err.println(e.getClass().getName() + ": " + e.getMessage());
    		System.exit(0);
    	}
    }
	
	
}else if (cState==GOTPASS){
	if (theInput.equals(pass)){
		cState = GOTCODE;
		
		Random r = new Random();
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder( 5 );
		for( int i = 0; i < 5; i++ ){
			sb.append( AB.charAt( r.nextInt(AB.length()) ) );
			}
	 code = sb.toString();
	 System.out.println(code);
	 
	 try{
	 stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT phone FROM users WHERE users = '" + inUser + "';");
		
		while(rs.next()){
			String number = rs.getString(1);
			System.out.println(number);
			//ser.sendMessage(code, number);
		}
	 
	 }catch(Exception e){System.err.println("DB Number Error");}
	 
	 theOutput = "Enter Code: ";
	}else{
		theOutput = "Incorrect password!";
	}
	
	
}else if (cState==GOTCODE){
	if(theInput.equals(code)){
		theOutput = "Login.";
		cState=INIT;
	}else{
		theOutput = "Incorrect Code!";
	}
}










        return theOutput;
    }
}

