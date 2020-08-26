package kr.co.platform.code;

import java.util.Arrays;
import java.util.Random;

public class LandMine {
	
	// 자신을 제외한 주변 8개지점에 지뢰가 하나도 없는 경우 표시(기본값)
	private static final String NONE = " 0 "; 
	// 지뢰
	private static final String MINE = " * "; 
    
    // 설치 할 지뢰 수
    private static final int DEFAULT_MINE_CNT = 10; 
    
    // 로우 수 설정(10)
    private static final int ROW = 10; 
    // 컬럼 수 설정(10)
    private static final int COL = 10; 

    // 지뢰의 수 혹은 지뢰를 담은 배열(10X10)
    private String[][] mineAndCountList; 
    
    /**
     * 리스트 초기화
     * fill : java method
     * 2차원 배열이기 때문에 한번에 fill을 통해 초기화 할 수 없고, ROW 수만큼 만복하여 COL에 해당되는 요소를 초기화한다.
     */
    public LandMine() {
    	mineAndCountList = new String[ROW][COL];
    	for (String init[] : mineAndCountList) {
    		Arrays.fill(init, NONE);    				
		}    	
    }
    
    public static void main(String[] args) {
    	
    	LandMine landMine = new LandMine();
    	
    	/** 지뢰 배치 */
    	landMine.setMine(); 
    	/** 자신의 위치가 지뢰가 아닌경우 자신을 제외한 주변 8칸의 지뢰 수를 배치 */
    	landMine.setNearMineCount(); 
    	
    	/** 출력 */
    	System.out.println("ROW : " + ROW);
        System.out.println("COL : " + COL);
        System.out.println("Total Mine Count: " + COL);

        System.out.println("--------------------------------");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(landMine.mineAndCountList[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    /** 지뢰 배치(배치 위치는 랜덤) */
    public void setMine() {
    	
    	Random random = new Random();
    	
        for (int i = 0; i < DEFAULT_MINE_CNT; i++) {
        	int ranRow = random.nextInt(ROW);
            int ranCol = random.nextInt(COL);
            
            /** 배치할 위치가 중복될 수 있으므로 중복체크 */
            if (!isMine(ranRow, ranCol)) {
            	mineAndCountList[ranRow][ranCol] = MINE;
            }
		}
    }

    /** 배열 내 기준 위치순번에 지뢰가 아닌 경우 주변 지역 지뢰 카운트 배치 */
    public void setNearMineCount() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (isNotMine(i, j)) mineAndCountList[i][j] = nearMineCheck(i, j);
            }
        }
    }

    /** 기준 위치(자신)를 제외한 주변 8개의 지뢰 존재 체크 및 지뢰 수 집계 */
    public String nearMineCheck(int row, int col) {
        int mineCnt = 0;
        
        if (isMine(row - 1, col - 1)) mineCnt++;
        if (isMine(row - 1, col)) mineCnt++;
        if (isMine(row - 1, col + 1)) mineCnt++;
        if (isMine(row, col - 1)) mineCnt++;
        if (isMine(row, col + 1)) mineCnt++;
        if (isMine(row + 1, col - 1)) mineCnt++;
        if (isMine(row + 1, col)) mineCnt++;
        if (isMine(row + 1, col + 1)) mineCnt++;

        return " " + mineCnt + " ";
    }

    /**
     * 기준 위치가 지뢰인 경우 true 리턴
     * 이전 순번과 다음 순번이 없는 위치는 지뢰체크가 불가능하며 오류가 발생함. 
     * 따라서 try~catch를 통해  false를 리턴하고 지뢰체크에서 제외 
     */
    private boolean isMine(int row, int col) {
        try {
            return mineAndCountList[row][col].equals(MINE);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
    /** 기준 위치가 지뢰가 아닌경우 false 리턴 */
    private boolean isNotMine(int i, int j) {
        return !isMine(i, j);
    }
	
}
