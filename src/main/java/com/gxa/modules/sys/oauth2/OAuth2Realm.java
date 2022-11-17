package com.gxa.modules.sys.oauth2;

import com.gxa.common.utils.Base64Utils;
import com.gxa.modules.sys.entity.Power;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.form.UserPowerFrom;
import com.gxa.modules.sys.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class OAuth2Realm extends AuthorizingRealm {
//    @Autowired
//    private ShiroService shiroService;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //从redis中获取出来
        UserPowerFrom userPowerFrom = userTokenService.validateToken(accessToken);
        if(userPowerFrom == null){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userPowerFrom, accessToken, getName());
        return info;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserPowerFrom userPowerFrom = (UserPowerFrom)principals.getPrimaryPrincipal();

        Set<String> perms = new HashSet<>();
        Set<Power> powers = userPowerFrom.getRoleFrom().getPowers();

        perms.add("sys:user:role");

        for (Power power:powers) {
            perms.add(power.getPowerMark());
        }


        log.info("----{}",perms);


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(perms);

        return simpleAuthorizationInfo;
    }


}
 
