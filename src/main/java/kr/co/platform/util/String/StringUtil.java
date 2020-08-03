package kr.co.platform.util.String;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtil {
	public static Map<String, Object> getParameterMap(Enumeration<?> reqEnum, Map<String, String[]> paramMap){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		for(int i = 0; i < paramMap.size(); i++) {
			String paramName = (String)reqEnum.nextElement();
			resultMap.put(paramName, ((String[])paramMap.get(paramName))[0]);
//			System.out.println(paramName + " ? " + ((String[])paramMap.get(paramName))[0]);
		}
		
		return resultMap;
	}
	
	public static String returnParamString(Map<String, String> paramMap){
		if(!paramMap.isEmpty()) {
			try {
				StringBuffer strBuf = new StringBuffer();
				
				int idx = 0;
				for(String str : paramMap.keySet()) {
					if(idx == 0) {
						strBuf.append("?"+str+"="+paramMap.get(str));
					} else {
						strBuf.append("&"+str+"="+paramMap.get(str));
					}
					idx++;
				}
				
				return strBuf.toString();
			} catch(Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public static String returnParamsString(Map<String, Object> paramMap){
		if(!paramMap.isEmpty()) {
			try {
				StringBuffer strBuf = new StringBuffer();
				
				int idx = 0;
				for(String str : paramMap.keySet()) {
					if(idx == 0) {
						strBuf.append("?"+str+"="+paramMap.get(str));
					} else {
						strBuf.append("&"+str+"="+paramMap.get(str));
					}
					idx++;
				}
				
				return strBuf.toString();
			} catch(Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public static String nullcheck(String str, String Defaultvalue) throws Exception {
		String ReturnDefault = "";
		if (str == null || str.trim() == "") ReturnDefault = Defaultvalue; 
		else ReturnDefault = str;     
		return ReturnDefault;
	}
	
	public static int nullcheck(String str, int Defaultvalue) throws Exception {
		int ReturnDefault = 0;
		if (str == null || str.trim() == "") ReturnDefault = Defaultvalue; 
		else ReturnDefault = Integer.parseInt(str.trim());     
	    return ReturnDefault;
	}
	
	public boolean numberCheck(String in) {
		try {
			Integer nVar = Integer.parseInt(in);
			if(nVar instanceof Integer) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean datePatternCheck(String in) {
		String str_regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
		if(Pattern.matches(str_regex, in)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean detStrBytes(String _str, int _max) {
		if (_str.getBytes().length <= _max) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean numlistCheck(List<String> in) {
		try {
			boolean bool_result = false;
			for(String str : in) {
				if((Integer)Integer.parseInt(str) instanceof Integer) {
					bool_result = true;
				} else {
					bool_result = false;
					return bool_result;
				}
			}
			return bool_result;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean injectionCheck(String str) {
		boolean result = false;
		String[] arrWord = {"select", "update", "delete", "insert", "drop", "union", "script", "--", "'"};
		
		for (int i=0;i<arrWord.length;i++) {
			if (str.contains(arrWord[i])) {
				result = true;
			}
		}
		return result;
	}
	
	public static boolean isImageFile(String fileName) {
		String ext = getExt(fileName);

		return ext.equals(".gif") || ext.equals(".jpg") || ext.equals(".png") || ext.equals(".bmp");
	}
	
	public static String getExt(String szTemp) {
		if (szTemp == null) {
			return "";
		}

		String fname = "";

		if (szTemp.indexOf(".") != -1) {
			fname = szTemp.substring(szTemp.lastIndexOf("."));

			return fname;
		} else {
			return "";
		}
	}
	
	public static enum RandomType {
		LOWER_CASE, UPPER_CASE, NUMBER, LOWER_NUM, UPPER_NUM, ALPHA, ALL
	}
	public static String getRandomString(int generateNumber) {
		char[] arrLowerCase = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] arrUpperCase = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		int[] arrNumberCase = {0,1,2,3,4,5,6,7,8,9};
		Object[] arrObjCase = {arrLowerCase, arrUpperCase, arrNumberCase};
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < generateNumber; i++) {
			int value1 = randomIntegerRange(0, arrObjCase.length-1);
			Object arrObj = arrObjCase[value1];
			
			if(arrObj instanceof char[]) {
				char[] arrCh = (char[]) arrObj;
				char value2 = arrCh[randomIntegerRange(0, arrCh.length-1)];
				sb.append(value2);
			} else if(arrObj instanceof int[]) { 
				int[] arrCh = (int[]) arrObj;
				int value2 = arrCh[randomIntegerRange(0, arrCh.length-1)];
				sb.append(value2);
			}
		}
		
		return sb.toString();
	}
	
	public static int randomIntegerRange(int n1, int n2) {
        int result = (int)((Math.random() * (n2 - n1 + 1)) + n1);
        return result;
    }
	
	public static void createSecretKey() {
		FileOutputStream fos = null;
		
		try {
			String url = StringUtil.class.getResource("/kr/co/chacha/util/encrypt").getPath();
			File file = new File(url+"/key.cfg");
			
			if(!file.exists()) {
				String content = StringUtil.getRandomString(32);
				
				fos = new FileOutputStream(file);
				if (!file.exists()) file.createNewFile();
				byte[] contentInBytes = content.getBytes();

				fos.write(contentInBytes);
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fos != null) try{fos.close();}catch(IOException e){}
		}
	}
	
   public static String getDate(String type) {
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String Date="";
    	if(type.equals("start")) {
	    	 cal.add(Calendar.MONTH, -6);
	  	     cal.set(Calendar.DAY_OF_MONTH, 1);
	  	     Date = sdf.format(cal.getTime());
    	}else if (type.equals("end")) {
		  cal.add(Calendar.MONTH, -1);
	      cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	      Date = sdf.format(cal.getTime());
    	}
    	return Date;
    }

	
	public static List<String> getListfromCSV(String val){
	     
	    List<String> valList = new ArrayList<String>();	 
	    
	    if (val != null && !"".equals(val)) {
		    String[] aVal = val.split(",");
		    for(int i=0; i< aVal.length; i++){
		         
		    	valList.add(aVal[i].toString());
		    }
	    }	     
	 
	    return valList;       
	}
	
	// 이메일주소 형태 검증
	public static boolean isEmailAddress(String str) {
		if (Pattern.matches("^([a-zA-Z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", str)) {
			return true;
		} else {
			return false;
		}
	}
	
	// 휴대전화번호 형태 검증
	public static boolean isMobileNumber(String str) {
		if (str != null) {
			if (Pattern.matches("^([a-zA-Z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", str.replaceAll("\\D+",""))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}