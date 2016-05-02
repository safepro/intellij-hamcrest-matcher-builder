package org.hamcrest.matcher.generator.model;

import org.hamcrest.matcher.generator.model.type.ClassTypeDefinition;

import java.util.Collection;

public class MatcherBuilderClassDefinition {
    private final ClassTypeDefinition classMetadata;
    private final ClassTypeDefinition targetClassMetadata;
    private final Collection<MatcherBuilderMethodDefinition> methodDefinitions;

    public MatcherBuilderClassDefinition(ClassTypeDefinition classMetadata, ClassTypeDefinition targetClassMetadata,
                                         Collection<MatcherBuilderMethodDefinition> methodDefinitions) {
        this.classMetadata = classMetadata;
        this.targetClassMetadata = targetClassMetadata;
        this.methodDefinitions = methodDefinitions;
    }

    public ClassTypeDefinition getClassMetadata() {
        return classMetadata;
    }

    public Collection<MatcherBuilderMethodDefinition> getMethodDefinitions() {
        return methodDefinitions;
    }

    public ClassTypeDefinition getTargetClassMetadata() {
        return targetClassMetadata;
    }
}
