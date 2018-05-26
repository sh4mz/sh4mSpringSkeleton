package net.sh4m.genericjdbc.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import net.sh4m.genericjdbc.dto.PaginationProperties;


/**
 * Interface for generic JDBC utility operations.
 * 
 * @param <T>
 *            The type of the entity object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the entity object for which this instance is
 *            to be used.
 * 
 */
@NoRepositoryBean
public interface GenericJdbcRepository<T, ID extends Serializable> extends
		CrudRepository<T, ID> {

	/**
	 * Returns list of entities meeting the paging restriction provided in the
	 * {@code PaginationProperties} object.
	 * 
	 * @param paginationProperties
	 * @return a page of entities
	 */
	List<T> findAll(PaginationProperties paginationProperties);

	/**
	 * Returns list of entities meeting the paging restriction provided in the
	 * {@code PaginationProperties} object.
	 * 
	 * @param paginationProperties
	 * @param considerDeleted
	 * @return a page of entities
	 */
	List<T> findAll(PaginationProperties paginationProperties,
			boolean considerDeleted);

	/**
	 * Returns list of entities meeting the paging restriction provided in the
	 * {@code PaginationProperties} object.
	 * 
	 * @param paginationProperties
	 * @param propertyColumns
	 * @param propertyColumnValues
	 * @return a page of entities
	 */
	List<T> findAll(PaginationProperties paginationProperties,
			String[] propertyColumns, Object[] propertyColumnValues);

	/**
	 * Returns list of entities meeting the paging restriction provided in the
	 * {@code PaginationProperties} object.
	 * 
	 * @param paginationProperties
	 * @param propertyColumns
	 * @param propertyColumnValues
	 * @param considerDeleted
	 * @return a page of entities
	 */
	List<T> findAll(PaginationProperties paginationProperties,
			String[] propertyColumns, Object[] propertyColumnValues,
			boolean considerDeleted);

	/**
	 * checks for the existence of data with given propertyColumnValues on a
	 * given propertyColumns.
	 * 
	 * @param propertyColumns
	 * @param propertyColumnValues
	 * @return
	 */
	boolean existsByProperty(String[] propertyColumns,
			Object[] propertyColumnValues);

	/**
	 * checks for the existence of data with given propertyColumnValue on a
	 * given propertyColumn. <br/>
	 * Method caller can instruct this method to consider previously deleted
	 * records as well if required.
	 * 
	 * @param propertyColumns
	 * @param propertyColumnValues
	 * @param considerDeleted
	 *            if the value is true, even soft-deleted records will also be
	 *            taken into consideration
	 * @return true if a record exists with the given properyColumnValue exists
	 *         for given propertyColumn otherwise false
	 */
	boolean existsByProperty(String[] propertyColumns,
			Object[] propertyColumnValues, boolean considerDeleted);

	/**
	 * retrieves a single (or first occurrence) record from database with given
	 * propertyColumnValue on a given propertyColumn. this by default doesn't
	 * include any previously removed records into consideration.
	 * 
	 * @param propertyColumns
	 * @param propertyColumnValues
	 * @return
	 */
	T findOneByProperty(String[] propertyColumns, Object[] propertyColumnValues);

	/**
	 * retrieves a single (or first occurrence) record from database with given
	 * propertyColumnValue on a given propertyColumn. this by default doesn't
	 * include any previously removed records into consideration.
	 * 
	 * @param propertyColumns
	 * @param propertyColumnValues
	 * @param includeDeleted
	 * @return
	 */
	T findOneByProperty(String[] propertyColumns,
			Object[] propertyColumnValues, boolean includeDeleted);
	
	Long count(List<String> propertyColumns, Object[] columnsValues, boolean considerDeleted);
}
