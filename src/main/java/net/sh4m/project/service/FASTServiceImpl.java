/**
 * @author ssaleh
 *
 * Created date 26 Jan 2018
 */
package net.sh4m.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.sh4m.project.ErrorMessageConstant;
import net.sh4m.project.FASTConstant;
import net.sh4m.project.util.TimeWatch;

@Service
public class FASTServiceImpl implements FASTService {
	private static final Logger logger = Logger.getLogger(FASTServiceImpl.class);
	
	@Value("#{propVal['FAST.API']}")
	private String fastApiUrl;
	
	@Value("#{propVal['FAST.APIKEY.VALUE']}")
	private String fastApiKeyValue;
	
	@Value("#{propVal['FAST.CONVERTNAME.VALUE']}")
	private String fastConvertNameValue;
	
	@Value("#{propVal['FAST.SKIPDELETEDITEM.VALUE']}")
	private String fastSkipDeletedItemValue;
	
	
	
	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FASTService#getWorkReqIDByServiceOrder(java.lang.String)
	 */
	@Override
	public Map<String,Object> getWorkReqIDByServiceOrder(String serviceOrderNo) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(ErrorMessageConstant.CODE_KEY, 0);
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(fastApiUrl);
		builder.queryParam(FASTConstant.API_KEY,fastApiKeyValue);
		builder.queryParam(FASTConstant.SERVICEORDERNUM,serviceOrderNo);
		builder.queryParam(FASTConstant.CONVERTNAMEVALUE,fastConvertNameValue);
		builder.queryParam(FASTConstant.SKIPDELETEDITEM,fastSkipDeletedItemValue);
		logger.info("Fast API triggered. URL : " + builder.toUriString());
		
		InputStream responseInputStream = null;
		try {
			TimeWatch watch = TimeWatch.start();
			ResponseEntity<Resource> resp = restTemplate.getForEntity(builder.toUriString(), Resource.class);
			responseInputStream = resp.getBody().getInputStream();
		    logger.info("Elapsed Time " + watch.toMinuteSeconds());
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put(ErrorMessageConstant.CODE_KEY, 1);
			result.put(ErrorMessageConstant.ERROR_KEY, ErrorMessageConstant.FAST_API_NOT_AVAILABLE_VAL);
			return result;
		}
		
		if (responseInputStream == null){
			result.put(ErrorMessageConstant.CODE_KEY, 1);
			result.put(ErrorMessageConstant.ERROR_KEY, ErrorMessageConstant.FAST_API_NOT_AVAILABLE_VAL);
			return result;
		}
		
		
		
		return result;
	}

	/* (non-Javadoc)
	 * @see net.sh4m.project.service.FASTService#getBarcodeAttributesSL(java.lang.String)
	 */
	@Override
	public Map<String, Object> getBarcodeAttributesSL(String serviceOrder) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(ErrorMessageConstant.CODE_KEY, 0);
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(fastApiUrl);
		builder.queryParam(FASTConstant.API_KEY,fastApiKeyValue);
		builder.queryParam(FASTConstant.SERVICEORDERNUM,serviceOrder);
		builder.queryParam(FASTConstant.CONVERTNAMEVALUE,fastConvertNameValue);
		builder.queryParam(FASTConstant.SKIPDELETEDITEM,fastSkipDeletedItemValue);
		logger.info("Fast API triggered. URL : " + builder.toUriString());
		
		InputStream responseInputStream = null;
		try {
			TimeWatch watch = TimeWatch.start();
			ResponseEntity<Resource> resp = restTemplate.getForEntity(builder.toUriString(), Resource.class);
			responseInputStream = resp.getBody().getInputStream();
		    logger.info("Elapsed Time " + watch.toMinuteSeconds());
		}
		catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result.put(ErrorMessageConstant.CODE_KEY, 1);
			result.put(ErrorMessageConstant.ERROR_KEY, ErrorMessageConstant.FAST_API_NOT_AVAILABLE_VAL);
			return result;
		}
		
		if (responseInputStream == null){
			result.put(ErrorMessageConstant.CODE_KEY, 1);
			result.put(ErrorMessageConstant.ERROR_KEY, ErrorMessageConstant.FAST_API_NOT_AVAILABLE_VAL);
			return result;
		}
		
		return result;
	}
}
