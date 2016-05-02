package org.hamcrest.matcher.generator.intellij;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;

import java.util.ArrayList;
import java.util.List;

public class MethodsExtractor {
    private static final String[] ALLOWED_PREFIXES = new String[] {
        "get", "is"
    };

    public List<PsiMethod> extract (PsiClass psiClass) {
        List<PsiMethod> result = new ArrayList<PsiMethod>();
        for (PsiMethod psiMethod : psiClass.getAllMethods()) {
            if (psiMethod.getReturnType() == null) continue;
            if (PsiType.VOID.equals(psiMethod.getReturnType())) continue;
            if ("getClass".equals(psiMethod.getName())) continue;
            if (!isAllowedPrefix(psiMethod)) continue;
            if (psiMethod.getTypeParameters().length > 0) continue;

            result.add(psiMethod);
        }
        return result;
    }

    private boolean isAllowedPrefix(PsiMethod psiMethod) {
        for (String allowedPrefix : ALLOWED_PREFIXES) {
            if (psiMethod.getName().startsWith(allowedPrefix)) {
                return true;
            }
        }
        return false;
    }
}
