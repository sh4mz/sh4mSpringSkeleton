/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sh4m.project.RestURLConstant;
import net.sh4m.project.service.AccountUploadService;

@Controller
public class AccountUploadController {

	@Autowired
	private AccountUploadService accountUploadService;
	
	@RequestMapping(value = RestURLConstant.AVCCOUNTUPLOADCONTROLLER_ACCOUNTUPLOAD,
            method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, String>> accountUploadFile(@RequestParam("file") MultipartFile[] files )
    {
		List<Map<String, String>> result = accountUploadService.uploadMultipleFiles(files);
		return result;
		
    }
}
