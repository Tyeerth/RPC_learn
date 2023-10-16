package com.xdu.loadbalance;

import com.xdu.common.URL;

import java.util.List;
import java.util.Random;

/**
 * @author tyeerth
 * @date 2023/9/2 - 下午4:27
 * @description
 */
public class Loadbalance {
    /**
     * 提供一个随机策略
     */
    public static URL random(List<URL> urls){
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
