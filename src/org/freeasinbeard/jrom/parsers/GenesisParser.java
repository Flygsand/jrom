package org.freeasinbeard.jrom.parsers;

import java.util.Arrays;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.Util;

public class GenesisParser implements Parser {

    @Override
    public ROM parse(byte[] data) {
        if (!containsValidHeader(data)) {
            if (data.length % 16384 != 0) {
                data = Arrays.copyOfRange(data, 512, data.length-1);
                deinterleaveBytes(data, 0, 16384);
            } else {
                data = Arrays.copyOf(data, data.length);
                deinterleaveBytes(data, 0, data.length);
            }

            if (!containsValidHeader(data))
                return null;
        }

        long romStart = Util.uint32(data, 0x1a0);
        long romEnd = Util.uint32(data, 0x1a4);
        long ramStart = Util.uint32(data, 0x1b4);
        long ramEnd = Util.uint32(data, 0x1b8);

        ROM rom = new ROM(Util.str(data, 0x150, 48).replaceAll(" {2,}", " ").trim(), "Genesis");
        rom.setVersion(Util.str(data, 0x182, 12).replaceAll(" ", "").trim());
        rom.setROMSize((int) (romEnd-romStart+1)/1024);
        rom.setRAMSize((int) (ramEnd-ramStart+1)/1024);
        rom.setCountry(Util.str(data, 0x1f0, 16).trim());

        return rom;
    }

    private static boolean containsValidHeader(byte[] data) {
        String console = Util.str(data, 0x100, 16).trim();
        return "SEGA MEGA DRIVE".equals(console) || "SEGA GENESIS".equals(console);
    }

    private static void deinterleaveBytes(byte[] data, int start, int len) {
        byte[] deinterleaved = new byte[len];
        int middle = len / 2;

        for (int i = 0; i < middle; i++)
            deinterleaved[i*2+1] = data[i];

        for (int i = middle; i < len; i++)
            deinterleaved[(i-middle)*2] = data[i];

        for (int i = 0; i < len; i++)
            data[i] = deinterleaved[i];
    }

}
