/**
 * @author ssaleh
 *
 * Created date 13 Jul 2017
 */
package net.sh4m.project.service;

import java.util.List;
import java.util.Map;

import net.sh4m.project.dto.UserDTO;

public interface UserService {

	/**
	 * @return
	 */
	List<UserDTO> getAll();

	/**
	 * Get User by User ID
	 * @param userId
	 * @return
	 */
	UserDTO getByUserId(String userId);

	UserDTO getById(Long id);

	/**
	 * @return
	 */
	List<Map<String,Object>> getAllManagerNLocation();

	/**
	 * @param userDto
	 * @return
	 */
	UserDTO save(UserDTO userDto);

	/**
	 * @param id
	 */
	void deleteById(Long id);

	/**
	 * @param userId
	 * @return
	 */
	UserDTO getInfoById(long userId);


}
