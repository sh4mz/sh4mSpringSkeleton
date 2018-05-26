/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sh4m.project.RestURLConstant;
import net.sh4m.project.dto.AccountDTO;
import net.sh4m.project.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = RestURLConstant.ACCOUNTCONTROLLER_ACCOUNT,
            method = RequestMethod.GET, produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public AccountDTO getAccount(@RequestParam(value="id") Long id )
    {
		AccountDTO accDto = accountService.getAccountById(id);
		
		return accDto ;
    }
	
	@RequestMapping(value = RestURLConstant.ACCOUNTCONTROLLER_ACCOUNT,
            method = RequestMethod.POST, 
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },
            consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, String> addAccount(@RequestBody AccountDTO accdto )
    {
		Map<String, String> result = accountService.registerUser(accdto);
		
		return result ;
    }
	
	@RequestMapping(value = RestURLConstant.ACCOUNTCONTROLLER_ACCOUNT,
            method = RequestMethod.PUT, 
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },
            consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, String> updateAccount(@RequestBody AccountDTO accDto )
    {
		
		Map<String, String> result = accountService.updateInfo(accDto);
		
		return result ;
    }
	
	@RequestMapping(value = RestURLConstant.ACCOUNTCONTROLLER_ACCOUNT,
            method = RequestMethod.DELETE, 
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE },
            consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, String> deleteAccount(@RequestBody AccountDTO accDto )
    {
		
		Map<String, String> result = accountService.deleteAccount(accDto);
		
		return result ;
    }
}
