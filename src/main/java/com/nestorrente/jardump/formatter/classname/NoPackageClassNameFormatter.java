package com.nestorrente.jardump.formatter.classname;

import java.util.function.Function;

public class NoPackageClassNameFormatter implements ClassNameFormatter {

	@Override
	public String format(Class<?> clazz) {

		String packageName = clazz.getPackage().getName();

		String className = clazz.getName();

		return className.substring(packageName.length() + 1);

	}

}
