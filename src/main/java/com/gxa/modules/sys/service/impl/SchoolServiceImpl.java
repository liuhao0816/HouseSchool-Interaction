package com.gxa.modules.sys.service.impl;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/14
 * atime 10:18.
 */

import com.alipay.api.domain.Article;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.dto.School;
import com.gxa.modules.sys.mapper.SchoolMapper;
import com.gxa.modules.sys.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-14
 *
 */
@Service("schoolService")
@Transactional(rollbackFor = Throwable.class)
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Resource
    SchoolMapper schoolMapper;

    @Override
    public List<School> searchAddressByType(String type) {

        List<School> schools = schoolMapper.searchAddressByType(type);

        return schools;
    }
}


