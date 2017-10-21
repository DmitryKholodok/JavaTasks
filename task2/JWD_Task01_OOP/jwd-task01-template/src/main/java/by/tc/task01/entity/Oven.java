package by.tc.task01.entity;

public class Oven extends Appliance{
	
    private double powerConpsumption;
    private double weight;
    private double capacity;
    private double depth;
    private double height;
    private double width;

    public double getPowerConpsumption() {
        return powerConpsumption;
    }
    public double getWeight() {
        return weight;
    }
    public double getCapacity() {
        return capacity;
    }
    public double getDepth() {
        return depth;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }

    public Oven(double powerConpsumption, double weight, double capacity, double depth, double height, double width) {
        this.powerConpsumption = powerConpsumption;
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Oven{" +
                "powerConpsumption=" + powerConpsumption +
                ", weight=" + weight +
                ", capacity=" + capacity +
                ", depth=" + depth +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
