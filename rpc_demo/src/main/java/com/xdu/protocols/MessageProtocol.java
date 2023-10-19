package com.xdu.protocols;

import java.io.Serializable;

public class MessageProtocol<T> implements Serializable {
    /**
     * 请求头
     * */
    private MessageHeader messageHeader;
 
    /**
     * 请求体(实际的业务消息对象)
     * */
    private T bizDataBody;

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public T getBizDataBody() {
        return bizDataBody;
    }

    public void setBizDataBody(T bizDataBody) {
        this.bizDataBody = bizDataBody;
    }
}