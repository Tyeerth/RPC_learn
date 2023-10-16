package com.xdu.register;

import com.xdu.common.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tyeerth
 * @date 2023/9/2 - 下午4:13
 * @description 简单实现一个远程注册中心
 */
public class MapRemoteRegister {
    private static Map<String, List<URL>> map = new HashMap<String, List<URL>>();

    /**
     * 放入接口名字和实现类
     *
     * @param interfaceName
     */
    public static void register(String interfaceName, URL url) {
        List<URL> list = map.get(interfaceName);
        if (list == null) {
            list = new ArrayList<URL>();
        }
        list.add(url);
        map.put(interfaceName, list);
        saveFile();
    }

    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("temp.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Map<String, List<URL>>) objectInputStream.readObject();
    }

    /**
     * 由于不同进程不共享map，这里为了简单就使用文件来进行存储
     *
     * @param interfaceName
     * @return
     */
    public static List<URL> get(String interfaceName) throws IOException, ClassNotFoundException {
        Map<String, List<URL>> file = getFile();
        return file.get(interfaceName);
    }
}
