package org.hamcrest.matcher.generator.intellij;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;

import org.hamcrest.matcher.generator.service.processor.MatcherBuilderRequest;

public class BuilderWriterFactory {
    private final PsiDirectoryGenerator psiDirectoryGenerator;

    public BuilderWriterFactory(PsiDirectoryGenerator psiDirectoryGenerator) {
        this.psiDirectoryGenerator = psiDirectoryGenerator;
    }

    public BuilderWriter create (Project project, VirtualFile sourceRoot, MatcherBuilderRequest request, String content) {
        PsiDirectory baseDir = PsiManager.getInstance(project).findDirectory(sourceRoot);
        return new BuilderWriter(psiDirectoryGenerator, baseDir, request, content);
    }
}
