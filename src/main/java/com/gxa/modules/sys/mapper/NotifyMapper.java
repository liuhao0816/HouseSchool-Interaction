package com.gxa.modules.sys.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;



import com.gxa.modules.sys.entity.Notify;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.entity.Scope;
import com.gxa.modules.sys.form.ClassForm;
import com.gxa.modules.sys.form.NotifyForm;
import com.gxa.modules.sys.form.ReadForm;

import java.util.List;


public interface NotifyMapper extends BaseMapper<Notify> {


    List<Notify> queryByPage(Integer page, Integer limit, NotifyForm notifyForm);

    List<Scope> queryScopeById(Integer contentId);

    Integer deleteById(Integer id);

    Notify queryContentByTitle(String title);

    /**添加内容*/
    Integer addNotifyContent(NotifyForm notifyForm);

    /**添加到list*/
    Integer addNotify(Integer type,Integer content);

    /**添加到范围*/
    Integer addScope(Integer contentId,String scope);

    /**查询类型*/
    NotifyType getType(String type);

    /**当前用户发布的通知*/
    List<Notify> queryNotifyByUser(String userName);

    List<ReadForm> readList(Integer contentId);

    /**将用户标记为已读*/
    void addContent(Integer contentId,String studentName);

    ReadForm selectRead(Integer contentId,String studentName);

    /**查询班级列表*/
    List<ClassForm> queryAllClass();


}
