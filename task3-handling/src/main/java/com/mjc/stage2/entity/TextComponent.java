package com.mjc.stage2.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComponent extends AbstractTextComponent {
    protected List<AbstractTextComponent> componentList = new ArrayList<>();
    private int size = 0;

    public TextComponent(TextComponentType componentType) {
        super(componentType);
    }

    @Override
    public String operation() {
        StringBuilder result = new StringBuilder();
        for (AbstractTextComponent abstractTextComponent : componentList) {
            TextComponentType type = abstractTextComponent.getComponentType();
            if (type.equals(TextComponentType.WORD)) {
                TextComponent sentence = (TextComponent) abstractTextComponent;
                String word = sentence.componentList.stream()
                        .map(AbstractTextComponent::operation)
                        .collect(Collectors.joining());
                result.append(word);
                result.append(" ");
            } else if (type.equals(TextComponentType.SENTENCE)) {
                abstractTextComponent.operation();
            } else {
                result.append(abstractTextComponent.operation());
            }
        }
        return result.toString().trim();
    }

    @Override
    public void add(AbstractTextComponent textComponent) {
        componentList.add(textComponent);
        size++;
    }

    @Override
    public void remove(AbstractTextComponent textComponent) {
        componentList.remove(textComponent);
        size--;
    }

    @Override
    public int getSize() {
        return size;
    }
}
