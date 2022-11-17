package com.gxa.modules.sys.redis;

import com.gxa.common.utils.JsonUtils;
import com.gxa.common.utils.RedisKeys;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.sys.form.UserPowerFrom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SysUserRedis {

    private RedisUtils redisUtils;

    /**
     * 验证码存入
     * @param uuid
     * @param resultValue
     */
    public String addToken(String uuid, String resultValue){

        String loginCaptchaKey = RedisKeys.getLoginCaptchaKey(uuid);

        this.redisUtils.set(loginCaptchaKey,resultValue,600);


        return loginCaptchaKey;
    }


    /**
     * 用户信息存入
     * @param uuid
     * @param userPowerFrom
     */
    public String addToken(String uuid, UserPowerFrom userPowerFrom){

        String token = RedisKeys.getSysUserTokenKey(userPowerFrom.getUserId(), uuid);
        this.redisUtils.set(token,userPowerFrom);
        return token;
    }

    /**
     * 验证用户token
     * @param token
     * @return
     */
    public UserPowerFrom getUserByToken(String token){
        String userJsonStr = this.redisUtils.get(token);
        UserPowerFrom userPowerFrom = JsonUtils.parseObject(userJsonStr, UserPowerFrom.class);

        return userPowerFrom;
    }


    /**
     *查询验证码
     * @param uuid
     * @return
     */
    public String queryByCaptch(String uuid){

        String resultValue = this.redisUtils.get(uuid);

        return resultValue;
    }


    /**
     * 清除token，退出登录
     * @param token
     */
    public void deleteToken(String token){
        this.redisUtils.delete(token);
    }
}
