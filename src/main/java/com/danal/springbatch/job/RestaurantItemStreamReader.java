package com.danal.springbatch.job;

import com.danal.springbatch.model.RestaurantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class RestaurantItemStreamReader {

    @Bean
    @StepScope
    public SynchronizedItemStreamReader<RestaurantDto> restaurantDataReader(@Value("#{jobParameters['filePath']}") String filePath) {
        FlatFileItemReader<RestaurantDto> delegateReader = createDelegateReader(filePath);
        SynchronizedItemStreamReader<RestaurantDto> synchronizedReader = new SynchronizedItemStreamReader<>();
        synchronizedReader.setDelegate(delegateReader);
        return synchronizedReader;
    }

    public FlatFileItemReader<RestaurantDto> createDelegateReader(String filePath) {
        Resource resource;
        if (Objects.isNull(filePath) || filePath.isBlank()) {
            resource = new ClassPathResource("fulldata.csv");
        } else {
            resource = new FileSystemResource(filePath);
        }

        return new FlatFileItemReaderBuilder<RestaurantDto>()
                .name("restaurantItemReader")
                .resource(resource)
                .encoding("UTF-8")
                .delimited()
                .strict(false) // 맨 마지막 빈 필드 허용
                .names(RestaurantDto.getDeclaredFieldNames().toArray(String[]::new))
                .targetType(RestaurantDto.class)
                .linesToSkip(1) // 헤더 스킵
                .build();
    }

    private void setSkipLinesCallback(FlatFileItemReader<RestaurantDto> reader) {
        reader.setSkippedLinesCallback(line -> log.warn("Skipped line: {}", line));
    }
}
