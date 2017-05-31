package com.nestorrente.jardump.type.standard;

import java.io.IOException;
import java.util.List;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;

public class ListTypeDumper extends TypeDumper<List<?>> {

	@Override
	public void dump(DumpWriter writer, List<?> object) throws IOException {

		writer.beginObject(object.getClass());

		int total = object.size();

		for(int i = 0; i < total; ++i) {
			writer.key(i);
			writer.value(object.get(i));
		}

		writer.endObject();

	}

}
