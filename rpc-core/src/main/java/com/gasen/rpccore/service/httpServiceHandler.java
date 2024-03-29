package com.gasen.rpccore.service;

import com.gasen.rpccore.model.rpcRequest;
import com.gasen.rpccore.model.rpcResponse;
import com.gasen.rpccore.registry.localRegistry;
import com.gasen.rpccore.serializer.JdkSerializer;
import com.gasen.rpccore.serializer.Serializer;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author GASEN
 * @date 2024/3/29 10:11
 * @classType description
 */
@Slf4j
public class httpServiceHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        JdkSerializer jdkSerializer = new JdkSerializer();
        log.info("请求方法："+request.method()+" 请求uri："+request.uri());
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            rpcRequest dsRequest = null;
            rpcResponse rpcResponse = null;
            try{
                dsRequest = jdkSerializer.deserialize(bytes, rpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(dsRequest == null){
                log.error("请求参数解析失败----空");
                doResponse(request, new rpcResponse(), jdkSerializer);
                return;
            }
            Class<?> aClass = localRegistry.get(dsRequest.getServiceName());
            try {
                Method method = aClass.getMethod(dsRequest.getMethodName(), dsRequest.getParameterTypes());
                Object res = method.invoke(aClass.newInstance(), dsRequest.getParameters());
                rpcResponse = new rpcResponse(res, method.getReturnType(), "ok", null);
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage("failed");
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, jdkSerializer);
        });
    }

    private void doResponse(HttpServerRequest request, rpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response().putHeader("content-type", "application/json");
        try {
            byte[] bytes = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
