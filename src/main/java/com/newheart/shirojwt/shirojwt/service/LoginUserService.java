package com.newheart.shirojwt.shirojwt.service;

import com.newheart.shirojwt.shirojwt.entity.LoginUser;

/**
 * @author hanjie
 * @date 2019/12/30 17:31
 */
public interface LoginUserService {

    LoginUser selectByUserName(String username);
}
