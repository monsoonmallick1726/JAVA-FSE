class Computer {
    private final String cpu;
    private final int ram;
    private final int storage;
    private final String gpu;
    private final String operatingSystem;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.operatingSystem = builder.operatingSystem;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", gpu='" + gpu + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }

    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        private String gpu;
        private String operatingSystem;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
public class BuilderPatternTest {
    public static void main(String[] args) {
        // Create a basic computer
        Computer basicComputer = new Computer.Builder()
                .cpu("Intel i3")
                .ram(8)
                .storage(256)
                .build();
        System.out.println("Basic Computer: " + basicComputer);

        // Create a high-end computer
        Computer highEndComputer = new Computer.Builder()
                .cpu("Intel i9")
                .ram(32)
                .storage(1024)
                .gpu("NVIDIA RTX 3080")
                .operatingSystem("Windows 11")
                .build();
        System.out.println("High-End Computer: " + highEndComputer);

        // Create a custom computer
        Computer customComputer = new Computer.Builder()
                .cpu("AMD Ryzen 7")
                .ram(16)
                .storage(512)
                .gpu("AMD Radeon RX 6700 XT")
                .operatingSystem("Linux")
                .build();
        System.out.println("Custom Computer: " + customComputer);
    }
