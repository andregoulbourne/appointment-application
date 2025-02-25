package com.appointments.aspect;

import com.appointments.util.EncryptionUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspects {
    private static final Logger logger = LogManager.getLogger(ControllerAspects.class);

    @Autowired
    public ControllerAspects(EncryptionUtility encryptionUtility){
        this.encryptionUtility = encryptionUtility;
    }

    private final EncryptionUtility encryptionUtility;

    @Pointcut("execution(public * com.appointments.controller.AppointmentController.*(..))")
    public void allAppointmentControllerMethods() {}

    @Before("allAppointmentControllerMethods()")
    public void beforeAppointmentControllerCalls(JoinPoint joinPoint) {
        logger.info("Controller Method: {}", joinPoint.getSignature().getName());
        String token = (String) joinPoint.getArgs()[0];
        String userEmailId = encryptionUtility.decrypt(token);

        if(StringUtils.isBlank(userEmailId)){
            logger.error("Token is invalid ...");
            throw new IllegalStateException("Token is invalid");
        }
    }

}
