package org.freeasinbeard.jrom;

import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestJROM {

    @Test
    public void testInvalidROM()
    throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        assertNull(JROM.read("data/random_crap"));
    }
}
