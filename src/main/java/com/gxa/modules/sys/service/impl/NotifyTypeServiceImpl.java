package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.form.NotifyTypeForm;
import com.gxa.modules.sys.mapper.NotifyTypeMapper;
import com.gxa.modules.sys.service.NotifyTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 通知类型
 */
@Service
@Slf4j
public class NotifyTypeServiceImpl extends ServiceImpl<NotifyTypeMapper, NotifyType> implements NotifyTypeService   {


    @Autowired
   NotifyTypeMapper notifyTypeMapper;

    @Override
    public PageUtils queryByPage(Map<String, Object> params) {
        IPage<NotifyType> page = page(new Query<NotifyType>().getPage(params));
        PageUtils pageUtils = new PageUtils(page);

        List<NotifyType> list = (List<NotifyType>) pageUtils.getList();
        ArrayList<Object> notifyTypeFormList = new ArrayList<>();
        for (NotifyType notifyType : list) {
            NotifyTypeForm notifyTypeForm = new NotifyTypeForm();
            notifyTypeForm.setId(notifyType.getId());
            notifyTypeForm.setType(notifyType.getType());
            notifyTypeForm.setAttribute(notifyType.getAttribute());
            notifyTypeForm.setStatus(notifyType.getStatus());
            notifyTypeFormList.add(notifyTypeForm);
        }
        pageUtils.setList(notifyTypeFormList);
        return pageUtils;
    }

    @Override
    public Result getNotifyTypeById(Integer id) {
        NotifyType notifyType = baseMapper.selectOne(new QueryWrapper<NotifyType>().eq("id", id));
        NotifyTypeForm notifyTypeForm = new NotifyTypeForm();
        notifyTypeForm.setId(notifyType.getId());
        notifyTypeForm.setType(notifyType.getType());
        notifyTypeForm.setAttribute(notifyType.getAttribute());
        notifyTypeForm.setStatus(notifyType.getStatus());
        if (notifyType != null){
            return new Result().ok(notifyTypeForm);
        }

        return new Result().error("未查询到数据或通知查询失败。");


    }

    @Override
    public Result update(NotifyTypeForm notifyTypeForm) {
        UpdateWrapper<NotifyType> wrapper = new UpdateWrapper<>();
        wrapper.set("type",notifyTypeForm.getType())
                .set("attribute",notifyTypeForm.getAttribute())
                .eq("id", notifyTypeForm.getId());
        int result = baseMapper.update(null, wrapper);
        if (result != 0){
            return new Result().ok("通知类型修改成功。");
        }

        return new Result().error("未查询到数据或通知类型修改失败。");
    }

    @Override
    public Result updateStatus(Integer id) {
        NotifyType notifyType = baseMapper.selectById(id);
        String status = notifyType.getStatus();
        if ("启用".equals(status)){
            status = "停用";
        }else {
            status = "启用";
        }
        UpdateWrapper<NotifyType> wrapper = new UpdateWrapper<>();
        wrapper.set("status",status)
               .eq("id", id);;
        int result = baseMapper.update(notifyType, wrapper);
        if (result != 0){
            return new Result().ok("通知状态修改成功。");
        }
        return new Result().error("未查询到数据或通知状态修改失败。");
    }


    @Override
    public Result delete(Integer id) {
        int result = baseMapper.deleteById(id);
        if (result != 0){
            return new Result().ok("通知类型删除成功。");
        }
        return new Result().error("未查询到数据或通知类型删除失败。");
    }


    @Override
    public Result queryTypeName() {
        List<NotifyType> typeList = baseMapper.selectList(new QueryWrapper<NotifyType>().select("id", "type"));
        HashMap<Object, Object> map = new HashMap<>();
        for (NotifyType notifyType : typeList) {
            map.put(notifyType.getId(),notifyType.getType());
        }
        if (!typeList.isEmpty()){
            return new Result().ok(map);
        }
        return new Result().error("未查询到数据或所有通知类型查询失败。");
    }

    @Override
    public Result add(NotifyTypeForm notifyTypeForm) {
        NotifyType notifyType = new NotifyType();
        notifyType.setType(notifyTypeForm.getType());
        notifyType.setAttribute(notifyTypeForm.getAttribute());
        notifyType.setStatus("启用");
        notifyType.setIsDelete(1);

        int result = baseMapper.insert(notifyType);
        if (result != 0){
            return new Result().ok("通知类型添加成功。");
        }
        return new Result().error("通知类型添加失败。");
    }

}
