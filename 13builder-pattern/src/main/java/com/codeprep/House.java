package com.codeprep;

public class House {

    private String foundation;
    private String structure;
    private String roof;
    private boolean garage;
    private boolean swimmingPool;
    private boolean garden;

    private House(HouseBuilder houseBuilder) {
        this.foundation = houseBuilder.foundation;
        this.structure = houseBuilder.structure;
        this.roof = houseBuilder.roof;
        this.garage = houseBuilder.garage;
        this.swimmingPool = houseBuilder.swimmingPool;
        this.garden = houseBuilder.garden;
    }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", structure='" + structure + '\'' +
                ", roof='" + roof + '\'' +
                ", garage=" + garage +
                ", swimmingPool=" + swimmingPool +
                ", garden=" + garden +
                '}';
    }

    static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;
        private boolean garage;
        private boolean swimmingPool;
        private boolean garden;

        public HouseBuilder(String foundation, String structure, String roof) {
            this.foundation = foundation;
            this.structure = structure;
            this.roof = roof;
        }

        public HouseBuilder setGarage(boolean garage) {
            this.garage = garage;
            return this;
        }

        public HouseBuilder setSwimmingPool(boolean swimmingPool) {
            this.swimmingPool = swimmingPool;
            return this;
        }

        public HouseBuilder setGarden(boolean garden) {
            this.garden = garden;
            return this;
        }

        public House build() {
            if (foundation == null || foundation.isEmpty()) {
                throw new IllegalStateException("Foundation is required.");
            }
            if (structure == null || structure.isEmpty()) {
                throw new IllegalStateException("Structure is required.");
            }
            if (roof == null || roof.isEmpty()) {
                throw new IllegalStateException("Roof is required.");
            }
            return new House(this);
        }
    }
}
