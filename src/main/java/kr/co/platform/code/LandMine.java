package kr.co.platform.code;

import java.util.Arrays;
import java.util.Random;

public class LandMine {
	
	// 지뢰 표시
	private static final String MINE = " X "; 
	// 자신을 제외한 주변 8개지점에 지뢰가 하나도 없는 경우 표시
    private static final String NONE = " 0 "; 
    // 설치 할 지뢰 수
    private static final int MINE_CNT = 10; 
    // 로우 수 설정(10)
    private static final int ROW = 10; 
    // 컬럼 수 설정(10)
    private static final int COL = 10; 

    // 지뢰의 수 혹은 지뢰를 담은 배열(10X10)
    private static String[][] mineAndCountList; 
    
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
    	
    	LandMine landMine = new LandMine(); // 클래스 사용선언
    	
    	landMine.setMineLocation();
    	landMine.setBoardValues();
    	
    	System.out.println("ROW : " + ROW);
        System.out.println("COL : " + COL);
        System.out.println("Total Mine Count: " + COL);
        System.out.println("--------------------------------");

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(mineAndCountList[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Mine 위치 설정
     */
    public void setMineLocation() {
    	
    	Random random = new Random();
    	
        for (int i = 0; i < MINE_CNT; i++) {
        	int ranRow = random.nextInt(ROW);
            int ranCol = random.nextInt(COL);

            if (!isMine(ranRow, ranCol)) {
            	mineAndCountList[ranRow][ranCol] = MINE;
            }
		}
    }

    /**
     * 배열의 값(주변 mine 갯수)설정
     */
    public void setBoardValues() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (isNotMine(i, j)) mineAndCountList[i][j] = nearMineCheck(i, j);
            }
        }
    }

    /**
     * 기준 위치 주변 mine 갯수
     *
     * @param row
     * @param col
     * @return 주변 mine 갯수 String
     */
    public String nearMineCheck(int row, int col) {
        int count = 0;

        if (isMine(row - 1, col - 1)) count++;
        if (isMine(row - 1, col)) count++;
        if (isMine(row - 1, col + 1)) count++;
        if (isMine(row, col - 1)) count++;
        if (isMine(row, col + 1)) count++;
        if (isMine(row + 1, col - 1)) count++;
        if (isMine(row + 1, col)) count++;
        if (isMine(row + 1, col + 1)) count++;

        return " " + count + " ";
    }

    /**
     * 기준 위치의 값이 지롸인지 체크
     */
    private boolean isMine(int row, int col) {
        try {
            return mineAndCountList[row][col].equals(MINE);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
    
    /**
     * 기준 위치의 지뢰가 아닌경우를 리턴
     */
    private boolean isNotMine(int i, int j) {
        return !isMine(i, j);
    }
	
}
