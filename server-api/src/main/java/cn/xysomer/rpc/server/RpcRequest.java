package cn.xysomer.rpc.server;

import java.io.Serializable;

/**
 * @Description 请求封装
 * @Author Somer
 * @Date 2020-03-10 19:48
 */
public class RpcRequest implements Serializable {

    private String className;//服务对应的类

    private String methodName;//对应调用的方法

    private Object[] parameters;//方法请求参数

    private String version;//服务版本号

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
