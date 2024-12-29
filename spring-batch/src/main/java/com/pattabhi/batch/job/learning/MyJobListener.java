package com.pattabhi.batch.job.learning;

import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(org.springframework.batch.core.JobExecution jobExecution) {
        System.out.println("Before Job");
    }
    @Override
    public void afterJob(org.springframework.batch.core.JobExecution jobExecution) {
        System.out.println("After Job");
    }
}
