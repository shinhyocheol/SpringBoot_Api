package kr.co.platform.code;

import static org.junit.Assert.assertArrayEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class CrossTheBridge {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arrays = new int[n];
		for (int i = 0; i < n; i++) {
			arrays[i] = Integer.parseInt(br.readLine());

		}

		Arrays.sort(arrays);

		int result = 0;

		System.out.println();

		result =+ sum(arrays, 0, n-1);
		
		System.out.println("\r\n" + result);


	}

	public static int sum(int[] arrays, int first, int last) {
		int temp = 0;
		
		int A = arrays[first];

		if(first == last)
		{
			return A;
		}
		
		int Z = arrays[last];
		if(first == last - 1)
		{
			return Z;		
		}
		

		int B = arrays[first + 1];
		if(first == last-2)
		{
			return B + A + Z;
		}
		
		int Y = arrays[last - 1];
		if((B + A + Z + B) <= (Z + A + Y + A))
		{
			temp = sum(arrays, first, last - 2);
			temp += B + A + Z + B;
		}
		else
		{
			temp = sum(arrays, first, last - 2);
			temp += Z + A + Y + A;
		}

		return temp;
	}



}
