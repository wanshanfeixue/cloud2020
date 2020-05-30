package com.edian.springcloud;

import javax.swing.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
        //默认时区获取当前时间
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime);
        //指定时区获取当前时间
        ZonedDateTime.now(ZoneId.of("America/New_York"));

        JOptionPane.showMessageDialog(null, "异常：请联系管理员","提示", JOptionPane.INFORMATION_MESSAGE);
    }
}
