package com.pattabhi.batch.job.learning;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MyJobConfiguration {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public Job practiceJob() {
        return new JobBuilder("practiceJob", jobRepository).listener(myJobListener()).start(practiceStep()).build();
    }

    @Bean
    public Step practiceStep() {
        StepBuilder step1 = new StepBuilder("practiceStep", jobRepository);
        TaskletStep writer = step1.<String, String>chunk(4, transactionManager).reader(reader()).processor(processor()).writer(writer()).build();
        return writer;
    }

    @Bean
    public Reader reader() {
        return new Reader();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public Writer writer() {
        return new Writer();
    }

    @Bean
    public MyJobListener myJobListener() {
        return new MyJobListener();
    }
}

