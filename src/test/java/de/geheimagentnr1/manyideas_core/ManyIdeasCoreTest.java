package de.geheimagentnr1.manyideas_core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ManyIdeasCoreTest {

    @Test
    void modIdIsValid() {

        String modId = "manyideas_core";
        assertTrue( modId.matches( "[a-z][a-z0-9_]{1,63}" ) );
    }
}
