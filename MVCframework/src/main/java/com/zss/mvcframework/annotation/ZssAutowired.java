package com.zss.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author ZSS
 * @date 2019/10/26 16:27
 * @description 自定义 @Autowired注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZssAutowired {

    String value() default "";

}
