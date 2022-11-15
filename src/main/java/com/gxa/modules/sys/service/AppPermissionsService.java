package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.AppraisePermissions;
import com.gxa.modules.sys.entity.ClassGrade;
import com.gxa.modules.sys.entity.User;

import java.util.List;


public interface AppPermissionsService extends IService<AppraisePermissions> {
    void queryByPermissionsLaunch(AppraisePermissions appraisePermissions);

    List<ClassGrade> queryByPermissionsHeadteacher(int userId);

    AppraisePermissions queryByTeacherType();

    List<ClassGrade> queryByPermissionsTeacher(int userId);

    List<ClassGrade> queryByPermissionsappraiseUser(int userId);

    List<User> queryByAppraiseStudent(int classId);
}
