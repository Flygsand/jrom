package org.freeasinbeard.jrom.parsers;

import org.freeasinbeard.jrom.ROM;

public interface Parser {
	public ROM parse(byte[] data);
}
