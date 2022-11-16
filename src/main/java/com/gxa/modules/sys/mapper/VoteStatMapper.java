package com.gxa.modules.sys.mapper;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 15:03.
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.dto.VoteStat;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */
@Repository
@Mapper
public interface VoteStatMapper extends BaseMapper<VoteStat> {

}
