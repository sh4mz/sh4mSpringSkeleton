/**
 * @author ssaleh
 *
 * Created date 8 Aug 2017
 */
package net.sh4m.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	public static String toJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			
			return null;
		}
		return jsonInString;
	}
	
	
}
