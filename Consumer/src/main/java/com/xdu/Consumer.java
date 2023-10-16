package com.xdu;

import com.xdu.proxy.ProxyFactory;

/**
 * @author tyeerth
 * @date 2023/8/29 - 下午4:29
 * @description
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String s = helloService.printHello("zhangsan");
        System.out.println(s);


    }
}
