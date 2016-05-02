package org.hamcrest.matcher.generator.service.write;

import org.hamcrest.matcher.generator.model.MatcherBuilderClassDefinition;
import org.hamcrest.matcher.generator.model.MatcherBuilderMethodDefinition;
import org.hamcrest.matcher.generator.service.write.template.MatcherBuilderMethodTemplate;

public class WriteMatcherBuilderMethodService {
    private final MatcherBuilderMethodTemplate template;

    public WriteMatcherBuilderMethodService(MatcherBuilderMethodTemplate template) {
        this.template = template;
    }

    public String write(MatcherBuilderClassDefinition classDefinition, MatcherBuilderMethodDefinition definition) {
        return template.render(new MatcherBuilderMethodTemplate.Model(
            classDefinition.getClassMetadata().toDefinition(),
            definition.getMethodName().getCompleteName(),
            definition.getReturningType().toDefinition(),
            definition.getMethodName().toDescription(),
            definition.getMethodName().toField(),
            classDefinition.getTargetClassMetadata().toDefinition(),
            definition.getMethodName().getOriginalName()
        ));
    }
}
