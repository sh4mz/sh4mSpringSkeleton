/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import net.sh4m.genericjdbc.AppConstants;
import net.sh4m.genericjdbc.jdbc.RowUnmapper;
import net.sh4m.genericjdbc.repository.GenericJdbcRepository;
import net.sh4m.project.AccountConstant;
import net.sh4m.project.dto.AccountDTO;

public interface AccountRepository extends GenericJdbcRepository<AccountDTO, Long> {
	
	RowMapper<AccountDTO> ROW_MAPPER = new RowMapper<AccountDTO>(){

		@Override
		public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AccountDTO accDto = new AccountDTO();
			accDto.setId(rs.getLong(AccountConstant.ACCOUNT_ID));
			accDto.setAcc_name(rs.getString(AccountConstant.ACCOUNT_NAME));
			accDto.setAcc_email(rs.getString(AccountConstant.ACCOUNT_EMAIL));
			accDto.setAcc_password(rs.getString(AccountConstant.ACCOUNT_PASSWORD));
			accDto.setCreatedDate(rs.getDate(AppConstants.CREATED_DATE));
			accDto.setLastModifiedDate(rs.getDate(AppConstants.LAST_MODIFIED_DATE));
			accDto.setDeleted(rs.getBoolean(AppConstants.DELETED));
			return accDto;
		}
		
	};
	
	RowUnmapper<AccountDTO> ROW_UNMAPPER = new RowUnmapper<AccountDTO>(){

		@Override
		public Map<String, Object> mapColumns(AccountDTO accDTO) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			
			mapping.put(AccountConstant.ACCOUNT_ID, accDTO.getId());
			mapping.put(AccountConstant.ACCOUNT_EMAIL, accDTO.getAcc_email());
			mapping.put(AccountConstant.ACCOUNT_NAME, accDTO.getAcc_name());
			mapping.put(AccountConstant.ACCOUNT_PASSWORD, accDTO.getAcc_password());
			mapping.put(AppConstants.CREATED_DATE, accDTO.getCreatedDate());
			mapping.put(AppConstants.LAST_MODIFIED_DATE, accDTO.getLastModifiedDate());
			mapping.put(AppConstants.DELETED, accDTO.getDeleted());

			return mapping;
		}
		
		
	};

}
