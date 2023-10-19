package com.xdu.entity;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * rpc请求对象
 * */
public class RpcRequest implements Serializable {
 
    private static final AtomicLong INVOKE_ID = new AtomicLong(0);
 
    /**
     * 消息的唯一id（占8字节）
     * */
    private final long messageId;
 
    /**
     * 接口名
     * */
    private String interfaceName;
 
    /**
     * 方法名
     * */
    private String methodName;
 
    /**
     * 参数类型数组(每个参数一项)
     * */
    private Class<?>[] parameterClasses;
 
    /**
     * 实际参数对象数组(每个参数一项)
     * */
    private Object[] params;
 
    public RpcRequest() {
        // 每个请求对象生成时都自动生成单机全局唯一的自增id
        this.messageId = INVOKE_ID.getAndIncrement();
    }
}