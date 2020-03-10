package cn.xysomer.rpc.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 服务启动，将对外暴露的服务注册到容器中
 * @Author Somer
 * @Date 2020-03-10 20:23
 */
public class ProviderStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
    }
}
