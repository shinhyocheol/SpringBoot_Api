package kr.co.platform.util.advice.exception;

public class Code700Exception extends RuntimeException{
	
	public Code700Exception(String msg, Throwable t) {
		super(msg, t);
	}
	
	public Code700Exception(String msg) {
		super(msg);
	}
	
	public Code700Exception() {
		super();
	}
	
}
