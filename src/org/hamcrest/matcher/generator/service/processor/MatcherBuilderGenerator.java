package org.hamcrest.matcher.generator.service.processor;

import org.hamcrest.matcher.generator.model.MatcherBuilderClassDefinition;
import org.hamcrest.matcher.generator.service.write.WriteMatcherBuilderClassService;


public class MatcherBuilderGenerator {
    private final MatcherBuilderClassDefinitionFactory classDefinitionFactory;
    private final WriteMatcherBuilderClassService writeMatcherBuilderClassService;

    public MatcherBuilderGenerator(MatcherBuilderClassDefinitionFactory classDefinitionFactory, WriteMatcherBuilderClassService writeMatcherBuilderClassService) {
        this.classDefinitionFactory = classDefinitionFactory;
        this.writeMatcherBuilderClassService = writeMatcherBuilderClassService;
    }

    public String generate(MatcherBuilderRequest request) {
        MatcherBuilderClassDefinition matcherBuilderClassDefinition = classDefinitionFactory.create(request);
        return writeMatcherBuilderClassService.write(matcherBuilderClassDefinition);
    }

}
