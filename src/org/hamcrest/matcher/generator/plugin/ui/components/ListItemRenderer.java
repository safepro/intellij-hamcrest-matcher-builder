package org.hamcrest.matcher.generator.plugin.ui.components;

import com.intellij.ui.ListCellRendererWrapper;

import javax.swing.*;

public class ListItemRenderer<T> extends ListCellRendererWrapper<ListItem<T>> {
    @Override
    public void customize(JList jList, ListItem<T> item, int i, boolean b, boolean b1) {
        setIcon(item.getIcon());
        setText(item.getText());
    }
}
