/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.repository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class AccountUploadRespositoryImpl implements AccountUploadRespository {

	/* (non-Javadoc)
	 * @see com.schawk.tornado.repository.AccountUploadRespository#uploadFiles(java.lang.String, org.springframework.web.multipart.MultipartFile[])
	 */
	@Override
	public List<Map<String, String>> uploadFiles(String xmlDirectory, MultipartFile[] files) {
		List<Map<String, String>> result = null;
		String fileName = null;
		if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
	            try {
	                fileName = files[i].getOriginalFilename();
	                byte[] bytes = files[i].getBytes();
	                BufferedOutputStream buffStream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(xmlDirectory + fileName)));
	                buffStream.write(bytes);
	                buffStream.close();
	            } catch (Exception e) {
	            	Map<String, String> error = new HashMap<String, String>();
	            	error.put(fileName, e.getMessage());
	                result.add(error);
	            }
    		}
        } else {
        	Map<String, String> error = new HashMap<String, String>();
        	error.put("file", "notfound");
            result.add(error);
        }
		return result;
	}

}
