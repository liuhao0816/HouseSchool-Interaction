package com.gxa.modules.sys.controller;

import com.gxa.common.utils.ExcelUtils;
import com.gxa.modules.sys.entity.StudentAttendanceRate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "V-出勤统计列表导出接口")
@CrossOrigin
public class StudentAttStatisticsController {

    @GetMapping(value = "/sys/studentAttendance/export")
    @ApiOperation("出勤统计导出Excel")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<StudentAttendanceRate> studentAttendanceRates1 = new ArrayList<>();
        StudentAttendanceRate studentAttendanceRate1 = new StudentAttendanceRate();
        studentAttendanceRate1.setStudentClass("一年级");
        studentAttendanceRate1.setDueStudents(20);
        studentAttendanceRate1.setPracticalStudents(18);
        studentAttendanceRate1.setBelateStudents(1);
        studentAttendanceRate1.setActualArrivalStudents(2);
        //求百分比
        NumberFormat numberFormat = NumberFormat.getInstance();
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format1 = numberFormat.format((float) 18 / (float) 20 * 100);
        studentAttendanceRate1.setAttendanceRate(format1+"%");

        StudentAttendanceRate studentAttendanceRate2 = new StudentAttendanceRate();
        studentAttendanceRate2.setStudentClass("二年级");
        studentAttendanceRate2.setDueStudents(23);
        studentAttendanceRate2.setPracticalStudents(20);
        studentAttendanceRate2.setBelateStudents(1);
        studentAttendanceRate2.setActualArrivalStudents(3);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format2 = numberFormat.format((float) 20 / (float) 23 * 100);
        studentAttendanceRate2.setAttendanceRate(format2+"%");

        StudentAttendanceRate studentAttendanceRate3 = new StudentAttendanceRate();
        studentAttendanceRate3.setStudentClass("三年级");
        studentAttendanceRate3.setDueStudents(22);
        studentAttendanceRate3.setPracticalStudents(20);
        studentAttendanceRate3.setBelateStudents(1);
        studentAttendanceRate3.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format3 = numberFormat.format((float) 20 / (float) 22 * 100);
        studentAttendanceRate3.setAttendanceRate(format3+"%");

        StudentAttendanceRate studentAttendanceRate4 = new StudentAttendanceRate();
        studentAttendanceRate4.setStudentClass("四年级");
        studentAttendanceRate4.setDueStudents(25);
        studentAttendanceRate4.setPracticalStudents(23);
        studentAttendanceRate4.setBelateStudents(1);
        studentAttendanceRate4.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format4 = numberFormat.format((float) 23 / (float) 25 * 100);
        studentAttendanceRate4.setAttendanceRate(format4+"%");

        StudentAttendanceRate studentAttendanceRate5 = new StudentAttendanceRate();
        studentAttendanceRate5.setStudentClass("五年级");
        studentAttendanceRate5.setDueStudents(24);
        studentAttendanceRate5.setPracticalStudents(23);
        studentAttendanceRate5.setBelateStudents(1);
        studentAttendanceRate5.setActualArrivalStudents(1);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format5 = numberFormat.format((float) 23 / (float) 24 * 100);
        studentAttendanceRate5.setAttendanceRate(format5+"%");

        StudentAttendanceRate studentAttendanceRate6 = new StudentAttendanceRate();
        studentAttendanceRate6.setStudentClass("六年级");
        studentAttendanceRate6.setDueStudents(24);
        studentAttendanceRate6.setPracticalStudents(23);
        studentAttendanceRate6.setBelateStudents(1);
        studentAttendanceRate6.setActualArrivalStudents(1);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format6 = numberFormat.format((float) 23 / (float) 24 * 100);
        studentAttendanceRate6.setAttendanceRate(format6+"%");

        studentAttendanceRates1.add(studentAttendanceRate1);
        studentAttendanceRates1.add(studentAttendanceRate2);
        studentAttendanceRates1.add(studentAttendanceRate3);
        studentAttendanceRates1.add(studentAttendanceRate4);
        studentAttendanceRates1.add(studentAttendanceRate5);
        studentAttendanceRates1.add(studentAttendanceRate6);
        //excel标题
        String[] title = {"年级", "应到人数", "实到人数", "迟到", "缺勤","出勤率"};

        //excel文件名
        String fileName = "出勤统计" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "出勤统计";

        String[][] content = new String[studentAttendanceRates1.size()][20];
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < studentAttendanceRates1.size(); i++) {
            content[i] = new String[title.length];
            StudentAttendanceRate studentAttendanceRate = studentAttendanceRates1.get(i);
            content[i][0] = studentAttendanceRate.getStudentClass();
            content[i][1] = studentAttendanceRate.getDueStudents().toString();
            content[i][2] = studentAttendanceRate.getPracticalStudents().toString();
            content[i][3] = studentAttendanceRate.getBelateStudents().toString();
            content[i][4] = studentAttendanceRate.getActualArrivalStudents().toString();
            content[i][5] = studentAttendanceRate.getAttendanceRate();

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
