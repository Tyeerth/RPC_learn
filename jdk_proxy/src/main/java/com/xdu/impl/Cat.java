package com.xdu.impl;

import com.xdu.interfaces.Animal;

/**
 * @author tyeerth
 * @date 2023/9/4 - 下午7:47
 * @description 需要代理的实际对象
 */
public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("cat");
    }

    @Override
    public void printname(String name) {
        System.out.println(name);
    }
}
