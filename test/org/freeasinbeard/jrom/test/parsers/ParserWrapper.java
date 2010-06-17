package org.freeasinbeard.jrom.test.parsers;

import org.freeasinbeard.jrom.ROM;
import org.freeasinbeard.jrom.parsers.Parser;

public class ParserWrapper implements Parser {

    private Parser parser;
    private int callCount;

    public ParserWrapper(Parser parser) {
        this.parser = parser;
        this.callCount = 0;
    }

    @Override
    public ROM parse(byte[] data) {
        ++callCount;
        return parser.parse(data);
    }

    public Class<? extends Parser> getParserClass() {
        return parser.getClass();
    }

    public int getAndResetCallCount() {
        int callCount = this.callCount;
        this.callCount = 0;
        return callCount;
    }
}
