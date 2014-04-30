package com.tmg.ebscore.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimingAspect {
	
	private static Logger logger = Logger.getLogger(TimingAspect.class);

	@Around("execution(* com.tmg.ebscore.service.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object returnVal = joinPoint.proceed(); // continue on the intercepted method
		String staticPart = joinPoint.getStaticPart().toShortString();
		logger.debug("Method " + staticPart + " took "
				+ (System.currentTimeMillis() - startTime) + " milliseconds");
		
		return returnVal;
	}

}
