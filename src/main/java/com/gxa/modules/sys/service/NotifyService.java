package com.gxa.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.Notify;

import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.form.ClassForm;
import com.gxa.modules.sys.form.NotifyForm;

import java.util.List;


/**
 * @author 七天
 * @version 1.1
 * @date 2022/11/12
 */
public interface NotifyService extends IService<Notify> {

    List<NotifyForm> queryByPage(Integer page, Integer limit, NotifyForm notifyForm);

    NotifyForm getNotifyByTitle(String title,String userName);

    Result delete(Integer id);

    Result add(NotifyForm notifyForm);

    Result  queryNotifyByUser(String userName);

    Result queryAllClass();

}
