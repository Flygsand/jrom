package org.freeasinbeard.jrom.test.parsers;

import static org.freeasinbeard.jrom.test.asserts.Assert.assertPreferredParser;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.parsers.SNESParser;
import org.freeasinbeard.jrom.test.ROMReaderWrapper;
import org.junit.Test;

public class TestSNESParser {

    @Test
    public void testLoROM()
    throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = ROMReaderWrapper.getInstance().read("data/sc4.smc");
        assertEquals("SUPER CASTLEVANIA 4", rom.getName());
        assertEquals("SNES", rom.getConsole());
        assertEquals("9yq4dozvc18wemz2vekczk78pd3olyx", rom.getHash());
        assertEquals(new Integer(1024), rom.getROMSize());
        assertEquals(new Integer(0), rom.getRAMSize());
        assertEquals("Australia, Europe, Oceania and Asia", rom.getCountry());
        assertEquals("PAL", rom.getRegion());
        assertEquals("1.0", rom.getVersion());
        assertPreferredParser(SNESParser.class);
    }

    @Test
    public void testHiROM()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = ROMReaderWrapper.getInstance().read("data/smk.SMC");
        assertEquals("SUPER MARIO KART", rom.getName());
        assertEquals("SNES", rom.getConsole());
        assertEquals("8e9k4vm98421eit3n8qvitxhmdc0tob", rom.getHash());
        assertEquals(new Integer(512), rom.getROMSize());
        assertEquals(new Integer(2), rom.getRAMSize());
        assertEquals("USA", rom.getCountry());
        assertEquals("NTSC", rom.getRegion());
        assertEquals("1.0", rom.getVersion());
        assertPreferredParser(SNESParser.class);
    }
}
