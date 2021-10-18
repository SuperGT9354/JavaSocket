import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

//Castelletto Davide 5CIA 10/10/2021

public class Worker implements Runnable{

	private Socket s;
	private DateTimeFormatter dtf;
	
	
	public Worker(Socket s, DateTimeFormatter d)
	{
		this.s = s;
		this.dtf = d;
	}
	
	@Override
	public void run() {
		
		BufferedReader in = null;
		PrintWriter out = null;
		//Reverse re = new Reverse();
		boolean remain = true;
		
		
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		out.println("Benvenuto nel server di Castelletto Davide =)!");
		
		do
		{
			try {
				
				String line = "192.168.80.44";
				if (line.equals(s.getInetAddress().toString()))
				{
					out.println("Try next time");
					remain = false;
					s.close();
					break;
				}
				
				System.out.println("remote host: "+s.getInetAddress());
			} catch (IOException e) {			
				e.printStackTrace();
				System.out.println("Unable to get I/O stream on socket!");
				
			}
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
						remain = false;
						System.out.println(str);  // DEBUG
						break;
					}
					
					if (str.equalsIgnoreCase("shutdown"))
					{
						out.print("\033[H\033[2J");
						out.close();
						s.close();
						System.out.println(str);  // DEBUG
						System.exit(0);
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
				
		}while (remain);
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }	
}
