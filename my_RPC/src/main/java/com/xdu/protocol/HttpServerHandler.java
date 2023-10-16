package com.xdu.protocol;

import com.xdu.common.Invocation;
import com.xdu.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tyeerth
 * @date 2023/9/1 - 上午10:06
 * @description
 */
public class HttpServerHandler {

    public void handle(ServletRequest request, ServletResponse response){
        //    用于处理请求-->接口、方法、方法参数
        //使用jdk序列化的方式进行反序列化 TODO 思考为什么需要进行序列化
        try {
            //获取invocation
            Invocation invocation =  (Invocation) new ObjectInputStream(request.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            // TODO 如何根据接口找到对应的实现类
            Class implClass = LocalRegister.get(interfaceName);
            //根据方法名和方法参数获得具体的方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            //调用这个方法,并得到返回的结果
            String message = (String) method.invoke(implClass.newInstance(), invocation.getParameters());
            //写出结果
            IOUtils.write(message,response.getOutputStream());

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
