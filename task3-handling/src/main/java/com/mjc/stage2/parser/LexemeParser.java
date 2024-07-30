package com.mjc.stage2.parser;

import com.mjc.stage2.entity.AbstractTextComponent;
import com.mjc.stage2.entity.TextComponent;
import com.mjc.stage2.entity.TextComponentType;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final String LEXEME_REGEX = "\\s+";

    private static final String WORD_REGEX = "\\w[\\w!=?():]+";
    private static final Pattern PATTERN = Pattern.compile(WORD_REGEX);

    public LexemeParser() {
    }

    public LexemeParser(AbstractTextParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(AbstractTextComponent abstractTextComponent, String string) {
        Optional<AbstractTextParser> nextParserOptional = Optional.ofNullable(this.nextParser);
        if (!abstractTextComponent.getComponentType().equals(TextComponentType.SENTENCE)) {
            nextParserOptional.ifPresent(abstractTextParser -> abstractTextParser.parse(abstractTextComponent, string));
            return;
        }
        String[] split = string.trim().split(LEXEME_REGEX);
        Arrays.stream(split)
                .filter(word -> PATTERN.matcher(word).matches())
                .forEach(word -> {
                    TextComponent wordTextComponent = new TextComponent(TextComponentType.WORD);
                    abstractTextComponent.add(wordTextComponent);
                    nextParserOptional.ifPresent(parser -> parser.parse(wordTextComponent, word));
                });
    }
}
