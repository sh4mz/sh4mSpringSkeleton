/**
 * @author ssaleh
 *
 * Created date 20 Jul 2017
 */
package net.sh4m.project.dto;

public class LoginDTO {
	private String userId;
	private String pswd;
	private String domain;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}
