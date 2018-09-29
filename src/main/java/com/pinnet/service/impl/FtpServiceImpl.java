package com.pinnet.service.impl;

import com.pinnet.batch.common.CommonJob;
import com.pinnet.batch.job.CommonJobExecution;
import com.pinnet.batch.listener.UserListener;
import com.pinnet.batch.processor.UserItemProcessor;
import com.pinnet.batch.reader.setMapper.UserFieldSetMapper;
import com.pinnet.batch.writer.UserItemWriter;
import com.pinnet.service.IFtpService;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ServiceMode
@Transactional
public class FtpServiceImpl implements IFtpService{

    @Autowired
    JobLauncher jobLauncher;

    public void execute() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        //设置对应参数
        CommonJob commonJob = new CommonJob();
        commonJob.setJobName("userJob");
        commonJob.setStepName("userStep");
        File file = new File("D:/test/local");
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("not file");
            return;
        }
        List<Resource> resources = Arrays.stream(files).map(f -> new FileSystemResource(f)).collect(Collectors.toList());
        commonJob.setResources(resources.toArray(new Resource[resources.size()]));
        commonJob.setCacheNum(5);
        commonJob.setColums(new String[]{"id","name","age"});
        commonJob.setFieldSetMapper(new UserFieldSetMapper());
        commonJob.setItemProcessor(new UserItemProcessor());
        commonJob.setItemWriter(new UserItemWriter());
        commonJob.setJobExecutionListener(new UserListener());
        //建立采纳数
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        //运行
        jobLauncher.run(CommonJobExecution.getJob(commonJob), builder.toJobParameters());
    }
}
