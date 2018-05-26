package net.sh4m.genericjdbc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

import net.sh4m.genericjdbc.dto.PaginationProperties;
import net.sh4m.genericjdbc.jdbc.TableDescription;



public class SqlGenerator
{
    public static final String WHERE = " WHERE ";
    public static final String AND = " AND ";
    public static final String OR = " OR ";
    public static final String SELECT = "SELECT ";
    public static final String FROM = "FROM ";
    public static final String DELETE = "DELETE ";
    public static final String UPDATE = "UPDATE ";
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String COMMA = ", ";
    public static final String PARAM = " = ?";
    public static final String LIKE = " LIKE ?";
    public static final String LIKEHASH = "#LIKE#";
    
    private final String allColumnsClause;

    /**
     * 
     * @param allColumnsClause
     */
    public SqlGenerator(String allColumnsClause)
    {
        this.allColumnsClause = allColumnsClause;
    }

    /**
     * 
     */
    public SqlGenerator()
    {
        this("*");
    }

    /**
     * Constructs a basic count query with out any further filtering.
     * 
     * @param table
     * @return count query
     */
    public String count(TableDescription table)
    {
        StringBuilder countQuery = new StringBuilder(SELECT);
        countQuery.append("COUNT(*) ").append(FROM)
                .append(table.getFromClause());
        return countQuery.toString();
    }

    /**
     * Constructs count query by given columns names.
     * 
     * @param table
     * @param columns
     * @return count query with where clause
     */
    public String countByProperty(TableDescription table, List<String> columns)
    {
        StringBuilder countQuery = new StringBuilder(count(table));
        if (!CommonsUtil.isEmpty(columns))
        {
            countQuery.append(whereByColumnClause(table.getName(), columns));
        }
        return countQuery.toString();
    }

    /**
     * 
     * @param table
     * @return
     */
    public String deleteById(TableDescription table)
    {
        return DELETE + FROM + table.getName() + whereByIdClause(table);
    }

    /**
     * 
     * @param table
     * @return
     */
    public String selectAll(TableDescription table)
    {
        return SELECT + allColumnsClause + " " + FROM + table.getFromClause();
    }

    /**
     * 
     * @param table
     * @param page
     * @return
     */
    public String selectAll(TableDescription table, PaginationProperties page)
    {

        return selectAll(table, page.getSortProperty(), page.getSortOrder())
                + limitClause(page);
    }

    /**
     * constructs select query with all table's columns
     * 
     * @param table
     * @param sort
     * @return
     */
    public String selectAll(TableDescription table, String sortProperty,
            Direction sortOrder)
    {
        return selectAll(table)
                + sortingClauseIfRequired(sortProperty, sortOrder);
    }

    /**
     * constructs select query with given table's ID column as where clause
     * 
     * @param table
     *            table description
     * @return select query with where clause.
     */
    public String selectById(TableDescription table)
    {
        return selectAll(table) + whereByIdClause(table);
    }

    /**
     * constructs select query with given table's columns as part of where
     * clause
     * 
     * @param table
     *            table description
     * @param columns
     *            list of column names to be included. cannot be empty or null.
     * @return select query with where clause
     */
    public String selectByColumn(TableDescription table,
            PaginationProperties paginationProperties, List<String> columns)
    {
        if (paginationProperties == null)
        {
            return selectAll(table)
                    + whereByColumnClause(table.getName(), columns);
        }
        return selectAll(table) + whereByColumnClause(table.getName(), columns)
                + sortingClauseIfRequired(
                        paginationProperties.getSortProperty(),
                        paginationProperties.getSortOrder())
                + limitClause(paginationProperties);
    }

    /**
     * 
     * @param table
     * @param idsCount
     * @return
     */
    public String selectByIds(TableDescription table, int idsCount)
    {
        switch (idsCount)
        {
            case 0:
                return selectAll(table);
            case 1:
                return selectById(table);
            default:
                return selectAll(table) + whereByIdsClause(table, idsCount);
        }
    }

    /**
     * 
     * @param table
     * @param columns
     * @return
     */
    public String update(TableDescription table, Map<String, Object> columns)
    {
        final StringBuilder updateQuery = new StringBuilder(
                UPDATE + table.getName() + " SET ");
        for (Iterator<Map.Entry<String, Object>> iterator = columns.entrySet()
                .iterator(); iterator.hasNext();)
        {
            Map.Entry<String, Object> column = iterator.next();
            updateQuery.append(column.getKey()).append(" = ?");
            if (iterator.hasNext())
            {
                updateQuery.append(COMMA);
            }
        }
        updateQuery.append(whereByIdClause(table));
        return updateQuery.toString();
    }

    /**
     * 
     * @param table
     * @param columns
     * @return
     */
    public String create(TableDescription table, Map<String, Object> columns)
    {
        final StringBuilder createQuery = new StringBuilder(
                INSERT_INTO + table.getName() + " (");
        appendColumnNames(createQuery, columns.keySet());
        createQuery.append(")").append(" VALUES (");
        createQuery.append(repeat("?", COMMA, columns.size()));

        return createQuery.append(")").toString();
    }

    /**
     * 
     * @param table
     * @return
     */
    public String deleteAll(TableDescription table)
    {
        return DELETE + FROM + table.getName();
    }

    /**
     * 
     * @param table
     * @return
     */
    public String countById(TableDescription table)
    {
        return count(table) + whereByIdClause(table);
    }

    /**
     * 
     * @return
     */
    public String getAllColumnsClause()
    {
        return allColumnsClause;
    }

    /**
     * constructs the query string that will be used for optimistic locking
     * validation scheme. <br>
     * 
     * The column name is hard-coded as version for now.
     * 
     * @param table
     * @return
     */
    public String versionById(TableDescription table)
    {
        return SELECT + "version " + FROM + table.getName()
                + whereByIdClause(table);
    }

    /**
     * 
     * @param sort
     * @return
     */
    public String sortingClauseIfRequired(String sortProperty,
            Direction sortOrder)
    {
        if (!StringUtils.hasText(sortProperty))
        {
            return "";
        }
        StringBuilder orderByClause = new StringBuilder();
        orderByClause.append(" ORDER BY ");
        orderByClause.append(sortProperty).append(" ").append(sortOrder.name());

        return orderByClause.toString();
    }

    /**
     * 
     * @param page
     * @return
     */
    public String limitClause(PaginationProperties page)
    {
        if (page == null || page.getPageSize() < 1)
        {
            return "";
        }
        
        if (page.getOffset() > 0){
        	return " LIMIT " + page.getOffset() + COMMA + page.getPageSize();
        } else {
        	return " LIMIT " + page.getPageSize();
        }
        
    }

    public String paginationClause(PaginationProperties pagination)
    {

        if (pagination.getPageNumber() <= 0)
        {
            pagination.setOffset(0);
        }
        else
        {
            pagination.setOffset((pagination.getPageNumber() - 1)
                    * pagination.getPageSize());
        }

        return this.limitClause(pagination);
    }

    /**
     * 
     * @param table
     * @return
     */
    private String whereByIdClause(TableDescription table)
    {
        return whereByColumnClause(table.getName(), table.getIdColumns());
    }

    /**
     * 
     * @param whereClause
     * @param columns
     */
    private String whereByColumnClause(String tableAliasName,
            List<String> columns)
    {
        if (CommonsUtil.isEmpty(columns))
        {
            return "";
        }

        final StringBuilder whereClause = new StringBuilder(WHERE);
        for (Iterator<String> column = columns.iterator(); column.hasNext();)
        {
        	String colName = column.next();
        	//System.out.println(colName);
            
            if (colName.contains("#LIKE#")){
            	whereClause.append(tableAliasName).append(".").append(colName.replaceAll("#LIKE#", "")).append(" LIKE ?");
            } else {
            	whereClause.append(tableAliasName).append(".").append(colName).append(PARAM);
            }
            
            if (column.hasNext())
            {
                whereClause.append(AND);
            }
        }

        return whereClause.toString();
    }

    /**
     * 
     * @param table
     * @param idsCount
     * @return
     */
    private String whereByIdsClause(TableDescription table, int idsCount)
    {
        final List<String> idColumnNames = table.getIdColumns();
        if (idColumnNames.size() > 1)
        {
            return whereByIdsWithMultipleIdColumns(idsCount, idColumnNames);
        }
        else
        {
            return whereByIdsWithSingleIdColumn(idsCount, idColumnNames.get(0));
        }
    }

    /**
     * 
     * @param idsCount
     * @param idColumnNames
     * @return
     */
    private String whereByIdsWithMultipleIdColumns(int idsCount,
            List<String> idColumnNames)
    {
        int idColumnsCount = idColumnNames.size();
        final StringBuilder whereClause = new StringBuilder(WHERE);
        final int totalParams = idsCount * idColumnsCount;
        for (int idColumnIdx = 0; idColumnIdx < totalParams; idColumnIdx += idColumnsCount)
        {
            if (idColumnIdx > 0)
            {
                whereClause.append(OR);
            }
            whereClause.append("(");
            for (int i = 0; i < idColumnsCount; ++i)
            {
                if (i > 0)
                {
                    whereClause.append(AND);
                }
                whereClause.append(idColumnNames.get(i)).append(" = ?");
            }
            whereClause.append(")");
        }
        return whereClause.toString();
    }

    /**
     * 
     * @param idsCount
     * @param idColumn
     * @return
     */
    private String whereByIdsWithSingleIdColumn(int idsCount, String idColumn)
    {
        final StringBuilder whereClause = new StringBuilder(WHERE);
        return whereClause.append(idColumn).append(" IN (")
                .append(repeat("?", COMMA, idsCount)).append(")").toString();
    }

    /**
     * 
     * @param createQuery
     * @param columnNames
     */
    private void appendColumnNames(StringBuilder createQuery,
            Set<String> columnNames)
    {
        for (Iterator<String> iterator = columnNames.iterator(); iterator
                .hasNext();)
        {
            final String column = iterator.next();
            createQuery.append(column);
            if (iterator.hasNext())
            {
                createQuery.append(COMMA);
            }
        }
    }

    /**
     * 
     * @param s
     * @param separator
     * @param count
     * @return
     */
    private static String repeat(String s, String separator, int count)
    {
        int localCount = count;
        StringBuilder string = new StringBuilder(
                (s.length() + separator.length()) * localCount);
        while (--localCount > 0)
        {
            string.append(s).append(separator);
        }
        return string.append(s).toString();
    }

	/**
	 * @param localWhereColumnsList
	 * @return
	 */
	public List<Integer> idxLocLike(List<String> localWhereColumnsList) {
		List<Integer> colLikeLoc = new ArrayList<Integer>();
		for(int i = 0; i<localWhereColumnsList.size(); i++){
        	if (localWhereColumnsList.get(i).contains(LIKEHASH)){
        		colLikeLoc.add(i);
        	}
        }
		return colLikeLoc.size() > 0 ? colLikeLoc : null;
	}

	/**
	 * @param colLikeLoc
	 * @param localColumnValues
	 * @return
	 */
	public Object[] colValueReplaceAsterisk(List<Integer> colLikeLoc, Object[] localColumnValues) {
		if (colLikeLoc.size() > 0) {
	    	   for(int p : colLikeLoc){
	    		   String objValue = null;
	    		   try {
	    			   objValue = (String) localColumnValues[p];
	    			   if (objValue.startsWith("*")){
	    				   objValue = "%" + objValue.substring(1, objValue.length());
	        		   }
	    			   if (objValue.endsWith("*")){
	    				   objValue = objValue.substring(0, objValue.length() - 1) + "%";
	    			   }
	    			   localColumnValues[p] = objValue;
	    		   } catch (Exception e){
	    			   
	    		   }
	    		   
	    	   }
	       }
		return localColumnValues;
	}

	
}
