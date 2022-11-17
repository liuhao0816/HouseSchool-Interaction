package com.gxa.modules.sys.service;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.UserPower;
import com.gxa.modules.sys.form.UserPowerFrom;

public interface UserTokenService {
    Result createToken(UserPowerFrom userPowerFrom);
    String verificationCodeNo(String resultValue);

    String queryByCaptch(String uuid);
    void deleteToken(String token);

    UserPowerFrom validateToken(String token);
}
