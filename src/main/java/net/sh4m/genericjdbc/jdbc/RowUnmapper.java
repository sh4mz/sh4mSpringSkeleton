
package net.sh4m.genericjdbc.jdbc;

import java.util.Map;

/**
 * An interface used by org.springframework.jdbc.core.JdbcTemplate for mapping
 * entity object to columns of the database table.
 * 
 * @param <T>
 *            The type of the entity object for which this instance is to be
 *            used.
 * 
 */
public interface RowUnmapper<T> {

	/**
	 * Implementations must implement this method to map entity object to each
	 * of the column in the corresponding database table.
	 * 
	 * @param t
	 *            the object for the entity that needs to be persisted to
	 *            database.
	 * @return A map of the columns of the current entity &lt;T&gt;.
	 */
	Map<String, Object> mapColumns(T t);
}
