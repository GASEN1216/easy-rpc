package com.gasen.rpccore.service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author GASEN
 * @date 2024/3/29 10:07
 * @classType description
 */
@Slf4j
public class vertxHttpService implements httpService{
    @Override
    public void start(int port) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(new httpServiceHandler());

        httpServer.listen(port, res -> {
            if (res.succeeded()) {
                log.info("服务启动成功，监听端口"+port);
            } else {
                log.error("服务启动失败");
            }
        });
    }
}
