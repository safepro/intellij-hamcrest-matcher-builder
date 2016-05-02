package org.hamcrest.matcher.generator.model.type;

import org.hamcrest.matcher.generator.model.include.ImportDefinition;
import org.hamcrest.matcher.generator.utils.Optional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NativeTypeDefinition implements TypeDefinition {
    private static final Map<String, String> TYPES = new HashMap<String, String>() {{
        put(Integer.TYPE.getName(), Integer.class.getName());
        put(Character.TYPE.getName(), Character.class.getName());
        put(Float.TYPE.getName(), Float.class.getName());
        put(Boolean.TYPE.getName(), Boolean.class.getName());
        put(Long.TYPE.getName(), Long.class.getName());
        put(Double.TYPE.getName(), Double.class.getName());
        put(Byte.TYPE.getName(), Byte.class.getName());
        put(Short.TYPE.getName(), Short.class.getName());
    }};

    public static Optional<NativeTypeDefinition> from (String representation) {
        if (TYPES.containsKey(representation)) {
            return Optional.of(new NativeTypeDefinition(ClassTypeDefinition.from(TYPES.get(representation)).get(), representation));
        } else {
            return Optional.empty();
        }
    }

    private final String nativeType;
    private final ClassTypeDefinition boxedType;


    public NativeTypeDefinition(ClassTypeDefinition boxedType, String nativeType) {
        this.boxedType = boxedType;
        this.nativeType = nativeType;
    }

    @Override
    public Set<ImportDefinition> listImports() {
        return Collections.singleton(new ImportDefinition(boxedType));
    }

    @Override
    public String toDefinition() {
        return boxedType.toDefinition();
    }
}
