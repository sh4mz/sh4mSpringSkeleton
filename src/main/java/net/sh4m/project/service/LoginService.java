/**
 * @author ssaleh
 *
 * Created date 20 Jul 2017
 */
package net.sh4m.project.service;

import java.util.Map;

import net.sh4m.project.dto.LoginDTO;
import net.sh4m.project.dto.UserSessionDTO;
import net.sh4m.project.request.dto.RegisterUserReqDTO;

public interface LoginService {

	/**
	 * Auth user
	 * @param loginDto
	 * @return Map<String, String>
	 */
	Map<String, String> userAuth(LoginDTO loginDto);

	/**
	 * @return
	 */
	Object signUpInfo();

	/**
	 * @param userRegDto
	 * @param userSessionDto 
	 * @return
	 */
	Map<String, String> registerUser(RegisterUserReqDTO userRegDto);
	
	Map<String, String> userGroupAssign(RegisterUserReqDTO userRegDto);


}
