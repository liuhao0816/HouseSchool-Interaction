package com.gxa.service.impl;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/12
 * atime 9:49.
 */

import com.gxa.entity.School;
import com.gxa.mapper.SchoolMapper;
import com.gxa.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.smartcardio.Card;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author renmuqiao
 *@date 2022-11-12
 *
 */
@Service("SchoolService")
@Transactional(rollbackFor = Throwable.class)

public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    //按学校名模糊查询
    @Override
    public List<School> selectAllByName(String schoolName) {
        Map map = new HashMap();
        map.put("schoolName",schoolName);
        List<School> schools = schoolMapper.selectAllByName(map);
        return schools;
    }

}
