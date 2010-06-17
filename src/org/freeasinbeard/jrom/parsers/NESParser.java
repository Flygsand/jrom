package org.freeasinbeard.jrom.parsers;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.Util;

public class NESParser implements Parser {

    @Override
    public ROM parse(byte[] data) {
        if (!"NES\u001a".equals(Util.str(data, 0x0, 4)))
            return null;

        String name = null;
        if (data.length % 8192 != 16)
            name = Util.str(data, data.length-128, 128).trim();

        ROM rom = new ROM(name, "NES");
        rom.setROMSize(16*data[0x4] + 8*data[0x5]);
        rom.setRAMSize(8*data[0x8]);
        return rom;
    }

}
