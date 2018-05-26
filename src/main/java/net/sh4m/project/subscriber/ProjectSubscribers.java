/**
 * @author ssaleh
 *
 * Created date 27 Jul 2017
 */
package net.sh4m.project.subscriber;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

import net.sh4m.project.service.FileService;
import net.sh4m.project.subscriber.events.TemplateTrailEvent;

@Component
public class ProjectSubscribers {
	
	private static final Logger logger = Logger.getLogger(ProjectSubscribers.class);
	
	
	
	@Autowired
	private FileService fileService;
	
	
	/**
	 * Save activity template
	 * @param eventObj
	 */
	@Subscribe
	public void saveTemplTrail(TemplateTrailEvent eventObj){
		try{
			 logger.info("saveTemplTrail");
			
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		
	}
	
	
}
