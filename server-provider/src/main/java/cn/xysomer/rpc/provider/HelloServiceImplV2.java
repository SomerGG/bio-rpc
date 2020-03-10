package cn.xysomer.rpc.provider;

import cn.xysomer.rpc.server.IHelloService;
import cn.xysomer.rpc.server.User;


/**
 * @Description
 * @Author Somer
 * @Date 2020-03-10 19:53
 */
@RpcService(value = IHelloService.class, version = "v2")
public class HelloServiceImplV2 implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("【v2.0】 sayHello：" + content);
        return "【v2.0】 sayHello：" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【v2.0】saveUser：" + user);
        return "【v2.0】 SUCCESS";
    }
}
