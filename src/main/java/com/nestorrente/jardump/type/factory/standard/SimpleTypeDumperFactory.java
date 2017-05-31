package com.nestorrente.jardump.type.factory.standard;

import java.lang.reflect.Type;

import com.nestorrente.jardump.type.TypeDumper;
import com.nestorrente.jardump.type.factory.TypeDumperFactory;

public class SimpleTypeDumperFactory implements TypeDumperFactory {

	private final Type type;
	private final TypeDumper<?> dumper;

	public SimpleTypeDumperFactory(Type type, TypeDumper<?> dumper) {
		this.type = type;
		this.dumper = dumper;
	}

	@Override
	public TypeDumper<?> create(Type type) {
		return type == this.type ? this.dumper : null;
	}

}
