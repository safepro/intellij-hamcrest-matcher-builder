package org.hamcrest.matcher.generator.model.include;

import org.hamcrest.matcher.generator.model.type.ClassTypeDefinition;

public class ImportDefinition {
    private final ClassTypeDefinition classTypeDefinition;

    public ImportDefinition(ClassTypeDefinition classTypeDefinition) {
        this.classTypeDefinition = classTypeDefinition;
    }

    public ClassTypeDefinition getClassTypeDefinition() {
        return classTypeDefinition;
    }

    @Override
    public String toString () {
        return String.format("import %s;", classTypeDefinition.toQualifiedIdentifier());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImportDefinition that = (ImportDefinition) o;

        if (classTypeDefinition != null ? !classTypeDefinition.equals(that.classTypeDefinition) : that.classTypeDefinition != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return classTypeDefinition != null ? classTypeDefinition.hashCode() : 0;
    }
}
