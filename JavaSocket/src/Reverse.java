import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

//Castelletto Davide 5CIA 10/10/2021

public class Reverse 
{
	public String reverse(String in)
	{
		StringBuilder p = new StringBuilder(in);
		p.reverse();
		
		return p.toString();
	}
}
