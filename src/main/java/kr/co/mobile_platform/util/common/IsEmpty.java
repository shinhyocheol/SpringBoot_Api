package kr.co.mobile_platform.util.common;

import java.util.List;
import java.util.Map;

public class IsEmpty {
	
	public static boolean check(Object obj) {
		if(obj == null) return true;
		if(obj instanceof Integer && (Integer)obj == 0) {return true;} 
		if((obj instanceof String) && (((String)obj).trim().length() == 0)) {return true;}
		if(obj instanceof Map) { return ((Map<?, ?>) obj).isEmpty(); }
        if(obj instanceof Map) { return ((Map<?, ?>)obj).isEmpty(); } 
        if(obj instanceof List) { return ((List<?>)obj).isEmpty(); }
        if(obj instanceof Object[]) { return (((Object[])obj).length == 0); }
		return false;
	}
	
}
