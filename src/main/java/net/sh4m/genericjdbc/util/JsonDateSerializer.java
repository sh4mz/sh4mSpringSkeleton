package net.sh4m.genericjdbc.util;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.sh4m.genericjdbc.AppConstants;


/**
 * Used to serialize Java.util.Date, which is not a common JSON type, so we have
 * to create a custom serialize method.
 * 
 * @see JsonSerializer
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

	private final ThreadSafeDateFormat dateFormat = new ThreadSafeDateFormat(
			AppConstants.MARSHALLER_OR_SERIALIZER_DATE_FORMAT);

	/**
	 * converts the date object to string format
	 * 
	 * @param date
	 * @param gen
	 * @param provider
	 * 
	 * @throws IOException
	 *             in case of errors
	 */
	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException {
		String formattedDate = dateFormat.format(date);

		gen.writeString(formattedDate);
	}
}
