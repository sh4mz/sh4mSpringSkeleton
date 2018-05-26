/**
 * @author ssaleh
 *
 * Created date 18 Jul 2017
 */
package net.sh4m.project.response.dto;

import net.sh4m.project.enums.StatusEnum;

public class BasicResponseDTO {

	private StatusEnum status;
	private String code;
	private String message;
	
	public BasicResponseDTO(){
		this.status = StatusEnum.success;
		this.code = "0";
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
