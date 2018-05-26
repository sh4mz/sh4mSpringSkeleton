CREATE  TABLE IF NOT EXISTS `project_user_group` (
  `user_group_id` INT NOT NULL AUTO_INCREMENT ,
  `user_group_name` VARCHAR(45) NOT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`user_group_id`) 
);
  
CREATE  TABLE IF NOT EXISTS `PROJECT_LOCATION` (
  `LOCATION_ID` INT NOT NULL AUTO_INCREMENT ,
  `LOCATION_NAME` VARCHAR(45) NOT NULL ,
  `LOCATION_DOMAIN` VARCHAR(20) NOT NULL ,
  `DELETED` TINYINT NULL DEFAULT 0 ,
  `CREATED_DATE` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `LAST_MODIFIED_DATE` DATETIME NULL ,
  PRIMARY KEY (`LOCATION_ID`)
  );
  
  CREATE  TABLE IF NOT EXISTS `project_client` (
  `client_id` INT NOT NULL AUTO_INCREMENT ,
  `client_name` VARCHAR(45) NOT NULL ,
  `client_sector_name` VARCHAR(45) NOT NULL ,
   `client_default_value` TINYINT NULL DEFAULT 0 ,
    `client_reedit_element` TINYINT NULL DEFAULT 0 ,
  `last_modified_date` DATETIME NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`client_id`)
  );
  
  CREATE  TABLE IF NOT EXISTS `project_client_brand` (
  `client_brand_id` INT NOT NULL AUTO_INCREMENT ,
  `fk_client_id` INT NOT NULL ,
  `client_brand_name` VARCHAR(45) NOT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`client_brand_id`) ,
  CONSTRAINT `client_brand_fk_client_id_client_id`
    FOREIGN KEY (`fk_client_id` )
    REFERENCES `project_client` (`client_id` )
 );
 
 CREATE  TABLE IF NOT EXISTS `project_source_input` (
  `source_input_id` INT NOT NULL AUTO_INCREMENT ,
  `source_input_name` VARCHAR(50) NOT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`source_input_id`) 
 );
 
 CREATE  TABLE IF NOT EXISTS `project_output_type` (
  `output_type_id` INT NOT NULL AUTO_INCREMENT ,
  `output_type_name` VARCHAR(100) NOT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`output_type_id`) 
 );
 
 CREATE  TABLE IF NOT EXISTS `project_project_status` (
  `project_status_id` INT NOT NULL AUTO_INCREMENT ,
  `project_status_name` VARCHAR(45) NOT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`project_status_id`) 
  );
  
  CREATE  TABLE IF NOT EXISTS `project_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `user_ldap_id` VARCHAR(100) NOT NULL ,
  `fk_location_id` INT NOT NULL ,
  `user_cell` VARCHAR(45) NOT NULL ,
  `fk_user_group_id` INT NOT NULL ,
  `user_manager_id` INT NULL ,
  `user_email` VARCHAR(100) NOT NULL ,
  `user_status` VARCHAR(45) NOT NULL ,
  `user_ismanager` TINYINT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`user_id`)
  );
  
  CREATE  TABLE IF NOT EXISTS `project_project` (
  `project_id` INT NOT NULL AUTO_INCREMENT ,
  `project_name` VARCHAR(100) NOT NULL ,
  `project_create_by` INT NOT NULL ,
  `project_approve_by` INT NULL ,
  `project_approve_date` DATETIME NULL DEFAULT NULL ,
  `fk_source_input_id` INT NOT NULL ,
  `project_no_of_input` INT NOT NULL ,
  `project_application_used` VARCHAR(45) NOT NULL ,
  `project_multilanguage` TINYINT NOT NULL ,
  `fk_output_type_id` INT NOT NULL ,
  `project_volume_per_year` INT NOT NULL ,
  `project_time_savings` INT NOT NULL ,
  `project_template_approve_by` INT NULL ,
  `project_template_approve_date` DATETIME NULL ,
  `fk_project_status_id` INT NOT NULL ,
  `fk_location_id` INT NOT NULL ,
  `fk_client_id` INT NOT NULL ,
  `fk_client_brand_id` INT NOT NULL ,
  `project_cell` VARCHAR(4) NOT NULL ,
  `project_wip_path` VARCHAR(200) NOT NULL ,
  `project_wip_path_status` TINYINT NULL,
  `project_palletpattern` TINYINT NULL ,
  `project_logos` TINYINT NULL DEFAULT NULL ,
  `project_masterimages` TINYINT NULL DEFAULT NULL ,
  `project_image_location` VARCHAR(200) NOT NULL ,
  `project_essential` TINYINT NULL ,
  `project_masterdieline` TINYINT NULL ,
  `project_econtent` INT NULL ,
  `project_middleware` TINYINT NULL ,
  `project_autoartwork` TINYINT NULL ,
  `project_hybrid` TINYINT NULL ,
  `project_version` VARCHAR(45) NULL ,
  `project_multitable` TINYINT NULL ,
  `project_intxmltype` VARCHAR(45) NULL ,
  `project_interpreter` TINYINT NULL,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`project_id`) 
 );
 
 CREATE  TABLE IF NOT EXISTS `project_workorder` (
  `workorder_id` INT NOT NULL AUTO_INCREMENT ,
  `fk_project_id` INT NOT NULL ,
  `workorder_no` VARCHAR(45) NOT NULL ,
  `workorder_parent_id` INT NULL ,
  `workorder_multiplejobsfeed` VARCHAR(45) NULL ,
  `workorder_version_no` VARCHAR(10) NULL ,
  `workorder_type` TINYINT NULL ,
  `workorder_status` VARCHAR(45) NULL ,
  `workorder_aaw_status` VARCHAR(45) NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`workorder_id`)
 );
 
 CREATE  TABLE IF NOT EXISTS `project_template_trail` (
  `template_trail_id` INT NOT NULL AUTO_INCREMENT ,
  `fk_project_id` INT NULL ,
  `template_trail_proj_name` VARCHAR(100) NULL ,
  `fk_workorder_id` INT NULL ,
  `template_trail_workorder_no` VARCHAR(45) NULL ,
  `template_trail_type` VARCHAR(100) NULL ,
  `template_trail_created_by` INT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  `template_trail_created_by_id` VARCHAR(100) NULL ,
  PRIMARY KEY (`template_trail_id`) 
 );
 
 CREATE  TABLE IF NOT EXISTS `project_project_png` (
  `proj_png_id` INT NOT NULL AUTO_INCREMENT ,
  `fk_project_id` INT NOT NULL ,
  `proj_png_brand_name` VARCHAR(45) NOT NULL ,
  `proj_png_wip_path` VARCHAR(200) NOT NULL ,
  `proj_png_wip_path_status` TINYINT NULL ,
  `proj_image_location` VARCHAR(200) NOT NULL ,
  `fk_png_cat_id` INT NOT NULL ,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`proj_png_id`) 
);

CREATE  TABLE IF NOT EXISTS `project_png_category` (
  `png_cat_id` INT NOT NULL AUTO_INCREMENT ,
  `png_cat_name` VARCHAR(255) NOT NULL ,
  `png_cat_route` VARCHAR(255) NOT NULL ,
  `png_cat_createdby` INT,
  `deleted` TINYINT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`png_cat_id`) 
);