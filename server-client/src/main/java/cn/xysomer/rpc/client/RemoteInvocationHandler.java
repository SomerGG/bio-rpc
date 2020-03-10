package cn.xysomer.rpc.client;

import cn.xysomer.rpc.server.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description RPC 服务调用拦截器
 * @Author Somer
 * @Date 2020-03-10 20:37
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in invoke");
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion("v1");
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object result = rpcNetTransport.send(request);
        return result;
    }
}
