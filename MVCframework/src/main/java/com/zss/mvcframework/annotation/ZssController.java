package com.zss.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author ZSS
 * @date 2019/10/26 16:27
 * @description 自定义 @Controller 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZssController {

    String value() default "";

}
