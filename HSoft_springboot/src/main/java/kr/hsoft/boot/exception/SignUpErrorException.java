package kr.hsoft.boot.exception;

public class SignUpErrorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10002L;
	private String column;
	private String errorInfo;
	
	public SignUpErrorException(String column, String errorInfo) {
		// TODO Auto-generated constructor stub
		super();
		this.column = column;
		this.errorInfo = errorInfo;
	}

	public String getColumn() {
		return column;
	}

	public String getErrorInfo() {
		return errorInfo;
	}
}
