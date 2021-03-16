package kr.co.platform.code;

import javax.persistence.criteria.CriteriaBuilder.Case;

/**
 * @설명 : 로봇 조작
 */
public class Instructions {

	public static void main(String[] args) {
		
		String[] instructions = {"LEFT 5","TURN AROUND"};
		
		int result = 0;
		String[] command;
		
		for(int i=0; i<instructions.length; i++) 
		{
			
			if (instructions[i].equals("HALT")) 
			{
				
				// 즉시 명령 수행 중지
				break;
				
			} 
			else 
			{
				
				if (instructions[i].equals("TURN AROUND")) 
				{
					// 반바퀴 회전
					result += 180;
				} 
				else 
				{
					// 공백을 기준으로 현재 요소 분리
					command = instructions[i].split(" ");
					
					if (command.length == 1) 
					{
						
						// X 값이 없는 명령이므로 기준값 적용
						switch (command[0]) 
						{
						case "LEFT":
							result -= 90;
							break;
						case "RIGHT":
							result += 90;
						}
						
					} 
					else if (command.length > 1) 
					{
						
						// X 값이 있는 명령이므로 X값 적용
						switch (command[0]) 
						{
						case "LEFT":
							result -= Integer.parseInt(command[1]);
							break;
						case "RIGHT":
							result += Integer.parseInt(command[1]);
						}
					}					
				
				
				}
			}
			
		}

		// 시계방향으로 회전값을 구함. 따라서 결과가 음수일 경우 시계 방향이라는 것을 고려해 기존값은 0 -> 360으로 바꾸고 음수 값 더해서 회전 값 바꾸기
		if (result <= 0) 
		{
			result = 360 + result;
		}
		
		System.out.println(result);
		
	}
	
	
}
