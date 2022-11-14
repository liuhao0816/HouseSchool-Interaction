package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.Appraise;
import com.gxa.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AppraiseMapper extends BaseMapper<Appraise> {
   void queryByPublisherTime(@Param("publisher") String publisher, @Param("appraiseTime") String appraiseTime);
   List<Appraise> queryByPublisher01(@Param("publisher") String publisher, @Param("appraiseTime") String appraiseTime);
   List<Appraise> queryByAppraiseDto01(@Param("firstDateTime")String firstDateTime,@Param("lastDateTime")String lastDateTime,@Param("studentName")String studentName,@Param("gradeClass")String gradeClass);

    List<Appraise> queryByAppraiseDto02(@Param("studentName")String studentName,@Param("gradeClass")String gradeClass);

    List<User> queryByPermissionsTeacher();
}
