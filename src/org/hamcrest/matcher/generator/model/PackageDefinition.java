package org.hamcrest.matcher.generator.model;

import org.hamcrest.matcher.generator.utils.Optional;

public class PackageDefinition {
    private final Optional<String> packageName;

    public PackageDefinition(Optional<String> packageName) {
        this.packageName = packageName;
    }

    public Optional<String> getPackageName() {
        return packageName;
    }

    @Override
    public String toString() {
        if (packageName.isPresent()) {
            return String.format("package %s;", packageName.get());
        } else {
            return "";
        }
    }
}
