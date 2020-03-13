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

    private String version;

    public RemoteInvocationHandler(String host, int port, String version) {
        this.host = host;
        this.port = port;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(this.version);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        Object result = rpcNetTransport.send(request);
        return result;
    }
}
