package cn.xysomer.rpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Somer
 * @Date 2020-03-10 20:45
 */
@Configuration
public class SpringConfig {

    @Bean(name = "rpcClientProxy")
    public RpcClientProxy rpcClientProxy() {
        return new RpcClientProxy();
    }
}
