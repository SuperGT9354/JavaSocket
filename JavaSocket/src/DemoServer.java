import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

//Castelletto Davide 5CIA 11/10/2021

public class DemoServer {

	private static ServerSocket ws = null;  // welcoming socket
	private static Socket s = null;			// connection socket
	private static boolean run = false;
	
	public static void main(String[] args) {
		// Demo Server TCP
		BufferedReader in = null;
		Boolean remain = true;
		PrintWriter out = null;
		Reverse re = new Reverse();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
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
			//Prova	
			try {
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(), true);
				String line = "192.168.80.43";
				if (line.equals(s.getInetAddress().toString()))
				{
					out.println("Try next time");
					s.close();
				}
				out.println("Benvenuto nel server di Castelletto Davide =)!");
				System.out.println("remote host: "+s.getInetAddress());
			} catch (IOException e) {			
				e.printStackTrace();
				System.out.println("Unable to get I/O stream on socket!");
				
			}
			do
			{
				try {
					//out.println("Benvenuto in KeSuperSballo.Yeah.Ok!");
					
					// Per leggere da socket:
					String str = null;
					remain = true;
					str = in.readLine();
					System.out.println(str);  // DEBUG
					
					if (str.equalsIgnoreCase("date"))
					{
						out.print("\033[H\033[2J");
						LocalDateTime now = LocalDateTime.now();
						out.println(dtf.format(now));
						System.out.println(str);  // DEBUG
					}
					
					if (str.equalsIgnoreCase("quit") || str.equalsIgnoreCase("exit"))
					{
						out.print("\033[H\033[2J");
						remain=false;
						System.out.println(str);  // DEBUG
						break;
					}
					
					if (str.equalsIgnoreCase("shutdown"))
					{
						out.print("\033[H\033[2J");
						out.close();
						s.close();
						System.out.println(str);  // DEBUG
					}
					
					else if (!str.equalsIgnoreCase("date"))
					{
						out.print("\033[H\033[2J");
						Reverse p = new Reverse();
						out.println(p.reverse(str)); // ECHO
					}
					System.out.println(str);  // DEBUG
					
					// Per scrivere su socket:
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Communication Error!");
				} 
			}while(remain);
			
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
	}  // fine main
}
