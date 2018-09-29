package com.pinnet.batch.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

@Configuration
@Import(value = {DataSourceConfiguration.class})
public class BatchConfig {

    //job操作数据库，这里使用的缓存，可以自己配置其他数据库
    @Bean(name = "jobRepository")
    public MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean(DataSource dataSource, DataSourceTransactionManager transactionManager) {
//        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setDatabaseType("MYSQL");
//        factoryBean.setTransactionManager(transactionManager);
//        return factoryBean;
        MapJobRepositoryFactoryBean jobRepository = new MapJobRepositoryFactoryBean();
        jobRepository.setTransactionManager(transactionManager);
        return jobRepository;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        //执行器，用于多线程执行
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setKeepAliveSeconds(120);
        taskExecutor.setMaxPoolSize(1000);
        return taskExecutor;
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository, TaskExecutor taskExecutor) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(taskExecutor);
        return jobLauncher;
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
        JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(jobRepository);
        return jobBuilderFactory;
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, DataSourceTransactionManager transactionManager) {
        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository, transactionManager);
        return stepBuilderFactory;
    }
}
