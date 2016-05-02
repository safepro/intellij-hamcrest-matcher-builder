package org.hamcrest.matcher.generator.plugin.ui.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.roots.FileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PlatformIcons;

import org.hamcrest.matcher.generator.plugin.ui.components.ListItem;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ListItemVirtualFileFactory {
    public ListItem<VirtualFile> create (VirtualFile virtualFile, Project project) {
        String relativePath = ProjectUtil.calcRelativeToProjectPath(virtualFile, project, true, false, true);
        return new ListItem<VirtualFile>(virtualFile, relativePath, getSourceRootIcon(virtualFile, project));
    }

    @NotNull
    private Icon getSourceRootIcon(@NotNull VirtualFile virtualFile, Project project) {
        FileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();

        if (fileIndex.isInTestSourceContent(virtualFile)) {
            return PlatformIcons.MODULES_TEST_SOURCE_FOLDER;
        } else if (fileIndex.isInSourceContent(virtualFile)) {
            return PlatformIcons.MODULES_SOURCE_FOLDERS_ICON;
        } else {
            return PlatformIcons.FOLDER_ICON;
        }
    }
}
