package com.mycompany.myapp.exception;

//Spring에서 트랜잭션을 처리하려면 발생되는 예외가 
//반드시 RuntimeException이어야 합니다.
public class NoAccountException extends RuntimeException {
	public NoAccountException() {}
	public NoAccountException(String message) {
		super(message);
	}
}
