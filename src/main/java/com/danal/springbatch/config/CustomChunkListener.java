package com.danal.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;

@Slf4j
public class CustomChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        StepContext stepContext = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();

        log.info("{}:{}, nowReadCount: {}", stepContext.getJobName(), stepContext.getJobParameters(), stepExecution.getReadCount());
    }

    @Override
    public void afterChunk(ChunkContext context) {
        StepContext stepContext = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();

        log.info("{}:{}, nowReadCount:{}, nowCommitCount: {}", stepContext.getJobName(), stepContext.getJobParameters(),
                stepExecution.getReadCount(), stepExecution.getCommitCount());
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        StepContext stepContext = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();

        log.error("{}:{}, rollback: {}", stepContext.getJobName(), stepContext.getJobParameters(), stepExecution.getRollbackCount());
    }
}
