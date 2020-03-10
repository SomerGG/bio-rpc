package cn.xysomer.rpc.provider;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 对外暴露的服务注解
 * @Author Somer
 * @Date 2020-03-10 20:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component//被 Spring 扫描
public @interface RpcService {

    Class<?> value();//暴露的服务

    String version() default "";//版本号
}
