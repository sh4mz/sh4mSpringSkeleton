/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.service;

import java.util.Map;

import net.sh4m.project.dto.AccountDTO;

public interface AccountService {

	/**
	 * 
	 * @param account
	 * @return Map
	 */
	Map<String, String> registerUser(AccountDTO account);
	
	/**
	 * Get acc by ID
	 * 
	 * @param id
	 * @return AccountDTO
	 */
	AccountDTO getAccountById(Long id);

	/**
	 * @param accDto
	 * @return
	 */
	Map<String, String> updateInfo(AccountDTO accDto);

	/**
	 * @param accDto
	 * @return
	 */
	Map<String, String> deleteAccount(AccountDTO accDto);


	
	
}
