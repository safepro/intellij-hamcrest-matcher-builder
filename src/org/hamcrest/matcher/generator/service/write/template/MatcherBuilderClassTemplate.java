package org.hamcrest.matcher.generator.service.write.template;

import org.hamcrest.matcher.generator.utils.IOUtils;
import org.hamcrest.matcher.generator.utils.Optional;

import java.util.Formatter;

public class MatcherBuilderClassTemplate {
    public static final String TEMPLATE_NAME = "matcherBuilderClass.template";
    private static final String TEMPLATE = IOUtils.readContents(MatcherBuilderClassTemplate.class.getResourceAsStream(TEMPLATE_NAME));

    public String render(Model model) {
        return new Formatter()
            .format(TEMPLATE,
                    model.getMatcherBuilderPackageDefinition(),
                    model.getMatcherBuilderClassDefinition(),
                    model.getTargetClassDefinition(),
                    model.getImportsDefinition(),
                    model.getMethodsDefinition(),
                    lowerCase(model.getTargetClassName()),
                    genericTypes(model.getGenericTypes())
            )
            .out().toString();
    }


    private String genericTypes(Optional<String> genericTypes) {
        if (genericTypes.isPresent()) {
            return String.format("<%s>", genericTypes.get());
        } else {
            return "";
        }
    }

    private String lowerCase(String title) {
        if (!title.isEmpty()) {
            return title.substring(0, 1).toLowerCase() + title.substring(1);
        }
        return title;
    }

    public static class Model {
        private final String packageDefinition;
        private final String matcherBuilderClassDefinition;
        private final String targetClassName;
        private final String targetClassDefinition;
        private final String importsDefinition;
        private final String methodsDefinition;
        private final Optional<String> genericTypes;

        public Model(String packageDefinition, String matcherBuilderClassDefinition, String targetClassName, String targetClassDefinition, String importsDefinition, String methodsDefinition,
                     Optional<String> genericTypes) {
            this.packageDefinition = packageDefinition;
            this.matcherBuilderClassDefinition = matcherBuilderClassDefinition;
            this.targetClassName = targetClassName;
            this.targetClassDefinition = targetClassDefinition;
            this.importsDefinition = importsDefinition;
            this.methodsDefinition = methodsDefinition;
            this.genericTypes = genericTypes;
        }

        public String getMatcherBuilderPackageDefinition() {
            return packageDefinition;
        }

        public String getTargetClassName() {
            return targetClassName;
        }

        public String getMatcherBuilderClassDefinition() {
            return matcherBuilderClassDefinition;
        }

        public String getTargetClassDefinition() {
            return targetClassDefinition;
        }

        public String getImportsDefinition() {
            return importsDefinition;
        }

        public String getMethodsDefinition() {
            return methodsDefinition;
        }

        public Optional<String> getGenericTypes() {
            return genericTypes;
        }
    }
}
