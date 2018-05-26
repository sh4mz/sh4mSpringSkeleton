package net.sh4m.genericjdbc.util;

import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Thread-safe version of java.text.DateFormat. It can be declared as a static
 * final variable.
 * 
 * <code>
 * private static final ThreadSafeDateFormat DATE_FORMAT = new ThreadSafeDateFormat( DATE_PATTERN );
 * </code>
 * 
 */
public class ThreadSafeDateFormat extends DateFormat {

	private static final long serialVersionUID = 1L;

	private final String dateFormat;

	private final ThreadLocal<SoftReference<DateFormat>> formatCache = new ThreadLocal<SoftReference<DateFormat>>() {
		@Override
		public SoftReference<DateFormat> get() {
			SoftReference<DateFormat> softRef = super.get();
			if (softRef == null || softRef.get() == null) {
				softRef = new SoftReference<DateFormat>(new SimpleDateFormat(
						dateFormat));

				super.set(softRef);
			}
			return softRef;
		}
	};

	/**
	 * Constructs a ThreadSafeDateFormat using the given pattern.
	 */
	public ThreadSafeDateFormat(String sDateFormat) {
		dateFormat = sDateFormat;
	}

	/**
	 * Gets a copy of the date format from the thread local.
	 * 
	 * @return date format
	 */
	private DateFormat getDateFormat() {
		return formatCache.get().get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition fieldPosition) {
		return getDateFormat().format(date, toAppendTo, fieldPosition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date parse(String source, ParsePosition pos) {
		return getDateFormat().parse(source, pos);
	}

}
