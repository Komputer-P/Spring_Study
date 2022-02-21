package springbook.learningtest.spring;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springbook.learningtest.spring.pointcut.Bean;
import springbook.learningtest.spring.pointcut.Target;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PointcutExpressionTest {
    @Test
    public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int " + "springbook.learningtest.spring.pointcut.Target.minus(int,int) " + "throws java.lang.RuntimeException)");

        // Target.minus()
        assertThat(pointcut.getClassFilter().matches(Target.class) &&
                pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class), null), is(true));

        // Target.plus()
        assertThat(pointcut.getClassFilter().matches(Target.class) &&
                pointcut.getMethodMatcher().matches(Target.class.getMethod("plus", int.class, int.class), null), is(false));

        // Bean.method
        assertThat(pointcut.getClassFilter().matches(Bean.class) &&
                pointcut.getMethodMatcher().matches(Target.class.getMethod("method"), null), is(false));
    }

    @Test
    public void pointcut() throws Exception {
        targetClassPointcutMatches("execution(* *(..))", true, true, true, true, true, true);
        // 나머지 추가...
    }

    public void targetClassPointcutMatches(String expression, boolean... expected) throws Exception {
        pointcutMatchers(expression, expected[0], Target.class, "hello");
        pointcutMatchers(expression, expected[1], Target.class, "hello", String.class);
        pointcutMatchers(expression, expected[2], Target.class, "plus", int.class, int.class);
        pointcutMatchers(expression, expected[3], Target.class, "minus", int.class, int.class);
        pointcutMatchers(expression, expected[4], Target.class, "method");
        pointcutMatchers(expression, expected[5], Bean.class, "method");
    }

    public void pointcutMatchers(String expression, Boolean expected, Class<?> clazz, String methodName, Class<?>... args) throws Exception {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        assertThat(pointcut.getClassFilter().matches(clazz) &&
                pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null), is(expected));
    }
}
