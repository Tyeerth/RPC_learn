package com.xdu.proxy;

import com.xdu.HelloService;
import com.xdu.common.Invocation;
import com.xdu.common.URL;
import com.xdu.loadbalance.Loadbalance;
import com.xdu.protocol.HttpClient;
import com.xdu.register.LocalRegister;
import com.xdu.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author tyeerth
 * @date 2023/9/2 - 下午3:16
 * @description 接口的代理对象
 */
public class ProxyFactory {
    /**
     * 生成哪个接口的代理对象
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class interfaceClass) {
        // 用户配置
        //使用JDK动态代理
        // 第三个参数是用户代理逻辑
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override //调用对象  调用方法  调用的参数
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                //构建一个请求对象
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), objects);
                //通过网络发送请求
                HttpClient httpClient = new HttpClient();
                // TODO 思考：这个provider的IP地址是写死的，那么根据这个接口，知道这个接口具体的实现在哪个服务

                //根据注册中心拿到URL  服务发现
                List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());
//                System.out.println(urls);
//                System.out.println(LocalRegister.get(interfaceClass.getName()));
                //由于有多个节点提供了这个服务，所以需要负载均衡
                URL url = Loadbalance.random(urls);
                String result = null;
                // TODO  服务重试
                int max = 3;//重试3次
                while (max > 0) {

                    try {
                        result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                        System.out.println(result);
                    } catch (Exception e) {
                        if (max-- != 0) continue;
                        // TODO 容错
                        //一旦报错就执行下面这个类
                        // erroe-callback = com.xdu.HelloServiceErrorCallback implements HelloService
                        return "报错了";
                    }
                }
                return result;
            }
        });
        return (T) proxyInstance;
    }
}
