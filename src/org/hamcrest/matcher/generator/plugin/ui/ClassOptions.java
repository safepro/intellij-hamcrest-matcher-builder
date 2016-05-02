package org.hamcrest.matcher.generator.plugin.ui;

import javax.swing.*;

public class ClassOptions {
    private JPanel rootPanel;
    private JComboBox sourceLocation;
    private JTextField matcherName;
    private JLabel targetClassName;

    public JLabel getTargetClassName() {
        return targetClassName;
    }
    public JTextField getMatcherName() {
        return matcherName;
    }
    public JComboBox getSourceLocation() {
        return sourceLocation;
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
