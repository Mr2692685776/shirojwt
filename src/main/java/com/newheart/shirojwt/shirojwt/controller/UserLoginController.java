package com.newheart.shirojwt.shirojwt.controller;


import com.newheart.shirojwt.shirojwt.entity.LoginUser;
import com.newheart.shirojwt.shirojwt.service.LoginUserService;
import com.newheart.shirojwt.shirojwt.util.JwtUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanjie
 * @date 2019/12/21 10:14
 */
@RestController
public class UserLoginController {

    @Autowired
    private LoginUserService loginUserService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody LoginUser user){
        Map<String, Object> map = new HashMap<>(4);
        LoginUser loginUser = loginUserService.selectByUserName(user.getUsername());
        if (null==loginUser){
            map.put("code",500);
            map.put("msg","用户不存在!");
            return map;
        }else if (!loginUser.getPassword().equals(new SimpleHash("md5",user.getPassword(),null,1).toString())){
            map.put("code",500);
            map.put("msg","密码错误");
            return map;
        }
        map.put("code",0);
        map.put("msg","登录成功");
        map.put("token", JwtUtil.sign(user.getUsername()));
        return map;
    }

    /**
     * 未登录
     * @Author Sans
     * @CreateTime 2019/6/20 9:22
     */
    @RequestMapping("/unauth")
    public Map<String,Object> unauth(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg","未登录");
        return map;
    }
}
