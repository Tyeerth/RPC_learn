package com.xdu.impl;


import com.xdu.interfaces.Animal;
import com.xdu.proxy.InvocationHandlerImpl;
import com.xdu.proxy.JdkProxy;

import java.lang.reflect.Proxy;

/**
 * @author tyeerth
 * @date 2023/9/4 - 下午7:30
 * @description
 */
public class test {
    public static void main(String[] args) {
        Cat cat = new Cat();
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(cat);
        Animal hh = (Animal)Proxy.newProxyInstance(cat.getClass().getClassLoader(), cat.getClass().getInterfaces(),invocationHandler);
        hh.printname("tom");

    }
}
