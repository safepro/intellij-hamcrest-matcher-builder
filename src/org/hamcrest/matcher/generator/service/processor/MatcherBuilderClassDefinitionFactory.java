package org.hamcrest.matcher.generator.service.processor;

import com.intellij.psi.PsiMethod;

import org.hamcrest.matcher.generator.intellij.MethodsExtractor;
import org.hamcrest.matcher.generator.model.MatcherBuilderClassDefinition;
import org.hamcrest.matcher.generator.model.MatcherBuilderMethodDefinition;
import org.hamcrest.matcher.generator.model.type.ClassTypeDefinition;
import org.hamcrest.matcher.generator.model.type.ParameterizedClassTypeDefinition;
import org.hamcrest.matcher.generator.service.convert.TypeConverter;
import org.hamcrest.matcher.generator.utils.Optional;

import java.util.ArrayList;
import java.util.List;

public class MatcherBuilderClassDefinitionFactory {

    private final TypeConverter typeConverter;
    private final MatcherBuilderMethodDefinitionFactory methodDefinitionFactory;
    private final MethodsExtractor methodsExtractor;

    public MatcherBuilderClassDefinitionFactory(TypeConverter typeConverter, MatcherBuilderMethodDefinitionFactory methodDefinitionFactory, MethodsExtractor methodsExtractor) {
        this.typeConverter = typeConverter;
        this.methodDefinitionFactory = methodDefinitionFactory;
        this.methodsExtractor = methodsExtractor;
    }

    public MatcherBuilderClassDefinition create(MatcherBuilderRequest request) {
        List<PsiMethod> psiMethods = methodsExtractor.extract(request.getPsiClass());
        List<MatcherBuilderMethodDefinition> methodDefinitions = new ArrayList<MatcherBuilderMethodDefinition>();
        for (PsiMethod psiMethod : psiMethods) {
            methodDefinitions.add(methodDefinitionFactory.create(psiMethod));
        }

        ClassTypeDefinition targetClassMetadata = typeConverter.convert(request.getPsiClass());
        ClassTypeDefinition classTypeMetadata = new ClassTypeDefinition(Optional.ofNullable(request.getMatcherBuilderPackageName()), request.getMatcherBuilderClassName());

        if (targetClassMetadata instanceof ParameterizedClassTypeDefinition) {
            classTypeMetadata = new ParameterizedClassTypeDefinition(classTypeMetadata, ((ParameterizedClassTypeDefinition) targetClassMetadata).getParameters());
        }

        return new MatcherBuilderClassDefinition(
            classTypeMetadata,
            targetClassMetadata,
            methodDefinitions
        );
    }
}
