/**
 * @author ssaleh
 *
 * Created date 27 Jul 2017
 */
package net.sh4m.project.subscriber.events;

import net.sh4m.project.dto.TemplateTrailDTO;

public class TemplateTrailEvent {
	
	private TemplateTrailDTO tmplTrailDto;

	public TemplateTrailEvent(TemplateTrailDTO tmplTrailDto) {
		this.tmplTrailDto = tmplTrailDto;
	}

	public TemplateTrailDTO getTmplTrailDto() {
		return tmplTrailDto;
	}

	public void setTmplTrailDto(TemplateTrailDTO tmplTrailDto) {
		this.tmplTrailDto = tmplTrailDto;
	}
	
	

}
