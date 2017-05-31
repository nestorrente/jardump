package com.nestorrente.jardump;

import java.io.IOException;
import java.lang.reflect.Type;

import com.nestorrente.jardump.formatter.classname.ClassNameFormatter;
import com.nestorrente.jardump.internal.SimpleStack;

public class DumpWriter {

	private static enum ExpectedToken {
		VALUE_OR_BEGIN, // waiting value or beginObject (an object is also a value)
		KEY_OR_END, // waiting for key or endObject
	}

	private final JarDump owner;
	private final Appendable out;
	private final ClassNameFormatter classNameFormatter;
	private final String indent;
	private final String nullText;

	private final StringBuilder outStringBuilder;
	private final SimpleStack<ExpectedToken> expectedTokens;

	DumpWriter(JarDump owner, Appendable out, ClassNameFormatter classNameFormatter, String indent, String nullText) {

		this.owner = owner;
		this.out = out;
		this.classNameFormatter = classNameFormatter;
		this.indent = indent;
		this.nullText = nullText;

		this.outStringBuilder = new StringBuilder();
		this.expectedTokens = new SimpleStack<>();
		this.expectedTokens.push(ExpectedToken.VALUE_OR_BEGIN);

	}

	private void appendIndent(int indentLevel) throws IOException {

		this.outStringBuilder.append('\n');

		if(indentLevel > 0) {

			for(int i = 0; i < indentLevel; ++i) {
				this.outStringBuilder.append(this.indent);
			}

		}

	}

	private void beforeBeginObject() throws IOException {
		if(this.expectedTokens.isEmpty() || this.expectedTokens.peek() != ExpectedToken.VALUE_OR_BEGIN) {
			throw new IllegalStateException("Nesting problem");
		}
	}

	private void afterBeginObject() throws IOException {
		this.expectedTokens.pop(); // Se hace un pop porque ya no se espera un inicio de objeto (pues éste ya se ha empezado a escribir)
		this.expectedTokens.push(ExpectedToken.KEY_OR_END); // Se hace push porque se esperan las claves y valores del objeto actual
	}

	private void beforeEndObject() throws IOException {

		if(this.expectedTokens.isEmpty() || this.expectedTokens.peek() != ExpectedToken.KEY_OR_END) {
			throw new IllegalStateException("Nesting problem");
		}

		this.appendIndent(this.expectedTokens.size() - 1);

	}

	private void afterEndObject() throws IOException {

		this.expectedTokens.pop(); // Se hace pop porque ya no se espera el final del objeto
		// No se hace un push porque se ha terminado el objeto actual

		if(this.expectedTokens.isEmpty()) {
			this.out.append(this.outStringBuilder);
		}

	}

	private void beforeKey() throws IOException {

		if(this.expectedTokens.isEmpty() || this.expectedTokens.peek() != ExpectedToken.KEY_OR_END) {
			throw new IllegalStateException("Nesting problem");
		}

		this.appendIndent(this.expectedTokens.size());

	}

	private void afterKey() throws IOException {
		// No se hace un pop porque todavía no ha terminado el objeto actual
		this.expectedTokens.push(ExpectedToken.VALUE_OR_BEGIN); // Se hace push porque se espera el valor de la clave actual
	}

	private void beforeValue() throws IOException {
		if(this.expectedTokens.isEmpty() || this.expectedTokens.peek() != ExpectedToken.VALUE_OR_BEGIN) {
			throw new IllegalStateException("Nesting problem");
		}
	}

	private void afterValue() throws IOException {

		this.expectedTokens.pop(); // Se hace pop porque ya no se espera el valor
		// No se hace un push porque un valor no tiene propiedades

		if(this.expectedTokens.isEmpty()) {
			this.out.append(this.outStringBuilder);
		}

	}

	private String formatClassName(Class<?> type) {

		if(type.isAnonymousClass()) {

			// Anonymous classes can extend one class or implement only one interface

			Class<?>[] interfaces = type.getInterfaces();

			if(interfaces.length > 0) {
				return "(?) implements " + this.classNameFormatter.format(interfaces[0]);
			}

			return "(?) extends " + this.classNameFormatter.format(type.getSuperclass());

		}

		return this.classNameFormatter.format(type);

	}

	public DumpWriter beginObject(Class<?> type) throws IOException {

		this.beforeBeginObject();

		this.outStringBuilder.append(this.formatClassName(type)).append(" {");

		this.afterBeginObject();

		return this;

	}

	public DumpWriter endObject() throws IOException {

		this.beforeEndObject();

		this.outStringBuilder.append('}');

		this.afterEndObject();

		return this;

	}

	public DumpWriter key(String key) throws IOException {

		this.beforeKey();

		this.outStringBuilder.append("[").append(key == null ? this.nullText : key).append("] => ");

		this.afterKey();

		return this;

	}

	public DumpWriter key(int index) throws IOException {
		return this.key(Integer.toString(index));
	}

	// TODO deprecate?
	// @Deprecated
	public DumpWriter complexKey(Object object) throws IOException {

		StringBuilder keyBuilder = new StringBuilder();
		this.owner.dump(object, keyBuilder);

		String key = keyBuilder.toString();
		int keyIndentLevel = this.expectedTokens.size();

		if(keyIndentLevel > 0) {

			StringBuilder indentBuilder = new StringBuilder().append('\n');

			for(int i = 0; i < keyIndentLevel; ++i) {
				indentBuilder.append(this.indent);
			}

			key = key.replaceAll("\n", indentBuilder.toString());

		}

		return this.key(key);
	}

	public DumpWriter nullValue() throws IOException {

		this.beforeValue();

		this.outStringBuilder.append(this.nullText);

		this.afterValue();

		return this;

	}

	public DumpWriter value(Type type, CharSequence textualRepresentation) throws IOException {

		this.beforeValue();

		this.outStringBuilder.append(type instanceof Class ? this.formatClassName((Class<?>) type) : type.getTypeName())
			.append(" \"")
			.append(textualRepresentation)
			.append('"');

		this.afterValue();

		return this;

	}

	public DumpWriter value(byte value) throws IOException {
		return this.value(byte.class, Byte.toString(value));
	}

	public DumpWriter value(short value) throws IOException {
		return this.value(short.class, Short.toString(value));
	}

	public DumpWriter value(int value) throws IOException {
		return this.value(int.class, Integer.toString(value));
	}

	public DumpWriter value(long value) throws IOException {
		return this.value(long.class, Long.toString(value));
	}

	public DumpWriter value(float value) throws IOException {
		return this.value(float.class, Float.toString(value));
	}

	public DumpWriter value(double value) throws IOException {
		return this.value(double.class, Double.toString(value));
	}

	public DumpWriter value(char value) throws IOException {
		return this.value(char.class, Character.toString(value));
	}

	public DumpWriter value(boolean value) throws IOException {
		return this.value(boolean.class, Boolean.toString(value));
	}

	public DumpWriter value(Object object) throws IOException {

		this.owner.dump(object, this);

		return this;

	}

}
