package com.twodevsstudio.court.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class LoggingAspect {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Before("@annotation(com.twodevsstudio.court.utils.Loggable)")
	public void logBefore(JoinPoint joinPoint) {

		logger.info(joinPoint.getSignature().toShortString() + " begins with args:\n\t\t" + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "@annotation(com.twodevsstudio.court.utils.Loggable)", returning = "returning")
	public void logAfterReturning(JoinPoint joinPoint, Object returning) {
		logger.info(joinPoint.getSignature().toShortString() + " returns:\n\t\t" + returning);
	}

	@AfterThrowing(pointcut="execution(* com.twodevsstudio.court..*(..))",
			throwing="exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error(joinPoint.getSignature().toString() + " throws:\n\t\t" + exception.getMessage());
	}
}
