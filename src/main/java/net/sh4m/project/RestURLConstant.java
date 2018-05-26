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
	
	public static final String PROJECTCREATE_CONTROLLER = PRIVATERESTCONTEXT + "/ProjectCreateController";

	public static final String LOCATION_CONTROLLER = PUBLICRESTCONTEXT + "/location";

	public static final String TEMPLATETRAIL_INSERT_CONTROLLER = PRIVATERESTCONTEXT + "/templatetrailsave";

	public static final String TEMPLATETRAIL_GETLATEST_CONTROLLER = PRIVATERESTCONTEXT + "/gettemplatetrail";

	public static final String LOGINAUTHENTICATION = PUBLICRESTCONTEXT + "/login/authentication";

	public static final String PROJECTCREATE_SAVE = PRIVATERESTCONTEXT + "/projectCreate";

	public static final String PROJECTCREATE_GET = PRIVATERESTCONTEXT + "/projectCreateGet";

	public static final String LOGINSESSION_CHECK = PUBLICRESTCONTEXT + "/login/check";

	public static final String LOGINSESSION_DESTROY = PUBLICRESTCONTEXT + "/login/logout";

	public static final String PROJECT_EDITAPPROVAL_CONTROLLER = PRIVATERESTCONTEXT + "/project/wfpalist";

	public static final String PROJECT_EDITAPPROVE_EDITPROJECT_CONTROLLER = PRIVATERESTCONTEXT + "/project/update";

	public static final String PROJECT_CREATEPNG_SAVE = PRIVATERESTCONTEXT + "/projectCreatePng";

	public static final String PROJECT_CREATEPNG_GET = PRIVATERESTCONTEXT + "/projectCreatePngGet";

	public static final String PROJECT_EDITAPPROVALSEARCH_CONTROLLER = PRIVATERESTCONTEXT + "/projectSearch";

	public static final String PROJECT_EDITAPPROVE_APPROVEPROJECT_CONTROLLER = PRIVATERESTCONTEXT + "/project/approve";

	public static final String TEMPLATE_SEARCH_CONTROLLER = PRIVATERESTCONTEXT + "/template/search";

	public static final String MD5_ENCRYPT = PUBLICRESTCONTEXT + "/md5/encrypt";

	public static final String PROJECTCREATE_GET_PROJECT_BY_ID = PRIVATERESTCONTEXT + "/getProjectDetails";

	public static final String TEMPLATE_BASICINFO_UPLOADDOC = PRIVATERESTCONTEXT + "/template/uploadDoc";

	public static final String TEMPLATE_BASICINFO_GETCONFIG = PRIVATERESTCONTEXT + "/template/config";

	public static final String TEMPLATE_BASICINFO_UPDATEMAPPING = PRIVATERESTCONTEXT + "/template/updateconfig";

	public static final String TEMPLATE_BASICINFO_REMOVEFILE = PRIVATERESTCONTEXT + "/template/removefile";

	public static final String TEMPLATE_BASICINFO_PREVIEW_MAPPING = PRIVATERESTCONTEXT + "/template/previewmapping";

	public static final String TEMPLATE_TEMPLATECREATION_GETELEMENT = PRIVATERESTCONTEXT + "/template/element";

	public static final String PROJECT_CREATEPNG_UPDATE = PRIVATERESTCONTEXT + "/projectpng/update";

	public static final String TEMPLATE_TEMPLATECREATION_PREVIEWCELL = PRIVATERESTCONTEXT + "/template/cellpreview";

	public static final String TEMPLATE_TEMPLATECREATION_GET_GS1_ELEMENT = PRIVATERESTCONTEXT + "/template/gs1element";
	
	public static final String TEMPLATE_GETPROJECT_BY_NAMESTATUS = PRIVATERESTCONTEXT + "/template/getprojectbynamestatus";

	public static final String UAT_WFTT_LIST = PRIVATERESTCONTEXT + "/uat/wfttlist";

	public static final String TEMPLATE_TEMPLATECREATION_UPDATE_MAPPING = PRIVATERESTCONTEXT + "/template/updatemapping";

	public static final String TEMPLATE_TEMPLATECREATION_GET_MAPPING = PRIVATERESTCONTEXT + "/template/getmapping";

	public static final String TEMPLATE_TEMPLATEDUPLICATE_DUPLICATE_PROJ = PRIVATERESTCONTEXT + "/template/duplicate";

	public static final String UAT_CHECK_WORKORDERNO_TEST_WIP = PRIVATERESTCONTEXT + "/uat/checkwipworkorderno";
	
	public static final String UAT_GET_WORKORDERNO_INFO = PRIVATERESTCONTEXT + "/uat/getwipworkordernoinfo";
	
	public static final String UAT_GENERATE_XML = PRIVATERESTCONTEXT + "/uat/generatexml";

	public static final String PROJECT_GETINFO = PRIVATERESTCONTEXT + "/project/list";

	public static final String DASHBOARD_STATISTIC = PRIVATERESTCONTEXT + "/dashboard/statistic";

	public static final String PROJECTINFO_GET_ALL_PROJ_INFO = PRIVATERESTCONTEXT + "/project/getallinfo";

	public static final String PROJECT_COUNTRY_CODE_INSERT = PRIVATERESTCONTEXT + "/project/addcountrycode";

	public static final String PROJECT_COUNTRY_CODE_GET_ALL = PRIVATERESTCONTEXT + "/project/getcountrycode";

	public static final String PROJECT_COUNTRY_CODE_UPDATE = PRIVATERESTCONTEXT + "/project/updatecountrycode";

	public static final String PROJECT_COUNTRY_CODE_DELETE = PRIVATERESTCONTEXT + "/project/deletecountrycode";

	public static final String ELEMENT_GET_ALL_LIST = PRIVATERESTCONTEXT + "/element/search";

	public static final String ELEMENT_ADD_ELEMENT = PRIVATERESTCONTEXT + "/element/add";

	public static final String TEMPLATE_TEMPLATEMAPPING_APPROVE = PRIVATERESTCONTEXT + "/template/approve";

	public static final String WORKFLOW_GET_CONFIG = PRIVATERESTCONTEXT + "/workflow/get";
	
	public static final String WORKFLOW_SAVE_CONFIG = PRIVATERESTCONTEXT + "/workflow/save";

	public static final String UIMENU_ADD_UICODE = PRIVATERESTCONTEXT + "/uimenu/add";

	public static final String UIMENU_GET_ALLLIST = PRIVATERESTCONTEXT + "/uimenu/search";

	public static final String UAT_GET_IMAGE_BY_DIRECTORY = PRIVATERESTCONTEXT + "/uat/getImageByDirectory";
	
	public static final String UAT_COMPARE_XML_INFO_BY_PROJID_WO = PRIVATERESTCONTEXT + "/comparexml/infobyprojid";

	public static final String MASTER_LIB_GET_INFO_BY_PROJID = PRIVATERESTCONTEXT + "/masterlib/getMasterLibInfoByProjId";

	public static final String MASTER_LIB_UPLOAD_FILE = PRIVATERESTCONTEXT + "/masterlib/uploadFile";

	public static final String MASTER_LIB_FOLDER_SYNC = PRIVATERESTCONTEXT + "/masterlib/folderSync";

	public static final String SETTINGS_ROLE_GET_GROUP = PRIVATERESTCONTEXT + "/role/getgroup";

	public static final String SETTINGS_ROLE_ADD_MENU = PRIVATERESTCONTEXT + "/role/addmenu";

	public static final String SETTINGS_ROLE_GET_MENU_BY_GROUP = PRIVATERESTCONTEXT + "/role/getMenu";

	public static final String SETTINGS_ROLE_REMOVE_GROUP_MENU = PRIVATERESTCONTEXT + "/role/removemenu";

	public static final String PROJECTCREATE_DELETE_BY_ID = PRIVATERESTCONTEXT + "/project/delete";

	public static final String INTERPRETER_GET_CONFIG_BY_PROJNAME = PRIVATERESTCONTEXT + "/interpreter/getconfigbyprojname";

	public static final String INTERPRETER_GET_ALLCONFIG_BY_PROJNAME = PRIVATERESTCONTEXT + "/interpreter/getallconfigbyprojname";

	public static final String UAT_COMPARE_XML_LINK_NONLINK_BY_PROJID_WO = PRIVATERESTCONTEXT + "/comparexml/linkeleminfobyprojid";

	public static final String UAT_COMPARE_XML_COMPAREXML_GENERATE_PDF_BY_PROJID_WO = PRIVATERESTCONTEXT + "/comparexml/pdfbyprojid";

	public static final String UAT_APPROVAL_SEND_TO_HOD = PRIVATERESTCONTEXT + "/uat/sendapprovalnotification";

	public static final String PROJECTCREATE_APPROVE_PROJECT_FOR_LIVE = PRIVATERESTCONTEXT + "/project/approveprojectforlive";

	public static final String UAT_RE_EDIT_ELEMENT = PRIVATERESTCONTEXT + "/uat/reeditelement";

	public static final String UAT_SAVE_RE_EDIT_ELEMENT = PRIVATERESTCONTEXT + "/uat/savereeditelement";
	
	public static final String UAT_SHOW_WIP_FILE = PRIVATERESTCONTEXT + "/uat/showwipfiles";
	
	public static final String UAT_SAVE_IMAGE_SELECTION = PRIVATERESTCONTEXT + "/uat/saveimageselection";
	
	public static final String UAT_SAVE_ALL_SELECTION = PRIVATERESTCONTEXT + "/uat/saveallselection";

	public static final String UAT_GET_IMAGE_SELECTION = PRIVATERESTCONTEXT + "/uat/getimageselection";
	
	public static final String UAT_GET_ALL_SELECTION = PRIVATERESTCONTEXT + "/uat/getallselection";

	public static final String PROJECT_LIVE_SEARCH = PRIVATERESTCONTEXT + "/project/livelist";
	
	public static final String PROJECT_LIVE_IMAGE_SELECTION = PRIVATERESTCONTEXT + "/live/saveimageselection";
	
	public static final String PROJECT_LIVE_ALL_SELECTION = PRIVATERESTCONTEXT + "/live/saveallselection";
	
	public static final String PROJECT_LIVE_GET_IMAGE_SELECTION = PRIVATERESTCONTEXT + "/live/getimageselection";
	
	public static final String PROJECT_LIVE_GET_ALL_SELECTION = PRIVATERESTCONTEXT + "/live/getallselection";

	public static final String PROJECT_LIVE_CHECK_WORKORDERNO_WIP = PRIVATERESTCONTEXT + "/live/checkwipworkorderno";

	public static final String PROJECT_LIVE_GET_WORKORDERNO_INFO = PRIVATERESTCONTEXT + "/live/getwipworkordernoinfo";
	
	public static final String PROJECT_LIVE_GENERATE_XML = PRIVATERESTCONTEXT + "/live/generatexml";
	
	public static final String PROJECT_LIVE_RE_EDIT_ELEMENT = PRIVATERESTCONTEXT + "/live/reeditelement";
	
	public static final String PROJECT_LIVE_SAVE_RE_EDIT_ELEMENT = PRIVATERESTCONTEXT + "/live/savereeditelement";

	public static final String PROJECT_LIVE_SHOW_WIP_FILE = PRIVATERESTCONTEXT + "/live/showwipfiles";
	
	public static final String PROJECT_LIVE_COMPARE_XML_INFO_BY_PROJID_WO = PRIVATERESTCONTEXT + "/livecomparexml/infobyprojid";
	
	public static final String PROJECT_LIVE_COMPARE_XML_COMPAREXML_GENERATE_PDF_BY_PROJID_WO = PRIVATERESTCONTEXT + "/livecomparexml/pdfbyprojid";

	public static final String PROJECT_LIVE_GET_IMAGE_BY_DIRECTORY = PRIVATERESTCONTEXT + "/live/getImageByDirectory";
	
	public static final String RULE_ENGINE_GET = PRIVATERESTCONTEXT + "/ruleengine/get";

	public static final String RULE_ENGINE_SAVE = PRIVATERESTCONTEXT + "/ruleengine/save";
	
	public static final String RULE_ENGINE_DUPLICATE = PRIVATERESTCONTEXT + "/ruleengine/duplicate";
	
	public static final String RULE_ENGINE_DUPLICATE_ALL = PRIVATERESTCONTEXT + "/ruleengine/duplicateAll";

	public static final String RULE_ENGINE_SEARCH_TEST = PRIVATERESTCONTEXT + "/ruleengine/searchtest";

	public static final String RULE_ENGINE_SEARCH_PROJECT_OR_BRAND = PRIVATERESTCONTEXT + "/ruleengine/searchprojectorbrand";

	public static final String RULE_ENGINE_PROJ_AVAILABLE = PRIVATERESTCONTEXT + "/ruleengine/projavailable";
	
	public static final String RULE_ENGINE_GET_RULELIST = PRIVATERESTCONTEXT + "/ruleengine/rulelist";

	public static final String PROJECT_LIVE_WO_STATUS_TO_BACKUP  = PRIVATERESTCONTEXT + "/live/woupdatetobackup";

	public static final String PROJECT_LIVE_WO_SEARCH = PRIVATERESTCONTEXT + "/live/projectwolivelist";

	public static final String LOGIN_SIGNUP_INFO = PUBLICRESTCONTEXT + "/login/signupinfo";

	public static final String LOGIN_REGISTER_USER = PUBLICRESTCONTEXT + "/login/registeruser";

	public static final String REPORTS_WO_REPORT = PRIVATERESTCONTEXT + "/reports/getworeport";

	public static final String REPORTS_WO_REPORT_EXCEL_DOWNLOAD = PRIVATERESTCONTEXT + "/reports/getworeportdownload";

	public static final String REPORTS_COMPARE_XML_REPORT = PRIVATERESTCONTEXT + "/reports/getcomparexmlreport";

	public static final String REPORTS_COMPARE_XML_REPORT_EXCEL_DOWNLOAD = PRIVATERESTCONTEXT + "/reports/getcomparexmlreportdownload";

	public static final String REPORTS_UTILIZATION_REPORT = PRIVATERESTCONTEXT + "/reports/getutilizationreport";

	public static final String REPORTS_UTILIZATION_REPORT_EXCEL_DOWNLOAD = PRIVATERESTCONTEXT + "/reports/getutilizationreportdownload";

	public static final String REPORTS_AUTO_ARTWORK_REPORT = PRIVATERESTCONTEXT + "/reports/getautoartworkreport";

	public static final String REPORTS_AUTO_ARTWORK_REPORT_DOWNLOAD = PRIVATERESTCONTEXT + "/reports/getautoartworkreportdownload";

	public static final String LOGIN_ASSIGN_GROUP = PUBLICRESTCONTEXT + "/login/userGroupAssign";

	public static final String UAT_COMPARE_XML_COMPAREXML_GET_IMAGE_BY_PROJNAME = PRIVATERESTCONTEXT + "/comparexml/getimagebyprojectidnimgname";
	
	public static final String UAT_COMPARE_XML_COMPAREXML_GETIMAGEBY_PROJNAMEBASE = PRIVATERESTCONTEXT + "/comparexml/getimagebyprojectidnimgnamebase";

	public static final String LIVE_COMPARE_XML_COMPAREXML_GET_IMAGE_BY_PROJNAME = PRIVATERESTCONTEXT + "/livecomparexml/getimagebyprojectidnimgname";
	
	public static final String LIVE_COMPARE_XML_COMPAREXML_GETIMAGEBY_PROJNAMEBASE = PRIVATERESTCONTEXT + "/livecomparexml/getimagebyprojectidnimgnamebase";

	public static final String SETTINGS_GET_LOCATION = PRIVATERESTCONTEXT + "/project/getlocation";

	public static final String USERLASTLONGIN_CONTROLLER =  PRIVATERESTCONTEXT + "/userlastlogin";

	public static final String USERLASTLOGIN_SEARCHBYUSERID_CONTROLLER =  PRIVATERESTCONTEXT + "/userlastloginbyid";

	public static final String USERLASTLOGIN_ADD_CONTROLLER = PRIVATERESTCONTEXT + "/userlastlogin/add";
	
    public static final String FONT_LIST_GET_FONTLIST = PRIVATERESTCONTEXT + "/fontlist/getFontList";
    
    public static final String FONT_LIST_ADD_FONT = PRIVATERESTCONTEXT + "/fontlist/addFont";

	public static final String USER_INFO_BY_ID = PRIVATERESTCONTEXT + "/user/infoById";

	public static final String PDFREPORT_CREATE_CONTROLLER = PRIVATERESTCONTEXT + "/comparexml/pdfreport";

	public static final String STRINGDIFF_CONTROLLER =  PRIVATERESTCONTEXT + "/comparexml/stringdiff";

	public static final String CLIENT_GET_ALL = PRIVATERESTCONTEXT + "/client/getAll";

	public static final String CLIENT_ELEMENT_GET_INFO_BY_PROJID = PRIVATERESTCONTEXT + "/clientelement/getbyprojid";

	public static final String CLIENT_ELEMENT_SAVE_INFO_BY_PROJID = PRIVATERESTCONTEXT + "/clientelement/savebyprojid";

	public static final String CLIENT_ELEMENT_SAVE_SINGLE_INFO_BY_PROJID = PRIVATERESTCONTEXT + "/clientelement/savesinglebyprojid";

	public static final String AAW_CONTROLLER =  PRIVATERESTCONTEXT + "/aaw/listdata";

	public static final String AAW_SEARCHBY_MQID_CONTROLLER = PRIVATERESTCONTEXT + "/aaw/getbymqid";

	public static final String AAW_UPDATE_FINALREPORT = PUBLICRESTCONTEXT + "/aaw/finalreport";

	public static final String AAW_COMPAREXMLBY_MQID = PUBLICRESTCONTEXT + "/aaw/xmlcompare";

	public static final String RR_SEARCHBY_MQID_CONTROLLER = PRIVATERESTCONTEXT + "/aaw/getbymqidfromrr";

	public static final String RR_MACHINE_CONTROLLER = PRIVATERESTCONTEXT + "/aaw/hb";

	public static final String GET_AUTOARTWORK_AI_FILE = PUBLICRESTCONTEXT + "/aaw/getaifile";

	public static final String GET_AUTOARTWORK_COMPAREXML_FILE = PUBLICRESTCONTEXT + "/aaw/getxmlcomparefile";
	
	public static final String UAT_COMPARE_XML_SAVE_REMARK = PRIVATERESTCONTEXT + "/comparexml/saveremark";
	
	public static final String UAT_COMPARE_XML_GET_REMARK = PRIVATERESTCONTEXT + "/comparexml/getremark";
	
	public static final String LIVE_COMPARE_XML_SAVE_REMARK = PRIVATERESTCONTEXT + "/livecomparexml/saveremark";
    
    public static final String LIVE_COMPARE_XML_GET_REMARK = PRIVATERESTCONTEXT + "/livecomparexml/getremark";   
}
