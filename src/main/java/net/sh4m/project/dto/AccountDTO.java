/**
 * @author ssaleh
 *
 * Created date May 25, 2017
 */
package net.sh4m.project.dto;


import net.sh4m.genericjdbc.dto.AbstractDTO;

public class AccountDTO extends AbstractDTO<Long> {

	private static final long serialVersionUID = -1696164067505275249L;
	
	private String acc_name;
	private String acc_email;
	private String acc_password;

	/**
	 * @return the acc_name
	 */
	public String getAcc_name() {
		return acc_name;
	}
	/**
	 * @param acc_name the acc_name to set
	 */
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	/**
	 * @return the acc_email
	 */
	public String getAcc_email() {
		return acc_email;
	}
	/**
	 * @param acc_email the acc_email to set
	 */
	public void setAcc_email(String acc_email) {
		this.acc_email = acc_email;
	}
	/**
	 * @return the acc_password
	 */
	public String getAcc_password() {
		return acc_password;
	}
	/**
	 * @param acc_password the acc_password to set
	 */
	public void setAcc_password(String acc_password) {
		this.acc_password = acc_password;
	}
	
	public AccountDTO(){
		
	}
}
