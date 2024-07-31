package com.mjc.stage2.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChainParserBuilder {
    private List<AbstractTextParser> parsers = new ArrayList<>();

    public ChainParserBuilder() {
    }

    public ChainParserBuilder setParser(AbstractTextParser abstractTextParser) {
        parsers.add(abstractTextParser);
        return this;
    }

    public AbstractTextParser build() {
        Optional<AbstractTextParser> allParsers = parsers.stream()
                .reduce((abstractTextParser, abstractTextParser2) -> {
                    abstractTextParser.setNextParser(abstractTextParser2);
                    return abstractTextParser2;
                });
        return allParsers.isPresent() ? parsers.get(0) : new LexemeParser(new WordParser());
    }
}
