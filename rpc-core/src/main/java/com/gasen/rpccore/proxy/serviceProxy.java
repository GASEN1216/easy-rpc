package com.gasen.rpccore.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.gasen.rpccore.model.rpcRequest;
import com.gasen.rpccore.model.rpcResponse;
import com.gasen.rpccore.registry.localRegistry;
import com.gasen.rpccore.serializer.JdkSerializer;
import com.gasen.rpccore.serializer.Serializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author GASEN
 * @date 2024/3/29 12:09
 * @classType description
 */
public class serviceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Serializer jdkSerializer = new JdkSerializer();
        rpcRequest request = rpcRequest.builder().serviceName(method.getDeclaringClass().getName()).methodName(method.getName()).parameterTypes(method.getParameterTypes()).parameters(args).build();
        try{
            byte[] bytes = jdkSerializer.serialize(request);
            HttpResponse res = HttpRequest.post("http://localhost:8080").body(bytes).execute();
            rpcResponse dsRes = (rpcResponse)jdkSerializer.deserialize(res.bodyBytes(), method.getReturnType());
            return dsRes.getData();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
