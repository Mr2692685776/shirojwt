package com.newheart.shirojwt.shirojwt.shiro.realm;

import com.newheart.shirojwt.shirojwt.entity.LoginUser;
import com.newheart.shirojwt.shirojwt.service.LoginUserService;
import com.newheart.shirojwt.shirojwt.shiro.jwt.JwtToken;
import com.newheart.shirojwt.shirojwt.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hanjie
 * @date 2019/12/30 16:51
 */
public class LoginUserRealm extends AuthorizingRealm {

    @Autowired
    private LoginUserService loginUserService;

    /**
     *  重写token检查方法
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    /**
     * 认证
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String)auth.getPrincipal();
        String username = JwtUtil.getUsername(token);
        if (!StringUtils.isEmpty(username)){
            return new SimpleAuthenticationInfo(token,token,username);
        }
        return null;
    }
}
