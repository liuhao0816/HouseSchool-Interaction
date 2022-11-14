package com.gxa.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<LeaveList> queryLeaveListAll1() {
        List<LeaveList> leaveLists = baseMapper.queryLeaveListAll1();
        return  leaveLists;
    }

    @Override
    public List<LeaveList> queryLeaveListBy(LeaveListDto leaveListDto) {
        List<LeaveList> leaveLists = baseMapper.queryLeaveListBy(leaveListDto);
        return leaveLists;
    }

    @Override
    public List<LeaveList> queryById(Integer id) {
        List<LeaveList> leaveList=baseMapper.queryById(id);
        return leaveList;
    }
}
