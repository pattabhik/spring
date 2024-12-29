package com.pattabhi.batch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductBatchJobTester {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job productJob;

    @Test
    public void testJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncher.run(productJob, jobParameters);
    }
}
