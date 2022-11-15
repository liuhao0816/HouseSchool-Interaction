package com.gxa.common.utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码生成工具类
 */
public class ValidateCode {

    public Map<String,Object> validateCode() {
        Map<String,Object> map = new HashMap<>();
        // 验证码图片边框宽度
        final int WIDTH = 100;
        // 验证码图片边框高度
        final int HEIGHT = 38;
        // 验证码字体高度
        int FONT_HEIGHT = HEIGHT - 12;
        // 验证码干扰线条数
        int INTERFERENCE_LINE = 4;
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Font mFont = new Font("Arial", Font.TRUETYPE_FONT, 18);
        Graphics g = image.getGraphics();
        Random rd = new Random();
        // 设置背景颜色
        g.setColor(new Color(rd.nextInt(55) + 200, rd.nextInt(55) + 200, rd
                .nextInt(55) + 200));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 设置字体
        g.setFont(mFont);
        // 画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
        // 验证码字符串
        String text = getText();

        // 运算表达式
        String operationExpression = text.substring(0, text.lastIndexOf("@") - 1);
        // 计算结果
        String resultValue = text.substring(text.lastIndexOf("@") + 1, text.length());

        g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
                .nextInt(200)));
        // 根据画笔颜色绘制字符
        g.drawString(operationExpression, 5, FONT_HEIGHT);
        int r = 0;
        int gg = 0;
        int b = 0;
        // 绘制干扰线
        int x1, y1, x2, y2;
        for (int i = 0; i < INTERFERENCE_LINE; i++) {
            // 随机生成rgb颜色值，并设置画笔颜色
            r = rd.nextInt(255);
            gg = rd.nextInt(255);
            b = rd.nextInt(255);
            g.setColor(new Color(r, gg, b));
            x1 = rd.nextInt(WIDTH);
            y1 = rd.nextInt(HEIGHT);
            x2 = rd.nextInt(WIDTH);
            y2 = rd.nextInt(HEIGHT);
            // 绘制线条
            g.drawLine(x1, y1, x2, y2);
        }
        g.dispose();

        map.put("image",image);
        map.put("resultValue",resultValue);
        return map;
    }

    /**
     * 获取验证码
     * @return
     */
    public  String getText() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder result = new StringBuilder(); // 运算验证码结果
        int x = random.nextInt(51);
        int y = random.nextInt(51);
        int operationalRules = random.nextInt(3);
        switch (operationalRules) {
            case 0:
                result = add(x, y);
                break;
            case 1:
                result = subtract(x, y);
                break;
            case 2:
                result = multiply(x, y);
                break;
            case 3:
                result = divide(x, y);
                break;
        }

        return result.toString();
    }

    /**
     * 加法运算
     */
    private  StringBuilder add(int x, int y) {
        StringBuilder result = new StringBuilder(); // 运算验证码结果
        result.append(x);
        result.append(" + ");
        result.append(y);
        result.append(" = ?@");
        result.append(x + y);
        return result;
    }
    /**
     * 减法运算
     */
    private  StringBuilder subtract(int x, int y) {
        StringBuilder result = new StringBuilder(); // 运算验证码结果
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        result.append(max);
        result.append(" - ");
        result.append(min);
        result.append(" = ?@");
        result.append(max - min);
        return result;
    }
    /**
     * 乘法运算
     */
    private  StringBuilder multiply(int x, int y) {
        StringBuilder result = new StringBuilder(); // 运算验证码结果
        int value = x * y;
        result.append(x);
        result.append(value > 100 ? " + " : " * ");
        result.append(y);
        result.append(" = ?@");
        result.append(value > 100 ? x + y : x * y);
        return result;
    }
    /**
     * 除法运算
     */
    private StringBuilder divide(int x, int y) {
        StringBuilder result = new StringBuilder(); // 运算验证码结果
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        if (min == 0) {
            multiply(max, min);
        } else if (max % min == 0) {
            result.append(max);
            result.append(" / ");
            result.append(min);
            result.append(" = ?@");
            result.append(max / min);
        } else {
            result.append(max);
            result.append(" % ");
            result.append(min);
            result.append(" = ?@");
            result.append(max % min);
        }
        return result;
    }

}
