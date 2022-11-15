package com.gxa.modules.sys.mapper;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/11
 * atime 16:47.
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.dto.School;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *@author renmuqiao
 *@date 2022-11-11
 *
 */
@Repository
@Mapper
public interface SchoolMapper extends BaseMapper<School> {

   List<School> searchAddressByType(String type) ;


}
