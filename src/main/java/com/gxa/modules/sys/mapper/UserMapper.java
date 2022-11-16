package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public IPage<User> queryByPage(Page<User> page, Map<String, Object> params);

}
