package com.danal.springbatch.job;

import com.danal.springbatch.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class RestaurantJdbcBatchWriter {

    private static final String INSERT_SQL =
            "INSERT INTO restaurant " +
                    "(id, open_service_name, open_service_id, open_autonomous_district_code, management_code, " +
                    "permit_date, permit_cancel_date, business_status_code, business_status_name, detailed_business_status_code, " +
                    "detailed_business_status_name, closure_date, suspension_start_date, suspension_end_date, reopen_date, " +
                    "phone, location_area, postal_code, address, road_address, " +
                    "road_postal_code, business_name, last_modified, data_update_type, data_update_date, " +
                    "business_type_name, x_coordinate, y_coordinate, hygiene_type, male_workers, " +
                    "female_workers, surrounding_area_type, rating_type, water_supply_type, total_workers, " +
                    "head_office_workers, factory_office_workers, factory_sales_workers, factory_production_workers, building_ownership_type, " +
                    "guarantee_amount, monthly_rent, multi_use_facility, facility_scale, traditional_store_number, " +
                    "traditional_main_dish, homepage) " +
                    "VALUES(:id, :openServiceName, :openServiceId, :openAutonomousDistrictCode, :managementCode, " +
                    ":permitDate, :permitCancelDate, :businessStatusCode, :businessStatusName, :detailedBusinessStatusCode, " +
                    ":detailedBusinessStatusName, :closureDate, :suspensionStartDate, :suspensionEndDate, :reopenDate, " +
                    ":phone, :locationArea, :postalCode, :address, :roadAddress, " +
                    ":roadPostalCode, :businessName, :lastModified, :dataUpdateType, :dataUpdateDate, " +
                    ":businessTypeName, :xCoordinate, :yCoordinate, :hygieneType, :maleWorkers, " +
                    ":femaleWorkers, :surroundingAreaType, :ratingType, :waterSupplyType, :totalWorkers, " +
                    ":headOfficeWorkers, :factoryOfficeWorkers, :factorySalesWorkers, :factoryProductionWorkers, :buildingOwnershipType, " +
                    ":guaranteeAmount, :monthlyRent, :multiUseFacility, :facilityScale, :traditionalStoreNumber, " +
                    ":traditionalMainDish, :homepage) " +
                    "ON DUPLICATE KEY UPDATE " +
                    "id = VALUES(id), open_service_name = VALUES(open_service_name), open_service_id = VALUES(open_service_id), " +
                    "open_autonomous_district_code = VALUES(open_autonomous_district_code), " +
                    "permit_date = VALUES(permit_date), permit_cancel_date = VALUES(permit_cancel_date), " +
                    "business_status_code = VALUES(business_status_code), business_status_name = VALUES(business_status_name), " +
                    "detailed_business_status_code = VALUES(detailed_business_status_code), " +
                    "detailed_business_status_name = VALUES(detailed_business_status_name), closure_date = VALUES(closure_date), " +
                    "suspension_start_date = VALUES(suspension_start_date), suspension_end_date = VALUES(suspension_end_date), " +
                    "reopen_date = VALUES(reopen_date), phone = VALUES(phone), location_area = VALUES(location_area), " +
                    "postal_code = VALUES(postal_code), address = VALUES(address), road_address = VALUES(road_address), " +
                    "road_postal_code = VALUES(road_postal_code), business_name = VALUES(business_name), " +
                    "last_modified = VALUES(last_modified), data_update_type = VALUES(data_update_type), " +
                    "data_update_date = VALUES(data_update_date), business_type_name = VALUES(business_type_name), " +
                    "x_coordinate = VALUES(x_coordinate), y_coordinate = VALUES(y_coordinate), hygiene_type = VALUES(hygiene_type), " +
                    "male_workers = VALUES(male_workers), female_workers = VALUES(female_workers), surrounding_area_type = VALUES(surrounding_area_type), " +
                    "rating_type = VALUES(rating_type), water_supply_type = VALUES(water_supply_type), total_workers = VALUES(total_workers), " +
                    "head_office_workers = VALUES(head_office_workers), factory_office_workers = VALUES(factory_office_workers), " +
                    "factory_sales_workers = VALUES(factory_sales_workers), factory_production_workers = VALUES(factory_production_workers), " +
                    "building_ownership_type = VALUES(building_ownership_type), guarantee_amount = VALUES(guarantee_amount), " +
                    "monthly_rent = VALUES(monthly_rent), multi_use_facility = VALUES(multi_use_facility), " +
                    "facility_scale = VALUES(facility_scale), traditional_store_number = VALUES(traditional_store_number), " +
                    "traditional_main_dish = VALUES(traditional_main_dish), homepage = VALUES(homepage);";

    private final DataSource dataSource;

    public RestaurantJdbcBatchWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcBatchItemWriter<Restaurant> restaurantDtoJdbcBatchItemWriter() {
        return new JdbcBatchItemWriterBuilder<Restaurant>()
                .sql(INSERT_SQL)
                .beanMapped()
                .assertUpdates(false)
                .dataSource(dataSource)
                .build();
    }
}
