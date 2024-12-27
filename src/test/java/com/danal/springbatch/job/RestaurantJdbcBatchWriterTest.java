package com.danal.springbatch.job;

import com.danal.springbatch.entity.Restaurant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
public class RestaurantJdbcBatchWriterTest {

    @Autowired
    private JdbcBatchItemWriter<Restaurant> writer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("DB에 저장이 잘 되는지 테스트")
    void testRestaurantJdbcBatchItemWriter() throws Exception {
        List<Restaurant> restaurants = List.of(
                new Restaurant("3140000-101-2024-00382", 1L, "일반음식점", "07_24_04_P", "3140000",
                        LocalDate.of(2024, 11, 18), null, "03", "폐업", "02", "폐업",
                        LocalDate.of(2024, 11, 18), null, null, null, null, null,
                        "158-724", "서울특별시 양천구 목동 916 현대하이페리온", "서울특별시 양천구 목동동로 257, 지하2층 (목동, 현대하이페리온)",
                        "07998", "페스토페스토", LocalDateTime.of(2024, 11, 29, 04, 15, 8),
                        "U", LocalDateTime.of(2024, 12, 1, 02, 40, 0),
                        "기타", 188884.075622342, 447186.888604306, "기타", 0, 0,
                        null,null,null, 0,0,0,0,0,
                        null, "0", "0", "Yes", "Scale1", null, null,null)
        );

        Chunk<Restaurant> chunk = new Chunk<>(restaurants);
        writer.write(chunk);

        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM restaurant", Long.class);
        assertThat(count).isEqualTo(1);

        Map<String, Object> savedData = jdbcTemplate.queryForMap("SELECT * FROM restaurant WHERE management_code = ?", "3140000-101-2024-00382");
        assertThat(savedData.get("management_code")).isEqualTo("3140000-101-2024-00382");
        assertThat(savedData.get("open_service_id")).isEqualTo("07_24_04_P");
    }
}
