package com.gasen.consumer;

import com.gasen.common.model.User;
import com.gasen.common.service.userService;
import com.gasen.rpccore.proxy.serviceProxyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author GASEN
 * @date 2024/3/29 9:45
 * @classType description
 */
@Slf4j
public class testConsumer {
    public static void main(String[] args) {
        // 1.创建代理对象
        userService userService = serviceProxyFactory.getProxy(userService.class);
        // 2.处理结果
        User user = new User();
        user.setName("gasen");
        log.info("用户名为："+userService.getUserName(user));
    }
}
