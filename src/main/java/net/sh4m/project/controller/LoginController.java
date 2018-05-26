/**
 * @author ssaleh
 *
 * Created date 20 Jul 2017
 */
package net.sh4m.project.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sh4m.project.RestURLConstant;
import net.sh4m.project.dto.LoginDTO;
import net.sh4m.project.dto.UserDTO;
import net.sh4m.project.dto.UserSessionDTO;
import net.sh4m.project.enums.StatusEnum;
import net.sh4m.project.request.dto.RegisterUserReqDTO;
import net.sh4m.project.response.dto.BasicResponseObjDTO;
import net.sh4m.project.service.LoginService;
import net.sh4m.project.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
			
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
    @Value("#{propVal['session.var']}")
    private String sessVar;
    
	@RequestMapping(value = RestURLConstant.LOGINAUTHENTICATION,
            method = RequestMethod.POST, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },consumes={
                    		MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE
                    })
	@ResponseBody
	public BasicResponseObjDTO loginAuthentication(@RequestBody LoginDTO loginDto, HttpSession httpSession){
		httpSession.removeAttribute(sessVar);
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		Map<String,String> errmsg = loginService.userAuth(loginDto);
		if (errmsg == null){
			UserDTO userDto = userService.getByUserId(loginDto.getUserId());
			
			UserSessionDTO userSessionDto = new UserSessionDTO();
			userSessionDto.setUserid(userDto.getId());
			userSessionDto.setUserName(userDto.getUser_ldap_id());
			userSessionDto.setRoleId(userDto.getFk_user_group_id());
			
			userSessionDto.setManagerId(userDto.getUser_manager_id());
			userSessionDto.setManager(userDto.isUser_ismanager());
			userSessionDto.setEmail(userDto.getUser_email());
			userSessionDto.setLocationId(userDto.getFk_location_id());
			userSessionDto.setUserCell(userDto.getUser_cell());
			if (userDto.getUser_manager_id() != null && userDto.getUser_manager_id() > 0) {
				UserDTO managerDto = userService.getById(userDto.getUser_manager_id());
				userSessionDto.setManagerEmail(managerDto.getUser_email());
			} else {
				userSessionDto.setManagerEmail(null);
			}
			
			
			
			if (httpSession.getAttribute(sessVar) == null){
				logger.debug("Session not found.. init new session..");
				httpSession.setAttribute(sessVar, userSessionDto);
				resp.setDetailsObj(userSessionDto);
				resp.setStatus(StatusEnum.success);
				resp.setCode("0");
			} 
		} else {
			resp.setStatus(StatusEnum.error);
			resp.setCode("1");
			resp.setDetailsObj(errmsg);
		}
		return resp;
	}
	
	@RequestMapping(value = RestURLConstant.LOGINSESSION_CHECK,
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public BasicResponseObjDTO sessionCheck(HttpSession httpSession)
    {
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		if (httpSession.getAttribute(sessVar) == null){
			resp.setStatus(StatusEnum.error);
			resp.setCode("1");
		} else {
			UserSessionDTO userSessionDto = (UserSessionDTO) httpSession.getAttribute(sessVar);
			resp.setStatus(StatusEnum.success);
			resp.setCode("0");
			resp.setDetailsObj(userSessionDto);
		}
		
		return resp ;
    }
	
	@RequestMapping(value = RestURLConstant.LOGINSESSION_DESTROY,
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public BasicResponseObjDTO sessionDestroy(HttpSession httpSession)
    {
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		httpSession.removeAttribute(sessVar);
		
		resp.setStatus(StatusEnum.success);
		resp.setCode("0");
		
		return resp ;
    }
	
	@RequestMapping(value = RestURLConstant.LOGIN_SIGNUP_INFO,
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public BasicResponseObjDTO signUpInfo()
    {
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		resp.setDetailsObj(loginService.signUpInfo());
		
		return resp ;
    }
	
	
	@RequestMapping(value = RestURLConstant.LOGIN_REGISTER_USER,
            method = RequestMethod.POST, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },consumes={
                    		MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE
                    })
	@ResponseBody
	public BasicResponseObjDTO registerUser(@RequestBody RegisterUserReqDTO userRegDto){
		
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		
		Map<String,String> obj = loginService.registerUser(userRegDto);
		if(obj != null){
			resp.setStatus(StatusEnum.error);
			resp.setCode("1");
			resp.setDetailsObj(obj);
		}
		return resp;
	}
	@RequestMapping(value = RestURLConstant.LOGIN_ASSIGN_GROUP,
            method = RequestMethod.POST, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },consumes={
                    		MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE
                    })
	@ResponseBody
	public BasicResponseObjDTO userGroupAssign(@RequestBody RegisterUserReqDTO userRegDto){
		
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		
		Map<String,String> obj = loginService.userGroupAssign(userRegDto);
		//resp.setDetailsObj(obj);
		if(obj != null){
			resp.setStatus(StatusEnum.error);
			resp.setCode("1");
			resp.setDetailsObj(obj);
		}
		return resp;
	}
}
