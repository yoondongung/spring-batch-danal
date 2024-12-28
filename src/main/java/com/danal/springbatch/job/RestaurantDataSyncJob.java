package com.danal.springbatch.job;

import com.danal.springbatch.config.CustomChunkListener;
import com.danal.springbatch.config.RestaurantSkipListener;
import com.danal.springbatch.entity.Restaurant;
import com.danal.springbatch.model.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestaurantDataSyncJob {

    private final String jobName = "restaurantDataJob";
    @Value("${custom.batch.chunk-size}")
    private int chunkSize;

    @Bean
    public Job restaurantDataInfoJob(JobRepository jobRepository, Step step) {
        return new JobBuilder(jobName, jobRepository)
                .incrementer(new RunIdIncrementer())// 매번 재실행시 변경된 것으로 인지시켜 실행 가능하도록
                .start(step)
                .listener(customSkipListener())
                .build();
    }

    @Bean
    public Step restaurantDataReadStep(JobRepository jobRepository,
                                       SynchronizedItemStreamReader<RestaurantDto> restaurantDataReader,
                                       RestaurantProcessor restaurantProcessor,
                                       JdbcBatchItemWriter<Restaurant> restaurantDtoJdbcBatchItemWriter,
                                       PlatformTransactionManager transactionManager) {
        return new StepBuilder(jobName, jobRepository)
                .<RestaurantDto, Restaurant>chunk(chunkSize, transactionManager)
                .reader(restaurantDataReader)
                .faultTolerant()
                .skip(IllegalArgumentException.class)
                .skipLimit(10)
                .processor(restaurantProcessor)
                .writer(restaurantDtoJdbcBatchItemWriter)
                .listener(customChunkListener())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(8);
        taskExecutor.setThreadNamePrefix("executor-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);

        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setAwaitTerminationSeconds(30);
        taskExecutor.setDaemon(true);

        taskExecutor.setRejectedExecutionHandler((r, executor) -> {
            log.warn("TaskExecutor Rejected : {}", r.toString());
        });
        return taskExecutor;
    }


    @Bean
    public CustomChunkListener customChunkListener() {
        return new CustomChunkListener();
    }

    @Bean
    public RestaurantSkipListener customSkipListener(){
        return new RestaurantSkipListener();
    }

}
