package com.mjc.stage2.parser;


import com.mjc.stage2.entity.AbstractTextComponent;
import com.mjc.stage2.entity.SymbolLeaf;
import com.mjc.stage2.entity.TextComponentType;

import java.util.Optional;

public class WordParser extends AbstractTextParser {

    public WordParser() {
    }

    public WordParser(AbstractTextParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(AbstractTextComponent abstractTextComponent, String string) {
        Optional<AbstractTextParser> nextParserOptional = Optional.ofNullable(this.nextParser);
        TextComponentType componentType = abstractTextComponent.getComponentType();
        if (!componentType.equals(TextComponentType.WORD)) {
            nextParserOptional.ifPresent(abstractTextParser -> abstractTextParser.parse(abstractTextComponent, string));
            return;
        }
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            abstractTextComponent.add(new SymbolLeaf(TextComponentType.SYMBOL, aChar));
        }
    }
}
