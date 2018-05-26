/**
 * @author ssaleh
 *
 * Created date 27 Jul 2017
 */
package net.sh4m.project.subscriber;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;

public class AsyncEventBusSubscriberProcessor implements BeanPostProcessor {

	private static final Logger logger = Logger.getLogger(AsyncEventBusSubscriberProcessor.class);
	
	@Autowired
	private AsyncEventBus asyncEventBus;
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object beanObject, String beanObjectName) throws BeansException {
		return beanObject;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object beanObject, String beanObjectName) throws BeansException {
		Method[] methods = beanObject.getClass().getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			
			for (Annotation methodAnnotation : annotations) {
				if (methodAnnotation.annotationType().equals(Subscribe.class)) {
					asyncEventBus.register(beanObject);
					logger.info("Bean {" + beanObjectName + "} method {"+method.getName()+"} has been subscribed to the AyncEventBus.");
				}
			}
		}
 
		return beanObject;
	}

}
