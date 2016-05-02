package org.hamcrest.matcher.generator.intellij;

import com.intellij.psi.PsiClass;

public class PackageNameExtractor {
    public String extract (PsiClass psiClass) {
        String qualifiedName = psiClass.getQualifiedName();
        if (qualifiedName == null) return "";

        int position = qualifiedName.lastIndexOf('.');
        if (position == -1) return "";

        return qualifiedName.substring(0, position);
    }
}
