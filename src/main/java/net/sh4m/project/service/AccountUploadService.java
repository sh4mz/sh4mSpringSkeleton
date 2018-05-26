/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface AccountUploadService {

	/**
	 * @param files
	 * @return
	 */
	List<Map<String, String>> uploadMultipleFiles(MultipartFile[] files);

}
