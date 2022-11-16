package com.gxa.modules.sys.controller;

import cn.hutool.poi.excel.ExcelUtil;
import com.gxa.common.utils.ExcelUtils;
import com.gxa.modules.sys.dto.AllLeaveListDto;
import com.gxa.modules.sys.service.LeaveListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@Api(tags = "请假Excel导出")
@CrossOrigin
public class ExcelController {
    @Autowired
    private LeaveListService leaveListService;
    /**
     * 导出报表
     *
     * @return
     */
    @GetMapping(value = "/export")
    @ApiOperation("导出Excel")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<AllLeaveListDto> list = this.leaveListService.queryLeaveListAll1();
        //excel标题
        String[] title = {"序号", "请假学生", "年级班级", "请假类型", "请假开始时间","请假结束时间","请假事由","发起人","请假发起时间","请假状态"};

        //excel文件名
        String fileName = "学生请假" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "学生请假";

        String[][] content = new String[list.size()][20];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            AllLeaveListDto allLeaveListDto = list.get(i);
            content[i][0] = allLeaveListDto.getId().toString();
            content[i][1] = allLeaveListDto.getStudentName();
            content[i][2] = allLeaveListDto.getClassGradeName();
            content[i][3] = allLeaveListDto.getLeaveType();
            if (allLeaveListDto.getStartTime()!=null){
                content[i][4] = sdf.format(allLeaveListDto.getStartTime());
            }else {
                content[i][4] = " ";
            }
            if (allLeaveListDto.getEndTime()!=null){
                content[i][5] = sdf.format(allLeaveListDto.getEndTime());
            }else {
                content[i][5] = " ";
            }
            content[i][6] = allLeaveListDto.getReason();
            content[i][7] = allLeaveListDto.getSponsor();
            if (allLeaveListDto.getEndTime()!=null){
                content[i][8] = sdf.format(allLeaveListDto.getLaunchTime());
            }else {
                content[i][8] = " ";
            }
            content[i][9] = allLeaveListDto.getRemarks();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    /**
     * 发送响应流方法
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
