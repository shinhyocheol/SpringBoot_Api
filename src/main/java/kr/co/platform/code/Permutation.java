package kr.co.platform.code;

import java.util.ArrayList;
import java.util.Scanner;

public class Permutation {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		ArrayList picked = new ArrayList<Integer>();
		boolean[] check = new boolean[n+1];
		
		pick(n, picked, check);
	}
	
	public static void pick(int n, ArrayList picked, boolean[] check) {
		if(picked.size() == n) {
			printAnswer(picked);
		}
		
		for (int i = 0; i < n; i++) {
			
			if(check[i])
				continue;
			
			check[i] = true;
			picked.add(i);
			pick(n, picked, check);
			picked.remove(picked.size()-1);
			check[i] = false;
			
		}
	}
	
	public static void printAnswer(ArrayList picked) {
		for(int i = 0; i < picked.size(); i++) {
			System.out.print(picked.get(i) + " ");
		}
		System.out.println();
	}
}
