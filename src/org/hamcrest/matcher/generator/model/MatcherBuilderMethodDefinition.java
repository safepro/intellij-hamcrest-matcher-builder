package org.hamcrest.matcher.generator.model;

import org.hamcrest.matcher.generator.model.type.TypeDefinition;

public class MatcherBuilderMethodDefinition {
    private final MethodName methodName;
    private final TypeDefinition returningType;

    public MatcherBuilderMethodDefinition(MethodName methodName, TypeDefinition returningType) {
        this.methodName = methodName;
        this.returningType = returningType;
    }

    public MethodName getMethodName() {
        return methodName;
    }

    public TypeDefinition getReturningType() {
        return returningType;
    }
}
