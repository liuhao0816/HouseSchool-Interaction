package com.gxa.modules.sys.redis;

import com.gxa.common.utils.JsonUtils;
import com.gxa.common.utils.RedisKeys;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.UserPower;
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
     * @param userPower
     */
    public String addToken(String uuid, UserPower userPower){

        String token = RedisKeys.getSysUserTokenKey(userPower.getUserId(), uuid);
        this.redisUtils.set(token,userPower);
        return token;
    }

    /**
     * 验证用户token
     * @param token
     * @return
     */
    public User getUserByToken(String token){
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        User user = JsonUtils.parseObject(userJsonStr, User.class);

        return user;
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

}
