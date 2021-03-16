package kr.co.platform.code;

import java.util.LinkedList;

public class Point {
	
	public static void main(String[] args) {
		
		int height = 17;
		int width = 5;
		
		int cnt = 0;

		if (height == 1) 
		{
			
			cnt = 1;
			
		} 
		else if (height == 2) 
		{
			
			cnt = Math.min(4, ((width+1) / 2));			
		
		} 
		else if (width < 7)
		{
		
			cnt = Math.min(4, width);				
			
		} 
		else 
		{
			
			cnt = width - 2;								
		
		}
		
		System.out.println(cnt);
		
	}
}
