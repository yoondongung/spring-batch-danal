
CREATE TABLE BATCH_JOB_INSTANCE  (
                                     JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
                                     VERSION BIGINT ,
                                     JOB_NAME VARCHAR(100) NOT NULL,
                                     JOB_KEY VARCHAR(32) NOT NULL,
                                     constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
                                      JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                      VERSION BIGINT  ,
                                      JOB_INSTANCE_ID BIGINT NOT NULL,
                                      CREATE_TIME DATETIME(6) NOT NULL,
                                      START_TIME DATETIME(6) DEFAULT NULL ,
                                      END_TIME DATETIME(6) DEFAULT NULL ,
                                      STATUS VARCHAR(10) ,
                                      EXIT_CODE VARCHAR(2500) ,
                                      EXIT_MESSAGE VARCHAR(2500) ,
                                      LAST_UPDATED DATETIME(6),
                                      constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
                                          references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
                                             JOB_EXECUTION_ID BIGINT NOT NULL ,
                                             PARAMETER_NAME VARCHAR(100) NOT NULL ,
                                             PARAMETER_TYPE VARCHAR(100) NOT NULL ,
                                             PARAMETER_VALUE VARCHAR(2500) ,
                                             IDENTIFYING CHAR(1) NOT NULL ,
                                             constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
                                                 references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
                                       STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                       VERSION BIGINT NOT NULL,
                                       STEP_NAME VARCHAR(100) NOT NULL,
                                       JOB_EXECUTION_ID BIGINT NOT NULL,
                                       CREATE_TIME DATETIME(6) NOT NULL,
                                       START_TIME DATETIME(6) DEFAULT NULL ,
                                       END_TIME DATETIME(6) DEFAULT NULL ,
                                       STATUS VARCHAR(10) ,
                                       COMMIT_COUNT BIGINT ,
                                       READ_COUNT BIGINT ,
                                       FILTER_COUNT BIGINT ,
                                       WRITE_COUNT BIGINT ,
                                       READ_SKIP_COUNT BIGINT ,
                                       WRITE_SKIP_COUNT BIGINT ,
                                       PROCESS_SKIP_COUNT BIGINT ,
                                       ROLLBACK_COUNT BIGINT ,
                                       EXIT_CODE VARCHAR(2500) ,
                                       EXIT_MESSAGE VARCHAR(2500) ,
                                       LAST_UPDATED DATETIME(6),
                                       constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
                                           references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
                                               STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                               SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                               SERIALIZED_CONTEXT TEXT ,
                                               constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
                                                   references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
                                              JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                              SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                              SERIALIZED_CONTEXT TEXT ,
                                              constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
                                                  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
                                          ID BIGINT NOT NULL,
                                          UNIQUE_KEY CHAR(1) NOT NULL,
                                          constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
                                         ID BIGINT NOT NULL,
                                         UNIQUE_KEY CHAR(1) NOT NULL,
                                         constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
                               ID BIGINT NOT NULL,
                               UNIQUE_KEY CHAR(1) NOT NULL,
                               constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);

CREATE TABLE IF NOT EXISTS `restaurant` (
                              `closure_date` date DEFAULT NULL,
                              `factory_office_workers` int DEFAULT NULL,
                              `factory_production_workers` int DEFAULT NULL,
                              `factory_sales_workers` int DEFAULT NULL,
                              `female_workers` int DEFAULT NULL,
                              `head_office_workers` int DEFAULT NULL,
                              `male_workers` int DEFAULT NULL,
                              `permit_cancel_date` date DEFAULT NULL,
                              `permit_date` date DEFAULT NULL,
                              `reopen_date` date DEFAULT NULL,
                              `suspension_end_date` date DEFAULT NULL,
                              `suspension_start_date` date DEFAULT NULL,
                              `total_workers` int DEFAULT NULL,
                              `x_coordinate` double DEFAULT NULL,
                              `y_coordinate` double DEFAULT NULL,
                              `data_update_date` datetime(6) DEFAULT NULL,
                              `id` bigint DEFAULT NULL,
                              `last_modified` datetime(6) DEFAULT NULL,
                              `address` varchar(255) DEFAULT NULL,
                              `building_ownership_type` varchar(255) DEFAULT NULL,
                              `business_name` varchar(255) DEFAULT NULL,
                              `business_status_code` varchar(255) DEFAULT NULL,
                              `business_status_name` varchar(255) DEFAULT NULL,
                              `business_type_name` varchar(255) DEFAULT NULL,
                              `data_update_type` varchar(255) DEFAULT NULL,
                              `detailed_business_status_code` varchar(255) DEFAULT NULL,
                              `detailed_business_status_name` varchar(255) DEFAULT NULL,
                              `facility_scale` varchar(255) DEFAULT NULL,
                              `guarantee_amount` varchar(255) DEFAULT NULL,
                              `homepage` varchar(255) DEFAULT NULL,
                              `hygiene_type` varchar(255) DEFAULT NULL,
                              `location_area` varchar(255) DEFAULT NULL,
                              `management_code` varchar(255) NOT NULL,
                              `monthly_rent` varchar(255) DEFAULT NULL,
                              `multi_use_facility` varchar(255) DEFAULT NULL,
                              `open_autonomous_district_code` varchar(255) DEFAULT NULL,
                              `open_service_id` varchar(255) DEFAULT NULL,
                              `open_service_name` varchar(255) DEFAULT NULL,
                              `phone` varchar(255) DEFAULT NULL,
                              `postal_code` varchar(255) DEFAULT NULL,
                              `rating_type` varchar(255) DEFAULT NULL,
                              `road_address` varchar(255) DEFAULT NULL,
                              `road_postal_code` varchar(255) DEFAULT NULL,
                              `surrounding_area_type` varchar(255) DEFAULT NULL,
                              `traditional_main_dish` varchar(255) DEFAULT NULL,
                              `traditional_store_number` varchar(255) DEFAULT NULL,
                              `water_supply_type` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`management_code`)
) ENGINE=InnoDB ;
