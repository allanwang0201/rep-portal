package au.com.jaycar.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	
	String toAustraliaFormat(Date date)
	{
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String s = df.format(new Date());
		System.out.println(s); //15/10/2013
		return s;
	}
}
