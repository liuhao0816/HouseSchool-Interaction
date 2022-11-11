package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.LeaveList;

import java.util.List;

public interface LeaveListService  extends IService<LeaveList> {

    List<LeaveList> queryLeaveListAll1();
}
