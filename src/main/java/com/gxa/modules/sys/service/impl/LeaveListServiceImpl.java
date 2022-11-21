package com.gxa.modules.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.AllLeaveListDto;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;
import com.gxa.modules.sys.mapper.LeaveListMapper;

import com.gxa.modules.sys.service.LeaveListService;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LeaveListServiceImpl extends ServiceImpl<LeaveListMapper,LeaveList> implements LeaveListService {



    @Override
    public List<AllLeaveListDto> queryLeaveListAll1() {
        List<AllLeaveListDto> allLeaveListDtos = baseMapper.queryLeaveListAll1();
        return  allLeaveListDtos;
    }

    @Override
    public Result<List<AllLeaveListDto>> queryLeaveListBy(LeaveListDto leaveListDto) {

        this.baseMapper.updateStatusA();
        this.baseMapper.updateStatus();

        Integer page=leaveListDto.getPage();
        Integer limit=leaveListDto.getLimit();
        PageHelper.startPage(page,limit);


        List<AllLeaveListDto> allLeaveListDtos = this.baseMapper.queryLeaveListBy(leaveListDto);

        Integer userId = leaveListDto.getUserId();
        List<Integer> integers = this.baseMapper.queryRole(userId);
        for (int i=0;i<integers.size();i++){
            Integer aa = integers.get(i);
            if (aa==2){
//                List<AllLeaveListDto> allLeaveListDtos = this.baseMapper.queryLeaveListBy(leaveListDto);
                for (int j=0;j<allLeaveListDtos.size();j++){
                    AllLeaveListDto allLeaveListDto1 = allLeaveListDtos.get(j);
                    allLeaveListDto1.setAbsence(baseMapper.queryAbsence());
                    allLeaveListDto1.setSick(baseMapper.querySick());
                }
                PageInfo<AllLeaveListDto> pageInfo = new PageInfo<>(allLeaveListDtos);
                long total = pageInfo.getTotal();
                return new Result<List<AllLeaveListDto>>().ok(allLeaveListDtos,total);
            }
            if (aa==3){
                List<AllLeaveListDto> allLeaveListDtos1 = baseMapper.queryLeaveListP(leaveListDto);
                for (int nn=0;nn<allLeaveListDtos1.size();nn++){
                    AllLeaveListDto allLeaveListDto1 = allLeaveListDtos1.get(nn);
                    allLeaveListDto1.setAbsence(baseMapper.queryAbsence());
                    allLeaveListDto1.setSick(baseMapper.querySick());
                }
                PageInfo<AllLeaveListDto> pageInfo = new PageInfo<>(allLeaveListDtos1);
                long total = pageInfo.getTotal();
                return new Result<List<AllLeaveListDto>>().ok(allLeaveListDtos1,total);

            }

        }

        return null;
    }

    @Override
    public List<AllLeaveListDto> queryById(Integer id) {
        List<AllLeaveListDto> allLeaveListDtos=baseMapper.queryById(id);
        return allLeaveListDtos;
    }

    @Override
    public List<AllLeaveListDto> queryByUserId(Integer user_id) {
        List<AllLeaveListDto> allLeaveListDtos=baseMapper.queryByUserId(user_id);
        return allLeaveListDtos;
    }

    @Override
    public List<LeaveList> addLeaveList(LeaveList leaveList) {
        leaveList.setIsDeleted(1);
        this.baseMapper.insert(leaveList);
        return null;
    }
    @Override
    public Result delete(Integer id) {
        int result = baseMapper.deleteById(id);
        if (result != 0){
            return new Result().ok("删除成功。");
        }
        return new Result().error("未查询到数据或删除失败。");
    }

    @Override
    public  Result updateByIdAllT(Integer id,Integer user_id) {
        List<Integer> integers = this.baseMapper.queryRole(user_id);
        for (int i=0;i<integers.size();i++){
            Integer aa = integers.get(i);
            if (aa==2){
                this.baseMapper.updateById(id);
            }
            if (aa==3){
                this.baseMapper.updateByIdP(id);
            }
        }

        return new Result<>().ok();
    }

    @Override
    public Result updateByIdAllF(Integer id, Integer user_id) {
        List<Integer> integers = this.baseMapper.queryRole(user_id);
        for (int i=0;i<integers.size();i++){
            Integer aa = integers.get(i);
            if (aa==2){
                this.baseMapper.updateByIdT(id);
            }
            if (aa==3){
                this.baseMapper.updateByIdPa(id);
            }
        }

        return new Result<>().ok();
    }

}
