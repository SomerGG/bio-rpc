package cn.xysomer.rpc.provider;

import cn.xysomer.rpc.server.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Description 请求处理
 * @Author Somer
 * @Date 2020-03-10 20:09
 */
public class ProcessorHandler implements Runnable {

    private Map<String, Object> handlerMapping;//调用的服务

    private Socket socket;

    public ProcessorHandler(Map<String, Object> handlerMapping, Socket socket) {
        this.handlerMapping = handlerMapping;
        this.socket = socket;
    }

    @Override
    public void run() {
        //根据 socket 进行处理
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            //根据输入流反序列化获取请求信息
            RpcRequest request = (RpcRequest) inputStream.readObject();
            //根据获取到的请求信息执行方法调用
            Object result = invoke(request);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //把方法执行结果进行返回
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String serviceName = request.getClassName();//获取请求对应的类
        String version = request.getVersion();//获取请求的版本号
        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }
        Object service = handlerMapping.get(serviceName);
        if (null == service) {
            throw new RuntimeException("service not found：" + serviceName);
        }
        Class serviceClass = Class.forName(request.getClassName());
        Method method;
        Object[] args = request.getParameters();//获取客户端请求参数
        if (null != args) {
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            method = serviceClass.getMethod(request.getMethodName(), types);
        } else {
            method = serviceClass.getMethod(request.getMethodName());
        }
        Object result = method.invoke(service, args);//反射调用
        return result;
    }
}
