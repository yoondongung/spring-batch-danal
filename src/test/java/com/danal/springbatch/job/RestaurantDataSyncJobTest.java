package com.danal.springbatch.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
public class RestaurantDataSyncJobTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    Job restaurantDataInfoJob;

    @BeforeEach
    void beforeEach() {
        jobLauncherTestUtils.setJob(restaurantDataInfoJob);
    }

    @Test
    public void restaurantDataInfoExecutionJobTest() throws Exception {
        JobParameters jobParameters = buildJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

    private JobParameters buildJobParameters() {
        return new JobParametersBuilder()
                .toJobParameters();
    }
}
