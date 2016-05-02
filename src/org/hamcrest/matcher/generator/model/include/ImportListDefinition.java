package org.hamcrest.matcher.generator.model.include;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.matcher.generator.model.PackageDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ImportListDefinition {
    private final Set<ImportDefinition> importDefinitionCollection;

    public ImportListDefinition(Set<ImportDefinition> importDefinitionCollection) {
        this.importDefinitionCollection = importDefinitionCollection;
    }

    public ImportListDefinition add (ImportDefinition importDefinitionType) {
        importDefinitionCollection.add(importDefinitionType);
        return this;
    }
    public ImportListDefinition add (Collection<ImportDefinition> importDefinitionType) {
        importDefinitionCollection.addAll(importDefinitionType);
        return this;
    }

    public String toDefinition (PackageDefinition packageDefinition) {
        Collection<ImportDefinition> filtered = new ArrayList<ImportDefinition>();
        for (ImportDefinition importDefinition : importDefinitionCollection) {
            if (!importDefinition.getClassTypeDefinition().getPackageName().equals(packageDefinition.getPackageName())) {
                filtered.add(importDefinition);
            }
        }
        return StringUtils.join(filtered, String.format("%n"));
    }
}
