package com.nestorrente.jardump;

import java.util.List;

import org.apache.commons.lang3.builder.Builder;

import com.nestorrente.jardump.formatter.classname.ClassNameFormatter;
import com.nestorrente.jardump.type.TypeDumper;
import com.nestorrente.jardump.type.factory.TypeDumperFactory;
import com.nestorrente.jardump.type.factory.standard.HierarchyTypeDumperFactory;
import com.nestorrente.jardump.type.factory.standard.SimpleTypeDumperFactory;

public class JarDumpBuilder implements Builder<JarDump> {

	private ClassNameFormatter classNameFormatter = JarDumpDefaults.getClassNameFormatter();
	private String indent = JarDumpDefaults.getIndent();
	private String nullText = JarDumpDefaults.getNullText();
	private final List<TypeDumperFactory> typeDumperFactories = JarDumpDefaults.getTypeDumperFactories();

	public JarDumpBuilder setClassNameFormatter(ClassNameFormatter classNameFormatter) {
		this.classNameFormatter = classNameFormatter;
		return this;
	}

	public JarDumpBuilder setIndent(String indent) {
		this.indent = indent;
		return this;
	}

	public JarDumpBuilder setNullText(String nullText) {
		this.nullText = nullText;
		return this;
	}

	public <T> JarDumpBuilder registerTypeDumper(Class<? extends T> type, TypeDumper<T> dumper) {
		this.typeDumperFactories.add(new SimpleTypeDumperFactory(type, dumper));
		return this;
	}

	public <T> JarDumpBuilder registerHierarchyTypeDumper(Class<? extends T> type, TypeDumper<T> dumper) {
		this.typeDumperFactories.add(new HierarchyTypeDumperFactory(type, dumper));
		return this;
	}

	public <T> JarDumpBuilder registerTypeDumperFactory(TypeDumperFactory factory) {
		this.typeDumperFactories.add(factory);
		return this;
	}

	/**
	 * Use {@link #build()} method instead.
	 */
	@Deprecated
	public JarDump create() {
		return this.build();
	}

	@Override
	public JarDump build() {
		return new JarDump(this.classNameFormatter, this.indent, this.nullText, this.typeDumperFactories);
	}

}
