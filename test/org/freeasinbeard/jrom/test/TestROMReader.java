package org.freeasinbeard.jrom.test;

import static org.freeasinbeard.jrom.test.asserts.Assert.assertAllParsersCalled;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestROMReader {

    @Test
    public void testInvalidROM()
    throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        assertNull(ROMReaderWrapper.getInstance().read("data/random_crap"));
        assertAllParsersCalled();
    }
}
