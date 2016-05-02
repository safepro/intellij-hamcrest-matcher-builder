package org.hamcrest.matcher.generator.model.type;

import org.hamcrest.matcher.generator.model.include.ImportDefinition;
import org.hamcrest.matcher.generator.utils.Optional;

import java.util.Collections;
import java.util.Set;

public class ClassTypeDefinition implements TypeDefinition {

    public static Optional<ClassTypeDefinition> from(String representation) {
        if (representation.contains(".")) {
            int index = representation.lastIndexOf(".");
            return Optional.of(new ClassTypeDefinition(Optional.of(representation.substring(0, index)), representation.substring(index + 1)));
        } else {
            return Optional.of(new ClassTypeDefinition(Optional.<String>empty(), representation));
        }
    }

    private final Optional<String> packageName;
    private final String className;

    public ClassTypeDefinition(Optional<String> packageName, String className) {
        this.className = className;
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public Optional<String> getPackageName() {
        return packageName;
    }

    @Override
    public Set<ImportDefinition> listImports() {
        return Collections.singleton(new ImportDefinition(this));
    }

    public String toQualifiedIdentifier () {
        StringBuilder builder = new StringBuilder(packageName.or(""));
        if (packageName.isPresent()) builder.append(".");
        builder.append(className);
        return builder.toString();
    }

    public String toDefinition() {
        return className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassTypeDefinition that = (ClassTypeDefinition) o;

        if (className != null ? !className.equals(that.className) : that.className != null) {
            return false;
        }
        if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
}
