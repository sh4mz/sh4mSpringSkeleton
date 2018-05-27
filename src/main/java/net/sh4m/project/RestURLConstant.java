/**
 * @author ssaleh
 *
 * Created date Jun 22, 2017
 */
package net.sh4m.project;

public class RestURLConstant {

	public static final String PUBLICRESTCONTEXT = "/pub";
	
	public static final String PRIVATERESTCONTEXT = "/sec";
	
	public static final String ACCOUNTCONTROLLER_ACCOUNT = PRIVATERESTCONTEXT + "/account";

	public static final String ACCOUNTAUTHCONTROLLER_ACCOUNTLOGIN = PUBLICRESTCONTEXT + "/account/login";
	
	public static final String ACCOUNTAUTHCONTROLLER_ACCOUNTLOGOUT = PRIVATERESTCONTEXT + "/account/logout";
	
	public static final String AVCCOUNTUPLOADCONTROLLER_ACCOUNTUPLOAD = PRIVATERESTCONTEXT + "/account/upload";
	
	public static final String LDAPCONTROLLER_LDAP = PUBLICRESTCONTEXT + "/ldap";

	public static final String LOCATION_CONTROLLER = PUBLICRESTCONTEXT + "/location";

	public static final String LOGINAUTHENTICATION = PUBLICRESTCONTEXT + "/login/authentication";

	public static final String PROJECTCREATE_SAVE = PRIVATERESTCONTEXT + "/projectCreate";

	public static final String PROJECTCREATE_GET = PRIVATERESTCONTEXT + "/projectCreateGet";

	public static final String LOGINSESSION_CHECK = PUBLICRESTCONTEXT + "/login/check";

	public static final String LOGINSESSION_DESTROY = PUBLICRESTCONTEXT + "/login/logout";

	public static final String DASHBOARD_STATISTIC = PRIVATERESTCONTEXT + "/dashboard/statistic";

	public static final String LOGIN_SIGNUP_INFO = PUBLICRESTCONTEXT + "/login/signupinfo";

	public static final String LOGIN_REGISTER_USER = PUBLICRESTCONTEXT + "/login/registeruser";

	public static final String LOGIN_ASSIGN_GROUP = PUBLICRESTCONTEXT + "/login/userGroupAssign";

	public static final String USERLASTLONGIN_CONTROLLER =  PRIVATERESTCONTEXT + "/userlastlogin";

	public static final String USERLASTLOGIN_SEARCHBYUSERID_CONTROLLER =  PRIVATERESTCONTEXT + "/userlastloginbyid";

	public static final String USERLASTLOGIN_ADD_CONTROLLER = PRIVATERESTCONTEXT + "/userlastlogin/add";

	public static final String USER_INFO_BY_ID = PRIVATERESTCONTEXT + "/user/infoById";

}
