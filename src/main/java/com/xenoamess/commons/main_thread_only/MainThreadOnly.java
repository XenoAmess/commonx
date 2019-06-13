package com.xenoamess.commons.main_thread_only;

import java.lang.annotation.*;

/**
 * If the annotation {@code @MainThreadOnly} is present on the declaration
 * of a function <i>A</i>, then function {@code @A} must be called by main thread.
 * <p>
 * Other wise, an error might be thrown from inside function {@code @A}.
 *
 * @author XenoAmess
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MainThreadOnly {

}
