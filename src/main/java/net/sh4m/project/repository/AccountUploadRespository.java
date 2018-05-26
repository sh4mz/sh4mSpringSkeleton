/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.repository;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface AccountUploadRespository {

	/**
	 * @param xmlDirectory
	 * @param files
	 * @return
	 */
	List<Map<String, String>> uploadFiles(String xmlDirectory, MultipartFile[] files);

}
