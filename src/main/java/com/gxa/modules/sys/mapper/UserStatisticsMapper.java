package com.gxa.modules.sys.mapper;


import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.UserPower;
import com.gxa.modules.sys.entity.UserStatistics;
import com.gxa.modules.sys.form.UserPowerFrom;
import com.gxa.modules.sys.form.UserRoleForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Mapper
public interface UserStatisticsMapper{

    public List<UserStatistics> userStatisticsList(Map<String, Object> params);

    public UserPower userPowerByUserId(@Param("userId") int userId);
    public Set<Student> queryStudentByUserId(@Param("userId") int userId);
    public List<String> queryClassGradeName(UserRoleForm userRoleForm);

    public UserPowerFrom userPowerFromByUserIdAndRoleId(UserRoleForm userRoleForm);

}
