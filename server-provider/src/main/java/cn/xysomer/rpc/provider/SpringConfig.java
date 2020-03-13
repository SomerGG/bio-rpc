package cn.xysomer.rpc.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Somer
 * @Date 2020-03-10 20:21
 */
@Configuration
@ComponentScan(basePackages = "cn.xysomer.rpc.provider")
public class SpringConfig {

    @Bean(name = "rpcServer")
    public RpcServer rpcServer() {
        return new RpcServer(8080);
    }
}
