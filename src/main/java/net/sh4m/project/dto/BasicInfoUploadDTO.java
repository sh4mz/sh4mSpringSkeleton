/**
 * @author ssaleh
 *
 * Created date 8 Aug 2017
 */
package net.sh4m.project.dto;

import org.springframework.web.multipart.MultipartFile;

public class BasicInfoUploadDTO  {

	private Long projId;
	private MultipartFile uploadFile;
	
	public Long getProjId() {
		return projId;
	}
	public void setProjId(Long projId) {
		this.projId = projId;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}
