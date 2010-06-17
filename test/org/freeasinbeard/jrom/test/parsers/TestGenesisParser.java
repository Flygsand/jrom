package org.freeasinbeard.jrom.test.parsers;

import static org.freeasinbeard.jrom.test.asserts.Assert.assertPreferredParser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.parsers.GenesisParser;
import org.freeasinbeard.jrom.test.ROMReaderWrapper;
import org.junit.Test;


public class TestGenesisParser {

    @Test
    public void testRAW()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = ROMReaderWrapper.getInstance().read("data/sonic3.bin");
        assertSonic3(rom);
        assertEquals("efqxhj5yzm7lxqsmlkpyrhi3dnlyhgb", rom.getHash());
    }

    @Test
    public void testSMD()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = ROMReaderWrapper.getInstance().read("data/sonic3.smd");
        assertSonic3(rom);
        assertEquals("su0mn3g4hembr88f5tic2bh4qpz5z1i", rom.getHash());
    }

    @Test
    public void testMD()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = ROMReaderWrapper.getInstance().read("data/sonic3.md");
        assertSonic3(rom);
        assertEquals("7sq6g2dosfeaop3666yv842fokbbu4o", rom.getHash());
    }

    private void assertSonic3(ROM rom) {
        assertEquals("SONIC THE HEDGEHOG 3", rom.getName());
        assertEquals("Genesis", rom.getConsole());
        assertEquals(new Integer(2048), rom.getROMSize());
        assertEquals(new Integer(0), rom.getRAMSize());
        assertEquals("J", rom.getCountry());
        assertNull(rom.getRegion());
        assertEquals("MK-1079-00", rom.getVersion());
        assertPreferredParser(GenesisParser.class);
    }
}
