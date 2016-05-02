package org.hamcrest.matcher.generator.service.convert;

import com.intellij.psi.PsiMethod;

import org.hamcrest.matcher.generator.model.MethodName;

public class MethodNameConverter {
    public static final String GET = "get";
    public static final String IS = "is";
    public static final String WITH = "with";
    public static final String VALUE = "value";

    public MethodName create(PsiMethod method) {
        if (method.getName().equals(GET) || method.getName().equals(IS)) {
            return prettyName(method.getName(), VALUE);
        } else if (method.getName().startsWith(GET)) {
            return prettyName(method.getName(), method.getName().substring(GET.length()));
        } else if (method.getName().startsWith(IS)) {
            return prettyName(method.getName(), method.getName().substring(IS.length()));
        }
        return prettyName(method.getName());
    }

    private MethodName prettyName(String originalName, String name) {
        return new MethodName(originalName, WITH, name);
    }

    private MethodName prettyName(String name) {
        return new MethodName(name, WITH, name);
    }

}
