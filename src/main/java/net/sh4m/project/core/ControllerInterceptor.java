/**
 * @author ssaleh
 *
 * Created date Jun 16, 2017
 */
package net.sh4m.project.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sh4m.project.dto.UserSessionDTO;

public class ControllerInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(ControllerInterceptor.class);
	private static final String REST = "/rest/";
	@Value("#{propVal['session.var']}")
    private String sessVar;
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object arg2) throws Exception {
		//logger.info("Inside preHandle");
		//System.out.println(request.getRequestURI());
		HttpSession session = request.getSession(false);
		
		String path = request.getRequestURI().substring(request.getContextPath().length());

		
		if(path.contains(REST + "pub/")){
			return true;
	    }
	       	
        if(session == null || session.getAttribute(sessVar) == null) {
//        	logger.info("Status False. " + "Path : " + path );
        	resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;             
        } else {
        	//UserSessionDTO userSessionDto = (UserSessionDTO) session.getAttribute(sessVar);
//        	logger.info("Status true. " + "Path : " + path );
//        	logger.info(userSessionDto.getUserName());
            return true;
        }
	}

}
