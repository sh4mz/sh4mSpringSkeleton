/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sh4m.project.repository.AccountUploadRespository;

@Service
public class AccountUploadServiceImpl implements AccountUploadService {

	@Autowired
	private AccountUploadRespository accountUploadRepository;
	
	
    @Value("#{propVal['xml.directory']}")
    private String xmlDirectory;
    
	/* (non-Javadoc)
	 * @see net.sh4m.project.service.AccountUploadService#uploadMultipleFiles(org.springframework.web.multipart.MultipartFile[])
	 */
	@Override
	public List<Map<String, String>> uploadMultipleFiles(MultipartFile[] files) {
		List<Map<String, String>> result = accountUploadRepository.uploadFiles(xmlDirectory, files);
		return result;
	}

}
