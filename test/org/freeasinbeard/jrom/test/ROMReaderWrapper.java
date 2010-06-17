package org.freeasinbeard.jrom.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.ROMReader;
import org.freeasinbeard.jrom.parsers.GenesisParser;
import org.freeasinbeard.jrom.parsers.NESParser;
import org.freeasinbeard.jrom.parsers.Parser;
import org.freeasinbeard.jrom.parsers.SNESParser;
import org.freeasinbeard.jrom.test.parsers.ParserWrapper;

public final class ROMReaderWrapper {

    private static ROMReaderWrapper theInstance;

    private ROMReader jrom;

    public static ROMReaderWrapper getInstance() {
        if (theInstance == null)
            return (theInstance = new ROMReaderWrapper());
        else
            return theInstance;
    }

    private ROMReaderWrapper() {
        this.jrom = ROMReader.getInstance();
        jrom.clearParsers();
        jrom.setParser(new ParserWrapper(new GenesisParser()), "bin", "smd", "md");
        jrom.setParser(new ParserWrapper(new SNESParser()), "smc");
        jrom.setParser(new ParserWrapper(new NESParser()), "nes");
    }

    public ROM read(String path)
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        return jrom.read(path);
    }

    public ROM read(File f)
        throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        return jrom.read(f);
    }

    public Collection<ParserWrapper> getParserWrappers() {
        Collection<ParserWrapper> parserWrappers = new HashSet<ParserWrapper>();
        for (Parser p : jrom.getParsers())
            parserWrappers.add((ParserWrapper) p);

        return parserWrappers;
    }
}
