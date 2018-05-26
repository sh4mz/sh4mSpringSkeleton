/**
 * @author ssaleh
 *
 * Created date 5 Jan 2018
 */
package net.sh4m.project.request.dto;

import java.io.Serializable;

public class RegisterUserReqDTO implements Serializable{

	private static final long serialVersionUID = 8011654147186641422L;

	private String cell;
	private String domain;
	private String location;
	private String manager;
	private String username;
	private String managerEmail;
	private Long userId;
	private Long groupId;
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getManager() {
		return manager;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	
	
}
