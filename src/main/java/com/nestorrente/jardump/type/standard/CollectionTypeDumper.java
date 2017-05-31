package com.nestorrente.jardump.type.standard;

import java.io.IOException;
import java.util.Collection;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;

public class CollectionTypeDumper extends TypeDumper<Collection<?>> {

	@Override
	public void dump(DumpWriter writer, Collection<?> object) throws IOException {

		writer.beginObject(object.getClass());

		for(Object value : object) {
			writer.key("");
			writer.value(value);
		}

		writer.endObject();

	}

}
