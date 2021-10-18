import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

//Castelletto Davide 5CIA 10/10/2021

public class DemoServer {

	private static ServerSocket ws = null;  // welcoming socket
	private static Socket s = null;			// connection socket
	private static boolean run = true;
	public static Boolean remain = true;
	
	public static synchronized boolean run(boolean s)
	{
		run = s;
		return run;
	}
	
	public static void main(String[] args) {
		// Demo Server TCP
		//BufferedReader in = null;
		//PrintWriter out = null;
		//Reverse re = new Reverse();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		try {
			// Welcoming Socket (per 3-way handshake)
			ws = new ServerSocket(7979);
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Welcoming socket error!");
		}
		
		do
		{
			try {
				s = ws.accept();  // create connection socket
			} catch (IOException e) {			
				e.printStackTrace();
				System.out.println("Connection Accept error!");
			}
			
			Worker w = new Worker(s, dtf);
			new Thread(w).start();
			
		}while(run);
			
		System.out.println("Quitting server!");
		//out.close();
		try {
			//in.close();
			s.close();    // close connection socket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error closing socket!");
		}
	}
}  // fine main
