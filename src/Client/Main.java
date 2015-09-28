package Client;

public class Main {

	public static void main(String[] args) {
		
		 AuthClient auth = new AuthClient();
		
		if(auth.getAuth() == false){
			System.exit(1);
		}
		
		System.out.println("Log-in Successful!");

}
}
