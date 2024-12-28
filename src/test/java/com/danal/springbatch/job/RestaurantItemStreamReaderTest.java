package com.danal.springbatch.job;

import com.danal.springbatch.model.RestaurantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
public class RestaurantItemStreamReaderTest {

    @Autowired
    private RestaurantItemStreamReader reader;
    private FlatFileItemReader<RestaurantDto> flatFileItemReader;

    @BeforeEach
    void setUp() {
        flatFileItemReader = spy(reader.createDelegateReader(null));
    }


    @Test
    @DisplayName("FlatFileItemReader Test")
    void testRestaurantDataReader() throws Exception {

        ExecutionContext executionContext = new ExecutionContext();

        List<String> skippedLines = new ArrayList<>();
        flatFileItemReader.setSkippedLinesCallback(line -> {
            skippedLines.add(line);
            System.out.println("Skipped line: " + line); // 디버깅
        });

        flatFileItemReader.open(executionContext);

        List<RestaurantDto> result = new ArrayList<>();
        RestaurantDto item;
        while ((item = flatFileItemReader.read()) != null) {
            result.add(item);
        }

        flatFileItemReader.close();

        assertThat(skippedLines).hasSize(1);
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(21L);
        assertThat(result.get(0).id()).isEqualTo(1L);
    }
}
