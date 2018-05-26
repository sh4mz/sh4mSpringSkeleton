/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sh4m.project.AccountConstant;
import net.sh4m.project.dto.AccountDTO;
import net.sh4m.project.repository.AccountRepository;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountRepository accountRepository;
	
	/* (non-Javadoc)
	 * @see net.sh4m.project.db.service.AccountService#registerUser(net.sh4m.project.db.dto.AccountDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public Map<String, String> registerUser(AccountDTO account) {
		if (account.getAcc_name()==null){
			Map<String, String> result = new HashMap<String, String>();
			result.put("Account", "accountnull");
			return result;
		}
		
		accountRepository.save(account);
		
		//return null if everything is ok
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.db.service.AccountService#getAccountById(java.lang.Long)
	 */
	@Override
	public AccountDTO getAccountById(Long id) {
		
		//AccountDTO accDto = accountRepository.findOne(id);
		
		AccountDTO accDto = accountRepository.findOneByProperty(
				new String[]{ AccountConstant.ACCOUNT_ID},
				new Object[]{ id }, 
				true );
		return accDto;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.AccountService#updateInfo(net.sh4m.project.dto.AccountDTO)
	 */
	@Override
	public Map<String, String> updateInfo(AccountDTO accDto) {
		AccountDTO resAccDto = accountRepository.findOne(accDto.getId());
		if (resAccDto == null){
			Map<String, String> result = new HashMap<String, String>();
			result.put("Account", "accountnotfound");
			return result;
		}
		
		
		resAccDto.setAcc_email(accDto.getAcc_email() != null ? accDto.getAcc_email() : resAccDto.getAcc_email());
		resAccDto.setAcc_password(accDto.getAcc_password() != null ? accDto.getAcc_password() : resAccDto.getAcc_password());
		resAccDto.setAcc_name(accDto.getAcc_name() != null ? accDto.getAcc_name() : resAccDto.getAcc_name());
		accountRepository.save(resAccDto);
		
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.AccountService#deleteAccount(net.sh4m.project.dto.AccountDTO)
	 */
	@Override
	public Map<String, String> deleteAccount(AccountDTO accDto) {
		AccountDTO resAccDto = accountRepository.findOne(accDto.getId());
		if (resAccDto == null){
			Map<String, String> result = new HashMap<String, String>();
			result.put("Account", "accountnotfound");
			return result;
		}
		resAccDto.setDeleted(true);
		accountRepository.save(resAccDto);
		return null;
	}


}
