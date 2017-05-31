package com.nestorrente.jardump;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nestorrente.jardump.formatter.classname.ClassNameFormatter;
import com.nestorrente.jardump.formatter.classname.ClassNameFormatters;
import com.nestorrente.jardump.type.factory.TypeDumperFactory;
import com.nestorrente.jardump.type.factory.standard.HierarchyTypeDumperFactory;
import com.nestorrente.jardump.type.standard.ArrayTypeDumper;
import com.nestorrente.jardump.type.standard.CollectionTypeDumper;
import com.nestorrente.jardump.type.standard.DateTypeDumper;
import com.nestorrente.jardump.type.standard.ListTypeDumper;
import com.nestorrente.jardump.type.standard.MapTypeDumper;
import com.nestorrente.jardump.type.standard.ObjectTypeDumper;
import com.nestorrente.jardump.type.standard.SimpleObjectTypeDumper;
import com.nestorrente.jardump.type.standard.StringTypeDumper;

final class JarDumpDefaults {

	private static final ClassNameFormatter CLASS_NAME_FORMATTER = ClassNameFormatters.DEFAULT;
	private static final String INDENT = "    ";
	private static final String NULL_TEXT = "(null)";
	private static final List<TypeDumperFactory> TYPE_DUMPER_FACTORIES = new ArrayList<>();

	static {

		/* Classes */

		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Object.class, new ObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Map.class, new MapTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Collection.class, new CollectionTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(List.class, new ListTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Date.class, new DateTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(String.class, new StringTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Number.class, new SimpleObjectTypeDumper()));

		/* Primitive types' boxing classes */

		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Byte.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Short.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Integer.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Long.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Float.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Double.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Character.class, new SimpleObjectTypeDumper()));
		TYPE_DUMPER_FACTORIES.add(new HierarchyTypeDumperFactory(Boolean.class, new SimpleObjectTypeDumper()));

		/* Primitive arrays */

		TYPE_DUMPER_FACTORIES.add(ArrayTypeDumper.FACTORY);

	}

	public static ClassNameFormatter getClassNameFormatter() {
		return CLASS_NAME_FORMATTER;
	}

	public static String getIndent() {
		return INDENT;
	}

	public static String getNullText() {
		return NULL_TEXT;
	}

	public static List<TypeDumperFactory> getTypeDumperFactories() {
		return new ArrayList<>(TYPE_DUMPER_FACTORIES);
	}

}
