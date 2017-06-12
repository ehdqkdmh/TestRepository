package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Aspect
public class Exam14Aspect {
	private static final Logger logger = LoggerFactory.getLogger(Exam14Aspect.class);
	
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam14AopController.*(..))")
	private void runtimeCheck() {}
	
	@Around("runtimeCheck()")
	public Object runtimeCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//전처리
		String signatureString = joinPoint.getSignature().toShortString();
		logger.info(signatureString + " 시작");
		long start = System.nanoTime();
		
		//메소드 실행
		Object result = joinPoint.proceed();
		
		//후처리
		long finish = System.nanoTime();
		logger.info(signatureString + " 종료");
		long runtime = finish-start;
		logger.info(signatureString + " 실행 시간: " + runtime + "ns");
		
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		requestAttributes.setAttribute("runtime", runtime, RequestAttributes.SCOPE_REQUEST);
		
		return result;
	}
	
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam14AopController.exam02*(..))")
	private void loginCheck() {}
	
	@Around("loginCheck()")
	public Object loginCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		String mid = (String)requestAttributes.getAttribute("sessionMid", RequestAttributes.SCOPE_SESSION);
		if(mid == null) {
			requestAttributes.setAttribute("loginNeed", "로그인이 필요합니다.", RequestAttributes.SCOPE_REQUEST);
			return "aop/exam02LoginForm";
		} else {
			Object result = joinPoint.proceed(); 
			return result;
		}
	}
}
