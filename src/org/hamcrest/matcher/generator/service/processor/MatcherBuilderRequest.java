package org.hamcrest.matcher.generator.service.processor;

import com.intellij.psi.PsiClass;

public class MatcherBuilderRequest {
    private final String matcherBuilderPackageName;
    private final String matcherBuilderClassName;
    private final PsiClass psiClass;

    public MatcherBuilderRequest(String matcherBuilderClassName, String matcherBuilderPackageName, PsiClass psiClass) {
        this.matcherBuilderClassName = matcherBuilderClassName;
        this.matcherBuilderPackageName = matcherBuilderPackageName;
        this.psiClass = psiClass;
    }

    public String getMatcherBuilderClassName() {
        return matcherBuilderClassName;
    }

    public String getMatcherBuilderPackageName() {
        return matcherBuilderPackageName;
    }

    public PsiClass getPsiClass() {
        return psiClass;
    }
}
