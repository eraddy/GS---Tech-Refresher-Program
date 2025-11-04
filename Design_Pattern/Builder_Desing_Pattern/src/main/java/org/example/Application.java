package org.example;

public class Application {
    public static void main(String[] args) {
        System.out.println("💻 Computer Builder Demo\n");

        // ✅ Basic computer (only required component)
        Computer basicComputer = new Computer.ComputerBuilder("Intel i5").build();
        System.out.println("Basic Computer: " + basicComputer);

        // ✅ Mid-range computer (with RAM and SSD)
        Computer midRangeComputer = new Computer.ComputerBuilder("AMD Ryzen 5")
                .ram("16GB")
                .ssd("512GB")
                .build();
        System.out.println("Mid-Range Computer: " + midRangeComputer);

        // ✅ High-end computer (all components)
        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9")
                .ram("32GB DDR5")
                .ssd("2TB NVMe")
                .gpu("NVIDIA RTX 4090")
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);

        // ✅ Apple configuration
        Computer macBook = new Computer.ComputerBuilder("Apple M3")
                .ram("16GB Unified")
                .ssd("1TB SSD")
                .build();
        System.out.println("MacBook Configuration: " + macBook);

        // ❌ Example of invalid input (for demonstration)
        try {
            new Computer.ComputerBuilder(" ").build();
        } catch (IllegalArgumentException e) {
            System.err.println("\n⚠️ Error creating computer: " + e.getMessage());
        }

        System.out.println("\n✅ Demo completed successfully!");
    }
}
