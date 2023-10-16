package com.xdu.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tyeerth
 * @date 2023/9/1 - 上午10:39
 * @description 知道本地接口有哪些实现类
 */
public class LocalRegister {
    private static Map<String, Class> map = new HashMap<String, Class>();

    /**
     * 放入接口名字和实现类
     *
     * @param interfaceName
     * @param implClass
     */
    public static void register(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
