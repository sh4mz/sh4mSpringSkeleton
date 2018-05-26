/**
 * @author ssaleh
 *
 * Created date 23 Feb 2018
 */
package net.sh4m.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sh4m.project.RestURLConstant;
import net.sh4m.project.dto.UserDTO;
import net.sh4m.project.response.dto.BasicResponseObjDTO;
import net.sh4m.project.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = RestURLConstant.USER_INFO_BY_ID,
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public BasicResponseObjDTO getALLGroup(@RequestParam(value = "userId",required = true) long userId){
		
		BasicResponseObjDTO resp = new BasicResponseObjDTO();
		
		UserDTO userDto = userService.getInfoById(userId);
		resp.setDetailsObj(userDto);
		return resp;
	}
}
