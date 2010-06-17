package org.freeasinbeard.jrom.test.asserts;

import static org.junit.Assert.assertEquals;

import org.freeasinbeard.jrom.parsers.Parser;
import org.freeasinbeard.jrom.test.ROMReaderWrapper;
import org.freeasinbeard.jrom.test.parsers.ParserWrapper;

public class Assert {
    public static void assertPreferredParser(Class<? extends Parser> klass) {
        for (ParserWrapper p : ROMReaderWrapper.getInstance().getParserWrappers())
            assertEquals(p.getParserClass() == klass ? 1 : 0, p.getAndResetCallCount());
    }

    public static void assertAllParsersCalled() {
        for (ParserWrapper p : ROMReaderWrapper.getInstance().getParserWrappers())
            assertEquals(1, p.getAndResetCallCount());
    }
}
