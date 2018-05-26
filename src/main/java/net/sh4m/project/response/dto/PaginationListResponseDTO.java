/**
 * @author ssaleh
 *
 * Created date 25 Jul 2017
 */
package net.sh4m.project.response.dto;



public class PaginationListResponseDTO extends BasicResponseDTO {

	private long totalRecords;
	private Object listObj;
	

	
	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Object getListObj() {
		return listObj;
	}

	public void setListObj(Object listObj) {
		this.listObj = listObj;
	}

	
	
	
}
