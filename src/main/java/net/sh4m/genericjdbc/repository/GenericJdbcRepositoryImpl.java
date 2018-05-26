package net.sh4m.genericjdbc.repository;

import static net.sh4m.genericjdbc.AppConstants.DELETED;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import net.sh4m.genericjdbc.dto.AbstractDTO;
import net.sh4m.genericjdbc.dto.PaginationProperties;
import net.sh4m.genericjdbc.exception.StaleObjectStateException;
import net.sh4m.genericjdbc.jdbc.MissingRowUnmapper;
import net.sh4m.genericjdbc.jdbc.RowUnmapper;
import net.sh4m.genericjdbc.jdbc.TableDescription;
import net.sh4m.genericjdbc.util.CommonsUtil;
import net.sh4m.genericjdbc.util.SqlGenerator;


/**
 * 
 * @param <T>
 *            The type of the entity object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the entity object for which this instance is
 *            to be used.
 * 
 * @see GenericJdbcRepository
 * 
 */
public abstract class GenericJdbcRepositoryImpl<T extends AbstractDTO<ID>, ID extends Serializable>
        implements GenericJdbcRepository<T, ID>
{


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final TableDescription table;

    private final RowMapper<T> rowMapper;
    private final RowUnmapper<T> rowUnmapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private SqlGenerator sqlGenerator;

    @Value("#{propVal['db.table.prefix']}")
    private String dbTablePrefix;
    /**
     * Repository with {@link RowMapper} and {@link RowUnmapper} instances.
     * Defaults to {@link MissingRowUnmapper} as {@link RowUnmapper} and id as
     * primary key column.
     * 
     * @param rowMapper
     *            instance of the {@link RowMapper}
     * @param tableName
     *            name of the table
     */
    public GenericJdbcRepositoryImpl(RowMapper<T> rowMapper, String tableName)
    {
        this(rowMapper, new MissingRowUnmapper<T>(), tableName);
    }

    /**
     * Repository with {@link RowMapper} and {@link RowUnmapper} instances.
     * Defaults to id as primary key of the table.
     * 
     * @param rowMapper
     *            instance of the {@link RowMapper}
     * @param rowUnmapper
     *            instance of the {@link RowUnmapper}
     * @param tableName
     *            name of the table
     */
    public GenericJdbcRepositoryImpl(RowMapper<T> rowMapper,
            RowUnmapper<T> rowUnmapper, String tableName)
    {
        this(rowMapper, rowUnmapper, tableName, "id");
    }

    /**
     * Repository with {@link RowMapper} and {@link RowUnmapper} instances.
     * Defaults to {@link MissingRowUnmapper} as {@link RowUnmapper} and id as
     * primary key column.
     * 
     * @param rowMapper
     *            instance of the {@link RowMapper}
     * @param tableName
     *            name of the table
     * @param idColumn
     */
    public GenericJdbcRepositoryImpl(RowMapper<T> rowMapper, String tableName,
            String idColumn)
    {
        this(rowMapper, new MissingRowUnmapper<T>(), tableName, idColumn);
    }

    /**
     * fill-fledged Repository.
     * 
     * @param rowMapper
     *            instance of the {@link RowMapper}
     * @param rowUnmapper
     *            instance of the {@link RowUnmapper}
     * @param tableName
     *            name of the table
     * @param idColumn
     */
    public GenericJdbcRepositoryImpl(RowMapper<T> rowMapper,
            RowUnmapper<T> rowUnmapper, String tableName, String idColumn)
    {
        this(rowMapper, rowUnmapper, new TableDescription(tableName, idColumn));
    }

    /**
     * fill-fledged Repository.
     * 
     * @param rowMapper
     *            instance of the {@link RowMapper}
     * @param rowUnmapper
     *            instance of the {@link RowUnmapper}
     * @param tableName
     *            name of the table
     * @param tableDescription
     *            instance of table description
     */
    public GenericJdbcRepositoryImpl(RowMapper<T> rowMapper,
            RowUnmapper<T> rowUnmapper, TableDescription tableDescription)
    {
        this.rowMapper = rowMapper;
        this.rowUnmapper = rowUnmapper;
        this.table = tableDescription;
    }

    /**
     * @return the jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    /**
     * @return the sqlGenerator
     */
    public SqlGenerator getSqlGenerator()
    {
        return sqlGenerator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count()
    {
        return count(null, null,false);
    }

    /**
     * 
     * @param propertyColumns
     * @param columnsValues
     * @return
     */
    public Long count(List<String> propertyColumns, Object[] columnsValues, boolean considerDeleted)
    {
    	if (propertyColumns != null && columnsValues != null){
    		List<Integer> colLikeLoc = sqlGenerator.idxLocLike(propertyColumns);
            if (colLikeLoc != null){
            	columnsValues =   sqlGenerator.colValueReplaceAsterisk(colLikeLoc,columnsValues);
            }
    	}
    	
    	if (!considerDeleted)
        {
    		if(propertyColumns == null){
    			propertyColumns = new ArrayList<String>();
    		}
    		propertyColumns.add(DELETED);
    		columnsValues = CommonsUtil.addElement(columnsValues,
                    Boolean.FALSE);
        }
        return jdbcTemplate.queryForObject(
                sqlGenerator.countByProperty(getTable(), propertyColumns),
                columnsValues, Long.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(ID id)
    {
        jdbcTemplate
                .update(sqlGenerator.deleteById(table), idToObjectArray(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity)
    {
        jdbcTemplate.update(sqlGenerator.deleteById(table), entity.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Iterable<? extends T> entities)
    {
        for (T t : entities)
        {
            delete(t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll()
    {
        jdbcTemplate.update(sqlGenerator.deleteAll(table));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(ID id)
    {
        return jdbcTemplate.queryForObject(sqlGenerator.countById(table),
                Long.class, idToObjectArray(id)) > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll()
    {
        return jdbcTemplate.query(sqlGenerator.selectAll(table), rowMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOne(ID id)
    {
        final Object[] idColumns = idToObjectArray(id);
        final List<T> entityOrEmpty = jdbcTemplate.query(
                sqlGenerator.selectById(table), idColumns, rowMapper);
        return entityOrEmpty.isEmpty() ? null : entityOrEmpty.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends T> S save(S entity)
    {
        if (entity.isNew())
        {
            if (entity.getCreatedDate() == null)
            {
                entity.setCreatedDate(new Date());
            }

//            if (table.isHasVersionColumn())
//            {
//                entity.setVersion(DEFAULT_VERSION);
//            }

            return create(entity);
        }
        else
        {
            return update(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities)
    {
        List<S> ret = new ArrayList<S>();
        for (S s : entities)
        {
            ret.add(save(s));
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<T> findAll(Iterable<ID> ids)
    {
        final List<ID> idsList = toList(ids);
        if (idsList.isEmpty())
        {
            return Collections.emptyList();
        }
        final Object[] idColumnValues = flatten(idsList);
        return jdbcTemplate.query(
                sqlGenerator.selectByIds(table, idsList.size()), rowMapper,
                idColumnValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(PaginationProperties paginationProperties)
    {
        return findAll(paginationProperties, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(PaginationProperties paginationProperties,
            boolean considerDeleted)
    {
        return findAll(paginationProperties, null, null, considerDeleted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(PaginationProperties paginationProperties,
            String[] propertyColumns, Object[] propertyColumnValues)
    {
        return findAll(paginationProperties, propertyColumns,
                propertyColumnValues, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(PaginationProperties paginationProperties,
            String[] propertyColumns, Object[] propertyColumnValues,
            boolean considerDeleted)
    {
        String[] localWhereColumns = propertyColumns;
        Object[] localColumnValues = propertyColumnValues;
        if (!considerDeleted)
        {
            localWhereColumns = CommonsUtil.addStrElement(localWhereColumns,
                    DELETED);
            localColumnValues = CommonsUtil.addElement(localColumnValues,
                    Boolean.FALSE);
        }

        
        List<String> localWhereColumnsList = !CommonsUtil
                .isEmpty(localWhereColumns) ? Arrays.asList(localWhereColumns)
                : null;
                
       List<Integer> colLikeLoc = sqlGenerator.idxLocLike(localWhereColumnsList);
       
       if (colLikeLoc != null){
    	   localColumnValues =   sqlGenerator.colValueReplaceAsterisk(colLikeLoc,localColumnValues);
       }
       
        String query = sqlGenerator.selectByColumn(table, paginationProperties,
                localWhereColumnsList);

        return jdbcTemplate.query(query, localColumnValues, rowMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByProperty(String[] propertyColumns,
            Object[] propertyColumnValues)
    {
        return existsByProperty(propertyColumns, propertyColumnValues, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByProperty(String[] propertyColumns,
            Object[] propertyColumnValues, boolean considerDeleted)
    {
        String[] localWhereColumns = propertyColumns;
        Object[] localColumnValues = propertyColumnValues;
        if (!considerDeleted)
        {
            localWhereColumns = CommonsUtil.addStrElement(localWhereColumns,
                    DELETED);
            localColumnValues = CommonsUtil.addElement(localColumnValues,
                    Boolean.FALSE);
        }

        Integer count = jdbcTemplate.queryForObject(
                sqlGenerator.countByProperty(table,
                        Arrays.asList(localWhereColumns)), localColumnValues,
                Integer.class);

        if (count.intValue() <= 0)
        {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOneByProperty(String[] propertyColumns,
            Object[] propertyColumnValues)
    {
        return findOneByProperty(propertyColumns, propertyColumnValues, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOneByProperty(String[] propertyColumns,
            Object[] propertyColumnValues, boolean considerDeleted)
    {
        String[] localWhereColumns = propertyColumns;
        Object[] localColumnValues = propertyColumnValues;
        if (!considerDeleted)
        {
            localWhereColumns = CommonsUtil.addStrElement(localWhereColumns,
                    DELETED);
            localColumnValues = CommonsUtil.addElement(localColumnValues,
                    Boolean.FALSE);
        }

        List<T> list = jdbcTemplate.query(
                sqlGenerator.selectByColumn(table, null,
                        Arrays.asList(localWhereColumns)), localColumnValues,
                rowMapper);
        if (!CommonsUtil.isEmpty(list))
        {
            return list.get(0);
        }
        return null;
    }

    /**
     * this is to trigger the table name update with the prefix defined in
     * properties file.
     */
    @PostConstruct
    public void updateTableNameWithPrefix()
    {
        this.table.setPrefixToTableName(getDbPrefix());
    }

    /**
     * 
     * @return
     */
    protected TableDescription getTable()
    {
        return table;
    }

    /**
     * @param entity
     * @return
     */
    protected <S extends T> S create(S entity)
    {
        final Map<String, Object> columns = preCreate(columns(entity), entity);
        if (entity.getId() == null)
        {
            return createWithAutoGeneratedKey(entity, columns);
        }
        else
        {
            return createWithManuallyAssignedKey(entity, columns);
        }
    }

    /**
     * 
     * @param columns
     * @param entity
     * @return
     */
    protected Map<String, Object> preCreate(Map<String, Object> columns,
            T entity)
    {
        return columns;
    }

    /**
     * General purpose hook method that is called every time {@link #create} is
     * called with a new entity.
     * <p/>
     * OVerride this method e.g. if you want to fetch auto-generated key from
     * database
     * 
     * 
     * @param entity
     *            Entity that was passed to {@link #create}
     * @param generatedId
     *            ID generated during INSERT or NULL if not available/not
     *            generated. todo: Type should be ID, not Number
     * @return Either the same object as an argument or completely different one
     */
    protected <S extends T> S postCreate(S entity, ID generatedId)
    {
        return entity;
    }

    /**
     * Apart form normal update functionality, this method also checks for the
     * optimistic locking scheme. In this, every update event will be validated
     * to ensure that the version number get/test/increment happens as part of
     * the single atomic transaction. <br>
     * 
     * @param entity
     * @return
     * @throws StaleObjectStateException
     *             is there are any ambiguity with the data version number.
     */
    protected <S extends T> S update(S entity)
    {
//        if (table.isHasVersionColumn())
//        {
//            Integer submittedVersion = entity.getVersion();
//            if (submittedVersion == null)
//            {
//                throw new StaleObjectStateException(table.getName(),
//                        entity.getId(), "Submitted entity must have a version");
//            }
//
//            // Check and Update Version for optimistic locking
//            Integer latestVersion = jdbcTemplate.queryForObject(
//                    sqlGenerator.versionById(table), Integer.class,
//                    entity.getId());
//            if (latestVersion != null
//                    && (latestVersion.intValue() != submittedVersion.intValue()))
//            {
//                logger.error("Stale entity for table [" + table.getName()
//                        + "] with identifier [" + entity.getId()
//                        + "]: Submitted version is " + submittedVersion
//                        + " and latest version is " + latestVersion);
//                throw new StaleObjectStateException(table.getName(),
//                        entity.getId());
//            }
//
//            entity.setVersion(entity.getVersion() + 1);
//        }
        entity.setLastModifiedDate(new Date());

        final Map<String, Object> columns = preUpdate(entity, columns(entity));
        final List<Object> idValues = removeIdColumns(columns);
        final String updateQuery = sqlGenerator.update(table, columns);
        for (int i = 0; i < table.getIdColumns().size(); ++i)
        {
            columns.put(table.getIdColumns().get(i), idValues.get(i));
        }
        final Object[] queryParams = columns.values().toArray();
        jdbcTemplate.update(updateQuery, queryParams);
        return postUpdate(entity);
    }

    /**
     * @param entity
     * @param columns
     * @return
     */
    protected Map<String, Object> preUpdate(T entity,
            Map<String, Object> columns)
    {
        return columns;
    }

    /**
     * 
     * @param entity
     * @return
     */
    protected <S extends T> S postUpdate(S entity)
    {
        return entity;
    }

    /**
     * returns the logger instance.
     * 
     * @return Logger instance
     */
    protected Logger getLogger()
    {
        return logger;
    }

    /**
     * returns the DB table prefix
     * 
     * @return db prefix
     */
    protected String getDbPrefix()
    {
        return dbTablePrefix + "_";
    }

    /**
     * returns tablename with DB table prefix
     * 
     * @param tableName
     * @return tablename with db prefix
     */
    protected String getTableWithDbPrefix(String tableName)
    {
        StringBuilder sbTableName = new StringBuilder();
        sbTableName.append(dbTablePrefix).append("_").append(tableName);
        return sbTableName.toString();
    }

    /**
     * returns sequenceName with DB sequence prefix
     * 
     * @param sequenceName
     * @return sequenceName with db prefix
     */
    protected String getSequenceWithDbPrefix(String sequenceName)
    {
        StringBuilder sbSequenceName = new StringBuilder(dbTablePrefix);
        sbSequenceName.append("_").append(sequenceName);
        return sbSequenceName.toString();
    }

    /**
     * 
     * @param entity
     * @return
     */
    private Map<String, Object> columns(T entity)
    {
        Map<String, Object> columns = rowUnmapper.mapColumns(entity);
        return new LinkedHashMap<String, Object>(columns);
    }

    /**
     * 
     * @param entity
     * @param columns
     * @return
     */
    @SuppressWarnings("unchecked")
    private <S extends T> S createWithAutoGeneratedKey(S entity,
            Map<String, Object> columns)
    {
        removeIdColumns(columns);
        final String createQuery = sqlGenerator.create(table, columns);
        final Object[] queryParams = columns.values().toArray();
        final GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException
            {

                final String idColumnName = table.getIdColumns().get(0);
                final PreparedStatement ps = con.prepareStatement(createQuery,
                        new String[] { idColumnName });
                for (int i = 0; i < queryParams.length; ++i)
                {
                    ps.setObject(i + 1, queryParams[i]);
                }
                return ps;
            }
        }, key);

        entity.setId((ID) key.getKey());
        return postCreate(entity, (ID) key.getKey());
    }

    /**
     * 
     * @param entity
     * @param columns
     * @return
     */
    private <S extends T> S createWithManuallyAssignedKey(S entity,
            Map<String, Object> columns)
    {
        final String createQuery = sqlGenerator.create(table, columns);
        final Object[] queryParams = columns.values().toArray();
        jdbcTemplate.update(createQuery, queryParams);
        return postCreate(entity, null);
    }

    /**
     * 
     * @param columns
     * @return
     */
    private List<Object> removeIdColumns(Map<String, Object> columns)
    {
        List<Object> idColumnsValues = new ArrayList<Object>();
        for (String idColumn : table.getIdColumns())
        {
            idColumnsValues.add(columns.remove(idColumn));
        }
        return idColumnsValues;
    }

    /**
     * 
     * @param id
     * @return
     */
    private static <ID> List<Object> idToObjectList(ID id)
    {
        if (id instanceof Object[])
        {
            return Arrays.asList((Object[]) id);
        }

        return Collections.<Object> singletonList(id);
    }

    /**
     * 
     * @param id
     * @return
     */
    private static <ID> Object[] idToObjectArray(ID id)
    {
        if (id instanceof Object[])
        {
            return (Object[]) id;
        }
        return new Object[] { id };
    }

    /**
     * 
     * @param ids
     * @return
     */
    private static <ID> Object[] flatten(List<ID> ids)
    {
        final List<Object> result = new ArrayList<Object>();
        for (ID id : ids)
        {
            result.addAll(idToObjectList(id));
        }
        return result.toArray();
    }

    /**
     * 
     * @param iterable
     * @return
     */
    private static <T> List<T> toList(Iterable<T> iterable)
    {
        final List<T> result = new ArrayList<T>();
        for (T item : iterable)
        {
            result.add(item);
        }
        return result;
    }

}