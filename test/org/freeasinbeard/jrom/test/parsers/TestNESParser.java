package org.freeasinbeard.jrom.test.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.test.ROMReaderWrapper;
import org.junit.Test;

public class TestNESParser {

    @Test
    public void testiNES()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = ROMReaderWrapper.getInstance().read("data/smb");
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
