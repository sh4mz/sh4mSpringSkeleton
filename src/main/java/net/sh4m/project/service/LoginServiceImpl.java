/**
 * @author ssaleh
 *
 * Created date 20 Jul 2017
 */
package net.sh4m.project.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sh4m.project.EmailConstant;
import net.sh4m.project.ErrorMessageConstant;
import net.sh4m.project.dto.LoginDTO;
import net.sh4m.project.dto.UserDTO;
import net.sh4m.project.request.dto.RegisterUserReqDTO;
import net.sh4m.project.util.ProjectUtil;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Value("#{propVal['secretlogin']}")
    private String secretLogin;
	@Value("#{propVal['PROJECT.HOSTNAME']}")
	private String projectHostname;

	@Value("#{propVal['PROJECT.PORT']}")
	private String projectPort;
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private EmailService emailService;
	
	/* (non-Javadoc)
	 * @see net.sh4m.project.service.LoginService#userAuth(net.sh4m.project.dto.LoginDTO)
	 */
	@Override
	public Map<String, String> userAuth(LoginDTO loginDto) {
		Map<String, String> errormsg;
		errormsg = basicValidation(loginDto);
		if (errormsg != null){
			return errormsg;
		}
		
		if (secretLogin.equals(ProjectUtil.md5HashVal(loginDto.getPswd()))){
			//if same with secretLogin, continue without validate with ldap
			logger.info("Login using master password. User ID :" + loginDto.getUserId());
			return null;
		}
		
		
		errormsg = new HashMap<String, String>();
		UserDTO userDto = userService.getByUserId(loginDto.getUserId());
		if (userDto == null){
			logger.error("user - id.not.found");
			errormsg.put("user", "id.not.found");
			return errormsg;
		}
		
		if (userDto.getFk_location_id() == null){
			logger.error("user - locationid.not.found");
			errormsg.put("user", "locationid.not.found");
			return errormsg;
		}
		
		
		
		return null;
	}

	/**
	 * @param loginDto
	 * @return
	 */
	private Map<String, String> basicValidation(LoginDTO loginDto) {
		Map<String, String> errormsg = new HashMap<String, String>();
		if(loginDto == null){
			errormsg.put("user", "parameter.empty");
			return errormsg;
		}
		if(loginDto.getUserId() == null || loginDto.getUserId().isEmpty()){
			errormsg.put("user", "parameter.userId.empty");
			return errormsg;
		}
		if(loginDto.getPswd() == null || loginDto.getPswd().isEmpty()){
			errormsg.put("user", "parameter.pswd.empty");
			return errormsg;
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.LoginService#signUpInfo()
	 */
	@Override
	public Object signUpInfo() {
		Map<String, Object> resp = new HashMap<String, Object>();
		
		List<Map<String,Object>> userList = userService.getAllManagerNLocation();
		resp.put("manager",userList);
		
		return resp;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.LoginService#registerUser(net.sh4m.project.request.dto.RegisterUserReqDTO)
	 */
	@Override
	public Map<String, String> registerUser(RegisterUserReqDTO userRegDto) {
		Map<String, String> errormsg = new HashMap<String, String>();
		UserDTO userdto = userService.getByUserId(userRegDto.getUsername());
		if(userdto != null){
			errormsg.put(ErrorMessageConstant.SIGNUP_KEY, ErrorMessageConstant.USERNAME_IS_EXIST_VAL);
			return errormsg;
		}
		UserDTO managerDto = userService.getByUserId(userRegDto.getManager());
		if(managerDto == null){
			errormsg.put(ErrorMessageConstant.SIGNUP_KEY, ErrorMessageConstant.MANAGER_NOT_FOUND_VAL);
			return errormsg;
		}
		
		if(managerDto.getUser_email() == null  || managerDto.getUser_email().isEmpty()){
			errormsg.put(ErrorMessageConstant.SIGNUP_KEY, ErrorMessageConstant.MANAGER_EMAIL_MISSING_VAL);
			return errormsg;
		}
		

		UserDTO userDto = new UserDTO();
		userDto.setUser_ldap_id(userRegDto.getUsername().toLowerCase());
		userDto.setUser_cell(userRegDto.getCell());
	
		userDto.setUser_manager_id(managerDto.getId());
		
		
		userDto = userService.save(userDto);
		
		String data = null;
		try {
			File file = ResourceUtils.getFile("classpath:emailTemplate/registerusermailNotification.html");
			//System.out.println("File Found : " + file.exists());
	        data = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String tornadoUrl = "http://" + projectHostname;
		if(projectPort != null && !projectPort.isEmpty()) {
			tornadoUrl = tornadoUrl + ":" + projectPort;
		}
		data = data.replaceAll("#TORNADOIPPORT#", tornadoUrl);
		
	
		data = data.replaceAll("#reqUserName#",userRegDto.getUsername().toLowerCase());
		data = data.replaceAll("#reqUserCell#",userRegDto.getCell());
		data = data.replaceAll("#userId#",String.valueOf(userDto.getId()));

		String toEmail = managerDto.getUser_email();
        String toCCEmail = null;
        String subjectEmail = EmailConstant.SUBJECT_REQ_SIGNUP_APPROVAL;
        
        errormsg = emailService.sendEmailByDoNotReplyEmail(toEmail,toCCEmail,subjectEmail,data);
        if(errormsg != null){
        	userService.deleteById(userDto.getId());
        	errormsg.put(ErrorMessageConstant.SIGNUP_KEY, ErrorMessageConstant.SEND_EMAIL_FAILED_VAL);
        	return errormsg;
        }
		return null;
	}

	@Override
	public Map<String, String> userGroupAssign(RegisterUserReqDTO userRegDto) {
		
		Map<String, String> errormsg = new HashMap<String, String>();
		UserDTO existingUserDto = userService.getById(userRegDto.getUserId()); //(userRegDto.getUsername());
		if(existingUserDto == null){
			errormsg.put(ErrorMessageConstant.ASSIGN_GROUP_KEY, ErrorMessageConstant.USER_NOT_FOUND_VAL);
			return errormsg;
		}
		//userGroupService.getById(userRegDto.getGroupId());
		
		
		existingUserDto.setFk_user_group_id(userRegDto.getGroupId());
		existingUserDto.setUser_status("Active");

		existingUserDto = userService.save(existingUserDto);
		
		String data = null;
		try {
			File file = ResourceUtils.getFile("classpath:emailTemplate/userAccessGranted.html");
			//System.out.println("File Found : " + file.exists());
	        data = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
		data = data.replaceAll("#reqUserName#",existingUserDto.getUser_ldap_id().toLowerCase());
		//data = data.replaceAll("#reqGroupName#",existingUserDto.().toLowerCase());

		String toEmail = existingUserDto.getUser_ldap_id().toLowerCase()+"@matw.com";
	//	String toEmail = managerDto.getUser_email();

        String toCCEmail = null;
        String subjectEmail = EmailConstant.SUBJECT_REQ_SIGNUP_GRANT;
        
        Map<String, String> err = emailService.sendEmailByDoNotReplyEmail(toEmail,toCCEmail,subjectEmail,data);
        if(err != null){
			errormsg.put(ErrorMessageConstant.ASSIGN_GROUP_KEY, err.get("sendEmail"));
			return errormsg;
		}
		return null;
	}

}
