package com.jtudy.springaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import com.jtudy.springaspect.annotations.Duration;

/*
 * @Configuration: indicates that this file contains a Spring Bean Configuration for an Aspect.
 * 
 * For a core Spring application, the configuration is required to be done in spring configuration xml:
  
   xmlns:aop="http://www.springframework.org/schema/aop"
   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd"
 
    <aop:aspectj-autoproxy>
        <aop:include name="durationLoggingAspect"/>
        <aop:include name="methodLoggingAspect"/>
    </aop:aspectj-autoproxy>

    <bean name="durationLogging" class="com.jtudy.springaspect.DurationLogging"/>
    <bean name="serviceLogging" class="com.jtudy.springaspect.ServiceLogging"/>
    
    and in pom.xml:
    <dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-aop</artifactId>
	    <version>5.0.1.RELEASE</version>
	    <scope>compile</scope>
	</dependency>
	<dependency>
	    <groupId>org.aspectj</groupId>
	    <artifactId>aspectjweaver</artifactId>
	    <version>1.8.12</version>
	    <scope>compile</scope>
	</dependency>
 */
@Aspect
@Configuration
public class DurationLogging {

	@Around(value = "@annotation(durationAnnotation)")
    public Object logDuration(final ProceedingJoinPoint pjp, final Duration durationAnnotation) throws Throwable {
        try
        {
            StopWatch stopwatch = new StopWatch();
            stopwatch.start();
            Object returnValue = pjp.proceed();
            System.out.println(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + " --> " + stopwatch);          
            return returnValue;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }
}
