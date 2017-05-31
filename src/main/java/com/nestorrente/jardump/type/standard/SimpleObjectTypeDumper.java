package com.nestorrente.jardump.type.standard;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;
import java.io.IOException;

public class SimpleObjectTypeDumper extends TypeDumper<Object> {

	@Override
	public void dump(DumpWriter writer, Object object) throws IOException {
		writer.value(object.getClass(), object.toString());
	}

}
