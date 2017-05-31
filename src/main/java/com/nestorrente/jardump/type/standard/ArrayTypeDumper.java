package com.nestorrente.jardump.type.standard;

import java.io.IOException;
import java.lang.reflect.Array;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;

public class ArrayTypeDumper extends TypeDumper<Object> {

	@Override
	public void dump(DumpWriter writer, Object object) throws IOException {

		writer.beginObject(object.getClass());

		int length = Array.getLength(object);

		Class<?> componentType = object.getClass().getComponentType();
		boolean primitiveComponent = componentType.isPrimitive();

		for(int i = 0; i < length; ++i) {

			writer.key(i);

			if(primitiveComponent) {

				if(componentType == byte.class) {
					writer.value(Array.getByte(object, i));
				} else if(componentType == short.class) {
					writer.value(Array.getShort(object, i));
				} else if(componentType == int.class) {
					writer.value(Array.getInt(object, i));
				} else if(componentType == long.class) {
					writer.value(Array.getLong(object, i));
				} else if(componentType == float.class) {
					writer.value(Array.getFloat(object, i));
				} else if(componentType == double.class) {
					writer.value(Array.getDouble(object, i));
				} else if(componentType == char.class) {
					writer.value(Array.getChar(object, i));
				} else if(componentType == boolean.class) {
					writer.value(Array.getBoolean(object, i));
				} else {
					throw new IllegalArgumentException("Unknown primitive type: " + componentType);
				}

			} else {
				writer.value(Array.get(object, i));
			}

		}

		writer.endObject();

	}

}
