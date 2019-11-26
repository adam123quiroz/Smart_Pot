package com.example.smartpot.entity;

public class PotSensorData {
    private double ambientTemperature;
    private double light;
    private double earthHumidity;
    private double ambientHumidity;

    public PotSensorData(double ambientTemperature, double light, double earthHumidity, double ambientHumidity) {
        this.ambientTemperature = ambientTemperature;
        this.light = light;
        this.earthHumidity = earthHumidity;
        this.ambientHumidity = ambientHumidity;
    }

    public PotSensorData() {
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getEarthHumidity() {
        return earthHumidity;
    }

    public void setEarthHumidity(double earthHumidity) {
        this.earthHumidity = earthHumidity;
    }

    public double getAmbientHumidity() {
        return ambientHumidity;
    }

    public void setAmbientHumidity(double ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }

    @Override
    public String toString() {
        return "PotSensorData{" +
                "ambientTemperature=" + ambientTemperature +
                ", light=" + light +
                ", earthHumidity=" + earthHumidity +
                ", ambientHumidity=" + ambientHumidity +
                '}';
    }
}
