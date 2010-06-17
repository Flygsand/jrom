package org.freeasinbeard.jrom.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.freeasinbeard.jrom.JROM;
import org.freeasinbeard.jrom.ROM;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestGenesisParser {

    @Test
    public void testRAW()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = JROM.read("data/sonic3.bin");
        assertEquals("SONIC THE HEDGEHOG 3", rom.getName());
        assertEquals("Genesis", rom.getConsole());
        assertEquals(new Integer(2048), rom.getROMSize());
        assertEquals(new Integer(0), rom.getRAMSize());
        assertEquals("J", rom.getCountry());
        assertNull(rom.getRegion());
        assertEquals("MK-1079-00", rom.getVersion());
        assertEquals("efqxhj5yzm7lxqsmlkpyrhi3dnlyhgb", rom.getHash());
    }

    @Test
    public void testSMD()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = JROM.read("data/sonic3.smd");
        assertEquals("SONIC THE HEDGEHOG 3", rom.getName());
        assertEquals("Genesis", rom.getConsole());
        assertEquals(new Integer(2048), rom.getROMSize());
        assertEquals(new Integer(0), rom.getRAMSize());
        assertEquals("J", rom.getCountry());
        assertNull(rom.getRegion());
        assertEquals("MK-1079-00", rom.getVersion());
        assertEquals("su0mn3g4hembr88f5tic2bh4qpz5z1i", rom.getHash());
    }

    @Test
    public void testMD()
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        ROM rom = JROM.read("data/sonic3.md");
        assertEquals("SONIC THE HEDGEHOG 3", rom.getName());
        assertEquals("Genesis", rom.getConsole());
        assertEquals(new Integer(2048), rom.getROMSize());
        assertEquals(new Integer(0), rom.getRAMSize());
        assertEquals("J", rom.getCountry());
        assertNull(rom.getRegion());
        assertEquals("MK-1079-00", rom.getVersion());
        assertEquals("7sq6g2dosfeaop3666yv842fokbbu4o", rom.getHash());
    }
}
