package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.form.NotifyTypeForm;


import java.util.List;
import java.util.Map;

/**
 * @author 七天
 * @version 1.1
 * @date 2022/11/11
 */
public interface NotifyTypeService extends IService<NotifyType> {
    PageUtils queryByPage(Map<String,Object> params);

    Result getNotifyTypeById(Integer id);

    Result update(NotifyTypeForm notifyTypeForm);

    Result delete(Integer id);

    Result queryTypeName();

    Result updateStatus(Integer id);

    Result add(NotifyTypeForm notifyTypeForm);

    Map selectByType ();


}
