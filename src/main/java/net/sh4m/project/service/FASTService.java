/**
 * @author ssaleh
 *
 * Created date 26 Jan 2018
 */
package net.sh4m.project.service;

import java.util.Map;

public interface FASTService {

	/**
	 * @param serviceOrder
	 * @return
	 */
	Map<String,Object> getWorkReqIDByServiceOrder(String serviceOrder);

	/**
	 * @param serviceOrder
	 * @return
	 */
	Map<String, Object> getBarcodeAttributesSL(String serviceOrder);

}
