package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.AppraisePermissions;
import com.gxa.modules.sys.entity.ClassGrade;
import com.gxa.modules.sys.entity.User;

import java.util.List;

public interface AppraisePermissionsMapper extends BaseMapper<AppraisePermissions> {
    List<ClassGrade> queryByHeadteacherClass(int userId);

    List<ClassGrade> queryByTeacherClass(int userId);

    List<ClassGrade> queryByAppraiseUser(int userId);

    List<User> queryByAppraiseStudent(int classId);
}
