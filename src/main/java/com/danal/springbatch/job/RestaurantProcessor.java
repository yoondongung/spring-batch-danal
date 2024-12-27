package com.danal.springbatch.job;

import com.danal.springbatch.entity.Restaurant;
import com.danal.springbatch.model.RestaurantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Configuration
public class RestaurantProcessor implements ItemProcessor<RestaurantDto, Restaurant> {

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Restaurant process(RestaurantDto item) {
        try {
            return Restaurant.builder()
                    .id(item.id())
                    .openServiceName(item.openServiceName())
                    .openServiceId(item.openServiceId())
                    .openAutonomousDistrictCode(item.openAutonomousDistrictCode())
                    .managementCode(item.managementCode())
                    .permitDate(parseLocalDate(item.permitDate()))
                    .permitCancelDate(parseLocalDate(item.permitCancelDate()))
                    .businessStatusCode(item.businessStatusCode())
                    .businessStatusName(item.businessStatusName())
                    .businessName(item.businessName())
                    .detailedBusinessStatusCode(item.detailedBusinessStatusCode())
                    .detailedBusinessStatusName(item.detailedBusinessStatusName())
                    .closureDate(parseLocalDate(item.closureDate()))
                    .suspensionStartDate(parseLocalDate(item.suspensionStartDate()))
                    .suspensionEndDate(parseLocalDate(item.suspensionEndDate()))
                    .reopenDate(parseLocalDate(item.reopenDate()))
                    .phone(item.phone())
                    .locationArea(item.locationArea())
                    .postalCode(item.postalCode())
                    .address(item.address())
                    .roadAddress(item.roadAddress())
                    .roadPostalCode(item.roadPostalCode())
                    .businessName(item.businessName())
                    .lastModified(parseLocalDateTime(item.lastModified()))
                    .dataUpdateType(item.dataUpdateType())
                    .dataUpdateDate(parseLocalDateTime(item.dataUpdateDate()))
                    .businessTypeName(item.businessTypeName())
                    .xCoordinate(parseDouble(item.xCoordinate()))
                    .yCoordinate(parseDouble(item.yCoordinate()))
                    .hygieneType(item.hygieneType())
                    .maleWorkers(parseInteger(item.maleWorkers()))
                    .femaleWorkers(parseInteger(item.femaleWorkers()))
                    .surroundingAreaType(item.surroundingAreaType())
                    .ratingType(item.ratingType())
                    .waterSupplyType(item.waterSupplyType())
                    .totalWorkers(parseInteger(item.totalWorkers()))
                    .headOfficeWorkers(parseInteger(item.headOfficeWorkers()))
                    .factoryOfficeWorkers(parseInteger(item.factoryOfficeWorkers()))
                    .factorySalesWorkers(parseInteger(item.factorySalesWorkers()))
                    .factoryProductionWorkers(parseInteger(item.factoryProductionWorkers()))
                    .buildingOwnershipType(item.buildingOwnershipType())
                    .guaranteeAmount(item.guaranteeAmount())
                    .monthlyRent(item.monthlyRent())
                    .multiUseFacility(item.multiUseFacility())
                    .facilityScale(item.facilityScale())
                    .traditionalStoreNumber(item.traditionalStoreNumber())
                    .traditionalMainDish(item.traditionalMainDish())
                    .homepage(item.homepage())
                    .build();
        } catch (Exception e) {
            log.warn("RestaurantProcessor parsing error. item : {}, errorMsg: {}", item.toString(), e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private LocalDate parseLocalDate(String date) {
        if (Objects.isNull(date) || date.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(date, dateFormatter);
    }

    private LocalDateTime parseLocalDateTime(String date) {
        if (Objects.isNull(date) || date.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    private Double parseDouble(String data) {
        if (Objects.isNull(data) || data.trim().isEmpty()) {
            return null;
        }
        return Double.parseDouble(data);
    }

    private Integer parseInteger(String data){
        if (Objects.isNull(data) || data.trim().isEmpty()) {
            return null;
        }
        return Integer.parseInt(data);
    }

}
