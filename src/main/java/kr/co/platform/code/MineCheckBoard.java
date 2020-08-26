package kr.co.platform.code;

import java.util.Arrays;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MineCheckBoard {

	/** 지뢰 */
	private String mine; 
	/** 설치 할 지뢰수 */
	private int defaultMineCnt; 
	/** 기본 로우 수 */
	private int defaultRow; 
	/** 기본 컬럼 수 */
	private int defaultCol; 
	/** 지뢰 혹은 주변 지뢰수를 담은 배열 */
	private String[][] mineAndCountList;

	/** 지뢰 배치(배치 위치는 랜덤) */
	public void setMine() {
		Random random = new Random();
		for (int i = 0; i < defaultMineCnt; i++) {
			int ranRow = random.nextInt(defaultRow);
			int ranCol = random.nextInt(defaultCol);
			/** 
			 * 배치하려는 위치가 이미 지뢰가 존재하여 중복될 수 있으므로 중복체크를 통해 
			 * 중복 시 i를 차감하고 해당 순번을 다시 돌아 다른곳에 지뢰 배치
			 */
			if(!mineCheck(ranRow, ranCol)) {
				mineAndCountList[ranRow][ranCol] = mine;            	
			} else {
				i--;				
			}
		}
	}

	/** 배열 내 기준 위치순번에 지뢰가 아닌 경우 주변 지역 지뢰 카운트 배치 */
	public void setNearMineCount() {
		for (int i = 0; i < defaultRow; i++) {
			for (int j = 0; j < defaultCol; j++) {
				if (!mineCheck(i, j)) mineAndCountList[i][j] = nearMineCheck(i, j);
			}
		}
	}

	/** 기준 위치(자신)를 제외한 주변 8개의 지뢰 존재 체크 및 지뢰수 집계 */
	public String nearMineCheck(int row, int col) {
		int nearMineCnt = 0;

		if (mineCheck(row - 1, col - 1)) 	nearMineCnt++;
		if (mineCheck(row - 1, col)) 		nearMineCnt++;
		if (mineCheck(row - 1, col + 1)) 	nearMineCnt++;
		if (mineCheck(row, col - 1)) 		nearMineCnt++;
		if (mineCheck(row, col + 1)) 		nearMineCnt++;
		if (mineCheck(row + 1, col - 1)) 	nearMineCnt++;
		if (mineCheck(row + 1, col)) 		nearMineCnt++;
		if (mineCheck(row + 1, col + 1)) 	nearMineCnt++;

		return " " + nearMineCnt + " ";
	}

	/**
	 * 기준 위치가 지뢰인 경우 true 리턴
	 * 이전 순번과 다음 순번이 없는 위치는 지뢰체크가 불가능하며 오류가 발생함. 
	 * 따라서 try~catch를 통해  false 리턴
	 */
	private boolean mineCheck(int row, int col) {
		try {
			return mineAndCountList[row][col].equals(mine);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		/** 클래스 사용과 동시에 빌더를 통한 각 변수들 기본 값 설정 
		 * <설정 값 설명>
		 * mine : 지뢰
		 * defaultMineCount = 10
		 * defaultRow = 10
		 * defaultCol = 10
		 * mineAndCountList : 2중으로 설정된 배열이므로 길이 설정을 빌더를 통해 설정한 후
		 * 상위 배열의 langth 만큼 반복문을 돌려 하위 배열의 요소를 모두 " 0 "으로 채운다.
		 */
		MineCheckBoard mineCheckBoard = MineCheckBoard.builder()
				.defaultRow(10)
				.defaultCol(10)
				.defaultMineCnt(10)
				.mine(" * ")
				.mineAndCountList(new String[10][10])
				.build();
		for (String list[] : mineCheckBoard.getMineAndCountList()) {
			Arrays.fill(list, " 0 ");    				
		}

		/** 지뢰 배치 */
		mineCheckBoard.setMine(); 
		/** 자신의 위치가 지뢰가 아닌경우 자신을 제외한 주변 8칸의 지뢰수를 배치 */
		mineCheckBoard.setNearMineCount();     	
		
		System.out.println("----------지뢰찾기 출력----------");
		for (String list[] : mineCheckBoard.getMineAndCountList()) {
			for (String value : list) {
				System.out.print(value);				
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
		
	}

}
