package kr.co.platform.code;

/**
 * @설명 : 주어진 수로 피보나치 수열 출력
 * @참고 : 보통 0번째는 0 또는 1로 시작하는 경우가 대부분인데 해당문제는 0부터 시작하는것을 전제로 함
 */
public class FibonacciNumbers {
	
	public static void main(String[] args) {
		
		long a = 0;
		long b = 1;
		
		long sum = 0;
		
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
