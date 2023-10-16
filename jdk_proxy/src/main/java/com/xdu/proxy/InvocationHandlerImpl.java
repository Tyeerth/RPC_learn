package com.xdu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tyeerth
 * @date 2023/9/5 - 上午9:51
 * @description
 */
public class InvocationHandlerImpl implements InvocationHandler {

    //代理的实际对象，一般为具体的实例化对象
    private Object object;
    public InvocationHandlerImpl(Object object) {
        this.object = object;
    }

    /**
     *
     * @param o 代理类实例
     * @param method 调用的方法
     * @param objects 调用的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//在代理真实对象前我们可以添加一些自己的操作
        System.out.println("调用之前");
        System.out.println("Method:" + method);
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object returnValue = method.invoke(object, objects);
        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("调用之后");
        return returnValue;
    }
}
