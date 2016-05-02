package org.hamcrest.matcher.generator.intellij;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class PsiClassExtractor {
    public PsiClass extract(PsiFile selectedFile) {
        PsiElement[] children = selectedFile.getChildren();
        for (PsiElement child : children) {
            if (child instanceof PsiClass) {
                return (PsiClass) child;
            }
        }
        throw new IllegalArgumentException("Booom!");
    }
}
