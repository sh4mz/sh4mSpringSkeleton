package net.sh4m.genericjdbc.jdbc;

import static net.sh4m.genericjdbc.AppConstants.DB_PREFIX_EXPRESSION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Used by Spring JDBC repositories to describe about the corresponding database
 * table.
 * 
 */
public class TableDescription
{

    private String name;
    private final List<String> idColumns;
    private String fromClause;
    private static final String PREFIX_COLON = "";

    /**
     * Constructor
     * 
     * @param name
     *            table name. must not be empty or null.
     * @param fromClause
     *            from clause to be used for the table.
     * @param hasVersionColumn
     *            to mark whether the underlying database table has version
     *            column or not.
     * @param idColumns
     *            primary key column names. must not be empty or null.
     */
    public TableDescription(String name, String fromClause,
           String... idColumns)
    {
        Assert.notNull(name);
        Assert.notNull(idColumns);
        Assert.isTrue(idColumns.length > 0,
                "At least one primary key column must be provided");

        this.name = name;
        this.idColumns = Collections.unmodifiableList(Arrays.asList(idColumns));
        if (StringUtils.hasText(fromClause))
        {
            this.fromClause = fromClause;
        }
        else
        {
            this.fromClause = PREFIX_COLON + DB_PREFIX_EXPRESSION + name
                    + PREFIX_COLON;
        }
        //this.hasVersionColumn = hasVersionColumn;
    }

    /**
     * Constructor. Default assumes the underlying table has a
     * <code>version</code> column. generally the <code>version</code> column is
     * used for optimistic locking purposes.
     * 
     * @param name
     *            table name. must not be empty or null.
     * @param idColumn
     *            primary key column names. must not be empty or null.
     */
    public TableDescription(String name, String idColumn)
    {
        this(name, null, idColumn);
    }

    /**
     * Constructor.
     * 
     * @param name
     *            table name. must not be empty or null.
     * @param hasVersionColumn
     *            to mark whether the underlying database table has version
     *            column or not.
     * @param idColumn
     *            primary key column names. must not be empty or null.
     */
    public TableDescription(String name, boolean hasVersionColumn,
            String idColumn)
    {
        this(name, null, idColumn);
    }

    /**
     * returns the table name for the current table.
     * 
     * @return name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * set table name
     */
    public void setPrefixToTableName(String prefix)
    {
        this.name = PREFIX_COLON + prefix + this.name + PREFIX_COLON;
        if (StringUtils.hasText(fromClause)
                && fromClause.indexOf(DB_PREFIX_EXPRESSION) >= 0)
        {
            this.fromClause = StringUtils.replace(fromClause,
                    DB_PREFIX_EXPRESSION, prefix);
        }
    }

    /**
     * returns the primary keys of the current table.
     * 
     * @return idColumns
     */
    public List<String> getIdColumns()
    {
        return idColumns;
    }

    /**
     * returns the from clause of the table. return value can be null or empty.
     * 
     * @return fromClause.
     */
    public String getFromClause()
    {
        return fromClause;
    }

    
}
