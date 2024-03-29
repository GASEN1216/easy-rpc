package com.gasen.provider.service.impl;

import com.gasen.common.model.User;
import com.gasen.common.service.userService;

/**
 * @author GASEN
 * @date 2024/3/29 9:36
 * @classType description
 */
public class userServiceImpl implements userService {

    @Override
    public String getUserName(User user) {
        return user!=null ? user.getName() : "null";
    }
}
