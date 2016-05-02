package org.hamcrest.matcher.generator.service.write;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.matcher.generator.model.MatcherBuilderClassDefinition;
import org.hamcrest.matcher.generator.model.MatcherBuilderMethodDefinition;
import org.hamcrest.matcher.generator.model.PackageDefinition;
import org.hamcrest.matcher.generator.model.include.ImportListDefinition;
import org.hamcrest.matcher.generator.model.type.ParameterizedClassTypeDefinition;
import org.hamcrest.matcher.generator.model.type.TypeDefinition;
import org.hamcrest.matcher.generator.service.extract.ImportListDefinitionExtractor;
import org.hamcrest.matcher.generator.service.write.template.MatcherBuilderClassTemplate;
import org.hamcrest.matcher.generator.utils.Optional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WriteMatcherBuilderClassService {
    private final WriteMatcherBuilderMethodService methodService;
    private final ImportListDefinitionExtractor importListDefinitionExtractor;
    private final MatcherBuilderClassTemplate template;

    public WriteMatcherBuilderClassService(WriteMatcherBuilderMethodService methodService, ImportListDefinitionExtractor importListDefinitionExtractor, MatcherBuilderClassTemplate template) {
        this.methodService = methodService;
        this.importListDefinitionExtractor = importListDefinitionExtractor;
        this.template = template;
    }

    public String write (MatcherBuilderClassDefinition definition) {
        PackageDefinition packageDefinition = new PackageDefinition(definition.getClassMetadata().getPackageName());
        ImportListDefinition importListDefinition = importListDefinitionExtractor.extract(definition);

        Collection<String> methodDefinitions = new ArrayList<String>();
        for (MatcherBuilderMethodDefinition methodDefinition : definition.getMethodDefinitions()) {
            methodDefinitions.add(methodService.write(definition, methodDefinition));
        }

        return template.render(new MatcherBuilderClassTemplate.Model(
            packageDefinition.toString(),
            definition.getClassMetadata().toDefinition(),
            definition.getTargetClassMetadata().getClassName(),
            definition.getTargetClassMetadata().toDefinition(),
            importListDefinition.toDefinition(packageDefinition),
            StringUtils.join(methodDefinitions, ""),
            calculateGenerics(definition.getTargetClassMetadata())));
    }

    private Optional<String> calculateGenerics(TypeDefinition targetClassMetadata) {
        if (targetClassMetadata instanceof ParameterizedClassTypeDefinition) {
            List<TypeDefinition> parameters = ((ParameterizedClassTypeDefinition) targetClassMetadata).getParameters();
            List<String> types = new ArrayList<String>();
            for (TypeDefinition parameter : parameters) {
                types.add(parameter.toDefinition());
            }
            return Optional.of(StringUtils.join(types, ", "));
        } else {
            return Optional.empty();
        }
    }
}
