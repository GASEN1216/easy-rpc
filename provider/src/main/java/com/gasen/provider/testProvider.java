package com.gasen.provider;

import com.gasen.common.service.userService;
import com.gasen.provider.service.impl.userServiceImpl;
import com.gasen.rpccore.registry.localRegistry;
import com.gasen.rpccore.service.vertxHttpService;

/**
 * 提供服务测试
 * @author GASEN
 * @date 2024/3/29 9:43
 * @classType description
 */
public class testProvider {
    public static void main(String[] args) {
        //1.注册服务
        localRegistry.register(userService.class.getName(), userServiceImpl.class);
        //2.启动服务
        new vertxHttpService().start(8080);
    }
}
