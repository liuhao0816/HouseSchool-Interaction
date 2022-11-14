package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;

import java.util.List;

public interface LeaveListService  extends IService<LeaveList> {
    //查询所有
    List<LeaveList> queryLeaveListAll1();
    //多条件查询
    List<LeaveList> queryLeaveListBy(LeaveListDto leaveListDto);
    //根据id查询
    List<LeaveList> queryById(Integer id);
}
