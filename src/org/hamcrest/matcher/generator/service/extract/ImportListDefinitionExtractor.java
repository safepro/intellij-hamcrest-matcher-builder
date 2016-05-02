package org.hamcrest.matcher.generator.service.extract;

import org.hamcrest.matcher.generator.model.MatcherBuilderClassDefinition;
import org.hamcrest.matcher.generator.model.MatcherBuilderMethodDefinition;
import org.hamcrest.matcher.generator.model.include.ImportDefinition;
import org.hamcrest.matcher.generator.model.include.ImportListDefinition;

import java.util.HashSet;

public class ImportListDefinitionExtractor {
    public ImportListDefinition extract (MatcherBuilderClassDefinition classDefinition) {
        ImportListDefinition importListDefinition = new ImportListDefinition(new HashSet<ImportDefinition>());
        importListDefinition.add(classDefinition.getTargetClassMetadata().listImports());

        for (MatcherBuilderMethodDefinition methodDefinition : classDefinition.getMethodDefinitions()) {
            importListDefinition.add(methodDefinition.getReturningType().listImports());
        }

        return importListDefinition;
    }
}
