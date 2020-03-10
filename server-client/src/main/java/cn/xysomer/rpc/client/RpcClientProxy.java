package cn.xysomer.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @Description RPC 客户端代理类
 * @Author Somer
 * @Date 2020-03-10 20:35
 */
public class RpcClientProxy {

    /**
     * 生成代理
     *
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public <T> T clientProxy(final Class<?> interfaceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new RemoteInvocationHandler(host, port));
    }
}
