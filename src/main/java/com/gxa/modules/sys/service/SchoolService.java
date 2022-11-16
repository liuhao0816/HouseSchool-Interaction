package com.gxa.modules.sys.service;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/12
 * atime 9:46.
 */


import com.alipay.api.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.dto.School;

import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-12
 *
 */
public interface SchoolService extends IService<School>  {
    List<School> searchAddressByType(String type);
}
