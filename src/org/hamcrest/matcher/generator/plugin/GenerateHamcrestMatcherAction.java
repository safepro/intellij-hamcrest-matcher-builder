package org.hamcrest.matcher.generator.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;

import org.hamcrest.matcher.generator.intellij.BuilderWriterFactory;
import org.hamcrest.matcher.generator.intellij.MethodsExtractor;
import org.hamcrest.matcher.generator.intellij.PackageNameExtractor;
import org.hamcrest.matcher.generator.intellij.PsiClassExtractor;
import org.hamcrest.matcher.generator.intellij.PsiDirectoryGenerator;
import org.hamcrest.matcher.generator.plugin.ui.ClassOptionsDialog;
import org.hamcrest.matcher.generator.plugin.ui.factory.ListItemVirtualFileFactory;
import org.hamcrest.matcher.generator.service.convert.MethodNameConverter;
import org.hamcrest.matcher.generator.service.convert.TypeConverter;
import org.hamcrest.matcher.generator.service.extract.ImportListDefinitionExtractor;
import org.hamcrest.matcher.generator.service.processor.MatcherBuilderClassDefinitionFactory;
import org.hamcrest.matcher.generator.service.processor.MatcherBuilderGenerator;
import org.hamcrest.matcher.generator.service.processor.MatcherBuilderMethodDefinitionFactory;
import org.hamcrest.matcher.generator.service.processor.MatcherBuilderRequest;
import org.hamcrest.matcher.generator.service.write.WriteMatcherBuilderClassService;
import org.hamcrest.matcher.generator.service.write.WriteMatcherBuilderMethodService;
import org.hamcrest.matcher.generator.service.write.template.MatcherBuilderClassTemplate;
import org.hamcrest.matcher.generator.service.write.template.MatcherBuilderMethodTemplate;

public class GenerateHamcrestMatcherAction extends AnAction {
    private final PsiClassExtractor psiClassExtractor = new PsiClassExtractor();
    private final TypeConverter typeConverter = new TypeConverter();
    private final MatcherBuilderMethodDefinitionFactory methodDefinitionFactory = new MatcherBuilderMethodDefinitionFactory(new MethodNameConverter(), typeConverter);
    private final MethodsExtractor methodsExtractor = new MethodsExtractor();
    private final MatcherBuilderClassDefinitionFactory classDefinitionFactory = new MatcherBuilderClassDefinitionFactory(typeConverter, methodDefinitionFactory, methodsExtractor);
    private final WriteMatcherBuilderMethodService writeMatcherBuilderMethodService = new WriteMatcherBuilderMethodService(new MatcherBuilderMethodTemplate());
    private final ImportListDefinitionExtractor importListDefinitionExtractor = new ImportListDefinitionExtractor();
    private final MatcherBuilderClassTemplate matcherBuilderClassTemplate = new MatcherBuilderClassTemplate();
    private final WriteMatcherBuilderClassService writeMatcherBuilderClassService = new WriteMatcherBuilderClassService(writeMatcherBuilderMethodService, importListDefinitionExtractor, matcherBuilderClassTemplate);
    private final MatcherBuilderGenerator matcherBuilderGenerator = new MatcherBuilderGenerator(classDefinitionFactory, writeMatcherBuilderClassService);
    private final BuilderWriterFactory builderWriterFactory = new BuilderWriterFactory(new PsiDirectoryGenerator());
    private final ListItemVirtualFileFactory listItemVirtualFileFactory = new ListItemVirtualFileFactory();
    private final PackageNameExtractor packageNameExtractor = new PackageNameExtractor();

    @Override
    public void update(AnActionEvent e) {
        boolean enabled = (e.getData(CommonDataKeys.PROJECT) != null)
                          && (e.getData(CommonDataKeys.PSI_FILE) != null);

        Presentation presentation = e.getPresentation();
        presentation.setEnabled(enabled);
        presentation.setVisible(true);
    }

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(CommonDataKeys.PROJECT);
        PsiFile selectedFile = e.getData(CommonDataKeys.PSI_FILE);

        if (selectedFile == null) {
            Messages.showErrorDialog(project, "No selected file.", "Absent Data");
            return;
        }

        ClassOptionsDialog classOptionsDialog = new ClassOptionsDialog(packageNameExtractor, listItemVirtualFileFactory, project, psiClassExtractor.extract(selectedFile));
        classOptionsDialog.show();

        if (classOptionsDialog.isOK()) {
            MatcherBuilderRequest request = classOptionsDialog.generateRequest();
            String content = matcherBuilderGenerator.generate(request);

            ApplicationManager.getApplication().runWriteAction(builderWriterFactory.create(project, classOptionsDialog.getSourceRoot(), request, content));
        }
    }
}
