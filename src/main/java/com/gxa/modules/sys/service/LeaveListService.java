package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.AddLeaveListDto;
import com.gxa.modules.sys.dto.AllLeaveListDto;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;

import java.util.List;

public interface LeaveListService  extends IService<LeaveList> {
    //查询所有
    List<AllLeaveListDto> queryLeaveListAll1();
    //多条件查询
    List<AllLeaveListDto> queryLeaveListBy(LeaveListDto leaveListDto);
    //根据id查询
    List<AllLeaveListDto> queryById(Integer id);
    //根据userid查询学生id和姓名
    List<AllLeaveListDto> queryByUserId(Integer user_id);
    //添加请假
    List<LeaveList> addLeaveList(LeaveList leaveList);
    //根据id删除
    Result delete(Integer id);
}
