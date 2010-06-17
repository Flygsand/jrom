package org.freeasinbeard.jrom.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.freeasinbeard.jrom.JROM;
import org.freeasinbeard.jrom.ROM;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestNESParser {
	@Test
	public void testiNES() 
		throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		ROM rom = JROM.read("data/smb");
		assertEquals("Super Mario Bros  (Vimm's Lair - http://vimm.net)", rom.getName());
		assertEquals("NES", rom.getConsole());
		assertEquals("e0jnqxnr8eazihegh0w3v15ssut3sss", rom.getHash());
		assertEquals(new Integer(40), rom.getROMSize());
		assertEquals(new Integer(0), rom.getRAMSize());
		assertNull(rom.getCountry());
		assertNull(rom.getRegion());
		assertNull(rom.getVersion());
	}
}
