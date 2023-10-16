package com.xdu;


import com.xdu.common.URL;
import com.xdu.protocol.HttpServer;
import com.xdu.register.LocalRegister;
import com.xdu.register.MapRemoteRegister;

/**
 * @author tyeerth
 * @date 2023/8/29 - 下午4:41
 * @description
 */
public class Provider {
    public static void main(String[] args) {
        System.out.println("模拟服务器端启动");
//        tomcat
        //将使用的接口及其实现类放入到容器中
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        URL url = new URL("localhost",8080);
        // 同时也放到注册中心
        MapRemoteRegister.register(HelloService.class.getName(),url);

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());
    }
}
