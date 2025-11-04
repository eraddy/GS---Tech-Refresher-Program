package org.example;

public final class Computer {
    private final String cpu;
    private final String ram;
    private final String ssd;
    private final String gpu;

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.ssd = builder.ssd;
        this.gpu = builder.gpu;
    }

    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getSsd() {
        return ssd;
    }

    public String getGpu() {
        return gpu;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", ssd='" + ssd + '\'' +
                ", gpu='" + gpu + '\'' +
                '}';
    }

    public static class ComputerBuilder {
        private final String cpu; // required
        private String ram;       // optional
        private String ssd;       // optional
        private String gpu;       // optional

        public ComputerBuilder(String cpu) {
            if (cpu == null || cpu.isBlank())
                throw new IllegalArgumentException("CPU cannot be null or blank");
            this.cpu = cpu;
        }

        public ComputerBuilder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public ComputerBuilder ssd(String ssd) {
            this.ssd = ssd;
            return this;
        }

        public ComputerBuilder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
