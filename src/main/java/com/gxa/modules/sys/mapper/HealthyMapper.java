package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.ClassGrade;
import com.gxa.modules.sys.entity.Healthy;
import com.gxa.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HealthyMapper extends BaseMapper<Healthy> {
    void queryByPublisherTime(@Param("id") int id);
    List<Healthy> queryByPublisher01(@Param("userName") String userName, @Param("createTime") String createTime);
    List<Healthy> queryByHealthyDto01(@Param("firstDateTime")String firstDateTime, @Param("lastDateTime")String lastDateTime, @Param("studentName")String studentName, @Param("gradeClass")String gradeClass,@Param("createTime")String createTime);

    List<Healthy> queryByHealthyDto02(@Param("studentName")String studentName, @Param("gradeClass")String gradeClass);

    List<ClassGrade> queryByHealthyClass(int userId);

    List<User> queryByHealthyTeacher(int classId);
}
