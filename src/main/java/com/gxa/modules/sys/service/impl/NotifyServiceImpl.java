package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.Notify;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.entity.Scope;
import com.gxa.modules.sys.form.ClassForm;
import com.gxa.modules.sys.form.NotifyForm;
import com.gxa.modules.sys.form.ReadForm;
import com.gxa.modules.sys.mapper.NotifyMapper;
import com.gxa.modules.sys.mapper.UserMapper;
import com.gxa.modules.sys.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author 七天
 * @version 1.1
 * @date 2022/11/15
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class NotifyServiceImpl extends ServiceImpl<NotifyMapper, Notify> implements NotifyService {

    @Resource
    private NotifyMapper notifyMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<NotifyForm> queryByPage(Integer page, Integer limit, NotifyForm noForm) {

        page =(page-1)*limit;
        List<Notify> notifyList = notifyMapper.queryByPage(page, limit, noForm);


        ArrayList<NotifyForm> notifyFormArrayList = new ArrayList<NotifyForm>();


        for (Notify notify : notifyList) {
            NotifyForm notifyForm = new NotifyForm();

            notifyForm.setId(notify.getId());
            //获取类型
            NotifyType type = notifyMapper.getTypeByContentId(notify.getId());
            notifyForm.setType(type.getType());
            notifyForm.setTitle(notify.getContent().getTitle());

            notifyForm.setPublisher(notify.getContent().getWriter());
            Date releaseTime = notify.getContent().getReleaseTime();

            notifyForm.setStartTime(releaseTime);

            //获取消息发布范围
            List<Scope> scopeList = notifyMapper.queryScopeById(notify.getId());
            StringBuilder builder = new StringBuilder();
            if (scopeList.size()!=6){
                for (Scope scope : scopeList){
                    builder.append(scope.getNotifyScope());
                    builder.append(" ");
                }
            }else {
                builder.append("全校");
            }
            notifyForm.setScope(String.valueOf(builder));
            long now = System.currentTimeMillis();
            try {
                long time = releaseTime.getTime();
                if (time > now) {
                    notifyForm.setStatus("定时发布");
                }else if (time < now) {
                    notifyForm.setStatus("已发布");
                }
            }catch (Exception e) {
                notifyForm.setStatus("草稿");
            }
            notifyFormArrayList.add(notifyForm);
        }

        return notifyFormArrayList;
    }

    @Override
    public NotifyForm getNotifyByTitle(String title,String userName) {
        Notify notify = this.notifyMapper.queryContentByTitle(title);

        NotifyForm notifyForm = new NotifyForm();
        notifyForm.setId(notify.getId());
        notifyForm.setTitle(notify.getContent().getTitle());
        notifyForm.setContent(notify.getContent().getContent());
        notifyForm.setPublisher(notify.getContent().getWriter());
        notifyForm.setEnclosure(notify.getContent().getEnclosure());
        notifyForm.setPicture(notify.getContent().getPicture());
        Date releaseTime = notify.getContent().getReleaseTime();
        notifyForm.setStartTime(releaseTime);
        //获取消息发布范围
        List<Scope> scopeList = notifyMapper.queryScopeById(notify.getId());
        StringBuilder builder = new StringBuilder();
        if (scopeList.size()!=6){
            for (Scope scope : scopeList){
                builder.append(scope.getNotifyScope());
                builder.append(" ");
            }
        }else {
            builder.append("全校");
        }
        notifyForm.setScope(String.valueOf(builder));
        //获取当前时间
        long now = System.currentTimeMillis();
        try {
            long time = releaseTime.getTime();
            if (time > now) {
                notifyForm.setStatus("定时发布");
            }else if (time < now) {
                notifyForm.setStatus("已发布");
            }
        }catch (Exception e) {
            notifyForm.setStatus("草稿");
        }
        //当前用户点击了标题就进入阅读界面 所以将当前用户对应的学生添加到content_student表
        //查询用户对应的学生姓名
        List<ReadForm> readForm = this.userMapper.getReadForm(userName);
        // 一个家长可能对应多个学生
        if (readForm.size() > 0) {
            for (ReadForm form : readForm) {
                //将学生添加到已读表
                if (scopeList.contains("全校")){
                    this.notifyMapper.addContent(notify.getId(), form.getStudentName());
                }else {
                    for (Scope scope : scopeList) {
                        //判断学生是否在消息范围内 如果在 就判断学生是否读过这条消息
                        ReadForm form1 = this.notifyMapper.selectRead(notify.getId(), form.getStudentName());
                        if (form1==null) {
                            if (form.getClassGradeName().equals(scope.getNotifyScope())) {
                                this.notifyMapper.addContent(notify.getId(), form.getStudentName());
                            }
                        }
                    }
                }
            }
        }
        //获取已读用户
        List<ReadForm> readFormList = notifyMapper.readList(notify.getId());
        LinkedHashSet<ReadForm> notifyForms = new LinkedHashSet<>(readFormList);
        ArrayList<ReadForm> readForms = new ArrayList<>(notifyForms);

        notifyForm.setReadList(readForms);
        notifyForm.setReadCount(readForms.size());

        return notifyForm;

    }

    @Override
    public Result delete(Integer id) {
        int result = this.baseMapper.deleteById(id);
        if (result != 0){
            return new Result().ok("通知删除成功。");
        }
        log.info("未查询到数据或通知删除失败");
        return new Result().error("未查询到数据或通知删除失败。");
    }

    @Override
    public Result add(NotifyForm notifyForm) {

        //1、添加基本数据到content表
        if (notifyForm.getStartTime() == null ){
            notifyForm.setStartTime(new Date());
        }
        this.notifyMapper.addNotifyContent(notifyForm);
        //查询类型的type_id
        NotifyType type = notifyMapper.getType(notifyForm.getType());
        //查询content表中的content_id
        Notify notify = notifyMapper.queryContentByTitle(notifyForm.getTitle());
        //2、添加到list表
        notifyMapper.addNotify(type.getId(),notify.getId());
        //3、添加到scope表
        String scope = notifyForm.getScope();
        if ("全校".equals(scope)) {
            List<ClassForm> classForms = this.notifyMapper.queryAllClass();
            for (ClassForm classForm : classForms) {
                notifyMapper.addScope(notify.getId(),classForm.getClassGradeName());
            }
        }else {
            notifyMapper.addScope(notify.getId(),scope);
        }
        return new Result().ok("添加成功");
    }


    @Override
    public Result queryNotifyByUser(String userName) {
        List<Notify> notifyList = this.notifyMapper.queryNotifyByUser(userName);
        ArrayList<NotifyForm> formArrayList = new ArrayList<>();
        if (notifyList.size()!=0){
            for (Notify notify : notifyList) {
                NotifyForm notifyForm = new NotifyForm();
                notifyForm.setId(notify.getId());
                NotifyType type = notifyMapper.getTypeByContentId(notify.getId());
                notifyForm.setType(type.getType());
                notifyForm.setTitle(notify.getContent().getTitle());
//                notifyForm.setContent(notify.getContent().getContent());
                notifyForm.setPublisher(notify.getContent().getWriter());
//                notifyForm.setEnclosure(notify.getContent().getEnclosure());
//                notifyForm.setPicture(notify.getContent().getPicture());
                Date releaseTime = notify.getContent().getReleaseTime();
                notifyForm.setStartTime(releaseTime);
                //获取消息发布范围
                List<Scope> scopeList = notifyMapper.queryScopeById(notify.getId());
                StringBuilder builder = new StringBuilder();
                if (scopeList.size()!=6){
                    for (Scope scope : scopeList){
                        builder.append(scope.getNotifyScope());
                        builder.append(" ");
                    }
                }else {
                    builder.append("全校");
                }

                notifyForm.setScope(String.valueOf(builder));
                //获取当前时间
                long now = System.currentTimeMillis();
                try {
                    long time = releaseTime.getTime();
                    if (time > now) {
                        notifyForm.setStatus("定时发布");
                    }else if (time < now) {
                        notifyForm.setStatus("已发布");
                    }
                }catch (Exception e) {
                    notifyForm.setStatus("草稿");
                }
                formArrayList.add(notifyForm);
            }
//            LinkedHashSet<NotifyForm> notifyForms = new LinkedHashSet<>(formArrayList);
//            ArrayList<NotifyForm> forms = new ArrayList<>(notifyForms);
            Result<Object> result = new Result<>();
            result.setTotal(formArrayList.size());
            result.setData(formArrayList);
            return result.ok();
        }
        return new Result().error("您未发布任何消息");
    }

    @Override
    public Result queryAllClass() {
        List<ClassForm> classForms = this.notifyMapper.queryAllClass();
        if (classForms.size() > 0){
            Result<Object> result = new Result<>();
            result.setTotal(classForms.size());
            result.setData(classForms);
            return result.ok();
        }
        log.info("未查询到数据或年级列表查询失败失败");
        return new Result().error("未查询到数据或年级列表查询失败失败。");

    }
}

