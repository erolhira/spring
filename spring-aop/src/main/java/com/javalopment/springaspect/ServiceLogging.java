package com.javalopment.springaspect;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceLogging {

	boolean logDuration = false;

	@Around("execution(* com.javalopment.springaspect..*(..))" 
			+ "&& !execution(* com.javalopment.springaspect..*.get(..)) "
			+ "&& !@annotation(com.javalopment.springaspect.annotations.DontLog) "
            + "&& !@target(com.javalopment.springaspect.annotations.DontLog)"
			)			
	public Object logMethodCall(final ProceedingJoinPoint joinPoint) throws Throwable {

		String signatureName = getSignatureName(joinPoint);

		if (signatureName != null) {
			log("<" + signatureName + ">");
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				String args = toAspectString(joinPoint.getArgs());
				log("<args>" + replace(args) + "</args>");
			}
		}

		Object retVal = null;
		long st = System.currentTimeMillis();
		try {
			retVal = joinPoint.proceed();
		} finally {
			if (signatureName != null) {
				if (retVal != null) {
					log("<retval>" + toAspectString(retVal).replaceAll("[$]", "") + "</retval>");
				}
				if (logDuration) {
					long ft = System.currentTimeMillis();
					log("<duration>" + (ft - st) + " ms.</duration>");
				}

				log("</" + signatureName + ">");
			}
		}

		return retVal;
	}

	public String replace(String text) {
		text = text.replaceAll("\\r|\\n", "").replaceAll("[$]", "");
		return text;
	}

	public String getSignatureName(ProceedingJoinPoint joinPoint) {
		String signatureName = null;
		if (joinPoint != null && joinPoint.getSignature() != null) {

			if (joinPoint.getTarget() != null && joinPoint.getTarget().getClass() != null
					&& joinPoint.getTarget().getClass().getSimpleName() != null) {
				signatureName = (joinPoint.getTarget().getClass().getSimpleName() + "."
						+ joinPoint.getSignature().getName()).replaceAll("[$]", "");
			} else if (joinPoint.getSignature().getDeclaringType() != null
					&& joinPoint.getSignature().getDeclaringType().getName() != null) {
				signatureName = (joinPoint.getSignature().getDeclaringType().getName() + "."
						+ joinPoint.getSignature().getName()).replaceAll("[$]", "");
			}
			signatureName = filterSignatureName(signatureName);
		}

		return signatureName;
	}

	private String filterSignatureName(String signatureName) {
		if (signatureName.startsWith("Proxy")) {
			signatureName = null;
		}
		return signatureName;
	}

	public void log(String text) {
		System.out.println(text);
	}

	public static String toAspectString(Collection<?> col) {
		StringBuilder str = new StringBuilder();
		if (col != null) {
			col.stream().forEach(obj -> str.append(toAspectString(obj)));
		}
		return str.toString();
	}

	public static String toAspectString(Object obj) {
		StringBuilder str = new StringBuilder();
		if (obj != null && obj.getClass() != null && obj.getClass().isArray()) {
			str.append(toAspectString(Arrays.asList((Object[]) obj)));
		} else if (obj != null && obj instanceof Collection<?>) {
			str.append(toAspectString((Collection<?>) obj));
		} else if (obj != null) {
			if (!(obj.getClass().getSimpleName().equals("SOAPMessageContext"))) {
				try {
					str.append(ReflectionToStringBuilder.toString(obj, ToStringStyle.SHORT_PREFIX_STYLE));
					// str.append(ReflectionToStringBuilder.toString(obj, new
					// RecursiveToStringStyle()));
				} catch (Exception e) {
				}
			}
		}

		String result = str.toString().replaceAll("[<]", "").replaceAll("[>]", "");
		return result;
	}
}
