package org.hamcrest.matcher.generator.plugin.ui.components;

import javax.swing.*;

public class ListItem<T> {
    private T item;
    private String text;
    private Icon icon;

    public ListItem(T item, String text, Icon icon) {
        this.item = item;
        this.text = text;
        this.icon = icon;
    }

    public T getItem() {
        return item;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }
}
