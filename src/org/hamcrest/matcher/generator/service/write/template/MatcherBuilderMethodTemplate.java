package org.hamcrest.matcher.generator.service.write.template;

import org.hamcrest.matcher.generator.utils.IOUtils;

import java.util.Formatter;

public class MatcherBuilderMethodTemplate {
    public static final String TEMPLATE_NAME = "matcherBuilderMethod.template";
    private static final String TEMPLATE = IOUtils.readContents(MatcherBuilderClassTemplate.class.getResourceAsStream(TEMPLATE_NAME));


    public String render(Model model) {
        return new Formatter()
            .format(TEMPLATE,
                    model.getMatcherBuilderClassDefinition(),
                    model.getMatcherBuilderMethodName(),
                    model.getReturnTypeDefinition(),
                    model.getTargetClassDefinition(),
                    model.getReturnTypeDescription(),
                    model.getReturnTypeName(),
                    model.getMethodName()
            )
            .out().toString();
    }

    public static class Model {
        private final String matcherBuilderClassDefinition;
        private final String matcherBuilderMethodName;
        private final String returnTypeDefinition;
        private final String returnTypeDescription;
        private final String returnTypeName;
        private final String targetClassDefinition;
        private final String methodName;

        public Model(String matcherBuilderClassDefinition, String matcherBuilderMethodName, String returnTypeDefinition, String returnTypeDescription, String returnTypeName,
                     String targetClassDefinition, String methodName) {
            this.matcherBuilderClassDefinition = matcherBuilderClassDefinition;
            this.matcherBuilderMethodName = matcherBuilderMethodName;
            this.returnTypeDefinition = returnTypeDefinition;
            this.returnTypeDescription = returnTypeDescription;
            this.returnTypeName = returnTypeName;
            this.targetClassDefinition = targetClassDefinition;
            this.methodName = methodName;
        }

        public String getMatcherBuilderClassDefinition() {
            return matcherBuilderClassDefinition;
        }

        public String getMatcherBuilderMethodName() {
            return matcherBuilderMethodName;
        }

        public String getReturnTypeDefinition() {
            return returnTypeDefinition;
        }

        public String getTargetClassDefinition() {
            return targetClassDefinition;
        }

        public String getReturnTypeDescription() {
            return returnTypeDescription;
        }

        public String getReturnTypeName() {
            return returnTypeName;
        }

        public String getMethodName() {
            return methodName;
        }

    }
}
