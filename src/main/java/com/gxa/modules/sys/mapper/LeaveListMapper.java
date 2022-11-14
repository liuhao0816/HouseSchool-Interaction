package com.gxa.modules.sys.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;



public interface LeaveListMapper extends BaseMapper<LeaveList> {

    List<LeaveList> queryLeaveListAll1();

    List<LeaveList> queryLeaveListBy(LeaveListDto leaveListDto);

    List<LeaveList> queryById(Integer id);
}
