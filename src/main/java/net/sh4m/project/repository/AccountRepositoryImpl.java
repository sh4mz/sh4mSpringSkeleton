/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.repository;

import org.springframework.stereotype.Repository;

import net.sh4m.genericjdbc.repository.GenericJdbcRepositoryImpl;
import net.sh4m.project.AccountConstant;
import net.sh4m.project.dto.AccountDTO;

@Repository
public class AccountRepositoryImpl extends GenericJdbcRepositoryImpl<AccountDTO, Long> implements AccountRepository {

	public AccountRepositoryImpl()
    {
        super(ROW_MAPPER, ROW_UNMAPPER, AccountConstant.DB_ACCOUNT, AccountConstant.ACCOUNT_ID);
    }
	
	

}
