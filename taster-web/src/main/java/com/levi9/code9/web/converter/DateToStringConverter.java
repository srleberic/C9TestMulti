package com.levi9.code9.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Srle
 *
 */
public class DateToStringConverter implements Converter<Date, String> {
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public String convert(Date date) {
		return dateFormat.format(date);
	}

}
