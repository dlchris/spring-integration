package com.pinnet.batch.job;

import com.pinnet.batch.common.CommonJob;
import com.pinnet.batch.reader.CommonReader;
import com.pinnet.util.SpringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.util.Assert;

public class CommonJobExecution {

    //获取配置的job家里工程
    private static JobBuilderFactory jobBuilderFactory = SpringUtils.getBean("jobBuilderFactory", JobBuilderFactory.class);

    //获取节点的建立工程
    private static StepBuilderFactory stepBuilderFactory = SpringUtils.getBean("stepBuilderFactory", StepBuilderFactory.class);

    //此方法，用于公共的job提供
    public static Job getJob(CommonJob commonJob) {
        //去人job名称手存在
        Assert.notNull(commonJob.getJobName(), "jobName not null");
        //job的建立
        SimpleJobBuilder jobBuilder = jobBuilderFactory.get(commonJob.getJobName()).incrementer(new RunIdIncrementer()).start(getStep(commonJob));
        //如果存在监听则加入job监听
        if (commonJob.getJobExecutionListener() != null) {
            jobBuilder.listener(commonJob.getJobExecutionListener());
        }
        return jobBuilder.build();
    }

    //家里节点
    private static Step getStep(CommonJob commonJob) {
        Assert.notNull(commonJob.getStepName(), "stepName not null");
        Assert.notNull(commonJob.getCacheNum(), "cacheNum not null");
        SimpleStepBuilder<Object, Object> stepBuilder = stepBuilderFactory.get(commonJob.getStepName()).chunk(commonJob.getCacheNum());
        //读取方式的实现，默认可以使用File的read
        if (commonJob.getLineMapper() != null) {
            if (commonJob.getResource() != null) {
                FlatFileItemReader flatFileItemReader = CommonReader.setLineMapper(commonJob.getLineMapper(), commonJob.getResource());
                stepBuilder.reader(flatFileItemReader);
            } else {
                //多个问题文件的处理
                Assert.notNull(commonJob.getResources(), "please set resource or resources in commonJob");
                MultiResourceItemReader resourceItemReader = CommonReader.setLineMapper(commonJob.getResources(), CommonReader.setLineMapper(commonJob.getLineMapper()));
                stepBuilder.reader(resourceItemReader);
            }
        } else {
            Assert.notNull(commonJob.getColums(), "colums not null or set lineMapper");
            if (commonJob.getResource() != null) {
                FlatFileItemReader flatFileItemReader = CommonReader.setLineMapper(commonJob.getResource(), commonJob.getColums(), commonJob.getFieldSetMapper());
                stepBuilder.reader(flatFileItemReader);
            } else {
                //多个问题文件的处理
                Assert.notNull(commonJob.getResources(), "please set resource or resources in commonJob");
                MultiResourceItemReader resourceItemReader = CommonReader.setLineMapper(commonJob.getResources(), CommonReader.setLineMapper(commonJob.getColums(), commonJob.getFieldSetMapper()));
                stepBuilder.reader(resourceItemReader);
            }
        }
        //设置中间处理，和写的过程，以及对应监听
        if (commonJob.getItemProcessor() != null) {
            stepBuilder.processor(commonJob.getItemProcessor());
        }
        if (commonJob.getItemWriter() != null) {
            stepBuilder.writer(commonJob.getItemWriter());
        }
        if (commonJob.getReadListener() != null) {
            stepBuilder.listener(commonJob.getReadListener());
        }
        if (commonJob.getProcessListener() != null) {
            stepBuilder.listener(commonJob.getProcessListener());
        }
        if (commonJob.getWriteListener() != null) {
            stepBuilder.listener(commonJob.getWriteListener());
        }
        return stepBuilder.build();
    }
}
