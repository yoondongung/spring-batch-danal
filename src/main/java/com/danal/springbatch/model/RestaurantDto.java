package com.danal.springbatch.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public record RestaurantDto(
        Long id,
        String openServiceName,
        String openServiceId,
        String openAutonomousDistrictCode,
        String managementCode,
        String permitDate,
        String permitCancelDate,
        String businessStatusCode,
        String businessStatusName,
        String detailedBusinessStatusCode,
        String detailedBusinessStatusName,
        String closureDate,
        String suspensionStartDate,
        String suspensionEndDate,
        String reopenDate,
        String phone,
        String locationArea,
        String postalCode,
        String address,
        String roadAddress,
        String roadPostalCode,
        String businessName,
        String lastModified,
        String dataUpdateType,
        String dataUpdateDate,
        String businessTypeName,
        String xCoordinate,
        String yCoordinate,
        String hygieneType,
        String maleWorkers,
        String femaleWorkers,
        String surroundingAreaType,
        String ratingType,
        String waterSupplyType,
        String totalWorkers,
        String headOfficeWorkers,
        String factoryOfficeWorkers,
        String factorySalesWorkers,
        String factoryProductionWorkers,
        String buildingOwnershipType,
        String guaranteeAmount,
        String monthlyRent,
        String multiUseFacility,
        String facilityScale,
        String traditionalStoreNumber,
        String traditionalMainDish,
        String homepage
) {
    public static List<String> getDeclaredFieldNames(){
        Field[] fields = RestaurantDto.class.getDeclaredFields();
        List<String> result = new ArrayList<>();
        for (Field field: fields) {
            result.add(field.getName());
        }
        return result;
    }
}
