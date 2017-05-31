package com.nestorrente.jardump.formatter.classname;

public final class ClassNameFormatters {

	public static final ClassNameFormatter DEFAULT = Class::getName;

	public static final ClassNameFormatter CANONICAL = Class::getCanonicalName;

	public static final ClassNameFormatter SIMPLE = Class::getSimpleName;

	// TODO refactor and clean this two formatters:

	public static final ClassNameFormatter NO_PACKAGE = c -> {

		Package pkg = c.getPackage();

		return pkg == null ? c.getName() : c.getName().substring(pkg.getName().length() + 1);

	};

	public static final ClassNameFormatter NO_PACKAGE_CANONICAL = c -> {

		Package pkg = c.getPackage();

		return pkg == null ? c.getCanonicalName() : c.getCanonicalName().substring(pkg.getName().length() + 1);

	};

}
