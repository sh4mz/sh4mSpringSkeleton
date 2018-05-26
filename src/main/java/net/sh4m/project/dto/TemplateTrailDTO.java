/**
 * @author ssaleh
 *
 * Created date 14 Jul 2017
 */
package net.sh4m.project.dto;

import net.sh4m.genericjdbc.dto.AbstractDTO;

public class TemplateTrailDTO extends AbstractDTO<Long> {

	private static final long serialVersionUID = -6469712691272218874L;

	private Long fk_project_id;
	private String template_trail_proj_name;
	private Long fk_workorder_id;
	private String template_trail_workorder_no;
	private String template_trail_type;
	private Long template_trail_created_by;
	private String template_trail_created_by_id;
	
	public Long getFk_project_id() {
		return fk_project_id;
	}
	public void setFk_project_id(Long fk_project_id) {
		this.fk_project_id = fk_project_id;
	}
	public String getTemplate_trail_proj_name() {
		return template_trail_proj_name;
	}
	public void setTemplate_trail_proj_name(String template_trail_proj_name) {
		this.template_trail_proj_name = template_trail_proj_name;
	}
	public Long getFk_workorder_id() {
		return fk_workorder_id;
	}
	public void setFk_workorder_id(Long fk_workorder_id) {
		this.fk_workorder_id = fk_workorder_id;
	}
	public String getTemplate_trail_workorder_no() {
		return template_trail_workorder_no;
	}
	public void setTemplate_trail_workorder_no(String template_trail_workorder_no) {
		this.template_trail_workorder_no = template_trail_workorder_no;
	}
	public String getTemplate_trail_type() {
		return template_trail_type;
	}
	public void setTemplate_trail_type(String template_trail_type) {
		this.template_trail_type = template_trail_type;
	}
	public Long getTemplate_trail_created_by() {
		return template_trail_created_by;
	}
	public void setTemplate_trail_created_by(Long template_trail_created_by) {
		this.template_trail_created_by = template_trail_created_by;
	}
	public String getTemplate_trail_created_by_id() {
		return template_trail_created_by_id;
	}
	public void setTemplate_trail_created_by_id(String template_trail_created_by_id) {
		this.template_trail_created_by_id = template_trail_created_by_id;
	}
	
	
}
