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

    List<AllLeaveListDto> queryByUserId(Integer user_id);

    //判断角色
    List<Integer> queryRole(Integer user_id);
    //审核状态确认已审核
    void updateById(Integer id);

    void updateByIdP(Integer id);

    void updateStatus();
    //通过user_id查询家长的孩子的请假信息；
    List<AllLeaveListDto> queryLeaveListP(LeaveListDto leaveListDto);
    //审核状态确认 未通过
    void updateByIdT(Integer id);

    void updateByIdPa(Integer id);

    void updateStatusA();
    //事假数量
    Integer queryAbsence();
    //病假数量
    Integer querySick();

}
