package org.hamcrest.matcher.generator.utils;

import com.google.common.base.Preconditions;

public class Optional<T> {
    public static <T> Optional<T> empty () {
        return new Optional<T>(null);
    }

    public static <T> Optional<T> of (T value) {
        Preconditions.checkNotNull(value);
        return new Optional<T>(value);
    }

    public static <T> Optional<T> ofNullable (T value) {
        return new Optional<T>(value);
    }

    private final T value;

    public Optional(T value) {
        this.value = value;
    }

    public T get () {
        if (value == null) throw new IllegalStateException("Null value");
        return value;
    }

    public T or (T defaultValue) {
        if (value == null) return defaultValue;
        return value;
    }

    public <E extends RuntimeException> T orThrow (E exception) {
        if (value == null) throw exception;
        else return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Optional optional = (Optional) o;

        if ((value != null) ? !value.equals(optional.value) : (optional.value != null)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (value != null) ? value.hashCode() : 0;
    }
}
