package com.danal.springbatch.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="RESTAURANT")
@ToString
public class Restaurant {

    @Id
    private String managementCode;
    private Long id;
    private String openServiceName;
    private String openServiceId;
    private String openAutonomousDistrictCode;
    private LocalDate permitDate;
    private LocalDate permitCancelDate;
    private String businessStatusCode;
    private String businessStatusName;
    private String detailedBusinessStatusCode;
    private String detailedBusinessStatusName;
    private LocalDate closureDate;
    private LocalDate suspensionStartDate;
    private LocalDate suspensionEndDate;
    private LocalDate reopenDate;
    private String phone;
    private String locationArea;
    private String postalCode;
    private String address;
    private String roadAddress;
    private String roadPostalCode;
    private String businessName;
    private LocalDateTime lastModified;
    private String dataUpdateType;
    private LocalDateTime dataUpdateDate;
    private String businessTypeName;
    private Double xCoordinate;
    private Double yCoordinate;
    private String hygieneType;
    private Integer maleWorkers;
    private Integer femaleWorkers;
    private String surroundingAreaType;
    private String ratingType;
    private String waterSupplyType;
    private Integer totalWorkers;
    private Integer headOfficeWorkers;
    private Integer factoryOfficeWorkers;
    private Integer factorySalesWorkers;
    private Integer factoryProductionWorkers;
    private String buildingOwnershipType;
    private String guaranteeAmount;
    private String monthlyRent;
    private String multiUseFacility;
    private String facilityScale;
    private String traditionalStoreNumber;
    private String traditionalMainDish;
    private String homepage;

}
