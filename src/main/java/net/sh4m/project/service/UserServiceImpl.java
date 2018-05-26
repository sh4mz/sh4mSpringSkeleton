/**
 * @author ssaleh
 *
 * Created date 13 Jul 2017
 */
package net.sh4m.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sh4m.project.UserConstant;
import net.sh4m.project.dto.UserDTO;
import net.sh4m.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	
	/* (non-Javadoc)
	 * @see net.sh4m.project.service.UserService#getAll()
	 */
	@Override
	public List<UserDTO> getAll() {
		List<UserDTO> lists = new ArrayList<UserDTO>();
		Iterable<UserDTO> res = userRepo.findAll();
		res.forEach(lists::add);
		return lists;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.UserService#getByUserId(java.lang.String)
	 */
	@Override
	public UserDTO getByUserId(String userId) {
		UserDTO userDto = userRepo.findOneByProperty(new String[] {UserConstant.USER_LDAP_ID}, new String[] {userId});
		return userDto;
	}

	@Override
	public UserDTO getById(Long id) {
		return userRepo.findOne(id);
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.UserService#getAllManagerNLocation()
	 */
	@Override
	public List<Map<String,Object>> getAllManagerNLocation() {
		List<Map<String,Object>> list = userRepo.getAllManagerNLocation();
		return list;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.UserService#save(net.sh4m.project.dto.UserDTO)
	 */
	@Override
	public UserDTO save(UserDTO userDto) {
		
		return userRepo.save(userDto);
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.UserService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		userRepo.delete(id);
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.UserService#getInfoById(long)
	 */
	@Override
	public UserDTO getInfoById(long userId) {
		UserDTO userDto = userRepo.getUserAndManagerInfoByUserId(userId);
		
		return userDto;
	}

}
