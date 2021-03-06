package kr.co.platform.code;

/**
 * @설명 : 체스판에서 흰칸에 존재하는 말의 수 구하기
 * @참고 : 말 -> F
 */
public class WhiteBoardChk {

	public static void main(String[] args) {
		
		String[] board = {"FFFFFFFF","FFFFFFFF","FFFFFFFF","FFFFFFFF","FFFFFFFF","FFFFFFFF","FFFFFFFF","FFFFFFFF"};
		int result = 0;
		for (int i=0; i<board.length; i++) {
			
			if((i+1) % 2 == 1) {
				for(int j=0; j<board[i].length(); j++) {
					if((j+1) % 2 == 1) {
						if(Character.toString(board[i].charAt(j)).equals("F")) {
							result++;
						}
					}
					
				}
			} else {
				for(int j=0; j<board[i].length(); j++) {
					if((j+1) % 2 == 0) {
						if(Character.toString(board[i].charAt(j)).equals("F")) {
							result++;
						}
					}
					
				}
			}
		}
		
		System.out.println("Result : " + result);
			
	}
	
}

//3 10 3 5
