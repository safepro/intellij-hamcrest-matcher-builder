package org.hamcrest.matcher.generator.intellij;

import com.intellij.psi.PsiDirectory;

public class PsiDirectoryGenerator {
    public PsiDirectory findOrCreate(PsiDirectory baseDirectory, String packageName) {
        int index = packageName.indexOf('.');
        if (index > 0) {
            String packageStart = packageName.substring(0, index);
            String packageRemainder = packageName.substring(index + 1);

            PsiDirectory subdirectory = baseDirectory.findSubdirectory(packageStart);

            if (subdirectory == null) {
                subdirectory = baseDirectory.createSubdirectory(packageStart);
            }

            return findOrCreate(subdirectory, packageRemainder);
        } else {
            PsiDirectory subdirectory = baseDirectory.findSubdirectory(packageName);

            if (subdirectory == null) {
                return baseDirectory.createSubdirectory(packageName);
            }

            return subdirectory;
        }
    }
}
