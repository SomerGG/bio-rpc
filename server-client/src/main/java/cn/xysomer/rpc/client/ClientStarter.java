package cn.xysomer.rpc.client;

import cn.xysomer.rpc.server.IHelloService;
import cn.xysomer.rpc.server.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description 客户端调用测试
 * @Author Somer
 * @Date 2020-03-10 20:48
 */
public class ClientStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcClientProxy rpcClientProxy = applicationContext.getBean(RpcClientProxy.class);
        IHelloService helloService = rpcClientProxy.clientProxy(IHelloService.class, "localhost", 8080);
        String result = helloService.sayHello("Somer");
        System.out.println(result);
        String saveUserResult = helloService.saveUser(new User("Somer", 18));
        System.out.println(saveUserResult);
    }
}
