package com.danal.springbatch.job;

import com.danal.springbatch.model.RestaurantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RestaurantItemStreamReader {

    @Bean
    public SynchronizedItemStreamReader<RestaurantDto> restaurantDataReader() {
        FlatFileItemReader<RestaurantDto> delegateReader = createDelegateReader();
        SynchronizedItemStreamReader<RestaurantDto> synchronizedReader = new SynchronizedItemStreamReader<>();
        synchronizedReader.setDelegate(delegateReader);
        return synchronizedReader;
    }

    public FlatFileItemReader<RestaurantDto> createDelegateReader() {
        return new FlatFileItemReaderBuilder<RestaurantDto>()
                .name("restaurantItemReader")
                .resource(new ClassPathResource("fulldata.csv"))
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
