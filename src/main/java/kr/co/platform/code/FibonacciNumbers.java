package kr.co.platform.code;

public class FibonacciNumbers {
	
	public static void main(String[] args) {
		
		int a = 0;
		int b = 1;
		
		int sum = 0;
		
		for (int i = 0; i < 80; i++) {
			if (i > 0) {
				sum = a + b;				
			}
			
			a = b;
			b = sum;				

			System.out.println("SUM["+ i + "] : " + sum);
		}
		
	}
	
}
