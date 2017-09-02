package cn.tedu.netctoss.service;
/**
 * 应用异常类
 * @author soft01
 *
 */
public class AppException extends RuntimeException {

	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}
	
}
