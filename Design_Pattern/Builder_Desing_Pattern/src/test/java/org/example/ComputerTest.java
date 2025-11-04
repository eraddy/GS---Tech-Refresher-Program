package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ComputerTest {

    @Test
    void testBuildBasicComputer() {
        Computer computer = new Computer.ComputerBuilder("Intel i5").build();

        assertEquals("Intel i5", computer.getCpu());
        assertNull(computer.getRam());
        assertNull(computer.getSsd());
        assertNull(computer.getGpu());
    }

    @Test
    void testBuildFullComputer() {
        Computer computer = new Computer.ComputerBuilder("AMD Ryzen 7")
                .ram("16GB")
                .ssd("1TB")
                .gpu("NVIDIA RTX 4070")
                .build();

        assertEquals("AMD Ryzen 7", computer.getCpu());
        assertEquals("16GB", computer.getRam());
        assertEquals("1TB", computer.getSsd());
        assertEquals("NVIDIA RTX 4070", computer.getGpu());
    }

    @Test
    void testPartialBuild() {
        Computer computer = new Computer.ComputerBuilder("Intel i9")
                .ram("32GB")
                .build();

        assertEquals("Intel i9", computer.getCpu());
        assertEquals("32GB", computer.getRam());
        assertNull(computer.getSsd());
        assertNull(computer.getGpu());
    }

    @Test
    void testImmutability() {
        Computer computer = new Computer.ComputerBuilder("Apple M3")
                .ram("16GB")
                .ssd("512GB")
                .build();

        // No setters exist, ensuring immutability
        assertEquals("Apple M3", computer.getCpu());
        assertThrows(NoSuchMethodException.class,
                () -> computer.getClass().getDeclaredMethod("setCpu", String.class));
    }

    @Test
    void testInvalidCpuThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Computer.ComputerBuilder(" ").build());
    }

}