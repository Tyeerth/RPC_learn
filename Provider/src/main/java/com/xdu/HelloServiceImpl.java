package com.xdu;

/**
 * @author tyeerth
 * @date 2023/8/29 - 下午4:22
 * @description
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String printHello(String name) {
        System.out.println("hello, "+name);
        return "hello, "+name;
    }
}
