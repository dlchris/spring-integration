package com.pinnet.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;

import java.io.File;
import java.util.Arrays;

//作业监听
public class UserListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("开始执行");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
           File file = new File("D:/test/local");
            File[] files = file.listFiles();
            Arrays.stream(files).filter(f -> f.getName().indexOf("user") > -1).forEach(f -> f.delete());
        }
        System.out.println(jobExecution.getStatus());
        System.out.println("结束执行");
    }
}
