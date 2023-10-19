package com.xdu.entity;

import java.io.Serializable;

/**
 * rpc响应对象
 * */
public class RpcResponse implements Serializable {
 
    /**
     * 消息的唯一id（占8字节）
     * */
    private long messageId;
 
    /**
     * 返回值
     */
    private Object returnValue;
 
    /**
     * 异常值
     */
    private Exception exceptionValue;
}