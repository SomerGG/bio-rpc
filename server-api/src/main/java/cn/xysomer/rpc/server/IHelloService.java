package cn.xysomer.rpc.server;

/**
 * @Description
 * @Author Somer
 * @Date 2020-03-10 19:46
 */
public interface IHelloService {

    String sayHello(String content);

    String saveUser(User user);
}
