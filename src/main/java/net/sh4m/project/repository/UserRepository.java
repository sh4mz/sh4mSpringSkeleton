/**
 * @author ssaleh
 *
 * Created date 13 Jul 2017
 */
package net.sh4m.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import net.sh4m.genericjdbc.AppConstants;
import net.sh4m.genericjdbc.jdbc.RowUnmapper;
import net.sh4m.genericjdbc.repository.GenericJdbcRepository;
import net.sh4m.project.UserConstant;
import net.sh4m.project.dto.UserDTO;

public interface UserRepository extends GenericJdbcRepository<UserDTO, Long> {

	RowMapper<UserDTO> ROW_MAPPER = new RowMapper<UserDTO>(){

		@Override
		public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDTO dto = new UserDTO();
			dto.setId(rs.getLong(UserConstant.USER_ID));
			dto.setUser_ldap_id(rs.getString(UserConstant.USER_LDAP_ID));
			dto.setFk_location_id(rs.getLong(UserConstant.FK_LOCATION_ID));
			dto.setUser_cell(rs.getString(UserConstant.USER_CELL));
			dto.setFk_user_group_id(rs.getLong(UserConstant.FK_USER_GROUP_ID));
			dto.setUser_manager_id(rs.getLong(UserConstant.USER_MANAGER_ID));
			dto.setUser_email(rs.getString(UserConstant.USER_EMAIL));
			dto.setUser_status(rs.getString(UserConstant.USER_STATUS));
			dto.setUser_ismanager(rs.getBoolean(UserConstant.USER_ISMANAGER));
			dto.setCreatedDate(rs.getTimestamp(AppConstants.CREATED_DATE));
			dto.setLastModifiedDate(rs.getTimestamp(AppConstants.LAST_MODIFIED_DATE));
			dto.setDeleted(rs.getBoolean(AppConstants.DELETED));
			
			return dto;
		}
		
	};
	
	RowUnmapper<UserDTO> ROW_UNMAPPER = new RowUnmapper<UserDTO>(){

		@Override
		public Map<String, Object> mapColumns(UserDTO t) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put(UserConstant.USER_ID,t.getId());
			mapping.put(UserConstant.USER_LDAP_ID, t.getUser_ldap_id());
			mapping.put(UserConstant.FK_LOCATION_ID, t.getFk_location_id());
			mapping.put(UserConstant.USER_CELL, t.getUser_cell());
			mapping.put(UserConstant.FK_USER_GROUP_ID,t.getFk_user_group_id());
			mapping.put(UserConstant.USER_MANAGER_ID, t.getUser_manager_id());
			mapping.put(UserConstant.USER_EMAIL,t.getUser_email());
			mapping.put(UserConstant.USER_STATUS, t.getUser_status());
			mapping.put(UserConstant.USER_ISMANAGER, t.isUser_ismanager());
			mapping.put(AppConstants.CREATED_DATE, t.getCreatedDate());
			mapping.put(AppConstants.LAST_MODIFIED_DATE, t.getLastModifiedDate());
			mapping.put(AppConstants.DELETED, t.getDeleted());
			return mapping;
		}
		
	};

	/**
	 * @return
	 */
	List<Map<String,Object>> getAllManagerNLocation();

	/**
	 * @param userId
	 * @return
	 */
	UserDTO getUserAndManagerInfoByUserId(long userId);
	
}
