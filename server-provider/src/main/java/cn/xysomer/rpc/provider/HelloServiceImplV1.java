package cn.xysomer.rpc.provider;

import cn.xysomer.rpc.server.IHelloService;
import cn.xysomer.rpc.server.User;


/**
 * @Description
 * @Author Somer
 * @Date 2020-03-10 19:53
 */
@RpcService(value = IHelloService.class, version = "v1")
public class HelloServiceImplV1 implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("【v1.0】 sayHello：" + content);
        return "【v1.0】sayHello：" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【v1.0】saveUser：" + user);
        return "【v1.0】SUCCESS";
    }
}
