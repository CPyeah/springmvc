package org.cp.springmvc.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler {
	
//	@ExceptionHandler({RuntimeException.class})
//	public String runtimeExceptionHandle(Exception ex) {
//		System.out.println("RuntimeException: "+ex.getMessage());
//		return "error";
//	}

}
