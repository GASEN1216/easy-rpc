package com.gasen.rpccore.proxy;

import java.lang.reflect.Proxy;

/**
 * @author GASEN
 * @date 2024/3/29 12:09
 * @classType description
 */
public class serviceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass)
    {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, new serviceProxy());
    }
}
