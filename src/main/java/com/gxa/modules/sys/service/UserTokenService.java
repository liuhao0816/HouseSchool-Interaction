package com.gxa.modules.sys.service;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.UserPower;

public interface UserTokenService {
    Result createToken(UserPower userPower);

    User validateToken(String token);
}
