INSERT INTO project_user_group (user_group_name) VALUES ('Admin');
INSERT INTO project_user_group (user_group_name) VALUES ('Artist');
INSERT INTO project_user_group (user_group_name) VALUES ('CSR');
INSERT INTO project_user_group (user_group_name) VALUES ('Leader');
INSERT INTO project_user_group (user_group_name) VALUES ('TmplEng');
INSERT INTO project_user_group (user_group_name) VALUES ('SuperAdmin');
INSERT INTO project_user_group (user_group_name) VALUES ('QA');
INSERT INTO project_user_group (user_group_name) VALUES ('Others');

INSERT INTO project_location (location_name,location_domain) VALUES ('ANTWERP','EMEA');
INSERT INTO project_location (location_name,location_domain) VALUES ('CHENNAI','ASIA');
INSERT INTO project_location (location_name,location_domain) VALUES ('CINCINNATI','AMER');
INSERT INTO project_location (location_name,location_domain) VALUES ('MANCHESTER','EMEA');
INSERT INTO project_location (location_name,location_domain) VALUES ('MEXICO','AMER');
INSERT INTO project_location (location_name,location_domain) VALUES ('NEWCASTLE','EMEA');
INSERT INTO project_location (location_name,location_domain) VALUES ('PENANG','ASIA');
INSERT INTO project_location (location_name,location_domain) VALUES ('SHANGHAI','ASIA');
INSERT INTO project_location (location_name,location_domain) VALUES ('SHENZHEN','ASIA');
INSERT INTO project_location (location_name,location_domain) VALUES ('SINGAPORE','ASIA');


INSERT INTO project_source_input (source_input_name) VALUES ('xml');
INSERT INTO project_source_input (source_input_name) VALUES ('excel');
INSERT INTO project_source_input (source_input_name) VALUES ('word');


INSERT INTO project_output_type (output_type_name) VALUES ('Variable-XML');
INSERT INTO project_output_type (output_type_name) VALUES ('IPC-XML');
INSERT INTO project_output_type (output_type_name) VALUES ('ID-XML');
INSERT INTO project_output_type (output_type_name) VALUES ('GS1-XML');
INSERT INTO project_output_type (output_type_name) VALUES ('Merck-XML');
INSERT INTO project_output_type (output_type_name) VALUES ('WAVE-XML');

INSERT INTO project_project_status (project_status_name) VALUES ('In-Active');
INSERT INTO project_project_status (project_status_name) VALUES ('TRFP');
INSERT INTO project_project_status (project_status_name) VALUES ('WFTP');
INSERT INTO project_project_status (project_status_name) VALUES ('WFTT');
INSERT INTO project_project_status (project_status_name) VALUES ('WPAM');
INSERT INTO project_project_status (project_status_name) VALUES ('RFTC');
INSERT INTO project_project_status (project_status_name) VALUES ('WFPA');

INSERT INTO project_user (user_ldap_id,fk_location_id,user_cell,fk_user_group_id,user_manager_id,user_email,user_status,user_ismanager) VALUES ('AdamStewart',(select location_id from project_location where location_name = 'MANCHESTER'),'1',(select user_group_id from project_user_group where user_group_name = 'Admin'),null,'','Active',1);
INSERT INTO project_user (user_ldap_id,fk_location_id,user_cell,fk_user_group_id,user_manager_id,user_email,user_status,user_ismanager) VALUES ('Aditya',(select location_id from project_location where location_name = 'CHENNAI'),'2',(select user_group_id from project_user_group where user_group_name = 'Admin'),null,'','Active',1);
INSERT INTO project_user (user_ldap_id,fk_location_id,user_cell,fk_user_group_id,user_manager_id,user_email,user_status,user_ismanager) VALUES ('Ancona',(select location_id from project_location where location_name = 'SHANGHAI'),'Innovation Team',(select user_group_id from project_user_group where user_group_name = 'Admin'),null,'','Active',1);
