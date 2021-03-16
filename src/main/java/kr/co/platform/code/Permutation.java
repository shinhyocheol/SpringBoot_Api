package kr.co.platform.code;

/*
 * @설명 : n으로 나열되는 모든 순열을 출력
 * @참고 : n > 0
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Permutation {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		ArrayList<Integer> picked = new ArrayList<Integer>();
		boolean[] check = new boolean[n+1];
		
		pick(n, picked, check);
	}
	
	public static void pick(int n, ArrayList<Integer> picked, boolean[] check) {
		
		if(picked.size() == n) {
			for(int i = 0; i < picked.size(); i++) {
				int temp = picked.get(i);
				System.out.print(temp + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < n; i++) {
			
			System.out.println(i);
			
			if(check[i])
				continue;
			check[i] = true;
			
			picked.add(i);
			pick(n, picked, check);
			
			picked.remove(picked.size() - 1);
			
			check[i] = false;
			
		}
	}
	
}
