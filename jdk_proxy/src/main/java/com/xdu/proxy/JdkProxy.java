package com.xdu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tyeerth
 * @date 2023/9/4 - 下午7:18
 * @description 生成接口的代理对象
 */
public class JdkProxy{
    private static Class proxy;
    public JdkProxy(Class proxy){
        this.proxy = proxy;
    }

    /**
     * 生成接口的代理对象
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class implclass){
        Object proxyInstance = Proxy.newProxyInstance(implclass.getClassLoader(), implclass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("执行了代理方法");
                return method.invoke(proxy.newInstance(), objects);
            }
        });
        return (T) proxyInstance;
    }
}
