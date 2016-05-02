package org.hamcrest.matcher.generator.model.type;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.matcher.generator.model.include.ImportDefinition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParameterizedClassTypeDefinition extends ClassTypeDefinition {
    private final List<TypeDefinition> parameters;

    public ParameterizedClassTypeDefinition(ClassTypeDefinition prototype, List<TypeDefinition> parameters) {
        super(prototype.getPackageName(), prototype.getClassName());
        this.parameters = parameters;
    }

    public List<TypeDefinition> getParameters() {
        return parameters;
    }

    @Override
    public Set<ImportDefinition> listImports() {
        Set<ImportDefinition> types = new HashSet<ImportDefinition>();
        types.addAll(super.listImports());
        for (TypeDefinition parameter : parameters) {
            types.addAll(parameter.listImports());
        }
        return types;
    }

    @Override
    public String toDefinition() {
        List<String> typeDefinitions = new ArrayList<String>();
        for (TypeDefinition parameter : parameters) {
            typeDefinitions.add(parameter.toDefinition());
        }
        return String.format("%s<%s>", super.toDefinition(), StringUtils.join(typeDefinitions, ", "));
    }
}
