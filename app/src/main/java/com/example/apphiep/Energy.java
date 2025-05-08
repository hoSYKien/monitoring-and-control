package com.example.apphiep;

public class Energy {
    private double voltage;
    private double currents;
    private double powers;
    private double energy;
    private double frequency;
    private String device;

    // Constructor
    public Energy(double voltage, double currents, double powers, double energy, double frequency, String device) {
        this.voltage = voltage;
        this.currents = currents;
        this.powers = powers;
        this.energy = energy;
        this.frequency = frequency;
        this.device = device;
    }

    // Getters and Setters
    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrents() {
        return currents;
    }

    public void setCurrents(double currents) {
        this.currents = currents;
    }

    public double getPowers() {
        return powers;
    }

    public void setPowers(double powers) {
        this.powers = powers;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    // Override toString() method for easier debugging and printing
    @Override
    public String toString() {
        return "EnergyMeasurement{" +
                "voltage=" + voltage +
                ", currents=" + currents +
                ", powers=" + powers +
                ", energy=" + energy +
                ", frequency=" + frequency +
                ", device='" + device + '\'' +
                '}';
    }
}
