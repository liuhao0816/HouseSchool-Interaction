package com.gxa.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.AddLeaveListDto;
import com.gxa.modules.sys.dto.AllLeaveListDto;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;
import com.gxa.modules.sys.mapper.LeaveListMapper;

import com.gxa.modules.sys.service.LeaveListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeaveListServiceImpl extends ServiceImpl<LeaveListMapper,LeaveList> implements LeaveListService {



    @Override
    public List<AllLeaveListDto> queryLeaveListAll1() {
        List<AllLeaveListDto> allLeaveListDtos = baseMapper.queryLeaveListAll1();
        return  allLeaveListDtos;
    }

    @Override
    public List<AllLeaveListDto> queryLeaveListBy(LeaveListDto leaveListDto) {
        List<AllLeaveListDto> allLeaveListDtos = baseMapper.queryLeaveListBy(leaveListDto);
        return allLeaveListDtos;
    }

    @Override
    public List<AllLeaveListDto> queryById(Integer id) {
        List<AllLeaveListDto> allLeaveListDtos=baseMapper.queryById(id);
        return allLeaveListDtos;
    }

    @Override
    public List<AllLeaveListDto> queryByUserId(Integer user_id) {
        List<AllLeaveListDto> allLeaveListDtos=baseMapper.queryByUserId(user_id);
        return allLeaveListDtos;
    }

    @Override
    public List<LeaveList> addLeaveList(LeaveList leaveList) {

        this.baseMapper.insert(leaveList);


        /*return leaveList;*/
        return null;
    }
    @Override
    public Result delete(Integer id) {
        int result = baseMapper.deleteById(id);
        if (result != 0){
            return new Result().ok("通知类型删除成功。");
        }
        return new Result().error("未查询到数据或通知类型删除失败。");
    }
}
