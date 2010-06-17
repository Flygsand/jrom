package org.freeasinbeard.jrom.parsers;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.Util;

public class SNESParser implements Parser {
	
	private static final String[] countries = new String[] {
		"Japan", "USA", "Australia, Europe, Oceania and Asia",
        "Sweden", "Finland", "Denmark", "France", "Holland",
        "Spain", "Germany, Austria and Switzerland", "Italy",
        "Hong Kong and China", "Indonesia", "Korea"
	};

	@Override
	public ROM parse(byte[] data) {
		int hdr = findHeaderAddress(data);
		
		if (hdr == -1)
			return null;
		
		ROM rom = new ROM(Util.str(data, hdr, 21).trim(), "SNES");
		rom.setROMSize(data[hdr+0x17] > 0 ? (1 << data[hdr+0x17]) : 0);
		rom.setRAMSize(data[hdr+0x18] > 0 ? (1 << data[hdr+0x18]) : 0);
		rom.setCountry(countries[data[hdr+0x19]]);
		rom.setRegion(regionFor(data[hdr+0x19]));
		rom.setVersion(String.format("1.%d", data[hdr+0x1b]));
		
		return rom;
	}
	
	private static int findHeaderAddress(byte[] data) {
		int offset = data.length % 32768 != 0 ? 512 : 0;
		int header = 0x7fc0 + offset;
		
		if (containsValidHeaderAt(data, header))
			return header; // either LoROM or interleaved HiROM
		
		header = 0xffc0 + offset;
		if (containsValidHeaderAt(data, header) && !hasLoROMMakeupAt(data, header))
			return header; // HiROM
		
		header = (data.length / 65536) + offset; // middle 32KB block
		if (containsValidHeaderAt(data, header) && hasLoROMMakeupAt(data, header))
			return header; // interleaved LoROM
		
		return -1;
		
	}
	
	private static boolean containsValidHeaderAt(byte[] data, int offset) {
		return (offset < data.length) && (Util.uint16(data, offset+0x1c) ^ Util.uint16(data, offset+0x1e)) == 0xffff;
	}
	
	private static boolean hasLoROMMakeupAt(byte[] data, int offset) {
		return (data[offset+0x15] & 0xf) % 2 == 0;
	}
	
	private static String regionFor(int countryCode) {
		if (countryCode == 0x0 || countryCode == 0x1 || countryCode == 0xd)
			return "NTSC";
		else if (countryCode >= 0x2 && countryCode <= 0xc)
			return "PAL";
		else
			return "Invalid";
	}
}
