/**
 * @author ssaleh
 *
 * Created date 13 Jul 2017
 */
package net.sh4m.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import net.sh4m.genericjdbc.repository.GenericJdbcRepositoryImpl;
import net.sh4m.project.LocationConstant;
import net.sh4m.project.UserConstant;
import net.sh4m.project.dto.UserDTO;


@Repository
public class UserRepositoryImpl extends GenericJdbcRepositoryImpl<UserDTO, Long> implements UserRepository {

	private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);
	
	public UserRepositoryImpl() {
		super(ROW_MAPPER, ROW_UNMAPPER,UserConstant.DB_USER,UserConstant.USER_ID);
	}

	/* (non-Javadoc)
	 * @see com.schawk.tornado.repository.UserRepository#getAllManagerNLocation()
	 */
	@Override
	public List<Map<String, Object>> getAllManagerNLocation() {
		String userPrefix = "user";
		String locPrefic = "location";
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append(userPrefix).append(".").append(UserConstant.USER_LDAP_ID).append(",").append(locPrefic).append(".").append(LocationConstant.LOCATION_NAME);
		sql.append(" FROM ");
		sql.append(getTableWithDbPrefix(UserConstant.DB_USER)).append(" ").append(userPrefix).append(" JOIN ");
		sql.append(getTableWithDbPrefix(LocationConstant.DB_LOCATION)).append(" ").append(locPrefic).append(" ON ");
		sql.append(userPrefix).append(".").append(UserConstant.FK_LOCATION_ID).append(" = ").append(locPrefic).append(".").append(LocationConstant.LOCATION_ID);
		sql.append(" AND ").append(userPrefix).append(".").append(UserConstant.USER_ISMANAGER).append(" = true ");
		
		List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql.toString());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.schawk.tornado.repository.UserRepository#getUserAndManagerInfoByUserId(long)
	 */
	@Override
	public UserDTO getUserAndManagerInfoByUserId(long userId) {
		String userPrefix = "USER";
		String managerPrefix = "MNGR";
		StringBuilder sql = new StringBuilder("SELECT ");
		//column
		sql.append(userPrefix).append(".").append(UserConstant.USER_ID).append(" AS ").append(userPrefix).append(UserConstant.USER_ID).append(",");
		sql.append(userPrefix).append(".").append(UserConstant.USER_LDAP_ID).append(" AS ").append(userPrefix).append(UserConstant.USER_LDAP_ID).append(",");
		sql.append(userPrefix).append(".").append(UserConstant.USER_CELL).append(" AS ").append(userPrefix).append(UserConstant.USER_CELL).append(",");
		sql.append(userPrefix).append(".").append(UserConstant.USER_EMAIL).append(" AS ").append(userPrefix).append(UserConstant.USER_EMAIL).append(",");
		sql.append(userPrefix).append(".").append(UserConstant.FK_LOCATION_ID).append(" AS ").append(userPrefix).append(UserConstant.FK_LOCATION_ID).append(",");
		sql.append(managerPrefix).append(".").append(UserConstant.USER_LDAP_ID).append(" AS ").append(managerPrefix).append(UserConstant.USER_LDAP_ID).append(",");
		sql.append(managerPrefix).append(".").append(UserConstant.USER_EMAIL).append(" AS ").append(managerPrefix).append(UserConstant.USER_EMAIL);
		
		sql.append(" FROM ");
		sql.append(getTableWithDbPrefix(UserConstant.DB_USER)).append(" ").append(userPrefix).append(" JOIN ");
		sql.append(getTableWithDbPrefix(UserConstant.DB_USER)).append(" ").append(managerPrefix).append(" ON ");
		sql.append(userPrefix).append(".").append(UserConstant.USER_ID).append(" = ").append(managerPrefix).append(".").append(UserConstant.USER_MANAGER_ID);
		
		sql.append(" AND ");
		sql.append(userPrefix).append(".").append(UserConstant.USER_ID).append(" = ?");
		
		logger.info(sql.toString());
		
		UserDTO userDto = getJdbcTemplate().query(sql.toString(), new ResultSetExtractor<UserDTO>(){

			@Override
			public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()){
					UserDTO dto = new UserDTO();
					dto.setUser_ldap_id(rs.getString(userPrefix + UserConstant.USER_LDAP_ID));
					dto.setUser_cell(rs.getString(userPrefix + UserConstant.USER_CELL));
					dto.setFk_location_id(rs.getLong(userPrefix + UserConstant.FK_LOCATION_ID));
					dto.setManagerEmail(rs.getString(managerPrefix+UserConstant.USER_EMAIL));
					return dto;
				}
				return null;
			}},userId);
		return userDto;
	}
	
	
}
