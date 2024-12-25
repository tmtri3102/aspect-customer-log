package cg.wbd.grandemonstration.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class Logger {

    @AfterThrowing(pointcut = "execution(public * cg.wbd.grandemonstration.service.CustomerService.*(..))", throwing = "e")
    public void error(Exception e) {
        System.out.println("[CMS] co loi xay ra: " + e.getMessage());
    }
    @AfterReturning(pointcut = "execution(public * cg.wbd.grandemonstration.service..CustomerService.*(..))")
    public void error(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("\n[CMS] co phuong thuc xay ra: %s.%s: %s", className, method, args);
    }
}
