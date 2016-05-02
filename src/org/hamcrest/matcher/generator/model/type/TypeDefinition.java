package org.hamcrest.matcher.generator.model.type;

import org.hamcrest.matcher.generator.model.include.ImportDefinition;

import java.util.Set;

public interface TypeDefinition {
    Set<ImportDefinition> listImports ();
    String toDefinition ();
}
