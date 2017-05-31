package com.nestorrente.jardump.formatter.classname;

public final class ClassNameFormatters {

	// TODO document this formatters

	public static final ClassNameFormatter DEFAULT = Class::getName;

	public static final ClassNameFormatter CANONICAL = Class::getCanonicalName;

	public static final ClassNameFormatter SIMPLE = Class::getSimpleName;

	public static final ClassNameFormatter NO_PACKAGE = new NoPackageClassNameFormatter(Class::getName);

	public static final ClassNameFormatter NO_PACKAGE_CANONICAL = new NoPackageClassNameFormatter(Class::getCanonicalName);

}
