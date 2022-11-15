package com.gxa.modules.sys.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.dto.AddLeaveListDto;
import com.gxa.modules.sys.dto.AllLeaveListDto;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;



public interface LeaveListMapper extends BaseMapper<LeaveList> {

    List<AllLeaveListDto> queryLeaveListAll1();

    List<AllLeaveListDto> queryLeaveListBy(LeaveListDto leaveListDto);

    List<AllLeaveListDto> queryById(Integer id);

    List<AllLeaveListDto> queryByUserId(Integer id);

    void updateById(Integer id);

}
