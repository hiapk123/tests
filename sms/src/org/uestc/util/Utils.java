package org.uestc.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JDialog;

/**
 *
 * @author zxt
 */
public class Utils {

    public static final int pageSize = 10;//没10个位一页
    private final static AtomicInteger saleNo, orderNo;
    private static final Calendar current;

    static {
        current = new java.util.GregorianCalendar();
        saleNo = new AtomicInteger(1);
        orderNo = new AtomicInteger(1);
    }

    public static String formatDateFromStr(String longStr) {
        Date date = new Date();
        date.setTime(Long.valueOf(longStr));
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static void setCneter(JDialog jDialog) {
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = jDialog.getSize();
        if (frameSize.width > displaySize.width) {
            frameSize.width = displaySize.width;
        }
        jDialog.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height) / 2);
        jDialog.setAutoRequestFocus(true);
        //this.setAlwaysOnTop(true);
    }

    public static void setCneter(Frame frame) {
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > displaySize.width) {
            frameSize.width = displaySize.width;
        }
        frame.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height) / 2);
        frame.setAutoRequestFocus(true);
        //this.setAlwaysOnTop(true);
    }

    public static void setMax(Frame frame) {
        frame.setLocation(0, 0);
        Dimension scDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(scDimension);
    }

    public static void setHalfMax(Frame frame) {

        Dimension scDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension halfDimension = new Dimension(scDimension.width / 2, scDimension.height / 2);
        frame.setBounds((scDimension.width - halfDimension.width) / 2, (scDimension.height - halfDimension.height) / 2, halfDimension.width, halfDimension.height);
    }

    public static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    public static String generateAccountFlow() {
        String flowNum = current.get(Calendar.YEAR) + ""
                + (1 + current.get(Calendar.MONTH)) + ""
                + current.get(Calendar.DAY_OF_MONTH) + ""
                + current.get(Calendar.HOUR_OF_DAY) + ""
                + current.get(Calendar.MINUTE) + ""
                + current.get(Calendar.SECOND) + ""
                + current.get(Calendar.MILLISECOND) + ""
                + saleNo.getAndIncrement();

        return flowNum;//java.util.UUID.randomUUID().toString();
    }

    public static String generateOrderNo() {
        String orderNum = "ORDER"+current.get(Calendar.YEAR) + ""
                + (1 + current.get(Calendar.MONTH)) + ""
                + current.get(Calendar.DAY_OF_MONTH) + ""
                + current.get(Calendar.HOUR_OF_DAY) + ""
                + current.get(Calendar.MINUTE) + ""
                + current.get(Calendar.SECOND) + ""
                + current.get(Calendar.MILLISECOND) + ""
                + orderNo.getAndIncrement();
        return orderNum;
    }

    public static Object getEmpID(String username) {
        String sql = "SELECT emp_id FROM users WHERE s_del=1 and u_name=?";
        return SqlHelper.findAll(sql, username).get(0).get("emp_id");
    }
}
