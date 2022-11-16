package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.entity.VoteStat;
import com.gxa.modules.sys.form.NewsForm;
import com.gxa.modules.sys.mapper.VoteStatMapper;
import com.gxa.modules.sys.service.NotifyTypeService;
import com.gxa.modules.sys.service.VoteStatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


@Service("VoteStatService")
@Transactional(rollbackFor = Throwable.class)
public class VoteStatServiceImpl extends ServiceImpl<VoteStatMapper, VoteStat> implements VoteStatService {

    @Resource
    private NotifyTypeService notifyTypeService;


    @Resource
    private VoteStatMapper voteStatMapper;
    @Override
    public List selectAll() {
        List<VoteStat> list = this.list();
        return list;
    }

    @Override
    public Result selectNewsList() {

        List<NotifyType> notifyTypeList = this.voteStatMapper.selectType();
        Integer count = voteStatMapper.count();
        ArrayList<NewsForm> newsForms = new ArrayList<>();
        for (NotifyType list : notifyTypeList) {
            NewsForm newsForm = new NewsForm();
            newsForm.setId(list.getId());
            newsForm.setType(list.getType());
            Integer typeCount = voteStatMapper.typeCount(list.getId());
            newsForm.setCount(typeCount);
            if (typeCount != 0){
                //求百分比
                NumberFormat numberFormat = NumberFormat.getInstance();
                //小数位最大2位
                numberFormat.setMaximumFractionDigits(2);
                String format = numberFormat.format((float) typeCount / (float) count * 100);
                newsForm.setProportion(format+"%");
            }else {
                newsForm.setProportion("0");
            }
            newsForms.add(newsForm);
        }
        if (!newsForms.isEmpty()) {
            return new Result().ok(newsForms);
        }
        return new Result().error("查询失败。");
    }
}
