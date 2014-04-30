package com.tmg.uifwk.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimingAspect {

	private static Logger logger = Logger.getLogger(TimingAspect.class);

	@Around("execution(public * com.tmg.uifwk.service.ui.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object returnVal = joinPoint.proceed(); // continue on the intercepted
												// method
		String staticPart = joinPoint.getStaticPart().toShortString();
		logger.debug("Method " + staticPart + " took (milliseconds) :"
				+ (System.currentTimeMillis() - startTime));

		return returnVal;
	}

}
