package com.newheart.shirojwt.shirojwt.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newheart.shirojwt.shirojwt.entity.LoginUser;
import com.newheart.shirojwt.shirojwt.mapper.LoginUserMapper;
import com.newheart.shirojwt.shirojwt.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hanjie
 * @date 2019/12/30 17:33
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public LoginUser selectByUserName(String username) {
        QueryWrapper<LoginUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return loginUserMapper.selectOne(queryWrapper);
    }
}
