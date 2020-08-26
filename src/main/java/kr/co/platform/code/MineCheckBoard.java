package kr.co.platform.code;

import java.util.Arrays;
import java.util.Random;

import lombok.Getter;

@Getter
public class MineCheckBoard {

	/** 지뢰 */
	private String mine; 
	/** 설치 할 지뢰수 */
	private int mineCnt; 
	/** 기본 로우 수 */
	private int defaultRow; 
	/** 기본 컬럼 수 */
	private int defaultCol; 
	/** 지뢰 혹은 주변 지뢰수를 담은 배열 */
	private String[][] mineAndCountList;

	/** 
	 * <초기화 시 값 설정>
	 * mine : 지뢰
	 * defaultMineCount = 10
	 * defaultRow = 10
	 * defaultCol = 10
	 * mineAndCountList : 2중으로 설정된 배열이기 때문에 한번에 fill을 통해 초기화 할 수 없고, 
	 * ROW 만큼 반복문을 통해 ROW[COL]순번에 해당되는 요소를 0으로 초기화한다.
	 */ 
	public MineCheckBoard() {

		this.mine = " * ";

		this.mineCnt = 10;

		this.defaultRow = 10;

		this.defaultCol = 10;

		this.mineAndCountList = new String[defaultRow][defaultCol];
		for (String list[] : mineAndCountList) {
			Arrays.fill(list, " 0 ");    				
		}  	
	}

	/** 지뢰 배치(배치 위치는 랜덤) */
	public void setMine() {
		Random random = new Random();
		for (int i = 0; i < mineCnt; i++) {
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
		MineCheckBoard mineCheckBoard = new MineCheckBoard();
		/** 지뢰 배치 */
		mineCheckBoard.setMine(); 
		/** 자신의 위치가 지뢰가 아닌경우 자신을 제외한 주변 8칸의 지뢰수를 배치 */
		mineCheckBoard.setNearMineCount();     	

		System.out.println("----------지뢰찾기 출력----------");
		for (int i = 0; i < mineCheckBoard.getDefaultRow(); i++) {
			for (int j = 0; j < mineCheckBoard.getDefaultCol(); j++) {
				System.out.print(mineCheckBoard.getMineAndCountList()[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------");

	}

}
