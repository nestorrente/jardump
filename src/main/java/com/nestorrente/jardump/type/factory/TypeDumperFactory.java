package com.nestorrente.jardump.type.factory;

import java.lang.reflect.Type;

import com.nestorrente.jardump.type.TypeDumper;

public interface TypeDumperFactory {
	public TypeDumper<?> create(Type type);
}
