package org.max.cdc.debezium.metadata.util;

public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException("Utility class is not intended for instantiation!");
    }

    public static void checkArgument(boolean predicate, String errorMessage) {
        if (!predicate) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkArgument(boolean predicate) {
        checkArgument(predicate, "Pre-condition check failed");
    }

}
