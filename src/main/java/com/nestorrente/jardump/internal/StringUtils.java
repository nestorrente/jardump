package com.nestorrente.jardump.internal;

import java.util.Locale;

public class StringUtils {

	private static final char[] HEX_DIGITS = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	private static void appendEscaped(StringBuilder out, char c) {
		switch(c) {
			case '\b':
				out.append('\\').append('b');
				break;
			case '\n':
				out.append('\\').append('n');
				break;
			case '\t':
				out.append('\\').append('t');
				break;
			case '\f':
				out.append('\\').append('f');
				break;
			case '\r':
				out.append('\\').append('r');
				break;
			case '\"':
				out.append('\\').append('\"');
				break;
			case '\\':
				out.append('\\').append('\\');
				break;
			default:
				// TODO: Handle potential + sign per various Unicode escape implementations
				if (c > 0xffff) {
					out.append('\\').append('u')
						.append(Integer.toHexString(c).toUpperCase(Locale.ENGLISH));
				} else if(c < 32 || c > 0x7f) {
					out.append('\\').append('u')
						.append(HEX_DIGITS[(c >> 12) & 15])
						.append(HEX_DIGITS[(c >> 8) & 15])
						.append(HEX_DIGITS[(c >> 4) & 15])
						.append(HEX_DIGITS[(c) & 15]);
				} else {
					out.append(c);
				}
		}
	}

	public static String escape(String input) {

		int length = input.length();

		if(length == 0) return input;

		StringBuilder escapedBuilder = new StringBuilder();

		for(int i=0; i<length; ++i) {
			appendEscaped(escapedBuilder, input.charAt(i));
		}

		return escapedBuilder.toString();

	}

}
