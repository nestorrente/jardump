package com.nestorrente.jardump.type.factory.standard;

import java.lang.reflect.Type;

import com.nestorrente.jardump.type.TypeDumper;
import com.nestorrente.jardump.type.factory.TypeDumperFactory;

public class ArrayTypeDumperFactory implements TypeDumperFactory {

	private final TypeDumper<?> dumper;

	public ArrayTypeDumperFactory(TypeDumper<?> dumper) {
		this.dumper = dumper;
	}

	@Override
	public TypeDumper<?> create(Type type) {
		return type instanceof Class && ((Class<?>) type).isArray() ? this.dumper : null;
	}

}
