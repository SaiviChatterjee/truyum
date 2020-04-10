package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
public static Date convertToDate(String date) throws ParseException {
	SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
	return parser.parse(date);
}
}
