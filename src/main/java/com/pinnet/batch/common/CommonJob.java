package com.pinnet.batch.common;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.core.io.Resource;

public class CommonJob {

    //job名称
    private String jobName;
    //步骤名称
    private String stepName;
    //单个资源
    private Resource resource;
    //多个资源
    private Resource[] resources;
    //缓存写入的数量
    private Integer cacheNum;
    //解析数据字段
    private String[] colums;
    //解析规则，返回成pojo等
    private FieldSetMapper fieldSetMapper;
    //自定义解析规则
    private LineMapper lineMapper;
    //处理过程
    private ItemProcessor itemProcessor;
    //写入过程
    private ItemWriter itemWriter;
    //作业监听
    private JobExecutionListener jobExecutionListener;
    //读取监听
    private ItemReadListener readListener;
    //处理监听
    private ItemProcessListener processListener;
    //写入监听
    private ItemWriteListener writeListener;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource[] getResources() {
        return resources;
    }

    public void setResources(Resource[] resources) {
        this.resources = resources;
    }

    public Integer getCacheNum() {
        return cacheNum;
    }

    public void setCacheNum(Integer cacheNum) {
        this.cacheNum = cacheNum;
    }

    public String[] getColums() {
        return colums;
    }

    public void setColums(String[] colums) {
        this.colums = colums;
    }

    public FieldSetMapper getFieldSetMapper() {
        return fieldSetMapper;
    }

    public void setFieldSetMapper(FieldSetMapper fieldSetMapper) {
        this.fieldSetMapper = fieldSetMapper;
    }

    public LineMapper getLineMapper() {
        return lineMapper;
    }

    public void setLineMapper(LineMapper lineMapper) {
        this.lineMapper = lineMapper;
    }

    public ItemProcessor getItemProcessor() {
        return itemProcessor;
    }

    public void setItemProcessor(ItemProcessor itemProcessor) {
        this.itemProcessor = itemProcessor;
    }

    public ItemWriter getItemWriter() {
        return itemWriter;
    }

    public void setItemWriter(ItemWriter itemWriter) {
        this.itemWriter = itemWriter;
    }

    public JobExecutionListener getJobExecutionListener() {
        return jobExecutionListener;
    }

    public void setJobExecutionListener(JobExecutionListener jobExecutionListener) {
        this.jobExecutionListener = jobExecutionListener;
    }

    public ItemReadListener getReadListener() {
        return readListener;
    }

    public void setReadListener(ItemReadListener readListener) {
        this.readListener = readListener;
    }

    public ItemProcessListener getProcessListener() {
        return processListener;
    }

    public void setProcessListener(ItemProcessListener processListener) {
        this.processListener = processListener;
    }

    public ItemWriteListener getWriteListener() {
        return writeListener;
    }

    public void setWriteListener(ItemWriteListener writeListener) {
        this.writeListener = writeListener;
    }
}
