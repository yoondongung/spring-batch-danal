package com.danal.springbatch.job;

import com.danal.springbatch.entity.Restaurant;
import com.danal.springbatch.model.RestaurantDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
public class RestaurantProcessorTest {

    @Autowired
    private RestaurantProcessor restaurantProcessor;

    @Test
    @DisplayName("정상적인 데이터 processor")
    void testProcessor_withValidData() {
        RestaurantDto validDto = sampleRestaurantDto();

        Restaurant result = restaurantProcessor.process(validDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getManagementCode()).isEqualTo("3140000-101-2024-00382");
        assertThat(result.getPermitDate()).isEqualTo(LocalDate.of(2024, 11, 18));
        assertThat(result.getLastModified()).isEqualTo(LocalDateTime.of(2024,11,29, 4,15,8));
        assertThat(result.getTotalWorkers()).isEqualTo(10);
        assertThat(result.getXCoordinate()).isEqualTo(188884.075622342);
    }

    @Test
    @DisplayName("데이트 형식이 맞지 않는 데이터 processor")
    void testProcessor_withInValidData() {
        RestaurantDto invalidDto = sampleInValidRestaurantDto();
        assertThatThrownBy(() -> restaurantProcessor.process(invalidDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2024-11-00");
    }

    private RestaurantDto sampleRestaurantDto(){
        return new RestaurantDto(
                1L,"일반음식점", "07_24_04_P", "3140000", "3140000-101-2024-00382",
                "2024-11-18", null, "03", "폐업", "02",
                "폐업", "2024-11-18", null, null,null,
                null, null, "158-724", "서울특별시 양천구 목동 916 현대하이페리온", "서울특별시 양천구 목동동로 257, 지하2층 (목동, 현대하이페리온)",
                "07998", "페스토페스토", "2024-11-29 04:15:08", "U", "2024-12-01 02:40:00",
                "기타", "188884.075622342", "447186.888604306", "기타", "0",
                "0", null, null, null, "10",
                "0", "0", "0", "0", null,
                "0", "0", "Yes", "Scale1", null,
                null, null
        );
    }

    private RestaurantDto sampleInValidRestaurantDto(){
        return new RestaurantDto(
                2L,"일반음식점", "07_24_04_P", "3140000", "3140000-101-2024-00383",
                "2024-11-18", null, "03", "폐업", "02",
                "폐업", "2024-11-00", null, null,null,
                null, null, "158-724", "서울특별시 양천구 목동 916 현대하이페리온", "서울특별시 양천구 목동동로 257, 지하2층 (목동, 현대하이페리온)",
                "07998", "페스토페스토", "2024-11-29 04:15:08", "U", "2024-12-01 02:40:00",
                "기타", "188884.075622342", "447186.888604306", "기타", "0",
                "0", null, null, null, "10",
                "0", "0", "0", "0", null,
                "0", "0", "Yes", "Scale1", null,
                null, null
        );
    }

}
