package com.nestorrente.jardump.type;

import com.nestorrente.jardump.DumpWriter;
import java.io.IOException;

public abstract class TypeDumper<T> {
	public abstract void dump(DumpWriter writer, T object) throws IOException;
}
