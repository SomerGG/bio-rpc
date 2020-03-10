package cn.xysomer.rpc.client;

import cn.xysomer.rpc.server.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description rpc 网络信息传输
 * @Author Somer
 * @Date 2020-03-10 20:30
 */
public class RpcNetTransport {

    private String host;

    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 发送请求
     *
     * @param request
     * @return
     */
    public Object send(RpcRequest request) {
        Object result = null;
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            socket = new Socket(host, port);//建立连接
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);//序列化
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.readObject();//反序列化得到返回结果
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
