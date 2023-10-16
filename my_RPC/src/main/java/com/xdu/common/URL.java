package com.xdu.common;

import java.io.Serializable;

/**
 * @author tyeerth
 * @date 2023/9/2 - 下午4:05
 * @description
 */
public class URL implements Serializable { //因为URL要保存为文件的形式，所以需要实现序列化的接口
    private String hostname;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    private Integer port;
}
