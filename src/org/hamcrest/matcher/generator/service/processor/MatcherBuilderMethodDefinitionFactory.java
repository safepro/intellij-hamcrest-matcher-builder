package org.hamcrest.matcher.generator.service.processor;

import com.intellij.psi.PsiMethod;

import org.hamcrest.matcher.generator.model.MatcherBuilderMethodDefinition;
import org.hamcrest.matcher.generator.service.convert.MethodNameConverter;
import org.hamcrest.matcher.generator.service.convert.TypeConverter;

public class MatcherBuilderMethodDefinitionFactory {
    private final MethodNameConverter methodNameConverter;
    private final TypeConverter typeConverter;

    public MatcherBuilderMethodDefinitionFactory(MethodNameConverter methodNameConverter, TypeConverter typeConverter) {
        this.methodNameConverter = methodNameConverter;
        this.typeConverter = typeConverter;
    }

    public MatcherBuilderMethodDefinition create (PsiMethod psiMethod) {
        return new MatcherBuilderMethodDefinition(
            methodNameConverter.create(psiMethod),
            typeConverter.convert(psiMethod.getReturnType())
        );
    }
}
