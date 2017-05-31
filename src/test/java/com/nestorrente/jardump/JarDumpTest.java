package com.nestorrente.jardump;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.nestorrente.jardump.formatter.classname.ClassNameFormatters;

public class JarDumpTest {

	// TODO clean this class
	// TODO write some jUnit tests

	@SuppressWarnings("unused")
	private static final JarDump DEFAULT_CLASSNAME_JARDUMP = new JarDump();

	@SuppressWarnings("unused")
	private static final JarDump SIMPLE_CLASSNAME_JARDUMP = new JarDumpBuilder()
		.setClassNameFormatter(ClassNameFormatters.SIMPLE)
		.build();

	@SuppressWarnings("unused")
	private static final JarDump CANONICAL_CLASSNAME_JARDUMP = new JarDumpBuilder()
		.setClassNameFormatter(ClassNameFormatters.CANONICAL)
		.build();

	@SuppressWarnings("unused")
	private static final JarDump NO_PACKAGE_CLASSNAME_JARDUMP = new JarDumpBuilder()
		.setClassNameFormatter(ClassNameFormatters.NO_PACKAGE)
		.build();

	private static final JarDump NO_PACKAGE_CANONICAL_CLASSNAME_JARDUMP = new JarDumpBuilder()
		.setClassNameFormatter(ClassNameFormatters.NO_PACKAGE_CANONICAL)
		.build();

	@SuppressWarnings("unused")
	private static final JarDump LOWERCASE_CLASSNAME_JARDUMP = new JarDumpBuilder()
		.setClassNameFormatter(c -> c.getName().toLowerCase())
		.build();

	public static void main(String[] args) {

		Serializable extendedSerializable = new Serializable() {

			private static final long serialVersionUID = -2699090729034692481L;

			@SuppressWarnings("unused")
			private final int number = 5;

		};

		Map<String, Object> map = new HashMap<>();
		map.put("serializable", extendedSerializable);
		map.put("extendedList", new ArrayList<Object>() {

			private static final long serialVersionUID = -1338027664572077689L;

			{
				this.add(3);
				this.add("other");
			}

		});

		Object object = new Object();
		Object extendedObject = new Object() {};

		StringBuilder dumpStringBuilder = new StringBuilder();

		NO_PACKAGE_CANONICAL_CLASSNAME_JARDUMP.dump(Arrays.asList(object, extendedObject, map), dumpStringBuilder);

		System.out.println(dumpStringBuilder);
		// System.out.println(dumpStringBuilder.toString().startsWith("java.util.Arrays"));

	}

}
