import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//Castelletto Davide 5CIA 07/10/2021

public class DemoServer {

	private static ServerSocket ws = null;  // welcoming socket
	private static Socket s = null;			// connection socket
	private static boolean run = false;
	
	public static void main(String[] args) {
		// Demo Server TCP
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// Welcoming Socket (per 3-way handshake)
			ws = new ServerSocket(7979);
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Welcoming socket error!");
		}
		
		while(true)
		{
			try {
				s = ws.accept();  // create connection socket
			} catch (IOException e) {			
				e.printStackTrace();
				System.out.println("Connection Accept error!");
			}
				
			try {
				//in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(), true);
				out.println("Benvenuto nel server di Castelletto Davide =)!");
				System.out.println("remote host: "+s.getInetAddress());
			} catch (IOException e) {			
				e.printStackTrace();
				System.out.println("Unable to get I/O stream on socket!");
			}
			System.out.println("Quitting server!");
			out.close();
			try {
				//in.close();
				s.close();    // close connection socket
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error closing socket!");
			}
		}
		/*
		try {
			out.println("Benvenuto in KeSuperSballo.Yeah.Ok!");
			
			// Per leggere da socket:
			String str = in.readLine();
			out.println(str); // ECHO
			System.out.println(str);  // DEBUG
			
			// Per scrivere su socket:
			out.println("Arrivederci, e' stato un piacere!");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Communication Error!");
		} */
		
		
		

	}  // fine main

}
