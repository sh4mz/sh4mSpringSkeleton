/**
 * @author ssaleh
 *
 * Created date 13 Jul 2017
 */
package net.sh4m.project.dto;

import net.sh4m.genericjdbc.dto.AbstractDTO;

public class UserDTO extends AbstractDTO<Long> {

	private static final long serialVersionUID = 5324944181365863576L;
	
	private String user_ldap_id;
	private Long fk_location_id;
	private String user_cell;
	private Long fk_user_group_id;
	private Long user_manager_id;
	private String user_email;
	private String user_status;
	private boolean user_ismanager;
	
	private String managerEmail;
	private String locationName;
	
	public String getUser_ldap_id() {
		return user_ldap_id;
	}
	public void setUser_ldap_id(String user_ldap_id) {
		this.user_ldap_id = user_ldap_id;
	}
	public Long getFk_location_id() {
		return fk_location_id;
	}
	public void setFk_location_id(Long fk_location_id) {
		this.fk_location_id = fk_location_id;
	}
	public String getUser_cell() {
		return user_cell;
	}
	public void setUser_cell(String user_cell) {
		this.user_cell = user_cell;
	}
	public Long getFk_user_group_id() {
		return fk_user_group_id;
	}
	public void setFk_user_group_id(Long fk_user_group_id) {
		this.fk_user_group_id = fk_user_group_id;
	}
	public Long getUser_manager_id() {
		return user_manager_id;
	}
	public void setUser_manager_id(Long user_manager_id) {
		this.user_manager_id = user_manager_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public boolean isUser_ismanager() {
		return user_ismanager;
	}
	public void setUser_ismanager(boolean user_ismanager) {
		this.user_ismanager = user_ismanager;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	/**
	 * @return the managerEmail
	 */
	public String getManagerEmail() {
		return managerEmail;
	}
	/**
	 * @param managerEmail the managerEmail to set
	 */
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	
	
	

}
