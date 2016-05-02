package org.hamcrest.matcher.generator.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;

import org.hamcrest.matcher.generator.intellij.PackageNameExtractor;
import org.hamcrest.matcher.generator.plugin.ui.components.ListItem;
import org.hamcrest.matcher.generator.plugin.ui.components.ListItemRenderer;
import org.hamcrest.matcher.generator.plugin.ui.factory.ListItemVirtualFileFactory;
import org.hamcrest.matcher.generator.service.processor.MatcherBuilderRequest;
import org.hamcrest.matcher.generator.utils.SourceRootUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.swing.*;

public class ClassOptionsDialog extends DialogWrapper {
    private final PackageNameExtractor packageNameExtractor;
    private final ListItemVirtualFileFactory listItemVirtualFileFactory;
    private final Project project;
    private final PsiClass matchedClass;
    private final ClassOptions classOptions = new ClassOptions();

    public ClassOptionsDialog(PackageNameExtractor packageNameExtractor, ListItemVirtualFileFactory listItemVirtualFileFactory, @Nullable Project project, @NotNull PsiClass matchedClass) {
        super(project);
        this.packageNameExtractor = packageNameExtractor;
        this.listItemVirtualFileFactory = listItemVirtualFileFactory;
        this.project = project;
        this.matchedClass = matchedClass;

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        populateSources();
        classOptions.getTargetClassName().setText(matchedClass.getQualifiedName());
        classOptions.getMatcherName().setText(matchedClass.getName() + "MatcherBuilder");
        return classOptions.getRootPanel();
    }

    private void populateSources() {
        JComboBox sourceLocation = classOptions.getSourceLocation();
        List<VirtualFile> sourceAndTestSourceRoots = SourceRootUtils.getSourceAndTestSourceRoots(project);
        for (VirtualFile sourceAndTestSourceRoot : sourceAndTestSourceRoots) {
            sourceLocation.addItem(listItemVirtualFileFactory.create(sourceAndTestSourceRoot, project));
        }
        sourceLocation.setRenderer(new ListItemRenderer());
    }

    public MatcherBuilderRequest generateRequest() {
        return new MatcherBuilderRequest(
            classOptions.getMatcherName().getText(),
            packageNameExtractor.extract(matchedClass),
            matchedClass
        );
    }

    public VirtualFile getSourceRoot() {
        return ((ListItem<VirtualFile>)classOptions.getSourceLocation().getSelectedItem()).getItem();
    }
}
