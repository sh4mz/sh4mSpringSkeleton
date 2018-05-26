/**
 * @author ssaleh
 *
 * Created date May 26, 2017
 */
package net.sh4m.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sh4m.project.RestURLConstant;
import net.sh4m.project.dto.AccountDTO;

@Controller
public class AccountAuthController {

	private static final Logger logger = Logger.getLogger(AccountAuthController.class);
			
    @Value("#{propVal['session.var']}")
    private String sessVar;
    
	@RequestMapping(value = RestURLConstant.ACCOUNTAUTHCONTROLLER_ACCOUNTLOGIN,
            method = RequestMethod.POST, 
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },
            consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, String> initAccountLogin(@RequestBody AccountDTO accdto, HttpSession httpSession)
    {
		Map<String, String> error = new HashMap<String, String>();
		if (httpSession.getAttribute(sessVar) == null){
			logger.debug("Session not found.. init new session..");
			httpSession.setAttribute(sessVar, accdto);
			error.put("Success", "new session");
		} else {
			logger.debug("Session found..");
			error.put("Success", "old session");
		}
		
		return error ;
    }
	
	@RequestMapping(value = RestURLConstant.ACCOUNTAUTHCONTROLLER_ACCOUNTLOGOUT,
            method = RequestMethod.POST, 
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },
            consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, String> addAccount(HttpSession httpSession)
    {
		Map<String, String> error = new HashMap<String, String>();
		if (httpSession.getAttribute(sessVar) == null){
			logger.debug("Session not found..");
			error.put("Success", "no session found");
		} else {
			logger.debug("Session found.. clear session..");
			httpSession.removeAttribute(sessVar);
			error.put("Success", "cleared session..");
		}
		return error ;
    }
	
	@RequestMapping(value = "/account/check",
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Map<String, String> getAccount(HttpSession httpSession)
    {
		Map<String, String> error = new HashMap<String, String>();
		if (httpSession.getAttribute(sessVar) == null){
			error.put("session", "valid");
		} else {
			error.put("session", "invalid");
		}
		
		return error ;
    }
}
