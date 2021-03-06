package com.hb.scms.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule {
    public final static long SECOND = 1 * 1000;
    public final static long HOUR = 1 * 1000*3600;
    /*
        "0 0 12 * * ?"    每天中午十二点触发
        "0 15 10 * * ?"    每天早上10：15触发
        "0 15 10 * * ? *"    每天早上10：15触发
        "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
        "0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
        "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
        "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
        "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
        "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
        "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
    */
    @Scheduled(fixedRate = SECOND * 30)
    public void fixedRateJob() {
        System.out.println("html is flush !");
    }

    public static void main(String[] args) {
    }
}
