package org.hamcrest.matcher.generator.intellij;

import com.intellij.codeInsight.actions.AbstractLayoutCodeProcessor;
import com.intellij.codeInsight.actions.OptimizeImportsProcessor;
import com.intellij.codeInsight.actions.RearrangeCodeProcessor;
import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

import org.hamcrest.matcher.generator.service.processor.MatcherBuilderRequest;

public class BuilderWriter implements Runnable {
    public static final String JAVA = ".java";

    private final PsiDirectoryGenerator psiDirectoryGenerator;
    private final PsiDirectory baseDirectory;
    private final MatcherBuilderRequest request;
    private final String content;

    public BuilderWriter(PsiDirectoryGenerator psiDirectoryGenerator, PsiDirectory baseDirectory, MatcherBuilderRequest request, String content) {
        this.psiDirectoryGenerator = psiDirectoryGenerator;
        this.baseDirectory = baseDirectory;
        this.request = request;
        this.content = content;
    }

    @Override
    public void run() {
        PsiDirectory directory = psiDirectoryGenerator.findOrCreate(baseDirectory, request.getMatcherBuilderPackageName());

        String targetFileName = request.getMatcherBuilderClassName() + JAVA;
        PsiFile existingFile = directory.findFile(targetFileName);
        PsiFile targetFile = (existingFile != null) ? existingFile : directory.createFile(targetFileName);

        PsiDocumentManager documentManager = PsiDocumentManager.getInstance(directory.getProject());
        Document document = documentManager.getDocument(targetFile);
        if (document == null) throw new IllegalStateException("Document is null and I'm new around this so I don't know how it can be null");

        document.setText(content);
        documentManager.commitDocument(document);

        AbstractLayoutCodeProcessor processor = new ReformatCodeProcessor(targetFile.getProject(), targetFile, null, false);
        processor = new OptimizeImportsProcessor(processor);
        processor = new RearrangeCodeProcessor(processor);
        processor.run();
    }
}
