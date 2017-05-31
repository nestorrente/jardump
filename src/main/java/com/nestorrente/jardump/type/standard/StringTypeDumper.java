package com.nestorrente.jardump.type.standard;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;
import com.nestorrente.jardump.internal.StringUtils;
import java.io.IOException;

public class StringTypeDumper extends TypeDumper<String> {

	@Override
	public void dump(DumpWriter writer, String object) throws IOException {
		writer.value(object.getClass(), StringUtils.escape(object));
	}

}
