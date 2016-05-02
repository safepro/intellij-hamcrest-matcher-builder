package org.hamcrest.matcher.generator.model;

public class MethodName {
    private final String originalName;
    private final String prefix;
    private final String name;

    public MethodName(String originalName, String prefix, String name) {
        this.originalName = originalName;
        this.prefix = prefix;
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getCompleteName() {
        return prefix  + capitalize(name);
    }

    public String toDescription () {
        return String.format("%s %s", prefix, getName());
    }

    public String toField () {
        return getName();
    }

    public String getName() {
        if (name.length() > 0) {
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    private String capitalize(String name) {
        if (name.length() > 0) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        } else {
            return name;
        }
    }
}
