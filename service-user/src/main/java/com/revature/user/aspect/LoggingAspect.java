package com.revature.user.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Before advice: Executes before the target method in all the controllers
    @Before("execution(* com.revature.user.controller.buyerController.*(..)) || " +
            
            "execution(* com.revature.user.controller.PaymentController.*(..)) || " +
            "execution(* com.revature.user.controller.ProductController.*(..)) || " +
            "execution(* com.revature.user.controller.SellerController.*(..)) || " +
            "execution(* com.revature.user.controller.UserController.*(..)) || " +
            "execution(* com.revature.user.controller.ViewController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    // AfterReturning advice: Executes after the target method in all the controllers returns successfully
    @AfterReturning(pointcut = "execution(* com.revature.user.controller.buyerController.*(..)) || " +
                               
                                "execution(* com.revature.user.controller.PaymentController.*(..)) || " +
                                "execution(* com.revature.user.controller.ProductController.*(..)) || " +
                                "execution(* com.revature.user.controller.SellerController.*(..)) || " +
                                "execution(* com.revature.user.controller.UserController.*(..)) || " +
                                "execution(* com.revature.user.controller.ViewController.*(..))", 
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} executed successfully with return value: {}", joinPoint.getSignature(), result);
    }

    // AfterThrowing advice: Executes if a target method in all the controllers throws an exception
    @AfterThrowing(pointcut = "execution(* com.revature.user.controller.buyerController.*(..)) || " +
                               
                               "execution(* com.revature.user.controller.PaymentController.*(..)) || " +
                               "execution(* com.revature.user.controller.ProductController.*(..)) || " +
                               "execution(* com.revature.user.controller.SellerController.*(..)) || " +
                               "execution(* com.revature.user.controller.UserController.*(..)) || " +
                               "execution(* com.revature.user.controller.ViewController.*(..))", 
                    throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception thrown in method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
        logger.error("Exception message: {}", error.getMessage());
    }
}