/**
 * @author ssaleh
 *
 * Created date 20 Jul 2017
 */
package net.sh4m.project.response.dto;

import net.sh4m.project.enums.StatusEnum;

public class BasicResponseObjDTO {
	private StatusEnum status;
	private String code;
	private Object detailsObj;
	
	public BasicResponseObjDTO(){
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
	public Object getDetailsObj() {
		return detailsObj;
	}
	public void setDetailsObj(Object detailsObj) {
		this.detailsObj = detailsObj;
	}
	
	
}
