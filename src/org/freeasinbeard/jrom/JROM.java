package org.freeasinbeard.jrom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeasinbeard.jrom.parsers.GenesisParser;
import org.freeasinbeard.jrom.parsers.NESParser;
import org.freeasinbeard.jrom.parsers.Parser;
import org.freeasinbeard.jrom.parsers.SNESParser;

public class JROM {
	
	private static final Pattern EXT_RE = Pattern.compile(".+\\.(.+)$");
	private static Map<String, Parser> parsers;
	
	static {
		parsers = new HashMap<String, Parser>();
		addParser(new GenesisParser(), "bin", "smd", "md");
		addParser(new SNESParser(), "smc");
		addParser(new NESParser(), "nes");
	}
	
	public static ROM read(String path) 
		throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		return read(new File(path));
	}
	
	public static ROM read(File f) 
		throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		if (f.length() > Integer.MAX_VALUE)
			throw new IOException("File too large.");

		byte[] data = new byte[(int)f.length()];
		FileInputStream is = new FileInputStream(f);
		try {
			is.read(data);
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}
		
		Collection<Parser> parsersToTry = new HashSet<Parser>(parsers.values());
		Parser preferredParser = parserForFile(f);
		ROM rom = null;
		if (preferredParser != null) {
			rom = preferredParser.parse(data);
			if (rom != null) {
				rom.setHash(Util.hash(data));
				return rom;
			}
			
			parsersToTry.remove(preferredParser);
		}
		
		for (Parser p : parsersToTry) {
			rom = p.parse(data);
			if (rom != null) {
				rom.setHash(Util.hash(data));
				return rom;
			}
		}
		
		return null;
	}
	
	private static Parser parserForFile(File f) {
		Matcher m = EXT_RE.matcher(f.getName());
		if (m.matches())
			return parsers.get(m.group(1));
		else
			return null;
	}
	
	private static void addParser(Parser p, String...extensions) {
		for (String e : extensions) {
			parsers.put(e, p);
		}
	}
}
