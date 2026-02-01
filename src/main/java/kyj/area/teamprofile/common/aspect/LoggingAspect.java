package kyj.area.teamprofile.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* kyj.area.teamprofile.domain.member.service.MemberService.*(..))")
    public Object logMember(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String methodName = joinPoint.getSignature().getName();

        log.info("[API - LOG] URL: {}, {}", request.getMethod(), request.getRequestURL());
        log.info("[API - LOG] Method: {}", methodName);

        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("[API - LOG] Error : {}", e.getMessage());
            throw e;
        }

        return result;
    }
}
