/**
 * 
 */
package com.levi9.code9.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Srle
 *
 */
public class StringToDateConverter implements Converter<String, Date> {
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public Date convert(String dateString) {
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
