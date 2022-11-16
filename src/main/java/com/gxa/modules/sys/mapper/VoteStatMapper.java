package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.entity.dto.VoteStat;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VoteStatMapper extends BaseMapper<VoteStat> {
    Integer count();
    Integer typeCount(Integer id);
    List<NotifyType> selectType();
}
