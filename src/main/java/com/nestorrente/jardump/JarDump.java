package com.nestorrente.jardump;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import com.nestorrente.jardump.exception.DumpException;
import com.nestorrente.jardump.formatter.classname.ClassNameFormatter;
import com.nestorrente.jardump.type.TypeDumper;
import com.nestorrente.jardump.type.factory.TypeDumperFactory;

public class JarDump {

	public static final JarDump INSTANCE = new JarDump();

	private final ClassNameFormatter classNameFormatter;
	private final String indent;
	private final String nullText;
	private final List<TypeDumperFactory> typeDumperFactories;

	public JarDump() {
		this(JarDumpDefaults.getClassNameFormatter(), JarDumpDefaults.getIndent(), JarDumpDefaults.getNullText(), JarDumpDefaults.getTypeDumperFactories());
	}

	public JarDump(ClassNameFormatter classNameFormatter, String indent, String nullText, List<TypeDumperFactory> typeDumperFactories) {
		this.classNameFormatter = classNameFormatter;
		this.indent = indent;
		this.nullText = nullText;
		this.typeDumperFactories = typeDumperFactories;

		// En la lista que se recibe como argumento, por cuestiones de diseño,
		// se pide que los dumpers globales hayan sido insertados antes que los
		// específicos. No obstante, a la hora de recorrer dicha lista, nos
		// interesa obtener primero los dumpers más específicos.
		// Por ello, la mejor opción, es guardar la lista en orden inverso.
		Collections.reverse(this.typeDumperFactories);
	}

	private TypeDumper<?> findTypeDumper(Type type) {

		if(type != null) {

			TypeDumper<?> dumper;

			for(TypeDumperFactory factory : this.typeDumperFactories) {
				if((dumper = factory.create(type)) != null) {
					return dumper;
				}
			}

		}

		return null;

	}

	void dump(Object obj, DumpWriter writer) throws DumpException {

		try {

			if(obj == null) {

				writer.nullValue();

			} else {

				Class<?> type = obj.getClass();

				@SuppressWarnings("unchecked")
				TypeDumper<Object> dumper = (TypeDumper<Object>) this.findTypeDumper(type);

				if(dumper == null) {
					throw new IllegalArgumentException(this.getClass().getSimpleName() + " cannot handle " + type);
				}

				dumper.dump(writer, obj);

			}

		} catch(IOException ioex) {
			throw new DumpException(ioex);
		}

	}

	public void dump(Object obj, Appendable out) throws DumpException {
		this.dump(obj, new DumpWriter(this, out, this.classNameFormatter, this.indent, this.nullText));
	}

	public void dump(Object obj) throws DumpException {
		this.dump(obj, System.out);
		System.out.println();
	}

}
