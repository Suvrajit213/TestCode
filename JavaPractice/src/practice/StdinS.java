package practice;

import java.util.Scanner;

public class StdinS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		int i = scanner.nextInt();
		double d = scanner.nextDouble();
		

		
		System.out.println("Int:" + i);
		System.out.println("Double:" + d);
		System.out.println("String:" + s);

	}

}
