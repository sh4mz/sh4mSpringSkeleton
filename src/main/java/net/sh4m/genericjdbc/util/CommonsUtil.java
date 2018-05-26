
package net.sh4m.genericjdbc.util;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * Core Utility for the whole platform
 * 
 */
public final class CommonsUtil
{

    /**
     * Constructor. Private to prevent unnecessary instantiation.
     */
    private CommonsUtil()
    {
    }

    /**
     * This method append List<Long> into String with comma
     * 
     * @param List
     *            <Long> Ids
     * @return String
     */
    public static String idListToStringOfComma(List<Long> Ids)
    {
        StringBuffer sbIds = new StringBuffer();
        int size = Ids.size();
        for (int i = 0; i < size; i++)
        {
            sbIds.append(Ids.get(i));
            if (i < (size - 1))
            {
                sbIds.append(",");
            }
        }
        return sbIds.toString();
    }

    /**
     * This method append List<Long> into String with comma
     * 
     * @param List
     *            <Long> Ids
     * @return String
     */
    public static String strListToStringOfComma(List<String> strCodes)
    {
        StringBuffer sbCodes = new StringBuffer();
        int size = strCodes.size();
        for (int i = 0; i < size; i++)
        {
            sbCodes.append("'");
            sbCodes.append(strCodes.get(i));
            sbCodes.append("'");
            if (i < (size - 1))
            {
                sbCodes.append(",");
            }
        }
        return sbCodes.toString();
    }

    /**
     * This method returns true if the collection is null or is empty.
     * 
     * @param collection
     * @return true | false
     */
    public static boolean isEmpty(Collection<?> collection)
    {
        if (collection == null || collection.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * This method returns true of the map is null or is empty.
     * 
     * @param map
     * @return true | false
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        if (map == null || map.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input array is null or its length is
     * zero.
     * 
     * @param array
     * @return true | false
     */
    public static boolean isEmpty(Object[] array)
    {
        if (array == null || array.length == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * return empty string if string object is null or empty
     * 
     * @param str
     * @return
     */
    public static String checkForEmptyString(String str)
    {
        if (str == null || StringUtils.isEmpty(str))
        {
            str = "";
        }
        return str;
    }

    /**
     * compares passed in firstDate is before the passed in secondDate or not.
     * 
     * @param firstDate
     * @param secondDate
     * 
     * @return true if firstDate is before secondDate
     */
    public static boolean isBefore(Date firstDate, Date secondDate)
    {
        if (firstDate != null && secondDate != null)
        {
            return firstDate.compareTo(secondDate) < 0;
        }
        return false;
    }

    /**
     * compares passed in firstDate is after the passed in secondDate or not.
     * 
     * @param firstDate
     * @param secondDate
     * 
     * @return true if firstDate is after secondDate
     */
    public static boolean isAfter(Date firstDate, Date secondDate)
    {
        if (firstDate != null && secondDate != null)
        {
            return firstDate.compareTo(secondDate) > 0;
        }
        return false;
    }

    /**
     * compares passed in firstNumber is less than the passed in secondNumber or
     * not.
     * 
     * @param firstNumber
     * @param secondNumber
     * 
     * @return true if firstNumber is less than secondNumber
     */
    public static boolean isLessThan(Float firstNumber, Float secondNumber)
    {
        if (firstNumber != null && secondNumber != null)
        {
            return firstNumber.compareTo(secondNumber) < 0;
        }
        return false;
    }

    /**
     * compares passed in firstNumber is less than the passed in secondNumber or
     * not.
     * 
     * @param firstNumber
     * @param secondNumber
     * 
     * @return true if firstNumber is less than secondNumber
     */
    public static boolean isLessThan(Double firstNumber, Double secondNumber)
    {
        if (firstNumber != null && secondNumber != null)
        {
            return firstNumber.compareTo(secondNumber) < 0;
        }
        return false;
    }

    /**
     * compares passed in firstNumber is greater than the passed in secondNumber
     * or not.
     * 
     * @param firstNumber
     * @param secondNumber
     * 
     * @return true if firstNumber is greater than secondNumber
     */
    public static boolean isGreaterThan(Float firstNumber, Float secondNumber)
    {
        if (firstNumber != null && secondNumber != null)
        {
            return firstNumber.compareTo(secondNumber) > 0;
        }
        return false;
    }

    /**
     * compares passed in firstNumber is greater than the passed in secondNumber
     * or not.
     * 
     * @param firstNumber
     * @param secondNumber
     * 
     * @return true if firstNumber is greater than secondNumber
     */
    public static boolean isGreaterThan(Double firstNumber, Double secondNumber)
    {
        if (firstNumber != null && secondNumber != null)
        {
            return firstNumber.compareTo(secondNumber) > 0;
        }
        return false;
    }

    /**
     * compares passed in firstNumber is less than the passed in secondNumber or
     * not.
     * 
     * @param firstNumber
     * @param secondNumber
     * 
     * @return true if firstNumber is less than secondNumber
     */
    public static boolean isLessThan(Integer firstNumber, Integer secondNumber)
    {
        if (firstNumber != null && secondNumber != null)
        {
            return firstNumber.compareTo(secondNumber) < 0;
        }
        return false;
    }

    /**
     * compares passed in firstNumber is greater than the passed in secondNumber
     * or not.
     * 
     * @param firstNumber
     * @param secondNumber
     * 
     * @return true if firstNumber is greater than secondNumber
     */
    public static boolean isGreaterThan(Integer firstNumber,
            Integer secondNumber)
    {
        if (firstNumber != null && secondNumber != null)
        {
            return firstNumber.compareTo(secondNumber) > 0;
        }
        return false;
    }

    /**
     * This method is for splitting List into equal number of chunks based on
     * input size and returns List of List object
     * 
     * @param bigList
     *            List Object of Generic Type
     * @param n
     *            size limit of each chunk
     * @return List<List<T>> List of List object with Generic Type
     */
    public static <T> List<List<T>> listChunks(List<T> bigList, int n)
    {
        List<List<T>> chunks = null;
        if (!isEmpty(bigList))
        {
            chunks = new ArrayList<List<T>>();
            for (int startIndex = 0; startIndex < bigList
                    .size(); startIndex += n)
            {
                List<T> chunk = bigList.subList(startIndex,
                        Math.min(bigList.size(), startIndex + n));
                chunks.add(chunk);
            }
        }
        return chunks;
    }

    /**
     * <p>
     * Copies the given array and adds the given element at the end of the new
     * array.
     * </p>
     * <p>
     * The new array contains the same elements of the input array plus the
     * given element in the last position. The component type of the new array
     * is the same as that of the input array.
     * </p>
     * 
     * <p>
     * If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element, unless the element
     * itself is null, in which case the return type is Object[]
     * </p>
     * 
     * @param <VT>
     *            the component type of the array
     * @param array
     *            the array to "add" the element to, may be {@code null}
     * @param element
     *            the object to add, may be {@code null}
     * @return A new array containing the existing elements plus the new element
     *         The returned array type will be that of the input array (unless
     *         null),in which case it will have the same type as the element. If
     *         both are null, an IllegalArgumentException is thrown.
     * @throws IllegalArgumentException
     *             if both arguments are null
     */
    public static <VT> Object[] addElement(Object[] array, VT element)
    {
        Object[] newArray = (Object[]) copyArrayGrow(array);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>
     * Copies the given array and adds the given element at the end of the new
     * array.
     * </p>
     * <p>
     * The new array contains the same elements of the input array plus the
     * given element in the last position. The component type of the new array
     * is the same as that of the input array.
     * </p>
     * 
     * <p>
     * If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element, unless the element
     * itself is null, in which case the return type is String[]
     * </p>
     * 
     * @param array
     *            the string array to "add" the element to, may be {@code null}
     * @param element
     *            the string to add, may be {@code null}
     * @return A new array containing the existing elements plus the new element
     *         The returned array type will be that of the input array (unless
     *         null),in which case it will have the same type as the element. If
     *         both are null, an IllegalArgumentException is thrown.
     * @throws IllegalArgumentException
     *             if both arguments are null
     */
    public static String[] addStrElement(String[] array, String element)
    {
        String[] newArray = copyStrArrayGrow(array);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * Returns a copy of the given array of size 1 greater than the argument.
     * The last value of the array is left to the default value.
     * 
     * @param array
     *            The array to copy, must not be {@code null}.
     * @param newArrayComponentType
     *            If {@code array} is {@code null}, create a size 1 array of
     *            this type.
     * @return A new copy of the array of size 1 greater than the input.
     */
    private static Object copyArrayGrow(Object array)
    {
        if (array != null)
        {
            int arrayLength = Array.getLength(array);
            Object newArray = Array.newInstance(Object.class, arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(Object.class, 1);
    }

    /**
     * Returns a copy of the given array of size 1 greater than the argument.
     * The last value of the array is left to the default value.
     * 
     * @param array
     *            The array to copy, must not be {@code null}.
     * @param newArrayComponentType
     *            If {@code array} is {@code null}, create a size 1 array of
     *            this type.
     * @return A new copy of the array of size 1 greater than the input.
     */
    private static String[] copyStrArrayGrow(String[] array)
    {
        if (array != null)
        {
            int arrayLength = Array.getLength(array);
            String[] newArray = (String[]) Array.newInstance(String.class,
                    arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return (String[]) Array.newInstance(String.class, 1);
    }

    /**
     * To get localeCode from REST caller, by default is English
     * 
     * @param localeCode
     * @return
     */
    public static String checkAndAssignDefaultLocale(String localeCode)
    {
        if (StringUtils.hasText(localeCode))
        {
            return localeCode;
        }
        return "en";
    }

    /**
     * Converts date to timestamp
     * 
     * @param date
     * @return
     */
    public static Timestamp convertDateToTimestamp(Date date)
    {
        if (date != null)
            return new Timestamp(date.getTime());
        return null;
    }

    /**
     * Convert timestamp to date
     * 
     * @param timestamp
     * @return
     */
    public static Date convertTimestampToDate(Timestamp timestamp)
    {
        if (timestamp != null)
            return new Date(timestamp.getTime());
        return null;
    }

    /**
     * Return is String not empty
     * 
     * @return
     */
    public static boolean validateStringInput(String value)
    {
        return (value != null && !value.isEmpty());
    }

    public static Long idCheckForZero(Long id)
    {
        if (id != null && id == 0)
        {
            return null;
        }
        return id;
    }

    public static Boolean idIsEmpty(Long id)
    {
        if (id != null && id == 0)
        {
            return true;
        }
        else if (id == null)
        {
            return true;
        }
        return false;
    }

    public static String getDateFormatFromDate(String formatStr, Date calendar)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(calendar.getTime());
    }

    // getDateDiff(date1,date2,TimeUnit.MINUTES);
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit)
    {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * @param jsName
     * @return
     */
    public static String removeSpaceInStr(String str)
    {
        String newString = str.replaceAll("\\s", "");
        return newString;
    }

    public static String replaceCharAt(String s, int pos, char c)
    {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    public static String deleteCharAt(String s, int pos)
    {
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(pos);
        String str = sb.toString();
        return str;
    }

    public static String removeNonAlphabet(String str)
    {

        return str.replaceAll("[^A-Za-z]", "");
    }

    public static boolean checkStringWithPattern(String value, String pattern)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        boolean b = m.find();
        return b;
    }

}
