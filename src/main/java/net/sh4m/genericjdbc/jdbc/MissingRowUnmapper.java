
package net.sh4m.genericjdbc.jdbc;

import java.util.Map;

public class MissingRowUnmapper<T> implements RowUnmapper<T> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> mapColumns(Object o) {
		throw new UnsupportedOperationException(
				"This repository is read-only, it can't store or update entities");
	}
}
