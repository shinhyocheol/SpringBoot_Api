package kr.co.platform.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @설명 : 연락처 정렬하기
 */
public class MemberSort {

	 //표준입력을 수행할 Scanner를 선언한다 
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {   //프로그램의 시작부 
			
			int n = scanner.nextInt();
			
			List<Member> list = new ArrayList<>();
			
			for (int i=0; i<n; i++) {
				
				String name = scanner.next();
				String mobile = scanner.next();
				
				list.add(new Member(name, mobile));
			}
			
			// 리스트에 저장 후 Comparator 인터페이스를 통해 이름순으로 먼저 정렬 후 연락처순으로 한번 더 정렬
			list.sort(Comparator
					.comparing(Member::getName)
					.thenComparing(Member::getName)
					.thenComparing(Member::getMobile));;
			
			// 출력
			System.out.println();
			for (Member member : list) {
				System.out.println(member);
			}
			
    }
	
}

// 입력받은 이름과 연락처를 받아 리스트에 저장 및 출력기능을 포함한 클래스
class Member{
	
	private String name;
	private String mobile;
	
	public Member(String name, String mobile) {
		this.name = name;
		this.mobile = mobile;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return this.mobile;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(this.name).append(" : "); 
		sb.append(this.mobile); 
		return sb.toString();
	}

}
