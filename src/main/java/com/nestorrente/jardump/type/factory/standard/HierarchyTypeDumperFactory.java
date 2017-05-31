package com.nestorrente.jardump.type.factory.standard;

import java.lang.reflect.Type;

import com.nestorrente.jardump.type.TypeDumper;
import com.nestorrente.jardump.type.factory.TypeDumperFactory;

public class HierarchyTypeDumperFactory implements TypeDumperFactory {

	private final Class<?> type;
	private final TypeDumper<?> dumper;

	public HierarchyTypeDumperFactory(Class<?> type, TypeDumper<?> dumper) {
		this.type = type;
		this.dumper = dumper;
	}

	@Override
	public TypeDumper<?> create(Type type) {
		return type instanceof Class && this.type.isAssignableFrom((Class<?>) type) ? this.dumper : null;
	}

}
