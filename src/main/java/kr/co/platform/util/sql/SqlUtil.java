package kr.co.platform.util.sql;

public class SqlUtil {
	
	public static boolean injectionCheck(String str) {
		boolean result = false;
//		String[] arrWord = {"select", "update", "delete", "insert", "drop", "union", "script", "--", "'", "grant", "invoke", "alter"};
		String[] arrWord = {"select", "update", "delete", "insert", "drop", "union", "script", "'", "grant", "invoke", "alter"};
		
		for (int i = 0; i < arrWord.length; i++) {
			if (str.contains(arrWord[i])) {
				result = true;
			}
		}
		
		return result;
	}
}
