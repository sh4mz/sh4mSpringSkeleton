
package net.sh4m.genericjdbc.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Sort.Direction;

@XmlRootElement(name = "paginationProperties")
public class PaginationProperties implements Serializable {
	private static final long serialVersionUID = -3094320361677817862L;

	private long totalRecords;
	private int pageNumber;
	private int pageSize;
	private int offset;
	private String sortProperty;
	private Direction sortOrder;

	/**
	 * @return the totalRecords
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the sortProperty
	 */
	public String getSortProperty() {
		return sortProperty;
	}

	/**
	 * @param sortProperty
	 *            the sortProperty to set
	 */
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

	/**
	 * @return the sortOrder
	 */
	public Direction getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(Direction sortOrder) {
		this.sortOrder = sortOrder;
	}
}
