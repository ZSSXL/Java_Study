package com.zss.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author ZSS
 * @date 2019/10/26 16:27
 * @description 自定义 @RequestParam 注解
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZssRequestParam {

    String value() default "";

}
