package com.nestorrente.jardump.type.standard;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;

public class MapTypeDumper extends TypeDumper<Map<?, ?>> {

	@Override
	public void dump(DumpWriter writer, Map<?, ?> object) throws IOException {

		writer.beginObject(object.getClass());

		Set<? extends Entry<?, ?>> entries = object.entrySet();

		for(Entry<?, ?> entry : entries) {
			writer.complexKey(entry.getKey());
			writer.value(entry.getValue());
		}

		writer.endObject();

	}

}
