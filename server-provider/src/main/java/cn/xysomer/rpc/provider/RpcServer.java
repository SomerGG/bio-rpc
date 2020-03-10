package cn.xysomer.rpc.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description RPC 服务
 * @Author Somer
 * @Date 2020-03-10 20:04
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private int port;//服务对应的端口号

    private Map<String, Object> handlerMapping = new HashMap<>();//保存对外暴露的服务

    ExecutorService executorService = Executors.newCachedThreadPool();

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {//不断接收请求
                Socket socket = serverSocket.accept();//阻塞
                executorService.execute(new ProcessorHandler(handlerMapping, socket));//利用线程池处理每一个 socket 请求
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //handlerMapping初始化
        //获取添加了 RpcService 注解的服务信息
        Map<String, Object> servicesMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!servicesMap.isEmpty()) {
            for (Object serviceBean : servicesMap.values()) {
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);//取出服务上的注解
                String serviceName = rpcService.value().getName();
                String version = rpcService.version();
                if (!StringUtils.isEmpty(version)) {
                    serviceName += "-" + version;
                }
                handlerMapping.put(serviceName, serviceBean);
            }
        }
    }
}
