package shop.jtoon.global.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EventListener
public @interface AsyncEventListener {
	Phase phase() default Phase.AFTER_COMMIT;

	boolean fallbackExecution() default false;

	@AliasFor(
		annotation = EventListener.class,
		attribute = "classes"
	)
	Class<?>[] value() default {};

	@AliasFor(
		annotation = EventListener.class,
		attribute = "classes"
	)
	Class<?>[] classes() default {};

	@AliasFor(
		annotation = EventListener.class,
		attribute = "condition"
	)
	String condition() default "";

	@AliasFor(
		annotation = EventListener.class,
		attribute = "id"
	)
	String id() default "";
}
