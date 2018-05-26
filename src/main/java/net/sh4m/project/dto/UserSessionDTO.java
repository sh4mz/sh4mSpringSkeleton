/**
 * @author ssaleh
 *
 * Created date 25 Jul 2017
 */
package net.sh4m.project.dto;

import java.io.Serializable;
import java.util.List;

public class UserSessionDTO implements Serializable {

	private static final long serialVersionUID = -1576519392723506669L;
	
	private Long userid;
	private String userName;
	private String userCell;
	private Long roleId;
	private String roleName;
	private List<String> controlAccessList;
	private Long managerId;
	private boolean isManager;
	private String email;
	private String managerEmail;
	private Long locationId;
	
	public List<String> getControlAccessList() {
		return controlAccessList;
	}
	public void setControlAccessList(List<String> controlAccessList) {
		this.controlAccessList = controlAccessList;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getUserCell() {
		return userCell;
	}
	public void setUserCell(String userCell) {
		this.userCell = userCell;
	}

	
	
	
	
}
