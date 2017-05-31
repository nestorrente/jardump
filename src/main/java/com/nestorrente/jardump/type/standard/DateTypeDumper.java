package com.nestorrente.jardump.type.standard;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTypeDumper extends TypeDumper<Date> {

	private static final DateFormat format;

	static {
		format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@Override
	public void dump(DumpWriter writer, Date object) throws IOException {

		// MILLISECOND TIMESTAMP
//		writer.value(Date.class, Long.toString(object.getTime()));

		// ISO-8601
		writer.value(Date.class, format.format(object));

	}

}
