package com.gxa.modules.sys.service;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 15:04.
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.VoteStat;

import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */
public interface VoteStatService extends IService<VoteStat> {
    //查询全部
    List selectAll();

    Result selectNewsList();
}
