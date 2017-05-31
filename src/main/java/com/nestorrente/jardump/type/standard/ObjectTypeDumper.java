package com.nestorrente.jardump.type.standard;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.nestorrente.jardump.DumpWriter;
import com.nestorrente.jardump.type.TypeDumper;

public class ObjectTypeDumper extends TypeDumper<Object> {

	@Override
	public void dump(DumpWriter writer, Object object) throws IOException {

		writer.beginObject(object.getClass());

		for(Field field : FieldUtils.getAllFieldsList(object.getClass())) {

			int modifiers = field.getModifiers();

			if(Modifier.isStatic(modifiers)) {
				continue;
			}

			StringBuilder keyBuilder = new StringBuilder();

			String modifiersString = Modifier.toString(modifiers);

			if(!modifiersString.isEmpty()) {
				keyBuilder.append(modifiersString).append(' ');
			}

			keyBuilder.append(field.getType().getName()).append(" \"").append(field.getName()).append('"');

			writer.key(keyBuilder.toString());

			boolean accesible = field.isAccessible();
			field.setAccessible(true);

			try {

				Class<?> type = field.getType();
				// Object value = getValue(field, object);

				if(type.isPrimitive()) {

					if(type == byte.class) {
						writer.value(field.getByte(object));
					} else if(type == short.class) {
						writer.value(field.getShort(object));
					} else if(type == int.class) {
						writer.value(field.getInt(object));
					} else if(type == long.class) {
						writer.value(field.getLong(object));
					} else if(type == float.class) {
						writer.value(field.getFloat(object));
					} else if(type == double.class) {
						writer.value(field.getDouble(object));
					} else if(type == char.class) {
						writer.value(field.getChar(object));
					} else if(type == boolean.class) {
						writer.value(field.getBoolean(object));
					} else {
						// Será capturada por el catch de más abajo, pero tiene sentido que así sea.
						// La excepción dirá que no se ha podido escribir el valor del campo,
						// y su causa será que no se conoce su tipo primitivo.
						throw new IllegalArgumentException("Unknown primitive type: " + type);
					}

				} else {
					writer.value(field.get(object));
				}

			} catch(IllegalArgumentException | IllegalAccessException ex) {
				throw new IOException("Cannot write field value", ex);
			} finally {
				field.setAccessible(accesible);
			}

		}

		writer.endObject();

	}

}
