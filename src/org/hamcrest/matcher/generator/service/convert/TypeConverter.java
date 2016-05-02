package org.hamcrest.matcher.generator.service.convert;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.impl.source.PsiClassReferenceType;

import org.hamcrest.matcher.generator.model.type.ClassTypeDefinition;
import org.hamcrest.matcher.generator.model.type.NativeTypeDefinition;
import org.hamcrest.matcher.generator.model.type.ParameterizedClassTypeDefinition;
import org.hamcrest.matcher.generator.model.type.TypeDefinition;
import org.hamcrest.matcher.generator.utils.Optional;

import java.util.ArrayList;
import java.util.List;

public class TypeConverter {
    public ClassTypeDefinition convert (PsiClass psiClass) {
        PsiTypeParameter[] typeParameters = psiClass.getTypeParameters();
        if (typeParameters.length > 0) {
            List<TypeDefinition> parameters = new ArrayList<TypeDefinition>();
            for (PsiTypeParameter typeParameter : typeParameters) {
                parameters.add(fromString(typeParameter.getName()));
            }
            return new ParameterizedClassTypeDefinition(ClassTypeDefinition.from(psiClass.getQualifiedName()).get(), parameters);
        }
        return ClassTypeDefinition.from(psiClass.getQualifiedName()).get();
    }

    public TypeDefinition convert (PsiType type) {
        if (type instanceof PsiClassReferenceType) {
            PsiClassReferenceType referenceElement = (PsiClassReferenceType) type;
            if (referenceElement.getParameters().length > 0) {
                // Generic type
                PsiClassType mainType = referenceElement.rawType();
                List<TypeDefinition> parameters = new ArrayList<TypeDefinition>();
                for (PsiType psiType : referenceElement.getParameters()) {
                    parameters.add(convert(psiType));
                }
                return new ParameterizedClassTypeDefinition(ClassTypeDefinition.from(mainType.getCanonicalText()).get(), parameters);
            }
        }
        return fromString(type.getCanonicalText());
    }

    private TypeDefinition fromString (String representation) {
        Optional<NativeTypeDefinition> optional = NativeTypeDefinition.from(representation);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return ClassTypeDefinition.from(representation).get();
        }
    }
}
