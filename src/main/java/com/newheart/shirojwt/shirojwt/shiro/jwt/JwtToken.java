package com.newheart.shirojwt.shirojwt.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hanjie
 * @date 2019/12/30 17:05
 */

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
