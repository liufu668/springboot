package com.study.springboot_springtask.dingshi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTaskDemo {

    //打印日志
    private Logger logger=LoggerFactory.getLogger(SpringTaskDemo.class);

    private int fixedDelayCount=1;
    private int fixedRateCount=1;
    private int initialDelayCount=1;
    private int cronCount=1;

    @Scheduled(fixedDelay = 5000)
    //表示当前方法执行完毕5000ms后,Spring scheduling会再次调用该方法
    public void testFixDelay(){
        logger.info("=========fixedDelay: 第{}次执行方法",fixedDelayCount++);
    }

    @Scheduled(fixedRate = 5000)
    //表示当前方法开始执行5000ms后,Spring scheduling会再次调用该方法
    public void testFixedRate(){
        logger.info("=========fixedRate: 第{}次执行方法",fixedRateCount++);
    }

    @Scheduled(initialDelay = 1000,fixedRate = 5000)
    //表示延迟1000ms执行第一次任务
    public void testInitialDelay(){
        logger.info("=========initialDelay: 第{}次执行方法",initialDelayCount++);
    }

    @Scheduled(cron = "0 15 10 * * ?")
    //cron接受cron表达式,根据cron表达式确定定时规则,表示:每天10:15定时触发执行
    public void testCron(){
        logger.info("=========cron: 第{}次执行方法",cronCount++);
    }
}
