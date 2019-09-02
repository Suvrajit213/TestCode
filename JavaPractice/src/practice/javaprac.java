package practice;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class javaprac {

		// TODO Auto-generated method stub
		
		public static void main(String[] args) {
		    Scanner s  = new Scanner (System.in);
		    System.out.print("Enter a number");
		    int n = s.nextInt ();
		    if(n%2 != 0)
		      System.out.println("weird");
		      else if (n%2 == 0 && n>=2 && n<=5)
		          System.out.println("Not weird");
		     else if (n% 2 == 0 && n>=6 && n<= 20)
		          System.out.println("weird");
		     else if (n%2 == 0 && n>=20)
		       System.out.println("Not weird");

}

}
