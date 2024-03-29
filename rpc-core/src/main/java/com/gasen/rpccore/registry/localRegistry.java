package com.gasen.rpccore.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author GASEN
 * @date 2024/3/29 9:54
 * @classType description
 */
public class localRegistry {
    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    public static void register(String serviceName, Class<?> clazz) {
        map.put(serviceName, clazz);
    }

    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    public static void remove (String serviceName) {
        map.remove(serviceName);
    }

}
