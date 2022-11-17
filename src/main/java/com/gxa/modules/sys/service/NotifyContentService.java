package com.gxa.modules.sys.service;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/12
 * atime 9:46.
 */


import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.NOtifyContent;


import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-12
 *
 */
public interface NotifyContentService extends IService<NOtifyContent>  {
    //查询所有
    List selectAll();

}
