package net.sh4m.genericjdbc;

public final class AppConstants
{

    /**
     * to use in <code>@SuppressWarnings</code> annotations.
     */
    public static final String SUPPRESS_WARNINGS_UNCHECKED = "unchecked";

    /**
     * to use in <code>@SuppressWarnings</code> annotations.
     */
    public static final String SUPPRESS_WARNINGS_RAWTYPES = "rawtypes";

    /**
     * to use in <code>@SuppressWarnings</code> annotations.
     */
    public static final String SUPPRESS_WARNINGS_UNUSED = "unused";

    /**
     * Date format for serialization/marshaling purposes.
     */
    public static final String MARSHALLER_OR_SERIALIZER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * UTF-8
     */
    public static final String UTF8 = "UTF-8";

    /**
     * Response DTO Serialization Constants
     */
    public static final String SERIALIZE_KEY_RESPONSE = "response";

    /**
     * Constant for created_date column name. it is common for all tables.
     */
    public static final String CREATED_DATE = "created_date";
    /**
     * Constant for created_by column name. it is common for all tables.
     */
    public static final String CREATED_BY = "created_by";

    /**
     * Constant for last_modified_date column name. it is common for all tables.
     */
    public static final String LAST_MODIFIED_DATE = "last_modified_date";

    /**
     * Constant for deleted column name. It is common for all tables.
     */
    public static final String DELETED = "deleted";

//    /**
//     * Constant for version column name. It is common for all tables.
//     */
//    public static final String VERSION = "version";

    /**
     * 
     */
    public static final String DB_PREFIX_EXPRESSION = "{db.prefix}";

    /**
     * Constructor. Private to prevent unnecessary instantiation.
     */

    public static final String SQL_QUERY = "sql";
    public static final String SQL_PARAMS = "params";

    private AppConstants()
    {
    }
}